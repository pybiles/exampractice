import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)


import Login from "@/shopping/Login.vue";

const routes = [
  //我们在这里配置自己的路由规则
  {path:"/",name:"Login",component:Login},

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
