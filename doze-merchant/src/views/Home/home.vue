<template>
  <!--主页结构设计-->
  <div>
    <el-container class="cont">
      <!--状态栏-->
      <el-header height="80px" style="padding: 0" class="test">
        <statusbar></statusbar>
      </el-header>
      <el-container>
        <!--菜单-->
        <el-aside :width="asideW" class="test" id="aside">
          <my-menu></my-menu>
        </el-aside>
        <!--主面板-->
        <el-main class="test" id="main">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref, Ref, UnwrapRef, watch} from "vue";
import myMenu from "@/components/myMenu.vue";
import Statusbar from "@/components/statusbar.vue";
import store from "@/store";

export default defineComponent({
  name: "home",
  components: {Statusbar, myMenu},
  setup() {
    //菜单宽度
    let asideW: Ref<string> = ref("280px");
    //页面宽度
    let Hw: Ref<number> = ref(window.innerWidth);
    onMounted(() => {
      window.addEventListener('resize', function() {
        Hw.value = window.innerWidth;
      });
    });
    //监听页面宽度
    watch(
        () => Hw.value,
        (val) => {
          store.state.sm = val <= 1080;
          store.state.sm ? asideW.value="84px" : asideW.value="280px";
        },
        {immediate: true}
    );

    return {
      asideW,
    }
  }
});
</script>

<style lang="scss" scoped>
@import "static/scss/index.scss";
.cont {
  @include full-screen;
}
.test {
  //border: 1px solid red;
}
#main {
  ;
}
#aside {
  padding: 10px;
  background-color: $my-white;
  transition: 500ms;
}
</style>