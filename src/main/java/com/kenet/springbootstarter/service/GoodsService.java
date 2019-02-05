package com.kenet.springbootstarter.service;

import com.kenet.springbootstarter.entity.Goods;
import com.kenet.springbootstarter.entity.PageBean;

public interface GoodsService extends BaseService<Goods> {

    /**
     * 分页查询
     * @param goods 查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    PageBean findByPage(Goods goods, int pageCode, int pageSize);
}