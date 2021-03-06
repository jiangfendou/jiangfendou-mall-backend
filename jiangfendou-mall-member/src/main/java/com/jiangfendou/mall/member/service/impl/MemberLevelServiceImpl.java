package com.jiangfendou.mall.member.service.impl;

import com.jiangfendou.mall.member.dao.MemberLevelDao;
import com.jiangfendou.mall.member.entity.MemberLevelEntity;
import com.jiangfendou.mall.member.service.MemberLevelService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangfendou.common.utils.PageUtils;
import com.jiangfendou.common.utils.Query;


@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelDao, MemberLevelEntity>
    implements MemberLevelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberLevelEntity> page = this.page(
            new Query<MemberLevelEntity>().getPage(params),
            new QueryWrapper<MemberLevelEntity>()
        );

        return new PageUtils(page);
    }

}