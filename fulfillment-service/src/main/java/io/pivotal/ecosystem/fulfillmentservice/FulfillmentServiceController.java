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
public class FulfillmentServiceController  {

    //implement logging
    private static final Logger LOG = LoggerFactory.getLogger(FulfillmentServiceController.class);

    public FulfillmentServiceController() throws ServiceBusException, InterruptedException {

    }

    //Handles message received from subscription
    static class MessageHandler implements IMessageHandler {


        MessageHandler() throws ServiceBusException, InterruptedException {
        }

        public CompletableFuture<Void> onMessageAsync(IMessage message) {

            final TopicClient topicClient;

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

            //create Message
            String returnString = "return";
            Message returnMessage = new Message(returnString.getBytes(StandardCharsets.UTF_8));

            //write a new message to the queue
            try {
                topicClient = new TopicClient(new ConnectionStringBuilder("Endpoint=sb://u223b2da3d21.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=fohJEnsreG6zJC8pqM6NtS9nC79WjfS+bEl6TPetz1Y=",
                        "topic2"));
                topicClient.send(returnMessage);
                LOG.info("sent return message" + returnMessage.getBody());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ServiceBusException e) {
                e.printStackTrace();
            }



            return CompletableFuture.completedFuture(null);
        }

        private Integer determineCost(FulfillmentOrderModel order) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (order.getDestinationZipCode() == "78751")
                return 1;
            else
                return 2;
        }

        public void notifyException(Throwable exception, ExceptionPhase phase) {
            LOG.info(phase + " encountered exception:" + exception.getMessage());
        }

    }


}