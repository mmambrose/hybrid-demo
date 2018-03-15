package io.pivotal.ecosystem.webstore;

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

    @GetMapping("/")
    public String showForm(Model model)
    {
        model.addAttribute("order", new OrderModel());
        String template = "index";
        LOG.debug("returning template " + template);
        return template;
    }

    @RequestMapping(value="/process", method= RequestMethod.POST)
    public String submitForm(@ModelAttribute("order") OrderModel model)
    {
        LOG.info("model = " + model.getShippingAddress());
        String template = "confirmation";
        return template;
    }

}