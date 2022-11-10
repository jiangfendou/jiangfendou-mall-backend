package com.jiangfendou.mall.ware.controller;

import com.jiangfendou.common.to.SkuHasStockVo;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.jiangfendou.mall.ware.entity.WareSkuEntity;
import com.jiangfendou.mall.ware.service.WareSkuService;
import com.jiangfendou.common.utils.PageUtils;
import com.jiangfendou.common.utils.R;


/**
 * 商品库存
 *
 * @author jiangfendou
 * @email 49323245@qq.com
 * @date 2022-05-10 11:29:39
 */
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {
    @Autowired
    private WareSkuService wareSkuService;

    /**
     * 查询sku是否有库存
     */
    @PostMapping("/has-stock")
    public R getSkuHasStock(@RequestBody List<Long> skuIds) {
       List<SkuHasStockVo> vos = wareSkuService.getSkuHasStock(skuIds);
       return R.ok().setData(vos);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
            public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = wareSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
            public R info(@PathVariable("id") Long id) {
            WareSkuEntity wareSku = wareSkuService.getById(id);

        return R.ok().put("wareSku", wareSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
            public R save(@RequestBody WareSkuEntity wareSku) {
            wareSkuService.save(wareSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
            public R update(@RequestBody WareSkuEntity wareSku) {
            wareSkuService.updateById(wareSku);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
            public R delete(@RequestBody Long[] ids) {
            wareSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
