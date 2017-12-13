package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrelloService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    private static final String SUBJECT = "Task: New Trello Card";

    public List<TrelloBoardDto> fetchTrelloBoards(){
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard (final TrelloCardDto trelloCardDto){
        CreatedTrelloCardDto createdTrelloCard = trelloClient.createNewCard(trelloCardDto);
        //fNullable(createdTrelloCard).ifPresent(card -> emailService.send(new Mail(adminConfig.getAdminMail(),
        //                           SUBJECT, "New card: " + trelloCardDto.getName() + " has been created", "")));
        return createdTrelloCard;
    }


}
