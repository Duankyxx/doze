<template>
  <div id="contX">
    <div id="cont">
      <div>
        <!--我的表格-->
        <div>
          <el-table :data="data" v-loading="loading">
            <el-table-column align="center" type="selection"></el-table-column>
            <el-table-column align="center" type="expand">
              <!--展开行-->
              <template #default="props">
                <div id="expand">
                  <table>
                    <tr>
                      <td>单号:</td>
                      <td>{{props.row.id}}</td>
                    </tr>
                    <tr>
                      <td>餐桌号:</td>
                      <td>{{props.row.seatNumber}}</td>
                    </tr>
                    <tr>
                      <td>下单时间:</td>
                      <td>{{props.row.time}}</td>
                    </tr>
                    <tr>
                      <td>备注:</td>
                      <td>{{props.row.remark}}</td>
                    </tr>
                  </table>
                  <!--信息-->
                  <h2 style="margin-bottom: 20px">菜单</h2>
                  <el-table :data="props.row.receipts" :border="true" :show-summary="true" :summary-method="getSummaries" sum-text="合计">
                    <el-table-column align="center" label="名称" prop="cookName"></el-table-column>
                    <el-table-column align="center" label="数量" prop="quantity"></el-table-column>
                    <el-table-column align="center" label="单价" prop="price"></el-table-column>
                  </el-table>
                </div>
              </template>
            </el-table-column>
            <el-table-column align="center" label="单号" prop="id"></el-table-column>
            <el-table-column align="center" label="餐桌号" prop="seatNumber"></el-table-column>
            <el-table-column align="center" label="下单时间" prop="time"></el-table-column>
          </el-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref, Ref} from "vue";
import store from "@/store";
import Order from "@/pojo/Order";
import dozis from "@/dozis/dozis";
import Dozurl from "@/dozis/dozurl";

export default defineComponent({
  name: "orderHistory",
  setup() {
    //模拟数据
    let data: Ref<Order[]> = ref([
      {
        "id": 0,
        "seatNumber": 0,
        "time": "",
        "payment": 0,
        "remarks": null,
        "uid": 0,
        "receipts": [
          {
            "id": 0,
            "cookName": "测试数据",
            "quantity": 0,
            "price": 0,
            "serialNumber": 0,
          }
        ],
      }
    ]);
    //数据加载图层
    let loading: Ref<boolean> = ref(false);
    //获取数据
    const getOrder = async () => {
      loading.value = true;
      data.value = await dozis.post(Dozurl.getHistoryOrderOrder,{"id": store.state.user.id});
      loading.value = false;
    }

    //合计
    const getSummaries = (param: any) => {
      const { columns, data } = param;
      let sums: Array<string> = ["合计", "", ""];
      let total: number = 0;
      let totalMoney: number = 0;
      data.forEach((i: any, index: number) => {
        total += i.quantity;
        totalMoney += i.quantity*i.price;
      });
      sums[1] = total+"";
      sums[2] = totalMoney+"";
      return sums;
    }

    onMounted(() => {
      getOrder();
    })
    return {
      data,loading,
      getOrder,getSummaries
    }
  }
});
</script>

<style lang="scss" scoped>
@import "static/scss/index.scss";
#contX {
  height: calc(100% - 10px);
  #cont {
    @include my-cont;
    overflow-y: auto;


    #expand {
      width: 100%;
      height: 100%;
      padding: 20px;


      tr>td {
        font-size: $my-fontSize_18;
        color: $my-black;
      }
    }
  }
}
</style>