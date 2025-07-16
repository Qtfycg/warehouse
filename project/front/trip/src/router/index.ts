import type { RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  { path: '/',redirect:'/login'},
  { path: '/login', component: () => import('@/views/user/login.vue') }
]



export default routes
