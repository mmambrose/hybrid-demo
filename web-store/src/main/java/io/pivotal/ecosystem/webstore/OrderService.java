package io.pivotal.ecosystem.webstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Created by mambrose on 3/17/18.
 */

@Service
public class OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Value("${oms.url}")
    private String url;


    public OrderCostModel sendOrderData(Integer productID) {
        RestTemplate restTemplate = new RestTemplate();

        LOG.info("Starting send order Data process, URL is = " + url);
        //String userInput =

        OrderCostModel cost = restTemplate.postForObject(url,null, OrderCostModel.class);
        LOG.info("OrderService received cost Model" + cost.toString());
        return cost;
    }


    //SendOrderData

}
