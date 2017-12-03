package com.crud.tasks.controller;


import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
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

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloCard")
    public void getCreatedTrelloCard() {
        List<CreatedTrelloCard> resultList = trelloClient.getTrelloCard();

        System.out.println("The numbers of card is: " + resultList.size());


        for (CreatedTrelloCard card : resultList) {
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

