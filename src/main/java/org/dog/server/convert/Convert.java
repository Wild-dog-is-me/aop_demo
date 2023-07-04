package org.dog.server.convert;

import org.dog.server.model.OperateLog;

/**
 * @Author: Odin
 * @Date: 2023/7/4 00:15
 * @Description:
 */
public interface Convert<T> {

    OperateLog convert(T t);

}
