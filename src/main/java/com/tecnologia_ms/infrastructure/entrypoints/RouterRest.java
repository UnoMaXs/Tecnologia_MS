package com.tecnologia_ms.infrastructure.entrypoints;

import com.tecnologia_ms.infrastructure.entrypoints.handler.TechnologyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(TechnologyHandler technologyHandler) {
        return route(POST("/technology"), technologyHandler::saveTechnology)
                .andRoute(GET("/technology/ids"), technologyHandler::getTechnologiesByIds)
                .andRoute(GET("/technology/summaries"), technologyHandler::getTechnologySummariesByIds)
                .andRoute(DELETE("/technology/delete"), technologyHandler::deleteTechnologiesByIds);
    }

}
