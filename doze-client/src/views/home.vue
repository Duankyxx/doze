<template>
  <div id="cont">
    <!--悬浮栏-->
    <div id="bar">
      <div id="info">
        <a>XXX冒菜</a>
        <nut-image id="shopPicture" width="100px" height="100px" radius="16px"></nut-image>
      </div>
    </div>
    <!--正文-->
    <div id="content">
      <!--列表-->
      <div id="myList">
        <div v-for="(i, index) in data" :key="index">
          <!--图片-->
          <nut-image :src="i.cookPicture" width="100px" height="100px" radius="16px"></nut-image>
          <!--文字-->
          <a>{{i.cookName}}</a>
          <span><nut-price :price="i.price" size="large" :need-symbol="true" :thousands="true" /></span>
          <div><nut-inputnumber v-model="i.buy" min="0" max="100" :readonly="true" @click="calculate"/></div>
        </div>
        <div style="width: calc(100% - 20px);height: 120px"></div>
      </div>

      <!--提交-->
      <div id="subDiv">
        <!--购物车图片-->
        <div id="image">
          <img src="data:image/jpeg;base64,UklGRgBTAABXRUJQVlA4WAoAAAAQAAAA5wMA5wMAQUxQSDEmAAANHLVt2zDW/2enT2WLiAngEdU5t1KYsjRnppG4RG/btilym23bUVXNPQxiyRiOE+cMXldyMvPNzPDDmOFkTE5mQxwwypZssTQ8zd0FH6yWNe0ZuZea+36eiJgABW3bSOMPehjeO0dEBv/5z3/+85///Oc///nPf/7zn//85z//+c9//vOf//znP//5z3/+85///Oc///nPf/7zn//85z//+c9//vOf//znP//5z3/+85///Oc///nPf/7zn//85z//+c9//vOf//znP//5z3/+85///Oc///nPf/7zn//85z//+c9//vOf//znP/8LiPWiVagYmMdeHAShJygHADFthsvn/vqZogYx5YjoggClPb/dbXf6jhMzLoQAMQ2GK5/40nw/YgIAABFFVw27WCnUy0XTYtTt7+8ftPqBHzPOhRBTXNrFb83GAo6MEMIYE6JbWrlSqc02SiUUBN3tvW7PGcYRZYyL6SvU+OrLPhw/QggjrJh6uVabXa4XCzFz2jutwWDgB1FCKZ+eQs/8LYNDBhEghJCua41qfXGpWiA87h90+gNn4PlRHHEx9YQufM2DjCMArGulWmV+uVG2KfIHvcGw3x/6fhBFYmoJPfdVDx5jRTXqpeb8XKWGgfn+wB10h15/GId0+mj+mx6MQ5VUqtVGY65UVEQS+b476PZ7fS+MpoesryowVlWzXK7MzhULBYxYEkX9XrvVd4YJm/rBr70AR443b673iWbYZrFWtixLVzOXSgy9Um40q1ZR1xXOWa/T6bT7A5dO79S+Gh2FX3p3xyhanNKYJiETRDOsar3RrJRMC2csFamWVazWyuVC0TI04GGn29pquY4vpm7IJ3U4YvCTy2UAAMFpkoS+Gziu53uuFzJq2eW5udl6oayTbKVjyzTtUqVSLtvFsiJit7O3cdDzPMqmZspfpUcIfnRNgUelNImjwHGCQc/t9t2IQLmxMjtfrtgKRggAZSYVqYZuFKuFcq1Uqxu6G/YOtg7aHT/iQgCIaRb0CTgi//UVBMcuKE3i0HMdp9Pd7XoMm7W5U3P1UlFTMEYIZSYVKYqm2SWrXqk1ZiyLDAbtnftOux9TzoUQUyn2J/0jXH9PhdFzzpIkGXr9bnt/e+jpanlmaabRKFiEKBijzKQr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4L9t/7h+yXz88t+LnovwB+S/4W4mPaf8zy4OZf8h+Zv96/////+4n+w/ZP3g/nH/d+4F+pf+y/vX5J/RD/Y/tj7y/7L/vPyj+Af9H/uf/e/0XvV/8L/kf5z3S/4b/Af9T++/8D5A/6B/WOs//t//N9gD+df4v/2+ux/7P9b8LX9e/3n7a/A5+2//w9gD//+oB1D/Fz8af1z+cPkp+p/LL9ie8P+NzICe/ITwv+Vmod+T/0bzyos/P2Uz+2+h7OkWMPwHqFfyvzhPqr03/XvsMfrz1k/Qi/Z0LDQvIt39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm35Fu/tE82/It39onm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" alt="">
        </div>
        <!--价钱显示-->
        <div id="price"><nut-price :price="totalPrice" size="large" :need-symbol="true" :thousands="true" /></div>
        <!--提交按钮-->
        <div id="subButton">
          <div>
            <nut-button type="info" @click="sub">&nbsp;&nbsp;提&nbsp;交&nbsp;&nbsp;</nut-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, Ref } from "vue";
