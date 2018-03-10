package io.pivotal.ecosystem.ordermgmtservice;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.microsoft.azure.servicebus.*;
import javax.servlet.http.HttpServletResponse;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by cstewart on 3/9/18.
 */
@Controller
public class OrderQueueRestController {

    //implement logging
    private static final Logger LOG = LoggerFactory.getLogger(OrderQueueRestController.class);

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    // inject via application.properties
    // in future app may create if no topic exists
    @Autowired
    private TopicClient topicClient;

    @RequestMapping("/topic")
    public String process(HttpServletResponse response) throws ServiceBusException, InterruptedException {

        StringBuffer result = new StringBuffer();

        LOG.info("process started...");

        try{
            LOG.info("Connecting to topic and sending message");
            sendTopicMessage();
        } catch (ServiceBusException e)
        {
            LOG.error("Error sending topic Message");
        }

        return result.toString();
    }

    private void sendTopicMessage() throws ServiceBusException, InterruptedException {
        final String messageBody = "topic message";
        LOG.info("Sending message: " + messageBody);
        final Message message = new Message(messageBody.getBytes(StandardCharsets.UTF_8));
        topicClient.send(message);
        LOG.info("Message sent...");
        // topicClient.close();
        // LOG.info("topic connection closed");
    }


}

