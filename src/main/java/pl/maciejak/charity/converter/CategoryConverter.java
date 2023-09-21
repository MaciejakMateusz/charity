package pl.maciejak.charity.converter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.maciejak.charity.entity.Category;
import pl.maciejak.charity.repository.CategoryRepository;

@Component
@Getter
@Slf4j
public class CategoryConverter implements Converter<String, Category> {

    private final CategoryRepository categoryRepository;

    public CategoryConverter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category convert(@Nullable String source) {
        int id = -1;
        try {
            assert source != null;
            id = Integer.parseInt(source);
        } catch (NumberFormatException e) {
            log.error(String.valueOf(e));
        }
        return categoryRepository.findById(id).orElseThrow();
    }
}
