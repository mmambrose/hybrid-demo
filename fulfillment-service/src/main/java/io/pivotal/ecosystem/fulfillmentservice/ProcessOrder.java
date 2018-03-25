package io.pivotal.ecosystem.fulfillmentservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import static org.springframework.util.SerializationUtils.deserialize;


@Controller
public class FulfillmentServiceController {

    //implement logging
    private static final Logger LOG = LoggerFactory.getLogger(FulfillmentServiceController.class);

    // inject via application.properties
    // need to figure out how to create this on demand or if we should?
//    @Autowired
//    private SubscriptionClient subscriptionClient;


//    @RequestMapping("/subscribe")
//    public String process(HttpServletResponse response) throws ServiceBusException, InterruptedException {
//
//        LOG.info("Process beginning...");
//        StringBuffer result = new StringBuffer();
//
//        try{
//            LOG.info("About to receive message...");
//            receiveSubscriptionMessage();
//        }catch (ServiceBusException e) {
//
//        }
//
//        return result.toString();
//    }

//    private void receiveSubscriptionMessage() throws ServiceBusException, InterruptedException {
//
//        LOG.info("About to receive message...");
//        subscriptionClient.registerMessageHandler(new MessageHandler(), new MessageHandlerOptions());
//
//    }

    static class MessageHandler implements IMessageHandler {
        public CompletableFuture<Void> onMessageAsync(IMessage message) {

            ObjectMapper mapper = new ObjectMapper();
            FulfillmentOrderModel order = new FulfillmentOrderModel();
            final String messageString = new String(message.getBody(), StandardCharsets.UTF_8);
            LOG.info("Message in String is: " + messageString);

            try {
               order = mapper.readValue(messageString, FulfillmentOrderModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            LOG.info("Message converted to order is: " + order.toString());
            LOG.info("Message received with ID = " + message.getMessageId());
            return CompletableFuture.completedFuture(null);
        }

        public void notifyException(Throwable exception, ExceptionPhase phase) {
            LOG.info(phase + " encountered exception:" + exception.getMessage());
        }
    }

}