import router from "@/router";
import axios from "axios";
import CookBook from "@/pojo/CookBook";
import store from "@/store";

export default defineComponent({
  name: "home",
  setup() {
    //测试
    // const GetRequest = () => {
    //   let url = window.location.hash.split("?")[1]; //获取url中"?"符后的字串
    //   console.log(url);
    // }
    // GetRequest();

    //获取桌号
    const getQueryString = (name: string) => {
      let reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
      let search = window.location.hash.split("?")[1];
      let r = search.match(reg);
      if(r!=null)return  unescape(r[2]); return null;
    }
    let Number = getQueryString("Number");
    let arr: Array<string>;
    let data: Ref<Array<CookBook>> = ref([{
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
    try {
      arr = Number!.split(",");
      if(arr.length <2) {
        router.push("/notFound");
      }
      let tableNumber = arr[1];
      let uid = arr[0];
      store.state.seatNumber = tableNumber;
      store.state.uid = uid;
      //获取菜单
      axios.post("http://120.25.127.13:5080/doze_api/getCookBook",{
        "id" : uid
      }).then((res) => {
        for (const element of res.data) {
          element.buy = 0;
        }
        data.value = [...res.data]
      })
    } catch (e) {
      router.push("/notFound");
    }
    //总价钱
    let totalPrice: Ref<number> = ref(0);
    //计算
    const calculate = (): void => {
      totalPrice.value = 0;
      for (let i of data.value) {
        totalPrice.value += i.price * i.buy;
      }
    }
    //提交
    const sub = (): void => {
      store.state.data = [...data.value];
      router.push("/sub");
    }

    return {
      data,totalPrice,
      calculate,sub
    }
  }
})
</script>

<style lang="scss" scoped>
@import "@/scss/index.scss";

#cont {
  @include full-screen;
  background-color: $my-white;

  #content {
    background-color: #fff;
    height: calc(100% - 155px);
    overflow-y: auto;

      #myList {

      &>div {
        position: relative;
        width: calc(100% - 20px);
        height: 100px;
        margin: 10px;

        &>a {
          position: absolute;
          top: 15px;
          left: 120px;
          font-weight: bold;
          font-size: $my-fontSize_18;
        }

        &>span {
          position: absolute;
          top: 65px;
          left: 120px;
        }

        &>div {
          position: absolute;
          top: 75px;
          left: 260px;
        }
      }
    }

    #subDiv {
      position: fixed;
      bottom: 30px;
      left: 0px;
      margin-left: 5%;
      margin-right: 5%;
      padding-left: 30px;
      padding-right: 30px;
      width: calc(90% - 60px);
      height: 65px;
      border-radius: $my-round_50;
      background-color: $my-grid;

      &>div {
        display: inline-block;
      }

      #subButton {
        position: absolute;
        right: 10px;
        height: 65px;
        &>div {
          width: 100%;
          height: 100%;
          display: flex;
          justify-content: center;
          align-items: center;
        }
      }

      #price {
        position: relative;
        left: 10px;
        bottom: 10px;
      }

      #image {
        width: 65px;
        height: 65px;
        overflow: hidden;

        &>img {
          position: relative;
          top: -40px;
          left: -32.5px;
          width: 130px;
          height: 130px;
        }
      }
    }
  }

  #bar {
    width: calc(100% - 20px);
    height: 120px;
    padding: 10px;
    background-color: #fff;
    margin-bottom: 15px;
    border-bottom-right-radius: 30px;
    border-bottom-left-radius: 30px;

    #details {
      font-size: $my-fontSize_18;
      font-weight: bold;

      &>a {
        font-size: $my-fontSize_14;
      }
    }

    #info {
      font-weight: bold;
      margin: 10px;
      display: flex;
      justify-content: space-around;
      align-items: center;
    }

    &>div>a {
      font-size: $my-fontSize;
    }

    #shopPicture{
      display: inline-block;
    }
  }
}
</style>