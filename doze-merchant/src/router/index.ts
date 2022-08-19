import {createRouter, createWebHistory, Router, RouteRecordRaw} from 'vue-router'
import store from "@/store";

const routes: Array<RouteRecordRaw> = [
  {
    path: '/login',
    name: 'login',
    component: () => import("@/views/login/login.vue"),
  },
  {
    path: '/home',
    name: 'home',
    component: () => import("@/views/Home/home.vue"),
    children: [
        //首页
      {
        path: '/home/',
        name: "first",
        component: () => import("@/views/Fun/first.vue"),
      },
        //管理面板
      {
        path: '/home/panel',
        name: "panel",
        component: () => import("@/views/Fun/panel.vue"),
      },
        //菜谱管理
      {
        path: '/home/recipeManagement',
        name: "recipeManagement",
        component: () => import("@/views/Fun/recipeManagement.vue"),
      },
        //订单管理
      {
        path: '/home/orderManagement',
        name: "orderManagement",
        component: () => import("@/views/Fun/orderManagement.vue"),
      },
        //历史订单
      {
        path: '/home/orderHistory',
        name: "orderHistory",
        component: () => import("@/views/Fun/orderHistory.vue"),
      },
        //商铺管理
      {
        path: '/home/shopManagement',
        name: "shopManagement",
        component: () => import("@/views/Fun/shopManagement.vue"),
      },
        //二维码
      {
        path: '/home/generateQR',
        name: "generateQR",
        component: () => import("@/views/Fun/generateQR.vue"),
      },
    ],
  }
]

const router: Router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to,from,next) => {
  if (to.path !== "/login") {
    if (store.state.user.id === 0) {
      next("/login");
    }
  }
  next();
})

export default router
