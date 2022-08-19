<template>
  <div id="cont">
    <el-menu :collapse="cle">
      <el-menu-item ref="menuItem" id="menuItem" v-for="(i, index) in data" :key="i.id" :index="`'${index}'`" @click="to_page(i.menuLink)">
        <my-icon :src="i.icon"></my-icon>
        <span v-show="!cle_span">{{i.menuName}}</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script lang="ts">
import {defineComponent, ref, Ref, watch} from "vue";
import MyIcon from "@/components/myIcon.vue";
import Menu from "@/pojo/Menu";
import router from "@/router";
import store from "@/store";
import dozis from "@/dozis/dozis";
import Dozurl from "@/dozis/dozurl";

export default defineComponent({
  name: "myMenu",
  components: {
    MyIcon,
  },
  setup() {
    //数据
    let data: Ref<Menu[] | null> = ref(null);
    //控制菜单缩放
    let cle_span: Ref<boolean> = ref(false);
    let cle: Ref<boolean> = ref(false);
    //处理页面响应式布局
    watch(
        () => store.state.sm,
        (val) => {
          cle.value = val;
          cle_span.value = val;
        },
        {immediate: true}
    );
    //获取数据
    dozis.post(Dozurl.getBar,{
      "userId": store.state.user.id,
      "username": store.state.user.username,
      "level": store.state.user.level,
    },"导航栏请求失败,请检查网络!").then((res: Menu[]) => {
      data.value = {...res};
    });
    //跳转对应页面
    const to_page = (menu_link: string): void => {
      router.push(menu_link);
    }
    return {
      cle,cle_span,data,
      to_page,
    }
  }
});
</script>

<style lang="scss" scoped>
@import "static/scss/index.scss";
#cont {
  width: 100%;
  height: 100%;

  #menuItem {
    padding-left: 12px;
    padding-right: 12px;

    span {
      width: 100%;
      text-align: center;
      font-size: 20px;
      color: $my-black;
    }
  }
}
</style>