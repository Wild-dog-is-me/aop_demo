package org.dog.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Odin
 * @Date: 2023/7/3 20:47
 * @Description:
 */

@Slf4j
@SuppressWarnings("all")
@RestController
public class LogTestController {

    @GetMapping("/addInventory/{name}")
    public void addInventory(@PathVariable String name) throws InterruptedException {
        Thread.sleep(2000);
        log.error("商品:{} 库存添加成功", name);
    }

    @GetMapping("/addInventoryError/{name}")
    public void addInventoryError(@PathVariable String name) {
        log.error("商品:{} 库存添加成功", name, (10 / 0));
    }

    @GetMapping("/return/{name}")
    public String returnData(@PathVariable String name) {
        return "name : " + name;
    }
}
