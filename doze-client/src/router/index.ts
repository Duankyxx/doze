import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/home',
    name: 'home',
    component: () => import("@/views/home.vue"),
  },
  {
    path: '/',
    name: 'jump',
    component: () => import("@/views/notFound.vue"),
  },
  {
    path: '/notFound',
    name: 'notFound',
    component: () => import("@/views/notFound.vue"),
  },
  {
    path: '/sub',
    name: 'mySub',
    component: () => import("@/views/sub.vue"),
  },
  {
    path: '/wait',
    name: 'wait',
    component: () => import("@/views/wait.vue"),
  },
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  next();
})

export default router
