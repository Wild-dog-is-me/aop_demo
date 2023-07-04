package org.dog.server.service;

import org.dog.server.annotation.RecordOperate;
import org.dog.server.convert.SaveOrderConvert;
import org.dog.server.convert.UpdateOrderConvert;
import org.dog.server.model.SaveOrder;
import org.dog.server.model.UpdateOrder;
import org.springframework.stereotype.Service;

/**
 * @Author: Odin
 * @Date: 2023/7/3 23:00
 * @Description:
 */

@Service
public class OrderService {

    @RecordOperate(desc = "保存订单",converter =  SaveOrderConvert.class)
    public Boolean saveOrder(SaveOrder saveOrder) {
        System.out.println("save order , orderId : " + saveOrder.getId());
        return true;
    }

    @RecordOperate(desc = "更新订单", converter = UpdateOrderConvert.class)
    public Boolean updateOrder(UpdateOrder updateOrder) {
        System.out.println("update order, orderId : " + updateOrder.getOrderId());
        return true;
    }
}
