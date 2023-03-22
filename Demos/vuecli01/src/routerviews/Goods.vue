<template>
  <div>关山难越 谁悲失路之人
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