package org.dog.server.convert;

import org.dog.server.model.OperateLog;
import org.dog.server.model.SaveOrder;

/**
 * @Author: Odin
 * @Date: 2023/7/4 00:16
 * @Description:
 */
public class SaveOrderConvert implements Convert<SaveOrder> {
    @Override
    public OperateLog convert(SaveOrder saveOrder) {
        OperateLog operateLog = new OperateLog();
        operateLog.setOrderId(saveOrder.getId());
        return operateLog;
    }
}
