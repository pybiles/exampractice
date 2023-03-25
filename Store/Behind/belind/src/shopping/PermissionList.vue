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
            <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
          </el-input>
        </el-col>

        <!--添加权限按钮-->
        <el-col :span="4">
          <el-button type="primary" @click="showAddForm">添加权限</el-button>
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
            <!--scope.row代表当前行数据-->
            <el-button type="warning" size="mini" @click.prevent="showUpdateForm(scope.row)" >修改</el-button>
            <el-button type="danger" size="mini" @click.prevent="del(scope.row.id)" >删除</el-button>
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




    <!--添加权限对话框-->
    <el-dialog title="添加权限" :visible.sync="addFormVisible" @close="closeAddForm" :close-on-click-modal="false">

      <!--具体表单-->
      <el-form ref="addForm" :rules="addPermissionRules" :model="addPermission" label-width="150px" size="mini">

        <el-form-item label="权限名称" prop="name">
          <el-input v-model="addPermission.name"></el-input>
        </el-form-item>

        <el-form-item label="权限URL" prop="url">
          <el-input v-model="addPermission.url"></el-input>
        </el-form-item>

        <el-form-item label="权限类型" prop="urlType">
          <el-select v-model="addPermission.urlType" placeholder="请选择权限类型">
            <el-option label="菜单权限" :value="1"></el-option>
            <el-option label="接口权限" :value="-1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="父菜单" prop="parentId">
          <el-select v-model="addPermission.parentId"  placeholder="请选择父菜单">
            <el-option label="一级菜单" :value="-1"></el-option>
            <el-option v-for="p in parentMenus" :label="p.name" :value="p.id"></el-option>
          </el-select>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button  @click="closeAddForm">取 消</el-button>
        <el-button type="primary" @click="submitAddForm">确 定</el-button>
      </div>

    </el-dialog>


    <!--修改权限对话框-->
    <el-dialog title="修改权限" :visible.sync="updateFormVisible" @close="closeUpdateForm" :close-on-click-modal="false">

      <!--具体表单-->
      <el-form ref="updateForm" :rules="updatePermissionRules" :model="updatePermission" label-width="150px" size="mini">

        <el-form-item label="权限名称" prop="name">
          <el-input v-model="updatePermission.name"></el-input>
        </el-form-item>

        <el-form-item label="权限URL" prop="url">
          <el-input v-model="updatePermission.url"></el-input>
        </el-form-item>

        <el-form-item label="权限类型" prop="urlType">
          <el-select v-model="updatePermission.urlType" placeholder="请选择权限类型">
            <el-option label="菜单权限" :value="1"></el-option>
            <el-option label="接口权限" :value="-1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="父菜单" prop="parentId">
          <el-select v-model="updatePermission.parentId"  placeholder="请选择父菜单">
            <el-option label="一级菜单" :value="-1"></el-option>
            <el-option v-for="p in parentMenus" :label="p.name" :value="p.id"></el-option>
          </el-select>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button  @click="closeUpdateForm">取 消</el-button>
        <el-button type="primary" @click="submitupdateForm">确 定</el-button>
      </div>

    </el-dialog>




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
      total:0,
      //添加权限相关
      addFormVisible:false,
      addPermissionRules:{
        name:[
          {required:true,message:"名称不能为空",trigger:"blur"}
        ],
        urlType:[
          {required:true,message:"请选择菜单类型",trigger:"change"}
        ],
        parentId:[
          {required:true,message:"请选择父菜单",trigger:"change"}
        ]
      },
      addPermission:{
        name:'',
        url:'',
        urlType:1,
        parentId:-1
      },
      parentMenus:[],
      //修改权限相关
      updateFormVisible:false,
      updatePermissionRules:{
        name:[
          {required:true,message:"名称不能为空",trigger:"blur"}
        ],
        urlType:[
          {required:true,message:"请选择菜单类型",trigger:"change"}
        ],
        parentId:[
          {required:true,message:"请选择父菜单",trigger:"change"}
        ]
      },
      updatePermission:{
        id:0,
        name:'',
        url:'',
        urlType:1,
        parentId:-1
      },


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
    ,
    search(){
      this.currentPage = 1;
      this.initPermissionList();
    }
    //新增权限相关
    ,
    showAddForm(){
      this.addFormVisible=true;
      this.refreshParentMenu();
    }
    ,
    closeAddForm(){
      this.addFormVisible=false;
      this.$refs.addForm.resetFields(); //重置表单
    }
    ,
    submitAddForm(){

      console.log(this.addPermission)

      this.$refs.addForm.validate((isValid)=>{

        if (!isValid){
          this.$message({
            message:"请正确填写表单" ,
            type:'error',
            duration:2000
          })
          return;
        }

        //实现新增
        this.$axios.postForm("/urlPermission/add",this.addPermission)
            .then(response => {
              let responseData = response.data;

              if (responseData.code == 200){
                this.$message({
                  message:"新增成功",
                  type:"success",
                  duration:2000,
                })

                this.addFormVisible=false;
                this.initPermissionList();
              }else {
                this.$message({
                  message:responseData.msg,
                  type:"error",
                  duration:2000,
                })
              }

            })

      })

    }
    ,
    refreshParentMenu(){
      this.$axios.get("/urlPermission/all")
          .then(reponse=>{

            let responseData = reponse.data;
            this.parentMenus = responseData.data;

          })
    }
    ,
    //修改权限相关
    showUpdateForm(urlPermission){
      console.log(urlPermission)

      this.updatePermission.id = urlPermission.id;
      this.updatePermission.name = urlPermission.name;
      this.updatePermission.url = urlPermission.url;
      this.updatePermission.urlType = parseInt(urlPermission.urlType);
      this.updatePermission.parentId = urlPermission.parentId;

      this.refreshParentMenu();
      this.updateFormVisible=true;
    }
    ,
    closeUpdateForm(){
      this.updateFormVisible=false;
      this.$refs.updateForm.resetFields();
    }
    ,
    submitupdateForm(){

      this.$refs.updateForm.validate((isValid)=>{

        if (!isValid){
          this.$message({
            message:"请正确填写表单" ,
            type:'error',
            duration:2000
          })
          return;
        }

        console.log(this.updatePermission)
        //实现数据修改
        this.$axios.post("/urlPermission/update",this.updatePermission)
            .then(reponse => {
              let responseData  = reponse.data;

              if (responseData.code == 200){
                this.$message({
                  message:"修改成功",
                  type:"success",
                  duration:2000,
                })

                this.updateFormVisible=false;
                this.initPermissionList();
              }else {
                this.$message({
                  message:responseData.msg,
                  type:"error",
                  duration:2000,
                })
              }


            })

        this.updateFormVisible=false;
      })

    }
    ,
    del(id){

      this.$confirm("此操作将永久删除该权限及其子权限,是否继续?","警告", {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then( ()=>{   //点击确定对应的操作

        //执行删除
        this.$axios.delete("/urlPermission/delete/"+id)
            .then(reponse => {
              let responseData  = reponse.data;

              if (responseData.code == 200){
                this.$message({
                  message:"删除成功",
                  type:"success",
                  duration:2000,
                })

                this.initPermissionList();
              }else {
                this.$message({
                  message:responseData.msg,
                  type:"error",
                  duration:2000,
                })
              }

            })


      } ).catch( ()=>{  //点击取消对应的操作

        this.$message({
          message:"已取消删除",
          type:"success",
          duration:2000,
        })

      } )



    }


  },
  created() {
    this.initPermissionList()
  }

}
</script>

<style scoped>

</style>