package com.zz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.common.myEntity.WordInfo;
import com.zz.common.myEntity.WordPage;
import com.zz.entity.FunWord;
import com.zz.mapper.FunWordMapper;
import com.zz.service.FunWordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public WordPage listWordsByPage(int size, int page) {
        IPage<FunWord> iPage = page(new Page<>(page, size));

        WordPage wordPage = new WordPage();
        wordPage.setPages((int)iPage.getPages());
        wordPage.setTotal((int)iPage.getTotal());
        wordPage.setWords(iPage.getRecords());

        return wordPage;
    }

    @Override
    public List<FunWord> listLikeByWord(String word) {
        return wordMapper.selectList(
                new QueryWrapper<FunWord>().like("word", word)
        );
    }

    @Override
    public WordPage listLikeByWordPage(String word, int size, int page) {
        IPage<FunWord> iPage = page(new Page<>(page, size),
                new QueryWrapper<FunWord>().like("word", word));

        WordPage wordPage = new WordPage();
        wordPage.setPages((int)iPage.getPages());
        wordPage.setTotal((int)iPage.getTotal());
        wordPage.setWords(iPage.getRecords());

        return wordPage;
    }

    @Override
    public List<FunWord> listLikeByMeans(String means) {
        return wordMapper.selectList(
                new QueryWrapper<FunWord>().like("means", means)
        );
    }

    @Override
    public WordPage listLikeByMeansPage(String means, int size, int page) {
        IPage<FunWord> iPage = page(new Page<>(page, size),
                new QueryWrapper<FunWord>().like("means", means));

        WordPage wordPage = new WordPage();
        wordPage.setPages((int)iPage.getPages());
        wordPage.setTotal((int)iPage.getTotal());
        wordPage.setWords(iPage.getRecords());

        return wordPage;
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

    @Override
    public WordInfo findInfoByGrade(int grade) {
        WordInfo info = new WordInfo();
        info.setGrade(grade);
        info.setTotal(wordMapper.selectCount(
                new QueryWrapper<FunWord>().eq("grade", grade)
        ));

        info.setStudyNum(wordMapper.selectCount(
                new QueryWrapper<FunWord>().eq("grade", grade).eq("study", 1)
        ));

        info.setRememberNum(wordMapper.selectCount(
                new QueryWrapper<FunWord>().eq("grade", grade).eq("remember", 1)
        ));

        info.setCollectNum(wordMapper.selectCount(
                new QueryWrapper<FunWord>().eq("grade", grade).eq("collection", 1)
        ));

        return info;
    }

    @Override
    public FunWord findWordByOrder(int grade) {
        return wordMapper.selectOne(
                new QueryWrapper<FunWord>().eq("grade", grade).eq("study", 0)
                        .orderByAsc("id").last("limit 1")
        );
    }

    @Override
    public FunWord findWordDisorder(int grade) {
        List<FunWord> words = wordMapper.selectList(
                new QueryWrapper<FunWord>().eq("grade", grade).eq("study", 0)
        );

        // 在找出的单词做随机取一个数
        int index = new Random().nextInt(words.size());

        return words.get(index);
    }

    @Override
    public int restartByGrade(Integer grade) {
        return wordMapper.update(null,
                new UpdateWrapper<FunWord>().setSql("study = 0, remember = 0").eq("grade", grade));
    }

    @Override
    public int studyWord(FunWord word) {
        return wordMapper.update(null,
                new UpdateWrapper<FunWord>().set("study", 1).eq("id", word.getId()));
    }

    @Override
    public int rememberWord(FunWord word) {
        return wordMapper.update(null,
                new UpdateWrapper<FunWord>().set("remember", 1).eq("id", word.getId()));
    }

    @Override
    public int collectionWord(FunWord word) {
        return wordMapper.update(null,
                new UpdateWrapper<FunWord>().set("collection", word.getCollection() == 0 ? 1 : 0).eq("id", word.getId()));
    }

    @Override
    public List<FunWord> listCollectedWords() {
        return wordMapper.selectList(
                new QueryWrapper<FunWord>().eq("collection", 1)
        );
    }
}
