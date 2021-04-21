package com.dummybot.got.webflux;

import com.dummybot.got.model.Root;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GameOfThronesClient {

    private static final String SERVER_URI = "https://raw.githubusercontent.com/jeffreylancaster/game-of-thrones/master/data/characters-groups.json";

    public Flux<Root> fetchGameOfThronesRoot(){
        return webClientRetriever().bodyToFlux(Root.class);
    }

    public Mono<Root> fetchGameOfThronesClan(){
        return webClientRetriever().bodyToMono(Root.class);
    }

    private WebClient.ResponseSpec webClientRetriever() {
        return WebClient.builder()
                .baseUrl(SERVER_URI)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(this::acceptedCodecs).build())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri(SERVER_URI)
                .retrieve();
    }

    private void acceptedCodecs(ClientCodecConfigurer clientCodecConfigurer) {
        clientCodecConfigurer.customCodecs().register(new Jackson2JsonEncoder(new ObjectMapper(), MimeTypeUtils.parseMimeType(MediaType.TEXT_PLAIN_VALUE)));
        clientCodecConfigurer.customCodecs().register(new Jackson2JsonDecoder(new ObjectMapper(), MimeTypeUtils.parseMimeType(MediaType.TEXT_PLAIN_VALUE)));
    }
}
