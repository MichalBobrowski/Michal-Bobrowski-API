package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
public class TrelloClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrelloConfig trelloConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    public List<TrelloBoardDto> getTrelloBoards() {

        try{
            TrelloBoardDto[] boardResponse = restTemplate.getForObject(createUrlAdress(), TrelloBoardDto[].class);
            return Arrays.asList(ofNullable(boardResponse).orElse(new TrelloBoardDto[0]));
        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
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



    private URI createUrlAdress(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/antaresloren/boards" )
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();
        return url;
    }

    private URI createUrlAdressPost(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl("https://api.trello.com/1/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build().encode().toUri();
        return url;
    }

    private URI createUrlAdressGetCards(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/lists/59dd26c6c26c99de7f064d51/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
               // .queryParam("fields", "id, name, shortLink")
                //.queryParam("badges", "all")
                .build().encode().toUri();
        return url;
    }
}