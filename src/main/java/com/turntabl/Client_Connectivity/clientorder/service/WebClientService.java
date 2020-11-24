package com.turntabl.Client_Connectivity.clientorder.service;


import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientService {

    private WebClient webClient;

    public WebClientService(String url) {
        this.webClient = WebClient.create(url);
    }

    Mono<ClientOrder> sendClientOrder(ClientOrder clientOrder, String Uri){
        return webClient.post()
                .uri(Uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(clientOrder))
                .retrieve()
                .bodyToMono(ClientOrder.class);
    }
}
