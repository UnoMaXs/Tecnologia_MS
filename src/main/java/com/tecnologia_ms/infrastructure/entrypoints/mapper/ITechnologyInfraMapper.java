package com.tecnologia_ms.infrastructure.entrypoints.mapper;

import com.tecnologia_ms.domain.model.Technology;
import com.tecnologia_ms.infrastructure.entrypoints.dto.TechnologyDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITechnologyInfraMapper {

    Technology toTechnology(TechnologyDTO technologyDTO);
}
