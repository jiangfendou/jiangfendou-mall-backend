package com.jiangfendou.mall.product;

import com.jiangfendou.mall.product.entity.BrandEntity;
import com.jiangfendou.mall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Autowired
    RedissonClient redissonClient;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("Descript");
        brandEntity.setName("brandName");
        brandService.save(brandEntity);
        System.out.println("保存成功！！！");
    }

    @Test
    void redisson() {
        System.out.println(redissonClient);
    }
}
