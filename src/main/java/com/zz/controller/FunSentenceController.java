package com.zz.controller;


import com.zz.entity.FunBook;
import com.zz.entity.FunSentence;
import com.zz.result.Result;
import com.zz.result.ResultFactory;
import com.zz.service.FunSentenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  文章前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Slf4j
@Api(value = "SentenceController", tags = "美句管理控制类")
@RestController
@RequestMapping("/api")
public class FunSentenceController {

    @Autowired
    FunSentenceService sentenceService;

    @ApiOperation("列出所有美句接口")
    @GetMapping("/sentences")
    public Result listSentences() {
        log.info("美句查询成功");
        return ResultFactory.buildSuccessResult(sentenceService.list());
    }

    @ApiOperation("每日一句接口")
    @GetMapping("/sentences/everyday")
    public Result getSentenceEveryday() {
        log.info("每日一句");
        return ResultFactory.buildSuccessResult(sentenceService.getOneSentence());
    }

    @ApiOperation("添加美句接口")
    @PostMapping("/admin/content/sentence")
    public Result addSentence(@ApiParam("要添加的美句") @RequestBody FunSentence sentence) {

        if (sentenceService.save(sentence)) {
            String msg = "美句添加成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "美句添加失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

    @ApiOperation("修改美句接口")
    @PutMapping("/admin/content/sentence")
    public Result updateSentence(@ApiParam("被修改后的美句") @RequestBody FunSentence sentence) {
        if (sentenceService.updateById(sentence)) {
            String msg = "美句修改成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "美句修改失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

    @ApiOperation("删除美句接口")
    @DeleteMapping("/admin/content/sentence")
    public Result deleteSentence(@ApiParam("要删除的美句id") @RequestBody int id) {
        if (sentenceService.removeById(id)) {
            String msg = "美句删除成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "美句删除失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

}
