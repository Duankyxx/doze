import { ElMessage } from "element-plus";
import axios, {AxiosResponse} from "axios";

export default class dozis{
    static get(url: string,msg: string): Promise<unknown>{
        return new Promise((resolve, reject) => {
            axios.get(url).then((res) => {
                resolve(res.data);
            }).catch((err) => {
                ElMessage({
                    type: "error",
                    message: msg,
                })
                reject(err);
            });
        })
    }
    static post(url: string, data: any, msg: string = "连接失败!请检查网络!"): Promise<any>{
        return new Promise((resolve, reject) => {
            axios.post(url, data).then((res) => {
                resolve(res.data);
            }).catch(err => {
                ElMessage({
                    type: "error",
                    message: msg,
                })
                reject(err);
            })
        })
    }
}