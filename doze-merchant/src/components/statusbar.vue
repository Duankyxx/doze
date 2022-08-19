<template>
    <el-row id="cont">
      <!--logo-->
      <el-col :xl="6" :lg="8" :md="10" :sm="11" class="hidden-xs-only">
        <div id="logo_div">
          <div id="logo_80"></div>
        </div>
      </el-col>
      <!--云连接信息-->
      <el-col :xl="18" :lg="16" :md="14" :sm="13" :xs="24">
        <cloud></cloud>
      </el-col>
    </el-row>
</template>

<script>
import { defineComponent } from "vue";
import Cloud from "@/components/cloud";
export default defineComponent({
  name: "statusbar",
  components: {Cloud},
});
</script>

<style lang="scss" scoped>
@import "static/scss/index.scss";
#cont {
  width: 100%;
  height: 100%;
  background-color: $my-white;

  #logo_div {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;

    #logo_80 {
      width: 196px;
      height: 80px;
      background-image: url("../../static/image/logo_transparent_80_196.png");
    }
  }
}
</style>