package io.pivotal.ecosystem.ordermgmtservice;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by mambrose on 3/17/18.
 */
@RestController
public class OrderMgmtWebRestController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderMgmtWebRestController.class);

    @Autowired
    private final OrderQueueService queueService;

    public OrderMgmtWebRestController(OrderQueueService queueService) {
        this.queueService = queueService;
    }

    @RequestMapping(value = "/receiveOrder", method = POST)
    public CostModel receiveOrder(@RequestBody OrderModel order) throws ServiceBusException, InterruptedException {
        LOG.info("OMS received order " + order.toString());

        //call OrderQueue Service to process request to queue
        String result = queueService.process(order);

        //dummy data response
        CostModel costData = new CostModel(10.50, 10.70);

        LOG.info("Cost Model = " + costData.toString());
        return costData;
    }
}
