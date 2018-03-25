package io.pivotal.ecosystem.ordermgmtservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import com.microsoft.azure.servicebus.*;

import java.nio.charset.StandardCharsets;

import static org.springframework.util.SerializationUtils.serialize;

/**
 * Created by cstewart on 3/9/18.
 */
@Service
public class OrderQueueService {

    //implement logging
    private static final Logger LOG = LoggerFactory.getLogger(OrderQueueService.class);

    // inject via application.properties
    // in future app may create if no topic exists
    @Autowired
    private TopicClient topicClient;

    public String process(OrderModel order) throws ServiceBusException, InterruptedException {

        StringBuffer result = new StringBuffer();

        LOG.info("process started...");

        try{
            LOG.info("Connecting to topic and sending message");
            sendTopicMessage(order);
        } catch (ServiceBusException e)
        {
            LOG.error("Error sending topic Message");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    private void sendTopicMessage(OrderModel order) throws ServiceBusException, InterruptedException, JsonProcessingException {

        //serialize order object into String for Azure Service Bus serialization
        ObjectMapper mapper = new ObjectMapper();
        String orderInString = mapper.writeValueAsString(order);

      //  Serialize message into bytes
        final Message message = new Message(orderInString.getBytes(StandardCharsets.UTF_8));
        message.setLabel("new order");
        message.setContentType("application/json");
        LOG.info("Message sent with ID = " + message.getMessageId());
        topicClient.send(message);
        LOG.info("Message sent...Client=" + topicClient.toString() + " Message=" + orderInString);
        // topicClient.close();
        // LOG.info("topic connection closed");
    }


}

