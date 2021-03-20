package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    //注入Dao对象
    @Autowired
    private OrderSettingDao orderSettingDao;

    //批量导入预约设置
    public void add(List<OrderSetting> list) {
        if(list != null && list.size() > 0){
            for(OrderSetting orderSetting : list){
                //判断当前日期是否已经进行了预约设置
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if(count > 0){
                    orderSettingDao.editNumeberByOrderDate(orderSetting);
                }else {
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }


    public List<Map> getOrderSettingByMonth(String date) {
        String begin = date + "-1";
        String end = date + "-31";
        Map<String, String> map = new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> result = new ArrayList<>();
        if(list != null && list.size() > 0){
            for(OrderSetting orderSetting : list){
                Map<String, Object> m = new HashMap<>();
                m.put("date",orderSetting.getOrderDate().getDate());
                m.put("number",orderSetting.getNumber());
                m.put("reservations",orderSetting.getReservations());
                result.add(m);
            }
        }
        return result;
    }

    //根据传过来的日期查询出相应的预约数据返回前台
    public void editNumberByDate(OrderSetting orderSetting) {
        Date orderDate = orderSetting.getOrderDate();
        long orderCount = orderSettingDao.findCountByOrderDate(orderDate);
        if(orderCount > 0){
            //有，修改
            orderSettingDao.editNumeberByOrderDate(orderSetting);
        }else{
            //无，新增
            orderSettingDao.add(orderSetting);
        }
    }
}
