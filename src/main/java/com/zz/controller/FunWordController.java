package com.zz.controller;


import com.zz.result.Result;
import com.zz.result.ResultFactory;
import com.zz.service.FunWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  words前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-12-10
 */
@Api(value = "WordController", tags = "单词操作接口")
@RestController
@RequestMapping("/api")
public class FunWordController {

    @Autowired
    FunWordService funWordService;

    @ApiOperation("列出所有单词")
    @GetMapping("/listWords")
    public Result listWords() {
        return ResultFactory.buildSuccessResult(funWordService.list());
    }

    @ApiOperation("根据内容模糊查询")
    @GetMapping("/getWords")
    public Result getWordsByWord(@ApiParam("单词") String word) {
        System.out.println("根据内容模糊查询单词查询成功");
        return ResultFactory.buildSuccessResult(funWordService.listLikeByWord(word));
    }

    @ApiOperation("根据词义模糊查询")
    @GetMapping("/getWordsByMeans")
    public Result getWordsByMeans(@ApiParam("词义") String means) {
        System.out.println("根据词义模糊查询单词查询成功");
        return ResultFactory.buildSuccessResult(funWordService.listLikeByMeans(means));
    }

    @ApiOperation("根据等级查询单词")
    @GetMapping("/getWordsByGrade")
    public Result getWordsByGrade(@ApiParam("等级") int grade) {
        System.out.println("根据等级查询单词单词查询成功");
        return ResultFactory.buildSuccessResult(funWordService.listLikeByGrade(grade));
    }
}
