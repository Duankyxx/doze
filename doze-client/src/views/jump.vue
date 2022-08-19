<template>

</template>

<script lang="ts">
import { defineComponent } from "vue";
import router from "@/router";

export default defineComponent({
  name: "jump",
  setup() {
    router.push("/notFound");
  }
})
</script>

<style scoped>

</style>