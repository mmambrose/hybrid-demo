package io.pivotal.ecosystem.fulfillmentservice;

import com.microsoft.azure.servicebus.SubscriptionClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FulfillmentServiceApplication {

	//implement logging
	private static	final Logger LOG = LoggerFactory.getLogger(FulfillmentServiceController.class);

	public static void main(String[] args) {

		SpringApplication.run(FulfillmentServiceApplication.class, args);

	}

}
