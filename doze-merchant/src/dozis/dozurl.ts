export default class Dozurl{
    //基本变量
    //http://120.25.127.13:5080
    //http://127.0.0.1:5080
    static http: string = "http://127.0.0.1:5080";

    //链接
    static online: string = `${Dozurl.http}/doze_api/online`
    static login: string = `${Dozurl.http}/doze_api/login`;   // 登录
    static getBar: string = `${Dozurl.http}/doze_api/getBar`; // 获取导航栏
    static getCookBook: string = `${Dozurl.http}/doze_api/getCookBook`; // 获取菜谱
    static addCookBook: string = `${Dozurl.http}/doze_api/addCookBook`; // 增加菜谱
    static updateCookBook: string = `${Dozurl.http}/doze_api/updateCookBook`; // 修改菜谱
    static removeCookBook: string = `${Dozurl.http}/doze_api/removeCookBook`; // 删除菜谱
    static getOrder: string = `${Dozurl.http}/doze_api/getOrder`; //获取订单
    static getHistoryOrderOrder: string = `${Dozurl.http}/doze_api/getHistoryOrder`; //获取订单
    static subOrder: string = `${Dozurl.http}/doze_api/subOrder`;   //提交订单
    static getSTSToken: string = `${Dozurl.http}/doze_api/getSTS`; //获取Token


    static Merchant: string = `http://120.25.127.13:3050`;

}