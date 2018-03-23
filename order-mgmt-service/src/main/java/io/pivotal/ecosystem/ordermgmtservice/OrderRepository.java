package io.pivotal.ecosystem.ordermgmtservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mambrose on 3/23/18.
 */
public interface OrderRepository extends CrudRepository<OrderModel, Integer> {

}
