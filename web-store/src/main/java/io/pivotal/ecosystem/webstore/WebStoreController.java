package io.pivotal.ecosystem.webstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by mambrose on 3/13/18.
 */
@Controller
public class WebStoreController {

    private static final Logger LOG = LoggerFactory.getLogger(WebStoreController.class);

    @Autowired
    private final OrderService orderService;

    public WebStoreController(OrderService orderService) {
        this.orderService = orderService;
    }

    //root URL
    @GetMapping("/")
    public String showForm(Model model)
    {
        model.addAttribute("order", new OrderModel());
       // model.addAttribute("products", new ProductModel());

        String template = "index";
        LOG.info("returning template " + template);
        return template;
    }

    @ModelAttribute("productIDs")
    public Integer[] getProductIDs() {
        return new Integer[] {1,2,3};
    }

    @ModelAttribute("productImageURLs")
    public String[] getProductImageURLs(){
        return new String[] {"/images/blue-teapot.jpg", "/images/pink-teapot.jpeg", "/images/red-teapot.jpg"};
    }

    @ModelAttribute("productQuantities")
    public Integer[] getProductQuantities() {
        return new Integer[] {1,2,3,4,5,6,7,8,9,10};
    }

    //process form: receive order creation info
    @RequestMapping(value="/process", method= RequestMethod.POST)
    public String submitForm(@ModelAttribute("order") OrderModel order)
    {
        LOG.info("process started...");
        LOG.info("model = " + order.toString());

        //POST required info about order to OMS
        //get back info from OMS about the shipping cost from 2 FCs (dummy data today)
        OrderResult orderResult = orderService.sendOrderData(order);
        LOG.info("WebStore received response from OMS " + orderResult.toString());


        //set orderID on OrderModel so that view can access it
        order.setOrderID((orderResult.orderID));

        String template = "confirmation";
        return template;
    }

    //order status: get info about an order
    @RequestMapping(value="/order/{orderID}/status", method= RequestMethod.GET)
    public String getOrderStatus(@ModelAttribute("order") OrderModel order, @PathVariable("orderID") String orderID)
    {
        //logging orderID from URL string
        LOG.info("URI parameter OrderID is:" + orderID);

        //request info from OMS for order with id orderID
        LOG.info("sending order ID to OMS getOrder");
        OrderResult orderResult = orderService.getOrderData(orderID);

        //receive info back from OMS about specified order
        LOG.info("Order info for OrderID "+orderID+" received from OMS: " + orderResult.toString());

        //set orderID and status on OrderModel so that view can access it
        order.setOrderID((orderResult.orderID));
        order.setOrderStatus(orderResult.orderStatus);
        order.setFulfilledBy(orderResult.fulfilledBy);

        //return template
        String template = "status";
        return template;
    }

    @GetMapping("/ouch")
    public void killApp()
    {
        System.exit(0);
    }
    }

