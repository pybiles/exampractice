//引入Vue
import Vue from 'vue'
//引入组件App
import App from './App.vue'

//导入另一个js文件
import './registerServiceWorker'

//简化生产环境的提示
Vue.config.productionTip = false

import router from "@/routers";
//如果文件下只有一个js文件,那具体文件名可以省略, 等价于 import router from "@/routers/index";


//创建一个Vue对象
new Vue({
  render: h => h(App),  //render 渲染  指定向vue绑定的元素中显示什么内容,这里是显示App组件
  // router:router
  router
}).$mount('#app') //和index.html中id为app的元素绑定
