package io.pivotal.ecosystem.webstore;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public String showForm(Model model)
    {
        model.addAttribute("order", new OrderModel());
        //model.addAttribute("cost", new OrderResult());
        String template = "index";
        LOG.info("returning template " + template);
        return template;
    }

    @RequestMapping(value="/process", method= RequestMethod.POST)
    public String submitForm(@ModelAttribute("order") OrderModel order)
    {
        LOG.info("process started...");
        LOG.info("model = " + order.toString());

        //POST required info about order to OMS
        //get back info from OMS about the shipping cost from 2 FCs (dummy data today)
        OrderResult orderResult = orderService.sendOrderData(order);
        LOG.info("WebStore received response from OMS " + orderResult.toString());
        LOG.info("Order ID = " + orderResult.orderID);

        //set orderID on OrderModel so that view can access it
        order.setOrderID((orderResult.orderID));

        String template = "confirmation";
        return template;
    }

}

