package io.pivotal.ecosystem.fulfillmentservice;

import java.util.Map;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import com.microsoft.azure.servicebus.MessageHandlerOptions;
import com.microsoft.azure.servicebus.SubscriptionClient;
import java.util.concurrent.TimeUnit;

import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import com.microsoft.azure.servicebus.ExceptionPhase;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;



@Controller
public class FulfillmentServiceController {

    //implement logging
    private static final Logger LOG = LoggerFactory.getLogger(FulfillmentServiceController.class);

    // inject via application.properties
    // need to figure out how to create this on demand or if we should?
    @Autowired
    private SubscriptionClient subscriptionClient;


    @RequestMapping("/subscribe")
    public String process(HttpServletResponse response) throws ServiceBusException, InterruptedException {

        LOG.info("Process beginning...");
        StringBuffer result = new StringBuffer();

        try{
            LOG.info("About to receive message...");
            receiveSubscriptionMessage();
        }catch (ServiceBusException e) {

        }

        return result.toString();
    }

    private void receiveSubscriptionMessage() throws ServiceBusException, InterruptedException {

        LOG.info("About to receive message...");
        subscriptionClient.registerMessageHandler(new MessageHandler(), new MessageHandlerOptions());

    }

    static class MessageHandler implements IMessageHandler {
        public CompletableFuture<Void> onMessageAsync(IMessage message) {
            final String messageString = new String(message.getBody(), StandardCharsets.UTF_8);
            LOG.info("Received message: " + messageString);
            return CompletableFuture.completedFuture(null);
        }

        public void notifyException(Throwable exception, ExceptionPhase phase) {
            LOG.info(phase + " encountered exception:" + exception.getMessage());
        }
    }

}