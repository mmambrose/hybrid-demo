package io.pivotal.ecosystem.ordermgmtservice;

import com.microsoft.azure.servicebus.MessageHandlerOptions;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.SubscriptionClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by mambrose on 4/9/18.
 */

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private SubscriptionClient subscriptionClient = new SubscriptionClient(new ConnectionStringBuilder("Endpoint=sb://u223b2da3d21.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=fohJEnsreG6zJC8pqM6NtS9nC79WjfS+bEl6TPetz1Y=",
                "topic2" + "/subscriptions/" + "returnSubscription"), ReceiveMode.PEEKLOCK);

    //implement logging
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationStartup.class);

    public ApplicationStartup() throws ServiceBusException, InterruptedException {
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
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
        subscriptionClient.registerMessageHandler(new OrderQueueService.MessageHandler(), new MessageHandlerOptions());

    }
}
