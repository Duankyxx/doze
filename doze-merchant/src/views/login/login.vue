<template>
  <div id="bg">
    <!--横幅-->
    <div id="banner">
      <div id="logo_100"></div>
    </div>
    <!--表单-->
    <login_from @toReg="toReg" v-if="!isReg"></login_from>
    <reg @toReg="toReg" v-else-if="isReg"></reg>
  </div>
</template>

<script lang="ts">
import {defineComponent, ref, Ref} from "vue";
import Login_from from "@/views/login/login_from.vue";
import Reg from "@/views/login/reg.vue";

export default defineComponent({
  name: "login",
  components: {
    Reg,
    Login_from,
  },
  setup() {
    //是否加载注册组件s
    let isReg: Ref<boolean> = ref(false);
    //接收组件方法
    const toReg = (is_reg: boolean) => {
      isReg.value = is_reg;
    }
    return {
      isReg,
      toReg,
    }
  }
});
</script>

<style lang="scss" scoped>
@import "../../../static/scss/index.scss";
#bg {
  @include full-screen;
  background-image: url("../../../static/image/background_picture.jpg");

  #banner {
    height: 100px;
    width: 100%;
    background-color: rgba(0, 232, 253, 0.3);
    border-bottom-left-radius: $my-round_16;
    border-bottom-right-radius: $my-round_16;

    #logo_100 {
      margin-left: 100px;
      margin-right: 100px;
      @include logo;
      background-image: url("../../../static/image/logo_transparent_100_206.png");
    }
  }
}
</style>