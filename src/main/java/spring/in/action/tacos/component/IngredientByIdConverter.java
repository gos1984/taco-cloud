package spring.in.action.tacos.component;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.in.action.tacos.model.Ingredient;
import spring.in.action.tacos.repository.IngredientRepository;

@Component
@AllArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id)
                .orElse(null);
    }
}
