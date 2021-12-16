package com.zz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zz.entity.FunWord;
import com.zz.mapper.FunWordMapper;
import com.zz.service.FunWordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zZeng
 * @since 2021-12-10
 */
@Service
public class FunWordServiceImpl extends ServiceImpl<FunWordMapper, FunWord> implements FunWordService {

    @Autowired
    FunWordMapper wordMapper;

    @Override
    public List<FunWord> listLikeByWord(String word) {
        return wordMapper.selectList(
                new QueryWrapper<FunWord>().like("word", word)
        );
    }

    @Override
    public List<FunWord> listLikeByMeans(String means) {
        return wordMapper.selectList(
                new QueryWrapper<FunWord>().like("means", means)
        );
    }

    @Override
    public List<FunWord> listLikeByGrade(int grade) {
        return wordMapper.selectList(
                new QueryWrapper<FunWord>().eq("grade", grade)
        );
    }

    @Override
    public boolean isExistByWord(String word) {
        return null != wordMapper.selectOne(
                new QueryWrapper<FunWord>().eq("word", word)
        );
    }

    @Override
    public int insertWord(FunWord word) {
        if (isExistByWord(word.getWord())) {
            return 0;
        } else {
            if (wordMapper.insert(word) == 1) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}
