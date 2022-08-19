package com.doze.mapper;

import com.doze.pojo.*;

import java.util.List;

public interface DozeMapper {
    //客户端连线
    UserDetailed online(int userId);
    //有新的订单
    void newOrder(int isNewOrder, int uid);

    //用户登录
    User login(String username,String password);

    //请求Bar
    List<Bar> getBar(String level);

    //请求菜谱
    List<CookBook> getCookBook(int uid);

    //增加接口
    int addCookBook(int uid,
                    String cookName,
                    String cookPicture,
                    double price,
                    String isGood,
                    String isSell,
                    String cookLabel);

    //修改菜谱
    int updateCookBook(int id,
                       String cookName,
                       String cookPicture,
                       double price,
                       String isGood,
                       String isSell,
                       String cookLabel);

    //删除菜谱
    int removeCookBook(int id);

    //客户获取菜谱
    List<CookBook> customerGetCookBook();

    //商家获取订单
    List<Order> getOrder(int uid);
    List<Receipt> getReceipt(int serialNumber);

    //存入order
    void generateOrder(int seatNumber, String time, String remarks, double payment, int uid);
    //取出订单号
    Order getOrderNumber(int seatNumber, String time, String remarks, double payment, int uid);
    //存入receipt
    void generateReceipt(String CookName, int quantity, double price, int serialNumber);
}
