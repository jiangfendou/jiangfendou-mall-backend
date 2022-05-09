package com.jiangfendou.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangfendou.common.utils.PageUtils;
import com.jiangfendou.mall.member.entity.GrowthChangeHistoryEntity;

import java.util.Map;

/**
 * 成长值变化历史记录
 *
 * @author jiangfendou
 * @email 49323245@qq.com
 * @date 2022-05-09 22:04:29
 */
public interface GrowthChangeHistoryService extends IService<GrowthChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

