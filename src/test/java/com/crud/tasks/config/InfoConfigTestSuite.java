package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.actuate.info.Info;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InfoConfigTestSuite {

    @InjectMocks
    private InfoConfig infoConfig;

    @Mock
    private Info.Builder builder;

    @Test
    public void contributeTest(){
        //given
        Map<String, Object> map = new HashMap<>();
        map.put("test1", "test2");

        when(builder.withDetail(anyString(), any())).thenReturn(builder);

        //when
        infoConfig.contribute(builder);
    }
}
