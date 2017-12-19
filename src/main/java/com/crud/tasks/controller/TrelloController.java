package com.crud.tasks.controller;


import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.TrelloFacade;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "boards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "cards")
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloCard")
    public void getCreatedTrelloCard() {
        List<CreatedTrelloCardDto> resultList = trelloClient.getTrelloCard();

        System.out.println("The numbers of card is: " + resultList.size());


        for (CreatedTrelloCardDto card : resultList) {
            System.out.println("Card name: " + card.getName());
            System.out.println("Card id: " + card.getId());
            System.out.println("Short url: " + card.getShortUrl());
            System.out.println("Badges votes: " + card.getBadges().getVotes());
            System.out.println("Badges board: " + card.getBadges().getAttachmentsByType().getTrello().getBoard());
            System.out.println("Badges card: " + card.getBadges().getAttachmentsByType().getTrello().getCard());
            System.out.println();
        }
    }
}

