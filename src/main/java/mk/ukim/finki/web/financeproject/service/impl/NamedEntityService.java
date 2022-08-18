package mk.ukim.finki.web.financeproject.service.impl;

import com.querydsl.core.types.Predicate;
import mk.ukim.finki.web.financeproject.model.NamedEntities;
import mk.ukim.finki.web.financeproject.repository.NamedEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamedEntityService {

    private final NamedEntityRepository namedEntityRepository;

    public NamedEntityService(NamedEntityRepository namedEntityRepository) {
        this.namedEntityRepository = namedEntityRepository;
    }

    public List<String> getAllEntities() {
        return namedEntityRepository.getAllEntities();
    }

    public List<NamedEntities> getNamedEntities(Predicate predicate) {
        return namedEntityRepository.findAll(predicate);
    }
}
