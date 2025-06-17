package com.tecnologia_ms.infrastructure.entrypoints.handler;

import com.tecnologia_ms.domain.api.ITechnologyServicePort;
import com.tecnologia_ms.domain.model.Technology;
import com.tecnologia_ms.infrastructure.entrypoints.dto.TechnologyDTO;
import com.tecnologia_ms.infrastructure.entrypoints.dto.TechnologySummaryDTO;
import com.tecnologia_ms.infrastructure.entrypoints.mapper.ITechnologyInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TechnologyHandler {

    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyInfraMapper technologyInfraMapper;

    public Mono<ServerResponse> saveTechnology(ServerRequest request) {
        return request.bodyToMono(TechnologyDTO.class)
                .flatMap(dto -> {
                    Technology technology = technologyInfraMapper.toTechnology(dto);
                    return technologyServicePort.saveTechnology(technology);
                })
                .flatMap(savedTechnology ->
                        ServerResponse.ok()
                                .bodyValue(savedTechnology)
                );
    }

    public Mono<ServerResponse> getTechnologiesByIds(ServerRequest request) {
        List<Long> ids = Arrays.stream(request.queryParam("ids")
                        .orElse("")
                        .split(","))
                .filter(s -> !s.isBlank())
                .map(Long::valueOf)
                .toList();

        return technologyServicePort.getTechnologiesByIds(ids)
                .map(list -> {
                    List<Long> foundIds = list.stream().map(Technology::getId).toList();
                    List<Long> missing = ids.stream()
                            .filter(id -> !foundIds.contains(id))
                            .toList();
                    return Map.of(
                            "valid", missing.isEmpty(),
                            "missingIds", missing
                    );
                })
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

    public Mono<ServerResponse> getTechnologySummariesByIds(ServerRequest request) {
        List<Long> ids = Arrays.stream(request.queryParam("ids")
                        .orElse("")
                        .split(","))
                .filter(s -> !s.isBlank())
                .map(Long::valueOf)
                .toList();

        return technologyServicePort.getTechnologiesByIds(ids)
                .flatMapMany(Flux::fromIterable)
                .map(tech -> new TechnologySummaryDTO(tech.getId(), tech.getName()))
                .collectList()
                .flatMap(list -> ServerResponse.ok().bodyValue(list));
    }

    public Mono<ServerResponse> deleteTechnologiesByIds(ServerRequest request) {
        List<Long> ids = Arrays.stream(request.queryParam("ids")
                        .orElse("")
                        .split(","))
                .filter(id -> !id.isBlank())
                .map(Long::valueOf)
                .toList();

        return technologyServicePort.deleteTechnologiesByIds(ids)
                .then(ServerResponse.noContent().build());
    }

}
