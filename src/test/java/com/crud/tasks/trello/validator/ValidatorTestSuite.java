package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTestSuite {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void validateNormalCardTest(){
        //given
        TrelloCard trelloCard = new TrelloCard("AAA","DESC","TOP","1");
        //when
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    public void validateTestCardTest(){
        //given
        TrelloCard trelloCard = new TrelloCard("Test","DESC","TOP","1");
        //when
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    public void validateTrelloBoardsTest(){
        //given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "Test",null));
        trelloBoards.add(new TrelloBoard("2", "AAA",null));

        //when
        List<TrelloBoard> result = trelloValidator.validateTrelloBoards(trelloBoards);
        //then
        Assert.assertEquals(1,result.size());
    }

}
