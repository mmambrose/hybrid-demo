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

    @Autowired
    OrderRepository orderRepository;

    public OrderMgmtWebRestController(OrderQueueService queueService) {
        this.queueService = queueService;
    }

    @RequestMapping(value = "/receiveOrder", method = POST)
    public OrderResult receiveOrder(@RequestBody OrderModel order) throws ServiceBusException, InterruptedException {
        LOG.info("OMS received order " + order.toString());
        OrderResult orderResult = new OrderResult();

        try {
            order = orderRepository.save(order);
            LOG.info("OMS saved order to DB " + order.toString());
            String result = queueService.process(order);
            LOG.info("OMS sent order to topic");

            LOG.info("Customers found with findAll(): in DB");
            LOG.info("-------------------------------");
            for (OrderModel orderLog : orderRepository.findAll()) {
                LOG.info(orderLog.toString());
            }
            LOG.info("");

            //Return status OK and orderID;
            orderResult.setStatusCode("200 OK");
            orderResult.setOrderID(order.getOrderID());

        }
        catch (Exception e){
            orderResult.setStatusCode(e.toString());
            orderResult.setOrderID(null);
        }

        LOG.info("OMS returning order result " + orderResult.toString());
        return orderResult;
    }
}
