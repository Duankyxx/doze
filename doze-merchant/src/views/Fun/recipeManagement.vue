<template>
  <div id="cont">
    <div id="content" ref="cont">
      <!--我的表格-->
      <div id="tb">
        <el-table :data="dataList" :height="het">
          <el-table-column align="center" type="selection"></el-table-column>
          <el-table-column align="center" label="序号"></el-table-column>
          <el-table-column align="center" label="菜名" prop="cookName"></el-table-column>
          <el-table-column align="center" label="图片" prop="cookPicture"></el-table-column>
          <el-table-column align="center" label="价格" prop="price"></el-table-column>
          <el-table-column align="center" label="设为本店招牌" prop="isGood">
            <template #default="scope">
              <el-icon v-show="parseInt(scope.row.isGood)"><Select /></el-icon>
              <el-icon v-show="!parseInt(scope.row.isGood)"><CloseBold /></el-icon>
            </template>
          </el-table-column>
          <el-table-column align="center" label="是否启用" prop="isSell">
            <template #default="scope">
              <el-icon v-show="parseInt(scope.row.isSell)"><Select /></el-icon>
              <el-icon v-show="!parseInt(scope.row.isSell)"><CloseBold /></el-icon>
            </template>
          </el-table-column>
          <el-table-column align="center" label="商品标签" prop="cookLabel"></el-table-column>
          <el-table-column align="center" label="购买人数" prop="purchaseQuantity"></el-table-column>
          <el-table-column align="center" label="编辑">
            <template #default="scope">
              <el-button type="warning" @click="toEdit(scope.row)">编辑</el-button>
            </template>
          </el-table-column>
          <el-table-column align="center" label="删除">
            <template #default="scope">
              <el-button type="danger" @click="removeDish(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!--添加按钮-->
      <el-button type="primary" @click="addDish">添加</el-button>
    </div>

    <!--编辑-->
    <el-drawer
        :model-value="isEdit"
        :before-close="closeEdit"
        :title="title"
        :size="EditSize"
    >
      <!--编辑表单-->
      <edit-dish :data="data" :is-edit="isEdit" @closeEdit="closeEdit" @updateFlag="updateFlag"></edit-dish>
    </el-drawer>

    <!--添加-->
    <el-drawer
      :model-value="isAdd"
      :before-close="closeAdd"
      :title="title"
      :size="EditSize"
  >
    <el-dialog></el-dialog>
    <!--编辑表单-->
    <add-dish @closeAdd="closeAdd" @updateFlag="updateFlag"></add-dish>
  </el-drawer>
  </div>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref, Ref, watch} from "vue";
import { Select, CloseBold } from "@element-plus/icons-vue";
import {ElMessage, ElMessageBox} from "element-plus";
import store from "@/store";
import EditDish from "@/components/editDish.vue";
import AddDish from "@/components/addDish.vue";
import dozis from "@/dozis/dozis";
import Dozurl from "@/dozis/dozurl";

export default defineComponent({
  name: "recipeManagement",
  components: {
    AddDish,
    EditDish,
    Select,CloseBold
  },
  setup() {
    //添加
    let isAdd: Ref<boolean> = ref(false);
    const addDish = (): void => {
      //修改title
      title.value = "添加一个新的菜";
      //打开面板
      isAdd.value = true;
    }
    //关闭前的回调
    let closeFlag: boolean = false;
    const updateFlag = (): void => {
      closeFlag = true;
    }
    const closeAdd = (done: () => void): void => {
      if (closeFlag) {isAdd.value = false;return}
      closeFlag = false;
      ElMessageBox.confirm("那将前功尽弃!!!","你确定要退出编辑吗?",{
        confirmButtonText: '确定',
        cancelButtonText: '我不确定再想想',
      }).then((): void => {
        isAdd.value = false;
        done();
      });
    }

    //title
    let title: Ref<string> = ref("编辑这个菜品");
    //编辑开关
    let isEdit: Ref<boolean> = ref(false);
    //编辑页面的大小
    let EditSize: Ref<string> = ref("50%");
    //编辑方法
    let data: Ref<CookBook|null> = ref(null);
    const toEdit = (row: CookBook): void => {
      //修改title
      title.value = "编辑这个菜品";
      //传递数据
      data.value = row;
      //打开面板
      isEdit.value = true;
    }
    //关闭编辑前的回调
    const closeEdit = (done: () => void): void => {
      if (closeFlag) {isEdit.value = false;return}
      closeFlag = false;
      ElMessageBox.confirm("那将前功尽弃!!!","你确定要退出编辑吗?",{
        confirmButtonText: '确定',
        cancelButtonText: '我不确定再想想',
      }).then((): void => {
        isEdit.value = false;
        done();
      });
    }

    //移除当前菜品
    const removeDish = (cookBook: CookBook): void => {
      ElMessageBox.confirm("那将会使客户看不到这个菜!","确认删除当前菜品吗?",{
        confirmButtonText: '确定',
        cancelButtonText: '我不确定再想想',
      }).then((): void => {
        dozis.post(Dozurl.removeCookBook, cookBook, "请求失败!请检查网络!").then((res) => {
          if (res) {
            ElMessage({
              type: "success",
              message: "删除成功",
            })
          }
        })
      })
    }

    //监听页面尺寸,改变编辑页面的大小
    let cont: Ref<HTMLElement | null> = ref(null);
    let het: Ref<string> = ref("0px");
    watch(
        (): boolean => store.state.sm,
        (val): void => { val ? EditSize.value="80%" : EditSize.value="50%"; },
        {immediate: true}
    );
    onMounted(() => {
      het.value = document.body.clientHeight - 202 + "px";
      window.onresize = () => {
        het.value = document.body.clientHeight - 202 + "px";
      }
    })

    let dataList: Ref<CookBook[] | null> = ref(null);
    //获取数据
    dozis.post(Dozurl.getCookBook, store.state.user, "请求失败，请检查网络!").then((res: CookBook[]) => {
      dataList.value = [...res];
    })

    return {
      dataList,isEdit,EditSize,data,title,isAdd,cont,het,
      toEdit,closeEdit,removeDish,addDish,closeAdd,updateFlag,
    }
  }
});
</script>

<style lang="scss" scoped>
@import "static/scss/index.scss";
#cont {
  @include my-cont;
  width: calc(100% - 20px);
  height: calc(100vh - 140px);

  #content{
    //width: 100%;
    //height: 100%;

    //表格外面的div
    #tb {

    }
  }
}
</style>