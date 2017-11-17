package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
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

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
        return restTemplate.postForObject(createUrlAdressPost(trelloCardDto), null, CreatedTrelloCard.class);
    }

    public List<CreatedTrelloCard> getTrelloCard(){
        Optional<CreatedTrelloCard[]> cardsResponse = Optional.of(restTemplate.getForObject(createUrlAdressGetCards(),CreatedTrelloCard[].class));

        if (cardsResponse.isPresent()){
            return  Arrays.asList(cardsResponse.get());
        }
        return new ArrayList<>();
    }

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.username}")
    private String trelloUsername;

    private URI createUrlAdress(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername+ "/boards" )
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();
        return url;
    }

    private URI createUrlAdressPost(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build().encode().toUri();
        return url;
    }

    private URI createUrlAdressGetCards(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint +"/lists/59dd26c6c26c99de7f064d51/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
               // .queryParam("fields", "id, name, shortLink")
                //.queryParam("badges", "all")
                .build().encode().toUri();
        return url;
    }
}