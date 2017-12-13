package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void fetchTrelloBoardsTest(){
        //given
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(new TrelloBoardDto("1", "Test", new ArrayList<TrelloListDto>()));
        when(trelloClient.getTrelloBoards()).thenReturn(boardDtoList);

        //when
        List<TrelloBoardDto> result =  trelloService.fetchTrelloBoards();

        //then
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void createTrelloCardTest(){
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("1", "test","testpos","1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","Test","aa",null);

        when(trelloClient.createNewCard(any())).thenReturn(createdTrelloCardDto);

        //when
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //then
        Assert.assertEquals("Test", result.getName());
    }
}
