<template>
  <!--表单-->
  <div>
    <!--表单结构-->
    <el-form>
      <el-form-item label="菜名">
        <el-input v-model="data.cookName"></el-input>
      </el-form-item>
      <el-form-item label="价钱">
        <el-input v-model="data.price"></el-input>
      </el-form-item>
      <el-form-item label="设为本店招牌">
        <el-switch v-model="data.isGood"></el-switch>
      </el-form-item>
      <el-form-item label="设为启用">
        <el-switch v-model="data.isSell"></el-switch>
      </el-form-item>
      <el-form-item label="菜品标签">

      </el-form-item>
      <el-form-item label="图片">
        <el-upload
            class="avatar-uploader"
            :multiple="false"
            :before-upload="upload"
            accept="image/jpeg"
            :limit="1">
          <img v-if="data.cookPicture" :src="data.cookPicture" alt="" class="avatar">
          <el-icon v-else class="avatar-uploader-icon"><Plus></Plus></el-icon>
        </el-upload>
      </el-form-item>
    </el-form>
    <!--表单操作-->
    <div>
      <el-button type="success" @click="sub">提交</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
      <el-button type="danger" @click="quit">退出</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, ref, Ref} from "vue";
import {ElMessage, ElMessageBox, UploadRawFile} from "element-plus/es";
import { Plus } from '@element-plus/icons-vue';
import dozis from "@/dozis/dozis";
import Dozurl from "@/dozis/dozurl";
import store from "@/store";
import {sendCookPicture} from "@/aliyun-oss/request";

export default defineComponent({
  name: "addDish",
  components: {
    Plus
  },
  setup(props, context) {
    //初始空白数据
    const dataNull: CookBook = {
      id: "",
      uid: "",
      cookName: "",
      cookPicture: "",
      price: "",
      isGood: "0",
      isSell: "0",
      cookLabel: "",
      purchaseQuantity: ""
    }
    //初始化数据
    let data: Ref<CookBook> = ref({...dataNull});

    //重置
    const reset = (): void => {
      ElMessageBox.confirm("你确定要重置吗!!!",{
        confirmButtonText: '确定',
        cancelButtonText: '我不确定再想想',
      }).then((): void => {
        data.value = {...dataNull};
      });
    }

    //提交
    const sub = (): void => {
      data.value.uid = store.state.user.id + "";
      let dataNow = {...data.value};
      dataNow.isGood=(dataNow.isGood = "1");
      dataNow.isSell=(dataNow.isSell = "1");
      console.log(dataNow);
      dozis.post(Dozurl.addCookBook, dataNow, "请求失败，请检查网络!").then((res) => {
        if (res) {
          ElMessage({
            type: "success",
            message: "添加成功!"
          });
          data.value = {...dataNull};
          context.emit("updateFlag");
          context.emit("closeAdd");
          context.emit("getCookBook");
        }
      })
    }

    //
    const upload = async (file: UploadRawFile): Promise<boolean> => {
      console.log(file);
      data.value.cookPicture = await sendCookPicture(file)+"";
      console.log(data.value);
      return false;
    }

    //退出
    const quit = (): void => {
      context.emit("closeAdd");
    }

    return {
      data,
      reset,sub,quit,upload
    }
  }
});
</script>

<style lang="scss" scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>