package com.tecnologia_ms.domain.api;

import com.tecnologia_ms.domain.model.Technology;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ITechnologyServicePort {

    Mono<Technology> saveTechnology(Technology technology);
    Mono<List<Technology>> getTechnologiesByIds(List<Long> ids);
    Mono<Void> deleteTechnologiesByIds(List<Long> technologyIds);

}
