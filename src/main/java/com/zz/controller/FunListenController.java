package com.zz.controller;


import com.zz.entity.FunListen;
import com.zz.entity.FunSentence;
import com.zz.result.Result;
import com.zz.result.ResultFactory;
import com.zz.service.FunListenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  听力材料前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Slf4j
@Api(value = "ListenController", tags = "听力材料管理控制类")
@RestController
@RequestMapping("/api")
public class FunListenController {
    @Autowired
    FunListenService listenService;

    @ApiOperation("列出所有听力接口")
    @GetMapping("/listens")
    public Result listListens() {
        log.info("听力查询成功");
        return ResultFactory.buildSuccessResult(listenService.list());
    }

    @ApiOperation("听力分页查询接口")
    @GetMapping("/listens/page")
    public Result listPageUsers(@ApiParam("页面大小")int size,
                                @ApiParam("当前页")int page) {
        return ResultFactory.buildSuccessResult(listenService.listListensByPage(size, page));
    }

    @ApiOperation("根据id查找听力接口")
    @GetMapping("/listens/id")
    public Result getListenById(@ApiParam("听力材料id") int id) {
        log.info("根据听力id查询成功");
        return ResultFactory.buildSuccessResult(listenService.getById(id));
    }

    @ApiOperation("添加听力接口")
    @PostMapping("/admin/content/listen")
    public Result addListen(@ApiParam("要添加的听力") @RequestBody FunListen listen) {

        if (listenService.save(listen)) {
            String msg = "听力添加成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "听力添加失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

    @ApiOperation("修改听力接口")
    @PutMapping("/admin/content/listen")
    public Result updateListen(@ApiParam("被修改后的听力") @RequestBody FunListen listen) {
        if (listenService.updateById(listen)) {
            String msg = "听力修改成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "听力修改失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

    @ApiOperation("删除听力接口")
    @DeleteMapping("/admin/content/listen")
    public Result deleteListen(@ApiParam("要删除的听力id") @RequestBody FunListen listen) {
        if (listenService.removeById(listen.getId())) {
            String msg = "听力删除成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "听力删除失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

}
