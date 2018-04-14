package io.pivotal.ecosystem.fulfillmentservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.filter.OrderedRequestContextFilter;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;


@Controller
public class FulfillmentServiceController  {

    public static String location;

    @Value("${location}")
    public void setLocation(String loc) {
        location = loc;
    }


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

            FulfillmentResult result = new FulfillmentResult(order.getOrderID());


            //Create result of order (order ID and isFulfilled)
            result = determineCost(order, result);

            //sendMessage
            try {
                topicClient = new TopicClient(new ConnectionStringBuilder("Endpoint=sb://u223b2da3d21.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=fohJEnsreG6zJC8pqM6NtS9nC79WjfS+bEl6TPetz1Y=",
                        "topic2"));
                sendTopicMessage(topicClient, result);
                LOG.info("sent return message" + result.toString());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ServiceBusException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return CompletableFuture.completedFuture(null);
        }

        private void sendTopicMessage(TopicClient topicClient, FulfillmentResult result) throws JsonProcessingException, ServiceBusException, InterruptedException {
            try {
                Thread.sleep(5000);
                LOG.info("sleeping");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //create Message
            ObjectMapper mapper = new ObjectMapper();
            String resultInString = mapper.writeValueAsString(result);
            final Message message = new Message(resultInString.getBytes(StandardCharsets.UTF_8));
            message.setLabel("fulfillment result");
            message.setContentType("application/json");
            LOG.info("Message sent with ID = " + message.getMessageId());

            //send message
            topicClient.send(message);
            LOG.info("Message sent...Client=" + topicClient.toString() + " Message=" + resultInString);


        }

        private FulfillmentResult determineCost(FulfillmentOrderModel order, FulfillmentResult result) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (order.getDestinationZipCode().equals(location)) {
                LOG.info("Determining Cost... Location of this FC is " + location);
                LOG.info("Destination of this order is " + order.getDestinationZipCode());
                result.setFulfilled(true);
                result.setFC(location);
                LOG.info("Fulfilled is true");
                return result;
            }
            else {
                LOG.info("Determining Cost... Location of this FC is " + location);
                LOG.info("Destination of this order is " + order.getDestinationZipCode());
                result.setFulfilled(false);
                result.setFC(location);
                LOG.info("Fulfilled is false");
                return result;
            }
        }

        public void notifyException(Throwable exception, ExceptionPhase phase) {
            LOG.info(phase + " encountered exception:" + exception.getMessage());
        }

    }


}