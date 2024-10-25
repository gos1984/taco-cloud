package spring.in.action.tacos.dao;

import org.springframework.data.repository.CrudRepository;
import spring.in.action.tacos.model.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
