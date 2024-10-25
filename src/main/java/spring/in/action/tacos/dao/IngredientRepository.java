package spring.in.action.tacos.dao;

import org.springframework.data.repository.CrudRepository;
import spring.in.action.tacos.model.Ingredient;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    List<Ingredient> findAll();
}
