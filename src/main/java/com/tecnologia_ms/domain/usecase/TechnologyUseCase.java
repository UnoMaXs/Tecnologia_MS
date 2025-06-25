package com.tecnologia_ms.domain.usecase;

import com.tecnologia_ms.domain.api.ITechnologyServicePort;
import com.tecnologia_ms.domain.model.Technology;
import com.tecnologia_ms.domain.spi.ITechnologyPersistencePort;
import reactor.core.publisher.Mono;

import java.util.List;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {
        this.technologyPersistencePort = technologyPersistencePort;
    }

    @Override
    public Mono<Technology> saveTechnology(Technology technology) {
        return technologyPersistencePort.saveTechnology(technology);
    }

    @Override
    public Mono<List<Technology>> getTechnologiesByIds(List<Long> ids) {
        return technologyPersistencePort.getTechnologiesByIds(ids);
    }

    @Override
    public Mono<Void> deleteTechnologiesByIds(List<Long> technologyIds) {
        return technologyPersistencePort.deleteTechnologiesByIds(technologyIds);
    }


}
