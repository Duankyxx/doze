package com.doze.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.doze.lib.MyLog;
import com.doze.mapper.DozeMapper;
import com.doze.pojo.*;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/doze_api")
public class Doze {

    private MyLog myLog = new MyLog();

    //测试
    @RequestMapping(value = "/hello")
    public String hello() {
        myLog.info("来自/hello的测试", 2);
        return "Hello World!";
    }

    //客户端连线
    @RequestMapping(value = "/online",method = RequestMethod.POST)
    public boolean online(@RequestBody User user) throws IOException {
        myLog.info("客户端心跳  -->  用户id:  " + user.getId(), 2);
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
        User user = mapper.login(login.getUsername(),login.getPassword());
        myLog.info("客户登录  -->  用户id:  " + user.getId(), 2);
        return user;
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
        myLog.info("商户请求菜谱  -->  用户id:  " + user.getId(), 2);
        DozeMapper mapper = GetMapper.getMapper();
        return mapper.getCookBook(user.getId());
    }

    //增加接口
    @RequestMapping(value = "addCookBook", method = RequestMethod.POST)
    public boolean addCookBook(@RequestBody CookBook cookBook) throws IOException {
        myLog.info("商户新增菜谱  -->  用户id:  " + cookBook.getUid(), 2);
        DozeMapper mapper = GetMapper.getMapper();
        int a = mapper.addCookBook(cookBook.getUid(), cookBook.getCookName(), cookBook.getCookPicture(), cookBook.getPrice(), cookBook.getIsGood(), cookBook.getIsSell(), cookBook.getCookLabel());
        return true;
    }

    //修改菜谱
    @RequestMapping(value = "/updateCookBook", method = RequestMethod.POST)
    public boolean updateCookBook(@RequestBody CookBook cookBook) throws IOException {
        myLog.info("商户修改菜谱  -->  用户id:  " + cookBook.getUid(), 2);
        DozeMapper mapper = GetMapper.getMapper();
        int line = mapper.updateCookBook(cookBook.getId(), cookBook.getCookName(), cookBook.getCookPicture(), cookBook.getPrice(), cookBook.getIsGood(), cookBook.getIsSell(), cookBook.getCookLabel());
        return line == 1;
    }

    //删除接口
    @RequestMapping(value = "/removeCookBook", method = RequestMethod.POST)
    public boolean removeCookBook(@RequestBody CookBook cookBook) throws IOException {
        myLog.info("商户删除菜谱  -->  用户id:  " + cookBook.getUid(), 2);
        DozeMapper mapper = GetMapper.getMapper();
        int line = mapper.removeCookBook(cookBook.getId());
        return line == 1;
    }

    //获取订单
    @RequestMapping(value = "/getOrder", method = RequestMethod.POST)
    public List<Order> getOrder(@RequestBody User user) throws IOException {
        myLog.info("商户获取订单  -->  用户id:  " + user.getId(), 2);
        DozeMapper mapper = GetMapper.getMapper();
        List<Order> orders = mapper.getOrder(user.getId());
        for (int i=0; i<orders.size(); i++) {
            List<Receipt> receipts = mapper.getReceipt(orders.get(i).getId());
            orders.get(i).setReceipts(receipts);
        }
        return orders;
    }

    //获取历史订单
    //获取订单
    @RequestMapping(value = "/getHistoryOrder", method = RequestMethod.POST)
    public List<Order> getHistoryOrder(@RequestBody User user) throws IOException {
        myLog.info("商户获取历史订单  -->  用户id:  " + user.getId(), 2);
        DozeMapper mapper = GetMapper.getMapper();
        List<Order> orders = mapper.getHistoryOrder(user.getId());
        for (int i=0; i<orders.size(); i++) {
            List<Receipt> receipts = mapper.getReceipt(orders.get(i).getId());
            orders.get(i).setReceipts(receipts);
        }
        return orders;
    }


    //生成订单
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public boolean generateOrder(@RequestBody Order order) throws IOException {
        myLog.info("生成订单  -->  商家id:  " + order.getUid() + "   餐桌号:  -->  " + order.getSeatNumber(), 2);
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

    //完成订单
    @RequestMapping(value = "/subOrder")
    public boolean subOrder(@RequestBody Order order) throws IOException {
        myLog.info("商户完成订单  -->  用户id:  " + order.getUid(), 2);
        DozeMapper mapper = GetMapper.getMapper();
        mapper.deleteOrder(order.getId());
        mapper.intoHistoryOrder(order);
        return true;
    }

    //获取STSToken
    @RequestMapping(value = "/getSTS", method = RequestMethod.POST)
    public String getSTS() {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5tNsugLXceXPri8mDsU2", "cFiSVxuCk1bvyEiRPKX1wCqYFE8Mvj");
        IAcsClient client = new DefaultAcsClient(profile);

        //构造请求，设置参数。
        AssumeRoleRequest request = new AssumeRoleRequest();
        request.setRegionId("cn-hangzhou");
        request.setRoleArn("acs:ram::1052147859501565:role/ramosstest");
        request.setRoleSessionName("alice");

        //发起请求，并得到响应。
        String json = "";
        try {
            AssumeRoleResponse response = client.getAcsResponse(request);
            json = new Gson().toJson(response);
        } catch (ClientException e) {
            System.out.println(e);
        }
        return json;
    }
}
