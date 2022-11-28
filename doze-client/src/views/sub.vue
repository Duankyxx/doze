<template>
  <div id="container">
    <div id="barD">
      <div id="bar">生成订单...</div>
    </div>

    <div id="cont">
      <div id="list">
        <p>订单:</p>
        <div v-for="(i, index) in newData" :key="index">
          <div>{{i.cookName}}</div>
          <div>
            <div style="display: inline-block">x{{i.buy}}</div>
            <div id="price" style="display: inline-block"><a>单价:</a><nut-price :price="i.price" size="normal" :need-symbol="true" :thousands="true" /></div>
          </div>
        </div>
        <p>合计:&nbsp;&nbsp;&nbsp;&nbsp;{{totalPrice}}</p>
      </div>
    </div>

    <!--确认订单-->
    <div id="but">
      <div><nut-price :price="totalPrice" size="large" :need-symbol="true" :thousands="true" /></div>
      <div><nut-button type="info" @click="sub">确定订单</nut-button></div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import store from "@/store";
import router from "@/router";
import {Ref} from "vue";
import CookBook from "@/pojo/CookBook";
import axios from "axios";
import Order from "@/pojo/Order";
import Receipt from "@/pojo/Receipt";

export default defineComponent({
  name: "mySub",
  setup() {
    //检查合法
    if (store.state.data.length < 2) {
      router.push("/notFound");
    }


    //时间获取方法
    const getDayTimer = (): string => {
      let date: Date = new Date()
      let y: number = date.getFullYear()
      let m: number = date.getMonth() + 1
      let d: number = date.getDate()
      let hh: number = date.getHours()
      let mm: number = date.getMinutes()
      let ss: number = date.getSeconds()
      let currentdate: string = y + '-' + isDoubleDigits(m) + '-' + isDoubleDigits(d);
      let time: string = isDoubleDigits(hh) + ':' + isDoubleDigits(mm) + ':' + isDoubleDigits(ss);
      return currentdate + " " + time;
    }
    const isDoubleDigits = (i: number): string => {
      if (i < 10) {
        return "0" + i;
      }
      return i + "";
    }
    //合计
    let totalPrice: Ref<number> = ref(0);
    //处理数据
    let newData: Ref<Array<CookBook>> = ref([{
      cookLabel: "",
      cookName: "",
      cookPicture: "",
      id: 0,
      isGood: "",
      isSell: "",
      price: 0,
      purchaseQuantity: 0,
      uid: 0,
      buy: 0,
    }]);
    let j = 0;
    for (let i of store.state.data) {
      console.log(i.buy);
      if (i.buy === 0) {
        continue;
      }
      newData.value[j] = i;
      totalPrice.value += i.buy * i.price;
      j++;
    }
    console.log(newData.value);

    const sub = (): void => {
      //发送ajax
      let receipts: Array<Receipt> = [{
        "cookName": "",
        "quantity": "",
        "price": "",
      }]
      for (let i=0; i<newData.value.length; i++) {
        receipts[i] = {
          "cookName": newData.value[i].cookName,
          "quantity": newData.value[i].buy + "",
          "price": newData.value[i].price + "",
        }
      }
      let order: Order = {
        seatNumber: store.state.seatNumber,
        time: getDayTimer(),
        remarks: "",
        payment: totalPrice.value + "",
        uid: store.state.uid,
        receipts: receipts,
      }
      axios.post("http://120.25.127.13:5080/doze_api/generateOrder",order).then(res => {
        router.push("/wait");
      })
    }

    return {
      newData,totalPrice,
      sub
    }
  }
})
</script>

<style lang="scss" scoped>
@import "@/scss/index.scss";

#container {
  background-color: $my-white;
  min-height: 100vh;

  #but {
    width: calc(100% - 40px);
    height: 50px;
    padding: 10px;
    border-radius: 50px;
    margin: 10px;
    background-color: $my-grid;
    position: fixed;
    bottom: 20px;
    display: flex;
    justify-content: space-between;

    &>div {
      height: 50px;
      line-height: 50px;
      padding-left: 20px;
      padding-right: 20px;
    }
  }

  #cont {
    background-color: #fff;
    margin: 10px 10px 0;
    padding: 10px;
    border-radius: $my-round_16;
    width: calc(100% - 40px);
    min-height: 400px;

    #list {

      #price {
        position: absolute;
        right: 0;
      }

      &>p {
        font-size: 25px;
        font-weight: 600;
        color: $my-black;
        padding: 10px;
        border-bottom: 1px solid $my-black;
        margin-bottom: 10px;
      }

      &>div {
        padding: 5px;
        border-bottom: 1px dotted  $my-black;


      }

      &>div>div {
        font-size: 22px;
        color: $my-black;
        position: relative;

        &>div>a {
          font-size: 18px;
          color: $my-black;
        }
      }
    }
  }

  #barD {
    width: 100%;
    height: 60px;

    #bar {
      z-index: 999;
      font-size: 30px;
      font-weight: bold;
      color: $my-black;
      background-color: #fff;
      height: 40px;
      padding: 10px 20px;
      position: fixed;
      width: 100%;
      top: 0;
    }
  }
}
</style>