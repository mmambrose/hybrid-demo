package io.pivotal.ecosystem.fulfillmentservice;

import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

/**
 * Created by mambrose on 4/9/18.
 */
public class QueueService {

    TopicClient topicClient = new TopicClient(new ConnectionStringBuilder("Endpoint=sb://u223b2da3d21.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=fohJEnsreG6zJC8pqM6NtS9nC79WjfS+bEl6TPetz1Y=",
            "topic2"));

    public QueueService() throws ServiceBusException, InterruptedException {
    }

    public void process(FulfillmentOrderModel order) throws ServiceBusException, InterruptedException {

    }

}
