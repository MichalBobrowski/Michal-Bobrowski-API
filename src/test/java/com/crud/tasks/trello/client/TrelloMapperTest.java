package com.crud.tasks.trello.client;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;



    @Test
    public void testMapToBoardDto(){
        //given
        TrelloBoard trelloBoard = new TrelloBoard("111", "Test", new ArrayList<>());
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);
        //when
        List<TrelloBoardDto> result =  trelloMapper.mapToBoardsDto(trelloBoardList);

        //then
        Assert.assertEquals(1,result.size());
        Assert.assertEquals("Test", result.get(0).getName());
    }

    @Test
    public void testMapToBoard(){
        //given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("111", "Test", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        //when
        List<TrelloBoard> result =  trelloMapper.mapToBoards(trelloBoardDtoList);

        //then
        Assert.assertEquals(1,result.size());
        Assert.assertEquals("Test", result.get(0).getName());
    }

    @Test
    public void testMapToListDto(){
        //given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList("111T", "Test1",false);
        trelloLists.add(trelloList);
        //when
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloLists);
        //then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("111T", result.get(0).getId());
    }

    @Test
    public void testMapToList(){
        //given
        List<TrelloListDto> trelloListsDtos = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("111T", "Test1",false);
        trelloListsDtos.add(trelloListDto);
        //when
        List<TrelloList> result = trelloMapper.mapToList(trelloListsDtos);
        //then
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("111T", result.get(0).getId());
    }

    @Test
    public void testMapToCardDto(){
        //given
        TrelloCard trelloCard = new TrelloCard("Test1", "TestDesc", "testPos", "111T");
        //when
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);

        //then
        Assert.assertEquals("Test1", result.getName());
    }

    @Test
    public void testMapToCard(){
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test1", "TestDesc", "testPos", "111T");
        //when
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto);

        //then
        Assert.assertEquals("Test1", result.getName());
    }
}
