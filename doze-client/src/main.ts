import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import NutUI from "@nutui/nutui";
import "@nutui/nutui/dist/style.css";

createApp(App).use(NutUI).use(store).use(router).mount('#app')
