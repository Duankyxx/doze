export default class Dozurl{
    //基本变量
    static http: string = "http://127.0.0.1:8080";

    //链接
    static online: string = `${Dozurl.http}/doze_api/online`
    static login: string = `${Dozurl.http}/doze_api/login`;   // 登录
    static getBar: string = `${Dozurl.http}/doze_api/getBar`; // 获取导航栏
    static getCookBook: string = `${Dozurl.http}/doze_api/getCookBook`; // 获取菜谱
    static addCookBook: string = `${Dozurl.http}/doze_api/addCookBook`; // 增加菜谱
    static updateCookBook: string = `${Dozurl.http}/doze_api/updateCookBook`; // 修改菜谱
    static removeCookBook: string = `${Dozurl.http}/doze_api/removeCookBook`; // 删除菜谱
    static getOrder: string = `${Dozurl.http}/doze_api/getOrder`; //获取订单

    static Merchant: string = `http://127.0.0.1:3050`;

}