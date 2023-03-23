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
            <el-button slot="append" icon="el-icon-search" @click="initPermissionList"></el-button>
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



      <!--分页-->
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 8, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>


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
      currentPage:1,
      pageSize:5,
      total:0

    };
  },
  methods:{
    initPermissionList(){

      let params = {
        currentPage:this.currentPage,
        pageSize: this.pageSize,
        keyword: this.keyword
      }

      this.$axios.postForm("/urlPermission/pageSearch",params)
          .then(response=>{
            let responseData = response.data

            let pageData = responseData.data;

            this.currentPage = pageData.current;
            this.pageSize = pageData.size;
            this.total = pageData.total;
            this.permissionList = pageData.records;

          })

    }
    ,
    handleSizeChange(size){
      console.log(size)

      this.currentPage=1;
      this.pageSize = size;
      this.initPermissionList();
    }
    ,
    handleCurrentChange(current){
      console.log(current)

      this.currentPage = current;
      this.initPermissionList();
    }

  },
  created() {
    this.initPermissionList()
  }

}
</script>

<style scoped>

</style>