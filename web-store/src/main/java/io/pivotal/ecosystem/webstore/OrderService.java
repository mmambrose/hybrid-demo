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

    //send order information to OMS
    @Value("${oms.send-order.url}")
    private String sendOrderURL;


    public OrderResult sendOrderData(OrderModel order) {
        RestTemplate restTemplate = new RestTemplate();

        LOG.info("Starting send order Data process, URL is = " + sendOrderURL);

        //create POST request body from order data model
        HttpEntity<OrderModel> request = new HttpEntity<>(order);
        LOG.info("Request body created " + request.toString());

        //Send POST request to OMS and get cost model back
        OrderResult orderResult = restTemplate.postForObject(sendOrderURL, request, OrderResult.class);

        LOG.info("OrderService received response " + orderResult.toString());
        return orderResult;
    }

    //get order information from OMS
    @Value("${oms.get-order.url}")
    private String getOrderURL;

    public OrderResult getOrderData(String orderID) {
        //receive info request from Web Store for order with id orderID
        LOG.info("getOrder info request received by OrderService for order ID "+orderID);
        RestTemplate restTemplate = new RestTemplate();

        //append orderID to URL, terrible naming convention
        String fullURL = getOrderURL+"/"+orderID+"/";

        //send info request for order with orderID to OMS
        OrderResult orderResult;
        orderResult = restTemplate.getForObject(fullURL, OrderResult.class);
        return orderResult;
    }

}

