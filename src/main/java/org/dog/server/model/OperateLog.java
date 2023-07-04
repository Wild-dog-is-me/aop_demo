package org.dog.server.model;

import lombok.Data;

/**
 * @Author: Odin
 * @Date: 2023/7/3 23:41
 * @Description:
 */

@Data
public class OperateLog {

    private Long orderId;

    private String desc;

    private String result;

}
