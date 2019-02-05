package com.kenet.springbootstarter.controller.admin;

import com.kenet.springbootstarter.entity.Goods;
import com.kenet.springbootstarter.entity.PageBean;
import com.kenet.springbootstarter.entity.Result;
import com.kenet.springbootstarter.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/findByConPage")
    public PageBean findByConPage(Goods goods,
                                  @RequestParam(value = "pageCode", required = false) int pageCode,
                                  @RequestParam(value = "pageSize", required = false) int pageSize) {

       return goodsService.findByPage(goods,pageCode, pageSize);
    }


    /**
     * 新增商品
     *
     * @param goods
     * @return
     */
    @RequestMapping("/create")
    public Result create(@RequestBody Goods goods) {
        try {
            goodsService.create(goods);
            return new Result(true, "创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 更新数据成功
     *
     * @param goods
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Goods goods) {
        try {
            goodsService.update(goods);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 批量删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long... ids) {
        try {
            goodsService.delete(ids);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public List<Goods> findById(@RequestParam(value = "id", required = false) Long id) {
        return goodsService.findById(id);
    }
}
