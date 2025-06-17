package com.tecnologia_ms.infrastructure.adapters.repository;

import com.tecnologia_ms.infrastructure.adapters.entity.TechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ITechnologyRepository extends ReactiveCrudRepository<TechnologyEntity, Long> {

}
