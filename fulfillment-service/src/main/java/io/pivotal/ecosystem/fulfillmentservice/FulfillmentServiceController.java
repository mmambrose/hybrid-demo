package io.pivotal.ecosystem.fulfillmentservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;


@Controller
public class FulfillmentServiceController {

    //implement logging
    private static final Logger LOG = LoggerFactory.getLogger(FulfillmentServiceController.class);


    public FulfillmentServiceController() throws ServiceBusException, InterruptedException {
    }

    //Handles message received from subscription
    static class MessageHandler implements IMessageHandler {

        public CompletableFuture<Void> onMessageAsync(IMessage message) {
            LOG.info("Message received with ID = " + message.getMessageId());

            //This takes the message and converts to an Order object
            ObjectMapper mapper = new ObjectMapper();
            FulfillmentOrderModel order = new FulfillmentOrderModel();
            final String messageString = new String(message.getBody(), StandardCharsets.UTF_8);
            try {
               order = mapper.readValue(messageString, FulfillmentOrderModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LOG.info("Message converted to order is: " + order.toString());

            //do something with order
            Integer cost = determineCost(order);

            //write a new message to the queue


            return CompletableFuture.completedFuture(null);
        }

        private Integer determineCost(FulfillmentOrderModel order) {
            if (order.getDestinationZipCode() == "78751")
                return 2;
            else
                return 1;
        }

        public void notifyException(Throwable exception, ExceptionPhase phase) {
            LOG.info(phase + " encountered exception:" + exception.getMessage());
        }
    }

}