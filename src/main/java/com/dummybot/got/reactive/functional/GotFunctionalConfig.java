package com.dummybot.got.reactive.functional;

import com.dummybot.got.model.Clan;
import com.dummybot.got.repository.GotClanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class GotFunctionalConfig {

    @Autowired
    private GotClanRepository gotClanRepository;

    @Bean
    public RouterFunction<ServerResponse> getAllClans(){
        return route(GET("/functional/all-clans"), req -> ok().body(gotClanRepository.findAllClans(), Clan.class))
                .and(route(GET("/functional/{name}/clan"),
                        req -> ok().body(gotClanRepository.findClanByFamilyName(req.pathVariable("name")),Clan.class)));

    }

}
