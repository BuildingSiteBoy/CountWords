package com.zz;

import com.zz.entity.FunWord;
import com.zz.mapper.FunWordMapper;
import com.zz.service.FunSentenceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class CountWordsApplicationTests {
    @Autowired
    FunWordMapper wordMapper;
    @Autowired
    FunSentenceService sentenceService;

    @Test
    void contextLoads() {
    }

    @Test
    void test03() {
        int count = sentenceService.count();
        System.out.println(sentenceService.count());
        int randId = new Random().nextInt(count) + 1;
        System.out.println("randId: " + randId);
    }

}
