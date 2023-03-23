<template>
  <div>

    <!--面包屑导航-->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>权限列表</el-breadcrumb-item>
    </el-breadcrumb>


    <el-card>
      <el-row :gutter="20">

        <!--权限搜索框-->
        <el-col :span="8">
          <el-input placeholder="请输权限名称" clearable v-model="keyword" >
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>

        <!--添加权限按钮-->
        <el-col :span="4">
          <el-button type="primary">添加权限</el-button>
        </el-col>

      </el-row>

      <!--显示权限搜索结果项列表-->
      <el-table :data="permissionList" border stripe>
        <el-table-column label="#" type="index"></el-table-column>
        <el-table-column label="权限名称" prop="name"></el-table-column>
        <el-table-column label="权限路径" prop="url"></el-table-column>
        <el-table-column label="权限类型">
          <template slot-scope="sc">{{sc.row.urlType==1 ? '菜单权限':'接口权限'}}</template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="warning" size="mini" >修改</el-button>
            <el-button type="danger" size="mini" >删除</el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-card>


  </div>

</template>

<script>
export default {
  name: "PermissionList",
  data(){
    return {
      keyword:"", //要搜索的权限名称
      permissionList:[], //权限搜索结果

    };
  },
  methods:{

  },
  created() {

  }

}
</script>

<style scoped>

</style>