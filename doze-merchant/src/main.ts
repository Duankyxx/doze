import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/display.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import '../static/scss/index.scss'

createApp(App)
    .use(store)
    .use(router)
    .use(ElementPlus)
    .mount('#app');
