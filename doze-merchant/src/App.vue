<template>
  <div id="app_s">
    <router-view></router-view>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'App',
});
</script>

<style lang="scss">
@import "static/scss/index.scss";
* {
  margin: 0;
  padding: 0;
}
#app_s {
  @include full-screen;
}
</style>
