package io.pivotal.ecosystem.webstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
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


    public OrderCostModel sendOrderData(OrderModel order) {
        RestTemplate restTemplate = new RestTemplate();

        LOG.info("Starting send order Data process, URL is = " + url);

        //create POST request body from order data model
        HttpEntity<OrderModel> request = new HttpEntity<>(order);
        LOG.info("Request body created " + request.toString());

        //Send POST request to Order MGMT and get cost model back
        OrderCostModel cost = restTemplate.postForObject(url, request, OrderCostModel.class);

        LOG.info("OrderService received cost Model" + cost.toString());
        return cost;
    }

}
