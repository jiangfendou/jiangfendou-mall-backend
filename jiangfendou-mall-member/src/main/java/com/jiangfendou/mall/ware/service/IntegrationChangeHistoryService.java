package com.jiangfendou.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangfendou.common.utils.PageUtils;
import com.jiangfendou.mall.ware.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author jiangfendou
 * @email 49323245@qq.com
 * @date 2022-05-09 22:04:28
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
