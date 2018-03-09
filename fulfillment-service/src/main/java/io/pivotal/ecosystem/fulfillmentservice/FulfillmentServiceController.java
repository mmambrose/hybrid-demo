package io.pivotal.ecosystem.fulfillmentservice;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;


//@RestController
//public class FulfillmentServiceController {
//    @RequestMapping("/")
//    public String index() {
//        //return "Greetings from Hybrid Cloud Land!";
//        String template = "index";
//        //LOG.debug("returning template " + template);
//        return template;
//    }
//}

@Controller
public class FulfillmentServiceController {

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "index";
    }

}