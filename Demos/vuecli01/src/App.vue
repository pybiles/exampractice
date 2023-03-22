<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <hr/>

    <router-link to="/">主页</router-link>--
    <router-link to="/Index">Index</router-link>--
    <router-link to="/Goods">Goods</router-link>--
    <router-link to="/Manager">Manager</router-link>--

    <!--这里显示路由对应的组件内容-->
    <router-view></router-view>

  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
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
