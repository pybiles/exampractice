//引入Vue
import Vue from 'vue'
//引入组件App
import App from './App.vue'

//导入另一个js文件
import './registerServiceWorker'

//简化生产环境的提示
Vue.config.productionTip = false


//--------路由部分---------------------
//引入路由插件
import VueRouter from "vue-router";
Vue.use(VueRouter)

import Goods from "@/routerviews/Goods.vue";
import Index from "@/routerviews/Index.vue";
import Manager from "@/routerviews/Manager.vue";

//配置路由路径与组件的匹配关系
let router = new VueRouter({
  routes:[
    {path:"/",name:"Index",component:Index},
    {path:"/Index",name:"Index",component:Index},
    {path:"/Goods",name:"Goods",component:Goods},
    {path:"/Manager",name:"Manager",component:Manager},

  ],
  mode:"history"
})




//创建一个Vue对象
new Vue({
  render: h => h(App),  //render 渲染  指定向vue绑定的元素中显示什么内容,这里是显示App组件
  // router:router
  router
}).$mount('#app') //和index.html中id为app的元素绑定
