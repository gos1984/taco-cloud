package spring.in.action.tacos.repository;

import spring.in.action.tacos.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
