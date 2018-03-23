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
        model.addAttribute("cost", new OrderCostModel());
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
        String result = orderService.sendOrderData(order);
        //LOG.info("WebStore received cost model " + cost.toString());

        String template = "confirmation";
        return template;
    }

}