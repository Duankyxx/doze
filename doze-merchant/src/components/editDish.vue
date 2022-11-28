<template>
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
        <el-switch v-model="isGood"></el-switch>
      </el-form-item>
      <el-form-item label="设为启用">
        <el-switch v-model="isSell"></el-switch>
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
      <el-button type="danger" @click="quit">退出</el-button>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, onBeforeUnmount, onMounted, ref, Ref, watch} from "vue";
import dozis from "@/dozis/dozis";
import Dozurl from "@/dozis/dozurl";
import {ElMessage, UploadRawFile} from "element-plus/es";
import {sendCookPicture} from "@/aliyun-oss/request";

export default defineComponent({
  name: "editDish",
  props: ["data","isEdit"],
  setup(props, context) {
    let data: Ref<CookBook> = ref({...props.data});

    //布尔类型值的转换
    let isGood: Ref<boolean> = ref(false);
    let isSell: Ref<boolean> = ref(false);

    //操作
    const sub = () => {
      isGood.value ? data.value.isGood="1" : data.value.isGood="0";
      isSell.value ? data.value.isSell="1" : data.value.isSell="0";
      dozis.post(Dozurl.updateCookBook, data.value, "请求失败，请检查网络!").then(res =>{
        ElMessage({
          type: "success",
          message: "提交成功!"
        });
        context.emit("updateFlag");
        context.emit("closeEdit");
        context.emit("getCookBook");
      })
    }

    //更新
    //
    const upload = async (file: UploadRawFile): Promise<boolean> => {
      console.log(file);
      data.value.cookPicture = await sendCookPicture(file)+"";
      console.log(data.value);
      return false;
    }

    const quit = () => {
      context.emit("closeEdit");
    }

    watch(
        (): CookBook => props.data,
        (val): void => {
          data.value = {...val};
          isGood.value = data.value.isGood==="1";
          isSell.value = data.value.isSell==="1";
        },
        {deep: true,immediate: true}
    )
    watch(
        (): boolean => props.isEdit,
        (val): void => {
          if (!val) {
            console.log("推出");
            data.value = {...props.data};
            isGood.value = data.value.isGood==="1";
            isSell.value = data.value.isSell==="1";
          }
        },
        {deep: true,immediate: true}
    )

    return {
      isGood,isSell,data,
      sub,quit,upload
    }
  }
});
</script>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>