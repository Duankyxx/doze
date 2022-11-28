<template>
  <div id="cloud">
    <p>
      <el-icon class="Online_icon" size="25px"><MostlyCloudy /></el-icon>云端连接状态:
      <span v-show="isOnline">已连接<el-icon class="Online_icon" size="25px"><CircleCheckFilled /></el-icon></span>
      <span v-show="!isOnline">未连接<el-icon class="Online_icon" size="25px"><CircleCloseFilled /></el-icon></span>
    </p>
  </div>
</template>

<script lang="ts">
import {defineComponent, Ref, ref, watch} from "vue";
import {MostlyCloudy,CircleCheckFilled,CircleCloseFilled} from "@element-plus/icons-vue";
import dozis from "@/dozis/dozis";
import Dozurl from "@/dozis/dozurl";
import store from "@/store";
import {ElMessage, ElNotification} from "element-plus";
import orderManagement from "@/views/Fun/orderManagement.vue";

export default defineComponent({
  name: "cloud",
  components: {
    MostlyCloudy,
    CircleCheckFilled,
    CircleCloseFilled,
  },
  setup(props, context) {
    let isOnline: Ref<boolean> = ref(false);
    //监听
    watch(
        () => isOnline.value,
        (val) => {
          if (val) {
            ElMessage({
              type: "success",
              message: "云端连接成功!"
            })
          } else {
            ElMessage({
              type: "error",
              message: "云端连接失败!将获取不到实时订单!!!"
            })
          }
        },
        {deep: true}
    )
    const online = () => {
      dozis.post(Dozurl.online, store.state.user).then((res) => {
        isOnline.value = true;
        if (res) {
          ElNotification({
            title: '新的订单消息!',
            message: '您有新的订单!请前往订单页面!!',
            duration: 0,
          })
          store.state.refresh = true;
        }
      }).catch(() => {
        isOnline.value = false;
      })
    }
    setInterval(online,5000);
    return {
      isOnline,online
    }
  }
})
</script>

<style lang="scss" scoped>
@import "static/scss/index.scss";
#cloud {
  height: 100%;
  @include cent;

  .Online_icon {
    position: relative;
    top: 6px;
  }
}
</style>