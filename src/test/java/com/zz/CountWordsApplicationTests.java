package com.zz;

import com.zz.entity.FunWord;
import com.zz.mapper.FunWordMapper;
import com.zz.service.FunSentenceService;
import com.zz.service.FunWordService;
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
    @Autowired
    FunWordService funWordService;

    @Test
    void contextLoads() {
        System.out.println(funWordService.findInfoByGrade(1));

        //System.out.println(funWordService.restartByGrade(1));

        FunWord word = funWordService.findWordByOrder(1);
        //FunWord word = funWordService.findWordDisorder(1);
        System.out.println(word.getWord());

        funWordService.studyWord(funWordService.getById(word.getId()));

        funWordService.collectionWord(funWordService.getById(word.getId()));

        funWordService.rememberWord(funWordService.getById(word.getId()));

        System.out.println(funWordService.listCollectedWords());

        System.out.println(funWordService.findInfoByGrade(1));


    }

    @Test
    void test03() {
        int count = sentenceService.count();
        System.out.println(sentenceService.count());
        int randId = new Random().nextInt(count) + 1;
        System.out.println("randId: " + randId);
    }

}
