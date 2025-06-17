package com.tecnologia_ms.infrastructure.adapters.persistence;

import com.tecnologia_ms.domain.model.Technology;
import com.tecnologia_ms.domain.spi.ITechnologyPersistencePort;
import com.tecnologia_ms.infrastructure.adapters.mapper.ITechnologyMapper;
import com.tecnologia_ms.infrastructure.adapters.repository.ITechnologyRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {

    private final ITechnologyRepository technologyRepository;
    private final ITechnologyMapper technologyMapper;

    @Override
    public Mono<Technology> saveTechnology(Technology technology) {
        return Mono.just(technology).
                map(technologyMapper::toTechnologyEntity).
                flatMap(technologyRepository::save).
                map(technologyMapper::toTechnology);
    }

    @Override
    public Mono<List<Technology>> getTechnologiesByIds(List<Long> ids) {
        return Mono.just(ids)
                .flatMapMany(technologyRepository::findAllById)
                .map(technologyMapper::toTechnology)
                .collectList();
    }

    @Override
    public Mono<Void> deleteTechnologiesByIds(List<Long> technologyIds) {
        return Flux.fromIterable(technologyIds)
                .flatMap(technologyRepository::deleteById)
                .then();
    }

}
