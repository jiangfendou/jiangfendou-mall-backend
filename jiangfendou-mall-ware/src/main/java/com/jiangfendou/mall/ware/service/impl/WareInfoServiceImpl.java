package com.jiangfendou.mall.ware.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.jiangfendou.common.utils.R;
import com.jiangfendou.mall.ware.feign.MemberFeignService;
import com.jiangfendou.mall.ware.vo.FareVo;
import com.jiangfendou.mall.ware.vo.MemberAddressVo;
import java.math.BigDecimal;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.jiangfendou.common.utils.PageUtils;
import com.jiangfendou.common.utils.Query;

import com.jiangfendou.mall.ware.dao.WareInfoDao;
import com.jiangfendou.mall.ware.entity.WareInfoEntity;
import com.jiangfendou.mall.ware.service.WareInfoService;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity>
    implements WareInfoService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareInfoEntity> wareInfoEntityQueryWrapper = new QueryWrapper<>();
        String key = (String)params.get("key");
        if (!StringUtils.isEmpty(key)) {
            wareInfoEntityQueryWrapper.eq("id", key).or().like("name", key)
                .or().like("address", key).or().like("areacode", key);
        }
        IPage<WareInfoEntity> page = this.page(
            new Query<WareInfoEntity>().getPage(params),
            wareInfoEntityQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public FareVo getFare(Long addrId) {
        FareVo fareVo = new FareVo();

        //收获地址的详细信息
        R addrInfo = memberFeignService.info(addrId);

        MemberAddressVo memberAddressVo = addrInfo.getData("memberReceiveAddress",
            new TypeReference<MemberAddressVo>() {});

        if (memberAddressVo != null) {
            String phone = memberAddressVo.getPhone();
            //截取用户手机号码最后一位作为我们的运费计算
            //1558022051
            String fare = phone.substring(phone.length() - 10, phone.length()-8);
            BigDecimal bigDecimal = new BigDecimal(fare);

            fareVo.setFare(bigDecimal);
            fareVo.setAddress(memberAddressVo);

            return fareVo;
        }
        return null;

    }

}