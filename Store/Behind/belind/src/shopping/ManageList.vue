<template>
  <div>

    <!--面包屑导航-->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户列表</el-breadcrumb-item>
    </el-breadcrumb>


    <el-card>
      <el-row :gutter="20">

        <!--用户搜索框-->
        <el-col :span="8">
          <el-input placeholder="请输用户名称" clearable v-model="keyword" >
            <el-button slot="append" icon="el-icon-search" @click="initManagerList"></el-button>
          </el-input>
        </el-col>

        <!--添加用户按钮-->
        <el-col :span="4">
          <el-button type="primary" @click="showAddForm">添加用户</el-button>
        </el-col>

      </el-row>

      <!--显示用户搜索结果项列表-->
      <el-table :data="managerList" border stripe>
        <el-table-column label="#" type="index"></el-table-column>
        <el-table-column label="用户名" prop="userName"></el-table-column>
        <el-table-column label="用户密码">******</el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="success" size="mini"  @click="showManagerConfigRoleForm(scope.row)">配置角色</el-button>
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
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

    </el-card>




    <!--添加用户对话框-->
    <el-dialog title="添加用户" :visible.sync="addFormVisible" @close="closeAddForm" :close-on-click-modal="false">

      <!--具体表单-->
      <el-form ref="addForm" :rules="addManagerRules" :model="addManager" label-width="150px" size="mini">

        <el-form-item label="用户名" prop="userName">
          <el-input v-model="addManager.userName"></el-input>
        </el-form-item>

        <el-form-item label="用户密码" prop="userPassword">
          <el-input v-model="addManager.userPassword"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button  @click="closeAddForm">取 消</el-button>
        <el-button type="primary" @click="submitAddForm">确 定</el-button>
      </div>

    </el-dialog>



    <!--修改用户对话框-->
    <el-dialog title="修改用户" :visible.sync="updateFormVisible" @close="closeUpdateForm" :close-on-click-modal="false">

      <!--具体表单-->
      <el-form ref="updateForm" :rules="updateManagerRules" :model="updateManager" label-width="150px" size="mini">

        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="updateManager.userName"></el-input>
        </el-form-item>

        <el-form-item label="用户code" prop="userPassword">
          <el-input v-model="updateManager.userPassword"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button  @click="closeUpdateForm">取 消</el-button>
        <el-button type="primary" @click="submitupdateForm">确 定</el-button>
      </div>

    </el-dialog>



    <!--管理员配置角色对话框-->
    <el-dialog title="管理员配置角色" :visible.sync="configRoleVisible" @close="closeConfigForm" :close-on-click-modal="false">

      <!--具体表单-->
      <el-form ref="configRoleForm" :model="configRole" label-width="150px" size="mini">

        <el-form-item label="管理员名称" prop="userName">
          <el-input v-model="configRole.userName" disabled></el-input>
        </el-form-item>

        <el-form-item label="选择角色" prop="roleIds">
          <el-select v-model="configRole.roleIds" multiple placeholder="请选择角色">
            <el-option
                v-for="item in roleOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button  @click="closeConfigForm">取 消</el-button>
        <el-button type="primary" @click="submitConfigForm">确 定</el-button>
      </div>

    </el-dialog>



  </div>

</template>

