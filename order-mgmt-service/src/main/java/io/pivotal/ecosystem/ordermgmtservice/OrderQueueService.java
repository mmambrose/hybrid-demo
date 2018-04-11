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

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import static org.springframework.util.SerializationUtils.serialize;

/**
 * Created by cstewart on 3/9/18.
 */
@Service
public class OrderQueueService {

    private static OrderRepository orderRepository;

    @Autowired
    OrderRepository orderRepository0;

    @PostConstruct
    private void initOrderRepository(){
        orderRepository = orderRepository0;
    }

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

    //Handles message received from subscription
    static class MessageHandler implements IMessageHandler {

        MessageHandler() throws ServiceBusException, InterruptedException {
        }

        public CompletableFuture<Void> onMessageAsync(IMessage message) {

            LOG.info("Message received with ID = " + message.getMessageId());

            ObjectMapper mapper = new ObjectMapper();
            FulfillmentResult resultFromFC = new FulfillmentResult();
            final String messageString = new String(message.getBody(), StandardCharsets.UTF_8);
            try {
                resultFromFC = mapper.readValue(messageString, FulfillmentResult.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LOG.info("Message converted to Fulfillment Result is: " + resultFromFC.toString());

            if (resultFromFC.getFulfilled() == true){
                LOG.info("FC returned true for order" + resultFromFC.getOrderID());
                LOG.info("FC result for this order is " + resultFromFC.getFulfilled());
                OrderModel updated = orderRepository.findOne(resultFromFC.getOrderID());
                updated.setStatus("CONFIRMED");
                orderRepository.save(updated);
                LOG.info("Orders found with findAll(): in DB");
                LOG.info("-------------------------------");
                for (OrderModel orderLog : orderRepository.findAll()) {
                    LOG.info(orderLog.toString());
                }
                LOG.info("");
            }
            else {
                LOG.info("FC returned false for order" + resultFromFC.getOrderID());
                LOG.info("FC result for this order is " + resultFromFC.getFulfilled());
            }

            return CompletableFuture.completedFuture(null);
        }

        @Override
        public void notifyException(Throwable exception, ExceptionPhase phase) {
            LOG.info(phase + " encountered exception:" + exception.getMessage());
        }
    }


}

