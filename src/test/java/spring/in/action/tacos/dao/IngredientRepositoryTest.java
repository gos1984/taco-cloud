package spring.in.action.tacos.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import spring.in.action.tacos.dao.impl.IngredientRepositoryImpl;
import spring.in.action.tacos.model.Ingredient;
import spring.in.action.tacos.model.Type;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Sql({"classpath:data.sql", "classpath:schema.sql"})
public class IngredientRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private IngredientRepository ingredientRepository;

    @BeforeEach
    void setUp() {
        ingredientRepository = new IngredientRepositoryImpl(jdbcTemplate);
    }

    @Test
    void findAllTest() {
        List<Ingredient> ingredients = ingredientRepository.findAll();

        assertNotNull(ingredients);
        assertEquals(10, ingredients.size());
    }

    @Test
    void findByIdTest() {
        Optional<Ingredient> ingredient = ingredientRepository.findById("FLTO");

        assertTrue(ingredient.isPresent());
        assertEquals("Flour Tortilla", ingredient.get().getName());
    }

    @Test
    void saveTest() {
        final Ingredient ingredient = new Ingredient("TEST", "test ingredient", Type.WRAP);
        ingredientRepository.save(ingredient);

        List<Ingredient> ingredients = ingredientRepository.findAll();
        assertEquals(11, ingredients.size());

        Optional<Ingredient> newIngredient = ingredientRepository.findById("TEST");

        assertTrue(newIngredient.isPresent());
        assertEquals("test ingredient", newIngredient.get().getName());
    }
}
