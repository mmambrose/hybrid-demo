package io.pivotal.ecosystem.ordermgmtservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mambrose on 3/17/18.
 */
@RestController
public class OrderMgmtWebRestController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderMgmtWebRestController.class);

    @RequestMapping("/receiveOrder")
    public CostModel receiveOrder(@RequestParam(value="productID", required = false, defaultValue="1") Integer productID){
        CostModel result = new CostModel(productID, productID);
        LOG.info("Cost Model = " + result.toString());
        return result;
    }
}
