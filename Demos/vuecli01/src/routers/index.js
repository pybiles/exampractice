import Vue from 'vue'

//--------路由部分---------------------
//引入路由插件
import VueRouter from "vue-router";
Vue.use(VueRouter)

import Goods from "@/routerviews/Goods.vue";
import Index from "@/routerviews/Index.vue";
import Manager from "@/routerviews/Manager.vue";
import Food from "@/routerviews/goods/Food.vue";
import Arms from "@/routerviews/goods/Arms.vue";

//配置路由路径与组件的匹配关系
let router = new VueRouter({
    routes:[
        {path:"/",name:"Index",component:Index},
        {path:"/Index",name:"Index",component:Index},
        // {path:"/Goods",name:"Goods",component:Goods,
        {path:"/Goods/:name/:pass",name:"Goods",component:Goods,
            children:[
                {path:"/Food",name:"Food",component:Food},
                {path:"/Arms",name:"Arms",component:Arms},
            ]
        },
        {path:"/Manager",name:"Manager",component:Manager},

    ],
    mode:"history"
})

//导出
export default router;
