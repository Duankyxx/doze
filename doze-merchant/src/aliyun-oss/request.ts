//OSS
import Dozurl from "@/dozis/dozurl";
import axios from "axios";
import {UploadRawFile} from "element-plus/es";

const OSS = require('ali-oss');

//发送图片并拿到url图片
export const sendCookPicture = async (file: UploadRawFile): Promise<string> => {
    let STSToken: any = await axios.post(Dozurl.getSTSToken);
    console.log(STSToken);
    const client = new OSS({
        // yourRegion填写Bucket所在地域。以华东1（杭州）为例，yourRegion填写为oss-cn-hangzhou。
        region: 'oss-cn-hangzhou',
        // 从STS服务获取的临时访问密钥（AccessKey ID和AccessKey Secret）
        accessKeyId: STSToken.data.credentials.accessKeyId,
        accessKeySecret: STSToken.data.credentials.accessKeySecret,
        //STSToken
        stsToken: STSToken.data.credentials.securityToken,
        // 填写Bucket名称。
        bucket: 'udnsunusn'
    });
    let res = await client.put(`doze/picture/${file.name}`, file);
    return res.url;
}


