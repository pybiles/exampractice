import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'

Vue.config.productionTip = false
//导入ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
//引入全局CSS
import './assets/css/global.css'
import './assets/css/reset.css'
//导入axios
import axios from "axios";
//绑定地址前缀
axios.defaults.baseURL="http://localhost:9191/"
//绑定到vue上
Vue.prototype.$axios=axios;
//配置axios
axios.interceptors.request.use((config)=>{

    console.log(config)

    //尝试将本地token加入到请求头中
    let token = sessionStorage.getItem("token");
    if(token){
        config.headers['token']=token;
    }

    return config
})

axios.interceptors.response.use((config)=>{

    console.log(config)

    //如果响应值是"toLogin",那就直接到登录页去
    if(config.data == 'toLogin'){
        sessionStorage.removeItem("token")
        location.href="/Login"
        return
    }

    return config
})


new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
