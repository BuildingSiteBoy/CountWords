package com.zz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.common.myEntity.BookPage;
import com.zz.common.myEntity.SentencePage;
import com.zz.entity.FunBook;
import com.zz.entity.FunSentence;
import com.zz.mapper.FunSentenceMapper;
import com.zz.service.FunSentenceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Service
public class FunSentenceServiceImpl extends ServiceImpl<FunSentenceMapper, FunSentence> implements FunSentenceService {

    @Autowired
    FunSentenceMapper sentenceMapper;

    @Override
    public SentencePage listSentencesByPage(int size, int page) {
        IPage<FunSentence> iPage = page(new Page<>(page, size));

        SentencePage sentencePage = new SentencePage();
        sentencePage.setPages((int)iPage.getPages());
        sentencePage.setTotal((int)iPage.getTotal());
        sentencePage.setSentences(iPage.getRecords());

        return sentencePage;
    }

    @Override
    public FunSentence getOneSentence() {
        int count = count();
        int randId = new Random().nextInt(11) + 1;
        return sentenceMapper.selectById(randId);
    }

    @Override
    public boolean isExistByContent(String content) {
        return null != sentenceMapper.selectOne(
                new QueryWrapper<FunSentence>().eq("content", content)
        );
    }
}
