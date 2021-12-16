package com.zz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public FunSentence getOneSentence() {
        int count = count();
        int randId = new Random().nextInt(count) + 1;
        return sentenceMapper.selectById(randId);
    }

    @Override
    public boolean isExistByContent(String content) {
        return null != sentenceMapper.selectOne(
                new QueryWrapper<FunSentence>().eq("content", content)
        );
    }
}
