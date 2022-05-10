package mk.ukim.finki.web.financeproject.service;

import mk.ukim.finki.web.financeproject.model.Source;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SourceService {
    Optional<Source> findAll();
}
