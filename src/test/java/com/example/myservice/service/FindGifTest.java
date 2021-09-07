package com.example.myservice.service;

import com.example.myservice.model.MyGif;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class FindGifTest {

    @MockBean
    FindGif findGif;

    @Test
    void findRandomGifTest() {

        Mockito.doReturn(new MyGif("https://media.giphy.com/media/8L0Pky6C83SzkzU55a/source.gif?cid=ecf05e47bnkdsv1p83f0sgfd1mevdlf595o8wrym76rpbstc&rid=source.gif&ct=g"))
                .when(findGif)
                .findRandomGif(null);
    }
}