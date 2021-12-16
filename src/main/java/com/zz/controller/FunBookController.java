package com.zz.controller;


import com.zz.entity.FunBook;
import com.zz.entity.FunWord;
import com.zz.result.Result;
import com.zz.result.ResultFactory;
import com.zz.service.FunBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  书籍前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Slf4j
@Api(value = "BookController", tags = "书籍管理控制类")
@RestController
@RequestMapping("/api")
public class FunBookController {
    @Autowired
    FunBookService bookService;

    @ApiOperation("列出所有书籍接口")
    @GetMapping("/books")
    public Result listBooks() {
        log.info("书籍查询成功");
        return ResultFactory.buildSuccessResult(bookService.list());
    }

    @ApiOperation("根据书名查询书籍接口")
    @GetMapping("/books/title")
    public Result getBooksByTitle(@ApiParam("书名") String title) {
        log.info("根据书名模糊查询书籍查询成功");
        return ResultFactory.buildSuccessResult(bookService.listLikeByTitle(title));
    }

    @ApiOperation("根据作者查询书籍接口")
    @GetMapping("/books/author")
    public Result getBooksByAuthor(@ApiParam("作者") String author) {
        log.info("根据作者模糊查询书籍查询成功");
        return ResultFactory.buildSuccessResult(bookService.listLikeByAuthor(author));
    }

    @ApiOperation("根据出版社查询书籍接口")
    @GetMapping("/books/press")
    public Result getBooksByPress(@ApiParam("出版社") String press) {
        log.info("根据出版社查询书籍查询成功");
        return ResultFactory.buildSuccessResult(bookService.listLikeByPress(press));
    }


    @ApiOperation("添加书籍接口")
    @PostMapping("/admin/content/book")
    public Result addBook(@ApiParam("要添加的书籍") @RequestBody FunBook book) {
        int status = bookService.insertBook(book);

        switch (status) {
            case 0: {
                String msg = "抱歉，该书籍已存在";
                log.info(msg);
                return ResultFactory.buildFailResult(msg);
            } case 1: {
                String msg = "书籍添加成功";
                log.info(msg);
                return ResultFactory.buildSuccessResult(msg);
            } case 2: {
                String msg = "书籍添加失败";
                log.info(msg);
                return ResultFactory.buildFailResult(msg);
            } default:
                return ResultFactory.buildFailResult("未知错误！！！");
        }
    }

    @ApiOperation("修改书籍接口")
    @PutMapping("/admin/content/book")
    public Result updateBook(@ApiParam("被修改后的书籍") @RequestBody FunBook book) {
        if (bookService.updateById(book)) {
            String msg = "书籍修改成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "书籍修改失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }

    @ApiOperation("删除书籍接口")
    @DeleteMapping("/admin/content/book")
    public Result deleteBook(@ApiParam("要删除的书籍id") @RequestBody int id) {
        if (bookService.removeById(id)) {
            String msg = "书籍删除成功";
            log.info(msg);
            return ResultFactory.buildSuccessResult(msg);
        } else {
            String msg = "书籍删除失败";
            log.info(msg);
            return ResultFactory.buildFailResult(msg);
        }
    }
}
