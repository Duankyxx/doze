<template>
  <div id="bgg">
    <div id="bgg_input">
      <el-tooltip :visible="username_v" effect="light" placement="top">
        <template #content>
          <span>请输入3~12位数字、英文、下划线</span>
        </template>
        <el-input v-model="username" class="bgg_input_s" placeholder="请输入账号" @mouseenter="username_v = true" @mouseleave="username_v = false"></el-input>
      </el-tooltip>
      <el-tooltip :visible="password_v" effect="light" placement="left">
        <template #content>
          <span>请输入6~12位字母、数字</span>
        </template>
        <el-input v-model="password" class="bgg_input_s" placeholder="请输入密码" @mouseenter="password_v = true" @mouseleave="password_v = false"></el-input>
      </el-tooltip>
      <el-tooltip :visible="passwords_v" effect="light" placement="left">
        <template #content>
          <span>请和上边密码填写一致<br/>请输入6~12位字母、数字</span>
        </template>
        <el-input v-model="passwords" class="bgg_input_s" placeholder="请再次输入密码" @mouseenter="passwords_v = true" @mouseleave="passwords_v = false"></el-input>
      </el-tooltip>
    </div>
    <div id="bgg_login">
      <el-button @click="reg_btn" id="bgg_login_btn" type="primary">注册</el-button>
    </div>
    <p id="bgg_register" @click="reg">已有账号?去登陆</p>
  </div>
</template>

<script lang="ts">
import { ElMessage } from "element-plus";
import {defineComponent, ref, Ref} from "vue";
export default defineComponent({
  name: "reg",
  setup(props,context) {
    //账号密码
    let username:Ref<string> = ref("");
    let password:Ref<string> = ref("");
    let passwords:Ref<string> = ref("");
    //文本框提示
    let username_v:Ref<boolean> = ref(false);
    let password_v:Ref<boolean> = ref(false);
    let passwords_v:Ref<boolean> = ref(false);
    //注册按钮
    const reg = (): void => {
      context.emit("toReg",false);
    }
    //注册
    const reg_btn = (): void => {
      //检查是否合规
      if (!(/^[0-9a-zA-Z_]{3,12}$/.test(username.value))) {
        ElMessage({
          type: "warning",
          message: "请检查您输入的账号是否符合要求!",
        });
        return;
      }
      if (!/^[0-9a-zA-Z]{6,12}$/.test(password.value)) {
        ElMessage({
          type: "warning",
          message: "请检查您输入的密码是否符合要求!",
        });
        return;
      }
      if (!(password.value === passwords.value)) {
        ElMessage({
          type: "warning",
          message: "俩次密码不一致!",
        });
        return;
      }
      //发送ajax
    }
    return {
      username,password,passwords,
      username_v,password_v,passwords_v,
      reg,reg_btn
    }
  }
});
</script>

<style lang="scss" scoped>
@import "../../../static/scss/index.scss";
#bgg {
  width: 780px;
  height: 450px;
  background-color: rgba(0, 0, 0, 0.3);
  border-radius: $my-round_16;
  position: absolute;
  top: 250px;
  right: 100px;
  padding: 10px;

  #bgg_input{
    width: 600px;
    height: 260px;
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