package com.tecnologia_ms.infrastructure.adapters.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "technologies")
public class TechnologyEntity {

    @Id
    private Long id;
    private String name;
    private String description;

}
