package com.doze.controller;

import com.doze.mapper.DozeMapper;
import com.doze.pojo.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/doze_api")
public class Doze {

    //测试
    @RequestMapping(value = "/hello")
    public String hello() {
        return "Hello World!";
    }

    //客户端连线
    @RequestMapping(value = "/online",method = RequestMethod.POST)
    public boolean online(@RequestBody User user) throws IOException {
        DozeMapper mapper = GetMapper.getMapper();
        UserDetailed userDetailed = mapper.online(user.getId());
        if (userDetailed.getIsNewOrder() == 0) {
            return false;
        } else {
            mapper.newOrder(0,user.getId());
            return true;
        }
    }

    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public User login(@RequestBody Login login) throws IOException {
        DozeMapper mapper = GetMapper.getMapper();
        return mapper.login(login.getUsername(),login.getPassword());
    }

    //请求导航栏
    @RequestMapping(value = "/getBar", method = RequestMethod.POST)
    public List<Bar> getBar(@RequestBody Level level) throws IOException {
        DozeMapper mapper = GetMapper.getMapper();
        return mapper.getBar(level.getLevel());
    }

    //请求菜谱
    @RequestMapping(value = "/getCookBook", method = RequestMethod.POST)
    public List<CookBook> getCookBook(@RequestBody User user) throws IOException {
        DozeMapper mapper = GetMapper.getMapper();
        return mapper.getCookBook(user.getId());
    }

    //增加接口
    @RequestMapping(value = "addCookBook", method = RequestMethod.POST)
    public boolean addCookBook(@RequestBody CookBook cookBook) throws IOException {
        DozeMapper mapper = GetMapper.getMapper();
        System.out.println(cookBook);
        int a = mapper.addCookBook(cookBook.getUid(), cookBook.getCookName(), cookBook.getCookPicture(), cookBook.getPrice(), cookBook.getIsGood(), cookBook.getIsSell(), cookBook.getCookLabel());
        System.out.println(a);
        return true;
    }

    //修改菜谱
    @RequestMapping(value = "/updateCookBook", method = RequestMethod.POST)
    public boolean updateCookBook(@RequestBody CookBook cookBook) throws IOException {
        DozeMapper mapper = GetMapper.getMapper();
        int line = mapper.updateCookBook(cookBook.getId(), cookBook.getCookName(), cookBook.getCookPicture(), cookBook.getPrice(), cookBook.getIsGood(), cookBook.getIsSell(), cookBook.getCookLabel());
        return line == 1;
    }

    //删除接口
    @RequestMapping(value = "/removeCookBook", method = RequestMethod.POST)
    public boolean removeCookBook(@RequestBody CookBook cookBook) throws IOException {
        DozeMapper mapper = GetMapper.getMapper();
        int line = mapper.removeCookBook(cookBook.getId());
        return line == 1;
    }
    //上传图片
    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
    public boolean uploadPicture() {

        return true;
    }

    //获取订单
    @RequestMapping(value = "/getOrder", method = RequestMethod.POST)
    public List<Order> getOrder(@RequestBody User user) throws IOException {
        DozeMapper mapper = GetMapper.getMapper();
        List<Order> orders = mapper.getOrder(user.getId());
        for (int i=0; i<orders.size(); i++) {
            List<Receipt> receipts = mapper.getReceipt(orders.get(i).getId());
            orders.get(i).setReceipts(receipts);
        }
        return orders;
    }


    //生成订单
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public boolean generateOrder(@RequestBody Order order) throws IOException {
        DozeMapper mapper = GetMapper.getMapper();
        //存入order
        mapper.generateOrder(order.getSeatNumber(),order.getTime(),order.getRemarks(),order.getPayment(),order.getUid());
        //取出订单号
        Order newOrder = mapper.getOrderNumber(order.getSeatNumber(),order.getTime(),order.getRemarks(),order.getPayment(),order.getUid());
        //存入receipt
        List<Receipt> receipts = order.getReceipts();
        for (Receipt receipt: receipts) {
            mapper.generateReceipt(receipt.getCookName(),receipt.getQuantity(), receipt.getPrice(), newOrder.getId());
        }
        mapper.newOrder(1,order.getUid());
        return true;
    }
}
