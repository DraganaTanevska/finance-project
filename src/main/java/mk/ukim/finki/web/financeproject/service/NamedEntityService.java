package mk.ukim.finki.web.financeproject.service;

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
}
