<template>
  <div id="myIcon">
    <img alt="" :src="src">
  </div>
</template>

<script lang="ts">
import {defineComponent, watch} from "vue";
import store from "@/store";

export default defineComponent({
  name: "myIcon",
  props: {
    src: String
  },
  setup(props,context) {
    const src = props.src;
    return {
      src,
    }
  }
});
</script>

<style lang="scss" scoped>
#myIcon {
  width: 40px;
  height: 40px;
  position: relative;

  img {
    position: absolute;
    top: 0;
    width: 40px;
    height: 40px;
  }
}
</style>