package org.dog.server.convert;

import org.dog.server.model.OperateLog;
import org.dog.server.model.UpdateOrder;

/**
 * @Author: Odin
 * @Date: 2023/7/4 00:17
 * @Description:
 */
public class UpdateOrderConvert implements Convert<UpdateOrder> {

    @Override
    public OperateLog convert(UpdateOrder updateOrder) {
        OperateLog operateLog = new OperateLog();
        operateLog.setOrderId(updateOrder.getOrderId());
        return operateLog;
    }

}
