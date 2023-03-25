import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)


import Login from "@/shopping/Login.vue";
import TestElementUI from "@/shopping/TestElementUI.vue";
import Home from "@/shopping/Home.vue";
import Overview from "@/shopping/Overview.vue";
import PermissionList from "@/shopping/PermissionList.vue";
import RoleList from "@/shopping/RoleList.vue";

const routes = [
    //我们在这里配置自己的路由规则
    {path: "/", name: "Login", component: Login},
    {path: "/TestElementUI", name: "TestElementUI", component: TestElementUI},
    {path: "/Login", name: "Login", component: Login},
    {
        path: "/Home", name: "Home", component: Home,
        children: [
            {path: "/", name: "Overview", component: Overview},
            {path:"/PermissionList",name:"PermissionList",component:PermissionList},
            {path: "/RoleList",name:"RoleList",component: RoleList},
        ]
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
//路由拦截
/*
 to  目标路由对象
 from  起始路由对象
 next  执行视图切换的回调函数
      如果不传参 执行from到to
      如果传参,则到指定路由去
 */
router.beforeEach((to, from, next) => {

    console.log(from)
    console.log(to)
    console.log(next)

    //如果是到登录页,那就直接放行
    if (to.path == "/" || to.path == "/Login") {
        next();
        return;
    }

    //到其它页面需要检查是否已经登录
    let token = sessionStorage.getItem("token");
    if (token) {
        //有token,认为已经是登录状态,直接放行
        next()
    } else {
        //没有token,到登录页去
        next("/")
        //location.href = "/"
    }

})

router.afterEach((to, from) => {
    console.log(from)
    console.log(to)

})

export default router