<script>
export default {
  name: "ManagerList",
  data(){
    return {
      keyword:"", //要搜索的用户名称
      managerList:[], //用户搜索结果
      currentPage:1,
      pageSize:5,
      total:0,
      //添加用户相关
      addFormVisible:false,
      addManagerRules:{
        userName:[
          {required:true,message:"用户名不能为空",trigger:"blur"}
        ],
        userPassword:[
          {required:true,message:"密码不能为空",trigger:"blur"}
        ]
      },
      addManager:{
        userName:'',
        userPassword:'',
      },
      //修改用户相关
      updateFormVisible:false,
      updateManagerRules:{
        userName:[
          {required:true,message:"用户名不能为空",trigger:"blur"}
        ],
        userPassword:[
          {required:true,message:"密码不能为空",trigger:"blur"}
        ]
      },
      updateManager:{
        id:0,
        userName:'',
        userPassword:'',
      },
      //配置角色相关
      configRoleVisible:false,
      configRole:{
        managerId:0,
        userName:'xxx',
        roleIds:[2]
      },
      roleOptions:[{id:1,name:"aaa"},{id:2,name:"bbb"}],


    };
  },
  methods:{
    initManagerList(){

      let params = {
        currentPage:this.currentPage,
        pageSize: this.pageSize,
        keyword: this.keyword
      }

      this.$axios.postForm("/manager/pageSearch",params)
          .then(response => {
            let responseData = response.data;
            console.log(responseData)

            if (responseData.code==200){
              let pageData=responseData.data;
              this.managerList=pageData.records;
              this.currentPage=pageData.current;
              this.pageSize=pageData.size;
              this.total=pageData.total;
            }else {
              this.$message({
                message:responseData.msg ,
                type:'error',
                duration:2000
              })
            }

          })
    }
    ,
    handleSizeChange(size){
      this.currentPage=1;
      this.pageSize=size;
      this.initManagerList();
    }
    ,
    handleCurrentChange(currentPage){
      this.currentPage=currentPage;
      this.initManagerList();
    }
    ,
    showAddForm(){
      this.addFormVisible=true;
    }
    ,
    closeAddForm(){
      this.addFormVisible=false;
      this.$refs.addForm.resetFields();
    }
    ,
    submitAddForm(){
      this.$refs.addForm.validate(isValid => {
        //表单校验
        if (!isValid){
          this.$message({
            message:"请正确填写表单" ,
            type:'error',
            duration:2000
          })
          return;
        }

        console.log(this.addManager)

        this.$axios.post("/manager/add",this.addManager)
            .then(response=>{
              let responseData = response.data;
              console.log(responseData)

              if (responseData.code==200){
                this.$message({
                  message:"添加成功",
                  type:'success',
                  duration:2000
                })
                this.closeAddForm();
              }else {
                this.$message({
                  message:responseData.msg ,
                  type:'error',
                  duration:2000
                })
              }

            })

      })
    }
    ,
    //修改用户相关
    showUpdateForm(manager){
      console.log(manager)

      this.updateManager.id = manager.id;
      this.updateManager.userName = manager.userName;
      this.updateManager.userPassword = manager.userPassword;

      this.updateFormVisible=true;
    }
    ,
    closeUpdateForm(){
      this.updateFormVisible=false;
      this.$refs.updateForm.resetFields();
    }
    ,
    submitupdateForm(){
      this.$refs.updateForm.validate(isValid => {

        //表单校验
        if (!isValid){
          this.$message({
            message:"请正确填写表单" ,
            type:'error',
            duration:2000
          })
          return;
        }

        console.log(this.updateManager)

        this.$axios.post("/manager/update",this.updateManager)
            .then(response=>{
              let responseData = response.data;
              console.log(responseData)

              if (responseData.code==200){
                this.$message({
                  message:"修改成功",
                  type:'success',
                  duration:2000
                })
                this.closeUpdateForm();

                //刷新当前页数据
                this.initManagerList()
              }else {
                this.$message({
                  message:responseData.msg ,
                  type:'error',
                  duration:2000
                })
              }

            })
            .catch(error=>{
              console.log(error)
            })

      })

    }
    ,
    del(id){
      console.log(id)

      this.$confirm('此操作将永久删除该用户,是否继续?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then( ()=>{
        //确定

        this.$axios.delete("manager/delete/"+id)
            .then(response => {
              let responseData = response.data;

              if (responseData.code == 200){
                this.$message({
                  type: 'success',
                  message: '删除成功!',
                  duration:2000
                });

                //刷新当前页
                this.initManagerList();

              }else {
                this.$message({
                  type: 'error',
                  message: responseData.msg ,
                  duration:2000
                });
              }

            })

      }).catch( () => {
        //取消
        this.$message({
          type: 'success',
          message: '已取消删除'
        });
      })

    }
    ,
    showManagerConfigRoleForm(manager){
      console.log(manager)

      this.configRole.managerId = manager.id;
      this.configRole.userName = manager.userName;

      //获取已有的角色
      this.$axios.postForm("/manager/getRelationRoleIds",{managerId:manager.id})
          .then(response => {
            this.configRole.roleIds = response.data.data;
          })


      this.configRoleVisible=true;
    }
    ,
    closeConfigForm(){
      this.configRoleVisible=false;
    }
    ,
    submitConfigForm(){

      this.$axios.postForm("/manager/refreshRelationRoleIds",this.configRole)
          .then(response=>{

            let responseData = response.data;
            if (responseData.code == 200){
              this.$message({
                message:"配置成功",
                type:'success',
                duration:2000
              })
            }else {
              this.$message({
                message:responseData.msg,
                type:'error',
                duration:2000
              })
            }

          })

      this.configRoleVisible=false;
    }
    ,
    initRoleOptions(){
      this.$axios.get("/role/all")
          .then(response=>{
            this.roleOptions = response.data.data;
          })
    }



  },
  created() {
    this.initManagerList();
    this.initRoleOptions();
  }

}
</script>

<style scoped>

</style>