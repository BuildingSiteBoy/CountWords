package com.zz.controller;

import com.zz.entity.FunWord;
import com.zz.result.Result;
import com.zz.result.ResultFactory;
import com.zz.service.FunWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  wordStudy前端控制器
 * </p>
 *
 * @author zz
 */
@Slf4j
@Api(value = "WordStudyController", tags = "单词学习控制类")
@RestController
@RequestMapping("/api")
public class StudyWordController {
    @Autowired
    FunWordService funWordService;

    @ApiOperation("根据单词等级查询学习信息接口")
    @GetMapping("/words/info")
    public Result getInfoByGrade(@ApiParam("单词等级") Integer grade) {
        log.info("学习信息查询成功");
        return ResultFactory.buildSuccessResult(funWordService.findInfoByGrade(grade));
    }

    @ApiOperation("根据等级顺序学习单词接口")
    @GetMapping("/words/order")
    public Result getWordByOrder(@ApiParam("单词等级") Integer grade) {
        log.info("单词获取成功（顺序）");
        return ResultFactory.buildSuccessResult(funWordService.findWordByOrder(grade));
    }

    @ApiOperation("根据等级乱序学习单词接口")
    @GetMapping("/words/disorder")
    public Result getWordDisorder(@ApiParam("单词等级") Integer grade) {
        log.info("单词获取成功（乱序）");
        return ResultFactory.buildSuccessResult(funWordService.findWordDisorder(grade));
    }

    @ApiOperation("根据等级重新学习单词接口")
    @PutMapping("/words/restart")
    public Result restart(@ApiParam("单词等级") @RequestBody Integer grade) {
        funWordService.restartByGrade(grade);
        log.info("学习进度重置成功");
        return ResultFactory.buildSuccessResult("请重新开始学习！！！");
    }

    @ApiOperation("已学习单词接口")
    @PutMapping("/words/study")
    public Result studyWord(@ApiParam("单词等级") @RequestBody FunWord word) {
        funWordService.studyWord(word);
        log.info("单词学习成功");
        return ResultFactory.buildSuccessResult("单词学习成功！！！");
    }

    @ApiOperation("已记住单词接口")
    @PutMapping("/words/remember")
    public Result rememberWord(@ApiParam("单词") @RequestBody FunWord word) {
        funWordService.rememberWord(word);
        log.info("单词记忆成功");
        return ResultFactory.buildSuccessResult("单词记忆成功！！！");
    }

    @ApiOperation("收藏/取消收藏单词接口")
    @PutMapping("/words/collection")
    public Result collectWord(@ApiParam("单词") @RequestBody FunWord word) {
        funWordService.collectionWord(word);
        log.info("单词[取消]收藏成功");
        return ResultFactory.buildSuccessResult("单词[取消]收藏成功！！！");
    }

    @ApiOperation("查看收藏的单词接口")
    @GetMapping("/words/collection")
    public Result getCollectionWords() {
        log.info("查看单词收藏本");
        return ResultFactory.buildSuccessResult(funWordService.listCollectedWords());
    }
}
