package pl.maciejak.charity.converter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.maciejak.charity.entity.Institution;
import pl.maciejak.charity.repository.InstitutionRepository;

@Component
@Getter
@Slf4j
public class InstitutionConverter implements Converter<String, Institution> {

    private final InstitutionRepository institutionRepository;

    public InstitutionConverter(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Institution convert(@Nullable String source) {
        int id = -1;
        try {
            assert source != null;
            id = Integer.parseInt(source);
        } catch (NumberFormatException e) {
            log.error(String.valueOf(e));
        }
        return institutionRepository.findById(id).orElseThrow();
    }
}
