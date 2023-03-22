<template>

  <div>
    Gross Tea Croup Character
    <br/>
    <h2>路由参数 {{this.$route.params.name}} --- {{this.$route.params.pass}}</h2>

    <br/>
    <router-link to="/Food">搞吃的</router-link>--
    <router-link to="/Arms">吃饱了打架</router-link>

    <router-view></router-view>

  </div>

</template>

<script>
export default {
  name: "Goods"
}
</script>

<style scoped>

</style>