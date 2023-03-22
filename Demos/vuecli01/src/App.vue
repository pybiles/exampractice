<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome 22222 to Your Vue.js App"/>
    <button @click="showParentAndChild">showAppParentAndChild</button>

    <hr/>

    <button @click="toManager">toManager</button>
    <br/>


    <router-link to="/?name=urlName&pass=urlPass">主页</router-link>--
    <router-link to="/Index">主页</router-link>--
    <router-link to="/Goods/restFulName/restFulPass">货物</router-link>--
    <router-link to="/Manager">管理</router-link>--

    <!--这里显示路由对应的组件内容-->
    <router-view></router-view>

  </div>
</template>

<script>
//导入外部组件
import HelloWorld from './components/HelloWorld.vue'

export default {   //把当前文件导出为组件
  name: 'App',  //组件的名字
  components: {  //声明在template中要使用的组件
    HelloWorld
  },
  methods:{
    showParentAndChild(){
      console.log(this.$parent)
      console.log(this.$children)
    }
    ,
    toManager(){
      //this.$router.push("/Manager")

      this.$router.push({
        name:"Manager",
        params:{
          name:"pushName",
          pass:"pushPass"
        }
      })
    }
  }
}
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
