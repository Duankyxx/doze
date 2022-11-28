<template>
  <div id="cont">
    <!--设置-->
    <div id="setting">
      <div><a>餐桌数量:</a></div>
      <div><el-input-number v-model="number" :min="1"></el-input-number></div>
      <div><el-button type="primary" @click="getQR">生成餐桌二维码</el-button></div>
    </div>
    <div id="qrDiv">
      <div v-for="(i, index) in qr" :key="index">
        <img :src="i" alt="二维码生成失败">
        <p>餐桌号:<a>{{index+1}}</a></p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, ref, Ref} from "vue";
import Dozurl from "@/dozis/dozurl";
import store from "@/store";

export default defineComponent({
  name: "generateQR",
  setup() {
    //餐桌数量
    let number: Ref<number> = ref(1);
    //商家连接
    let url = `${Dozurl.Merchant}/%23/home?Number=${store.state.user.id},`;

    //获取二维码
    let qr: Ref<Array<string>> = ref([]);
    const getQR = () => {
      let i = 1;
      while (i<=number.value) {
        //组合url
        qr.value[i-1] = `https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=${url + i}`;
        i++;
      }
    }

    return {
      number,qr,
      getQR
    }
  }
})
</script>

<style lang="scss" scoped>
@import "static/scss/index.scss";
#cont {
  width: calc(100% - 20px);
  height: calc(100vh - 140px);
  background-color: $my-white;
  border-radius: $my-round_12;
  padding: 10px;
  overflow: auto;

  #setting {
    width: calc(100% - 30px);
    height:35px;
    padding: 15px;
    border-radius: $my-round_12;
    background-color: #fff;

    &>div {
      display: inline-block;
      margin-left: 30px;
      margin-right: 30px;
    }
  }

  #qrDiv {
    margin-top: 20px;
    width: calc(100% - 40px);
    border-radius: $my-round_12;
    padding: 20px;
    display: flex;
    flex-flow: row;
    flex-wrap: wrap;
    justify-content: space-around;

    &>div {
      margin-top: 20px;
      border-radius: $my-round_12;
      width: 300px;
      height: 340px;
      padding: 20px;
      background-color: #fff;

      &>p {
        font-size: 27px;
        font-weight: bold;
        text-align: center;
        color: $my-black;

        &>a {
          color: #409EFF;
        }
      }
    }
  }
}
</style>