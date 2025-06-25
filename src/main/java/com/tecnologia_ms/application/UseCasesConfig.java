package com.tecnologia_ms.application;

import com.tecnologia_ms.domain.api.ITechnologyServicePort;
import com.tecnologia_ms.domain.spi.ITechnologyPersistencePort;
import com.tecnologia_ms.domain.usecase.TechnologyUseCase;
import com.tecnologia_ms.infrastructure.adapters.mapper.ITechnologyMapper;
import com.tecnologia_ms.infrastructure.adapters.persistence.TechnologyAdapter;
import com.tecnologia_ms.infrastructure.adapters.repository.ITechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCasesConfig {

    private final ITechnologyRepository technologyRepository;
    private final ITechnologyMapper technologyMapper;

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyAdapter(technologyRepository, technologyMapper);
    }
    @Bean
    public ITechnologyServicePort technologyServicePort(ITechnologyPersistencePort technologyPersistencePort) {
        return new TechnologyUseCase(technologyPersistencePort);
    }
}
