package com.jiangfendou.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangfendou.common.to.mq.SeckillOrderTo;
import com.jiangfendou.common.utils.PageUtils;
import com.jiangfendou.mall.order.entity.OrderEntity;

import com.jiangfendou.mall.order.vo.OrderConfirmVo;
import com.jiangfendou.mall.order.vo.OrderSubmitVo;
import com.jiangfendou.mall.order.vo.PayAsyncVo;
import com.jiangfendou.mall.order.vo.PayVo;
import com.jiangfendou.mall.order.vo.SubmitOrderResponseVo;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 订单
 *
 * @author jiangfendou
 * @email 49323245@qq.com
 * @date 2022-05-09 21:39:35
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    SubmitOrderResponseVo submitOrder(OrderSubmitVo vo);

    OrderEntity getOrderByOrderSn(String orderSn);

    void closeOrder(OrderEntity orderEntity);

    void test();

    PayVo getOrderPay(String orderSn);

    PageUtils queryPageWithItem(Map<String, Object> params);

    String handlePayResult(PayAsyncVo asyncVo);

    String asyncNotify(String notifyData);

    void createSeckillOrder(SeckillOrderTo seckillOrderTo);
}

