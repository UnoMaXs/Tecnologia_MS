package com.tecnologia_ms.infrastructure.adapters.mapper;

import com.tecnologia_ms.domain.model.Technology;
import com.tecnologia_ms.infrastructure.adapters.entity.TechnologyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITechnologyMapper {

    Technology toTechnology(TechnologyEntity technologyEntity);
    TechnologyEntity toTechnologyEntity(Technology technology);

}
