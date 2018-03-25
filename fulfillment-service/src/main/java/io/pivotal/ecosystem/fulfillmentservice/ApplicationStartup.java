package io.pivotal.ecosystem.fulfillmentservice;

import com.microsoft.azure.servicebus.MessageHandlerOptions;
import com.microsoft.azure.servicebus.SubscriptionClient;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by mambrose on 3/25/18.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{

    @Autowired
    private SubscriptionClient subscriptionClient;

    //implement logging
    private static	final Logger LOG = LoggerFactory.getLogger(FulfillmentServiceController.class);

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event){
        try{
            LOG.info("About to allow subscription receipt...");
            receiveSubscriptionMessage();
        }catch (ServiceBusException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void receiveSubscriptionMessage() throws ServiceBusException, InterruptedException {

        LOG.info("About to receive message...");
        subscriptionClient.registerMessageHandler(new FulfillmentServiceController.MessageHandler(), new MessageHandlerOptions());

    }
}
