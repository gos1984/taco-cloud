package spring.in.action.tacos.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import spring.in.action.tacos.dao.impl.OrderRepositoryImpl;
import spring.in.action.tacos.model.IngredientRef;
import spring.in.action.tacos.model.Taco;
import spring.in.action.tacos.model.TacoOrder;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JdbcTest
@Sql({"classpath:data.sql", "classpath:schema.sql"})
public class OrderRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepositoryImpl(jdbcTemplate);
    }

    @Test
    void saveTest() {
        TacoOrder tacoOrder = new TacoOrder()
                .setDeliveryName("deliveryNameTest")
                .setDeliveryStreet("deliveryStreetTest")
                .setDeliveryCity("deliveryCityTest")
                .setDeliveryState("ds")
                .setDeliveryZip("dzt")
                .setCcNumber("5127880999999990")
                .setCcExpiration("03/30")
                .setCcCVV("737")
                .setTacos(Collections.singletonList(
                        new Taco()
                                .setName("nameTest")
                                .setIngredients(
                                        Collections.singletonList(
                                                new IngredientRef("FLTO")
                                        )
                                )
                ));
        tacoOrder = orderRepository.save(tacoOrder);

        assertNotNull(tacoOrder.getId());
        assertNotNull(tacoOrder.getTacos().get(0).getId());
        assertEquals(1L, tacoOrder.getId());
        assertEquals(1L, tacoOrder.getTacos().get(0).getId());
    }
}
