package com.example.myservice.service;

import com.example.myservice.model.Root;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FindRateTest {

    @MockBean
    FindRate findRate;

    @Test
    void testFindByRate() {
        Mockito.doReturn(new Root()).when(findRate).findByRate(true);
    }
}