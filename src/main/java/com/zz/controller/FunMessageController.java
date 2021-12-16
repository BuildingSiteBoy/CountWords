package com.zz.controller;


import com.zz.entity.FunListen;
import com.zz.entity.FunMessage;
import com.zz.result.Result;
import com.zz.result.ResultFactory;
import com.zz.service.FunMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  留言板前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Slf4j
@Api(value = "MessageController", tags = "留言板管理控制类")
@RestController
@RequestMapping("/api")
public class FunMessageController {

    @Autowired
    FunMessageService messageService;

    @ApiOperation("列出所有留言接口")
    @GetMapping("/message")
    public Result listMessages() {
        log.info("留言查询成功");
        return ResultFactory.buildSuccessResult(messageService.list());
    }

    @ApiOperation("添加留言接口")
    @PostMapping("/message")
    public Result addMessage(@ApiParam("要添加的留言") @RequestBody FunMessage message) {

        if (messageService.save(message)) {
            String msg = "留言添加成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "留言添加失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

    @ApiOperation("修改留言接口")
    @PutMapping("/admin/message")
    public Result updateMessage(@ApiParam("被修改后的留言") @RequestBody FunMessage message) {
        if (messageService.updateById(message)) {
            String msg = "留言修改成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "留言修改失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

    @ApiOperation("删除留言接口")
    @DeleteMapping("/admin/message")
    public Result deleteMessage(@ApiParam("要删除的留言id") @RequestBody int id) {
        if (messageService.removeById(id)) {
            String msg = "留言删除成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "留言删除失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }
}
