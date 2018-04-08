package io.pivotal.ecosystem.ordermgmtservice;

import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
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

    //receive info for order creation
    @RequestMapping(value = "/receiveOrder", method = POST)
    public OrderResult receiveOrder(@RequestBody OrderModel order) throws ServiceBusException, InterruptedException {
        LOG.info("OMS received order " + order.toString());
        OrderResult orderResult = new OrderResult();

        try {
            order = orderRepository.save(order);
            LOG.info("OMS saved order to DB " + order.toString());
            String result = queueService.process(order);
            LOG.info("OMS sent order to topic");

            LOG.info("Orders found with findAll(): in DB");
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

    //get info for a specific order
    @RequestMapping(value = "/getOrder/{orderID}", method = GET)
    public OrderResult getOrderInfo(@PathVariable("orderID") Integer orderID){

        LOG.info("OMS received getOrder info request, finding order by orderID");
        OrderModel order = orderRepository.findOne(orderID);

        OrderResult orderResult = new OrderResult();
        orderResult.setOrderID(order.getOrderID());
        orderResult.setStatusCode(order.orderStatus);

        LOG.info("OMS returning order info for order "+orderID+": " + orderResult);
        return orderResult;
    }
}


