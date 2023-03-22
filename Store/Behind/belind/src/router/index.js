import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)


import Login from "@/shopping/Login.vue";
import TestElementUI from "@/shopping/TestElementUI.vue";
import Home from "@/shopping/Home.vue";

const routes = [
  //我们在这里配置自己的路由规则
  {path:"/",name:"Login",component:Login},
  {path: "/TestElementUI",name: "TestElementUI",component: TestElementUI},
  {path: "/Home",name:"Home",component: Home}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
