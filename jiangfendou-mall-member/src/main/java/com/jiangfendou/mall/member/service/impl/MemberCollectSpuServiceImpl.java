package com.jiangfendou.mall.member.service.impl;

import com.jiangfendou.mall.member.dao.MemberCollectSpuDao;
import com.jiangfendou.mall.member.service.MemberCollectSpuService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangfendou.common.utils.PageUtils;
import com.jiangfendou.common.utils.Query;

import com.jiangfendou.mall.member.entity.MemberCollectSpuEntity;


@Service("memberCollectSpuService")
public class MemberCollectSpuServiceImpl extends ServiceImpl<MemberCollectSpuDao, MemberCollectSpuEntity>
    implements MemberCollectSpuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberCollectSpuEntity> page = this.page(
            new Query<MemberCollectSpuEntity>().getPage(params),
            new QueryWrapper<MemberCollectSpuEntity>()
        );

        return new PageUtils(page);
    }

}