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


new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
