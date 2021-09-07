package com.example.myservice.service;

import com.example.myservice.model.Root;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest

class FindServiceTest {
    @Autowired
    FindService findService;
    @MockBean
    FindRate findRate;
    @MockBean
    FindGif findGif;

    @Test
    void finder() {
        Mockito.doReturn(new Root(0.005))
                .when(findRate)
                .findByRate(true);


    /*    Mockito.verify(findRate, Mockito.times(1)).findByRate(true);
        Mockito.verify(findRate, Mockito.times(1)).findByRate(false);
        Mockito.verify(findGif, Mockito.times(1)).findGif(0.005);
    */}
}