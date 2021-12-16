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
 *  words前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-12-10
 */
@Slf4j
@Api(value = "WordController", tags = "单词管理控制类")
@RestController
@RequestMapping("/api")
public class FunWordController {

    @Autowired
    FunWordService funWordService;

    @ApiOperation("列出所有单词接口")
    @GetMapping("/words")
    public Result listWords() {
        log.info("单词查询成功");
        return ResultFactory.buildSuccessResult(funWordService.list());
    }

    @ApiOperation("根据内容模糊查询接口")
    @GetMapping("/words/word")
    public Result getWordsByWord(@ApiParam("单词") String word) {
        log.info("根据内容模糊查询单词查询成功");
        return ResultFactory.buildSuccessResult(funWordService.listLikeByWord(word));
    }

    @ApiOperation("根据词义模糊查询接口")
    @GetMapping("/words/means")
    public Result getWordsByMeans(@ApiParam("词义") String means) {
        log.info("根据词义模糊查询单词查询成功");
        return ResultFactory.buildSuccessResult(funWordService.listLikeByMeans(means));
    }

    @ApiOperation("根据等级查询单词接口")
    @GetMapping("/words/grade")
    public Result getWordsByGrade(@ApiParam("等级") int grade) {
        log.info("根据等级查询单词查询成功");
        return ResultFactory.buildSuccessResult(funWordService.listLikeByGrade(grade));
    }

    @ApiOperation("添加单词接口")
    @PostMapping("/admin/content/word")
    public Result addWord(@ApiParam("要添加的单词") @RequestBody FunWord word) {
        int status = funWordService.insertWord(word);

        switch (status) {
            case 0: {
                String msg = "抱歉，该单词已存在";
                log.info(msg);
                return ResultFactory.buildFailResult(msg);
            } case 1: {
                String msg = "单词添加成功";
                log.info(msg);
                return ResultFactory.buildSuccessResult(msg);
            } case 2: {
                String msg = "单词添加失败";
                log.info(msg);
                return ResultFactory.buildFailResult(msg);
            } default:
                return ResultFactory.buildFailResult("未知错误！！！");
        }
    }

    @ApiOperation("修改单词接口")
    @PutMapping("/admin/content/word")
    public Result updateWord(@ApiParam("被修改后的单词") @RequestBody FunWord word) {
        if (funWordService.updateById(word)) {
            String msg = "单词修改成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "单词修改失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

    @ApiOperation("删除单词接口")
    @DeleteMapping("/admin/content/word")
    public Result deleteWord(@ApiParam("要删除的单词id") @RequestBody int id) {
        if (funWordService.removeById(id)) {
            String msg = "单词删除成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "单词删除失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

}
