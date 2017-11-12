package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {
        Optional<TrelloBoardDto[]> boardsResponse = Optional.of(restTemplate.getForObject( createUrlAdress(), TrelloBoardDto[].class));

        if (boardsResponse.isPresent()){
          return  Arrays.asList(boardsResponse.get());
        }
        return new ArrayList<>();
    }


    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpointProd;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.username}")
    private String trelloUsername;

    private URI createUrlAdress(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpointProd + "/members/" + trelloUsername+ "/boards" )
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .build().encode().toUri();
        return url;
    }
}