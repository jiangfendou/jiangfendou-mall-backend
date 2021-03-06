package com.jiangfendou.mall.member.service.impl;

import com.jiangfendou.mall.member.dao.MemberDao;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangfendou.common.utils.PageUtils;
import com.jiangfendou.common.utils.Query;

import com.jiangfendou.mall.member.entity.MemberEntity;
import com.jiangfendou.mall.member.service.MemberService;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
            new Query<MemberEntity>().getPage(params),
            new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

}