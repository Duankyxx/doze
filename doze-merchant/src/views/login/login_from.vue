<template>
  <div id="bgg">
    <div id="bgg_input">
      <el-input v-model="username" class="bgg_input_s" placeholder="请输入账号"></el-input>
      <el-input v-model="password" class="bgg_input_s" placeholder="请输入密码"></el-input>
    </div>
    <div id="bgg_login">
      <el-button @click="login" id="bgg_login_btn" type="primary">登录</el-button>
    </div>
    <p id="bgg_register" @click="reg">没有账号?注册</p>
  </div>
</template>

<script lang="ts">
import {defineComponent, Ref, ref} from "vue";
import dozis from "@/dozis/dozis";
import Dozurl from "@/dozis/dozurl";
import router from "@/router";
import { ElMessage } from "element-plus";
import store from "@/store";
import User from "@/pojo/User";

export default defineComponent({
  name: "login_from",
  setup(props,context) {
    let username: Ref<string> = ref("");
    let password: Ref<string> = ref("");
    //登录
    const login = (): void => {
      let uname: string = username.value;
      let pass: string = password.value;
      dozis.post(Dozurl.login,{
        "username": uname,
        "password": pass
      },"请求失败，请检查网络!").then((res) => {
        if (res.id === undefined) {
          ElMessage({
            type: "warning",
            message: "账号或密码错误!"
          });
          return;
        }
        store.state.user = {...res};
        router.push("/home");
      })
    }
    //注册按钮
    const reg = (): void => {
      context.emit("toReg",true);
    }
    return {
      username,
      password,
      login,
      reg,
    }
  }
});
</script>

<style lang="scss" scoped>
@import "../../../static/scss/index.scss";
#bgg {
  width: 780px;
  height: 380px;
  background-color: rgba(0, 0, 0, 0.3);
  border-radius: $my-round_16;
  position: absolute;
  top: 300px;
  right: 100px;
  padding: 10px;

  #bgg_input{
    width: 600px;
    height: 180px;
    padding-top: 10px;
    padding-bottom: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    margin: 50px auto 0;

    .bgg_input_s {
      width: 600px;
      height: 60px;
      font-size: $my-fontSize;
      color: $my-black;
    }
  }

  #bgg_login {
    @include cent;
  }

  #bgg_login_btn {
    width: 250px;
    height: 50px;
    font-size: $my-fontSize;
  }

  #bgg_register{
    font-size: $my-fontSize;
    color: $my-blue;
    text-decoration: underline;
    text-align: right;
    position: relative;
    top: 40px;
  }
}
</style>