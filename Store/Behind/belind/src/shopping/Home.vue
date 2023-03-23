<template>
  <el-container class="home-container">

    <el-header>
      <!--头部标题-->
      <div>
        <img src="../assets/logo.png" />
        <span>后台管理系统</span>
        <span>  欢迎您:{{currentUserName}}</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>

    <!--具体内容-->
    <el-container>

      <!--左侧菜单-->
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" @click="toggleClapse">||||</div>
        <el-menu
            background-color="#333744"
            text-color="#fff"
            active-text-color="#ffd04b"
            router
            unique-opened
            :collapse="isCollapse"
            :collapse-transition="false"
        >
          <el-submenu v-for="item in menuList" :index="item.id + ''"  :key="item.id">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>{{item.name}}</span>
            </template>
            <el-menu-item
                v-for="subMenu in item.children"
                :index="subMenu.url+''"
                :key="subMenu.id"
            >
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>{{subMenu.name}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

      <!--右侧内容-->
      <el-main>
        <router-view v-if="isRouterAlive" />
      </el-main>

    </el-container>


  </el-container>
</template>
<script>
import login from "@/shopping/Login.vue";

export default {
  name: "Home",
  data() {
    return {
      //左侧菜单列表
      menuList: [
        {
          id: 1,
          name: "用户管理~~~~",
          children: [{ id: 2, name: "用户列表", url: "/employee" }]
        },
        {
          id: 4,
          name: "部门管理",
          children: [{ id: 5, name: "部门列表", url: "/department" }]
        },
        {
          id: 7,
          name: "权限管理",
          children: [{ id: 8, name: "权限列表", url: "/permission" }]
        },
        {
          id: 10,
          name: "角色管理",
          children: [{ id: 11, name: "角色列表", url: "/sysrole" }]
        },
        {
          id: 20,
          name: "课程管理",
          children: [{ id: 21, name: "课程列表", url: "/courseList" }]
        }
      ],
      //左侧菜单是否收窄
      isCollapse: false,
      //右侧内容区路由是否激活
      isRouterAlive: true,
      currentUserName:'',
    };
  },
  methods: {
    toggleClapse(){
      this.isCollapse = !this.isCollapse;
    }
    ,
    initCurrentUserName(){
      let token = sessionStorage.getItem("token")
      let config = {  //按照文档要求,组装get请求的参数
        params:{
          token:token
        }
      }

      this.$axios.get("/manager/getCurrentUserName",config)
          .then(response => {
            console.log(response)

            let responseData = response.data;
            console.log(responseData)

            if(responseData.code == 200){
              let username = responseData.data;
              if(username){
                this.currentUserName = username;
              }else {
                this.$message({
                  message:responseData.msg,
                  type:"error",
                  duration:2000,
                })

                this.logout();
              }
            }else {
              this.$message({
                message:"登录状态异常,请重新登录",
                type:"error",
                duration:2000,
              })

              this.logout();
            }

          })

    }
    ,
    logout(){
      sessionStorage.removeItem("token")
      location.href="/"
    }

  },
  created() {
    this.initCurrentUserName();
  }
};
</script>

<style lang="less" scoped>
img {
  width: 30px;
  height: 30px;
}
.toggle-button {
  background-color: #373d48;
  font-size: 10px;
  text-align: center;
  color: #fff;
  letter-spacing: 0.2em;
  cursor: pointer;
}

.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 22px;
  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 14px;
    }
  }
}
.el-aside {
  background-color: #333744;
  .el-menu {
    border-right: 0;
  }
}
.el-main {
  background-color: #eaedf1;
}
.home-container {
  height: 100%;
}
</style>