<template>
  <div>

    <!--面包屑导航-->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>


    <el-card>
      <el-row :gutter="20">

        <!--角色搜索框-->
        <el-col :span="8">
          <el-input placeholder="请输角色名称" clearable v-model="keyword" >
            <el-button slot="append" icon="el-icon-search" @click="initRoleList"></el-button>
          </el-input>
        </el-col>

        <!--添加角色按钮-->
        <el-col :span="4">
          <el-button type="primary" @click="showAddForm">添加角色</el-button>
        </el-col>

      </el-row>

      <!--显示角色搜索结果项列表-->
      <el-table :data="roleList" border stripe>
        <el-table-column label="#" type="index"></el-table-column>
        <el-table-column label="角色名称" prop="name"></el-table-column>
        <el-table-column label="角色code" prop="code"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="success" size="mini"  @click="showRoleAuthorityForm(scope.row)">配置权限</el-button>
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




    <!--添加角色对话框-->
    <el-dialog title="添加角色" :visible.sync="addFormVisible" @close="closeAddForm" :close-on-click-modal="false">

      <!--具体表单-->
      <el-form ref="addForm" :rules="addRoleRules" :model="addRole" label-width="150px" size="mini">

        <el-form-item label="角色名称" prop="name">
          <el-input v-model="addRole.name"></el-input>
        </el-form-item>

        <el-form-item label="角色code" prop="code">
          <el-input v-model="addRole.code"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button  @click="closeAddForm">取 消</el-button>
        <el-button type="primary" @click="submitAddForm">确 定</el-button>
      </div>

    </el-dialog>



    <!--修改角色对话框-->
    <el-dialog title="修改角色" :visible.sync="updateFormVisible" @close="closeUpdateForm" :close-on-click-modal="false">

      <!--具体表单-->
      <el-form ref="updateForm" :rules="updateRoleRules" :model="updateRole" label-width="150px" size="mini">

        <el-form-item label="角色名称" prop="name">
          <el-input v-model="updateRole.name"></el-input>
        </el-form-item>

        <el-form-item label="角色code" prop="code">
          <el-input v-model="updateRole.code"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button  @click="closeUpdateForm">取 消</el-button>
        <el-button type="primary" @click="submitupdateForm">确 定</el-button>
      </div>

    </el-dialog>



    <!--配置权限-->
    <el-dialog title="配置权限" :visible.sync="roleAuthorityFormVisible" @close="closeRoleAuthorityForm" :close-on-click-modal="false">
      <el-form ref="roleAuthorityForm" :model="roleAuthority" label-width="150px" size="mini">
        <el-form-item label="角色名称" prop="name">
          <el-input v-model="roleAuthority.name" disabled></el-input>
        </el-form-item>

        <el-form-item label="权限选择">
          <el-tree
              :data="permissionMenu"
              show-checkbox
              :props="defaultProps"
              default-expand-all
              node-key="id"
              ref="rolePermissionTree"
              check-strictly
          ></el-tree>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button  @click="closeRoleAuthorityForm">取 消</el-button>
        <el-button type="primary" @click="submitRoleAuthorityForm">确 定</el-button>
      </div>
    </el-dialog>



  </div>

</template>

<script>
export default {
  name: "RoleList",
  data(){
    return {
      keyword:"", //要搜索的角色名称
      roleList:[], //角色搜索结果
      currentPage:1,
      pageSize:5,
      total:0,
      //添加角色相关
      addFormVisible:false,
      addRoleRules:{
        name:[
          {required:true,message:"名称不能为空",trigger:"blur"}
        ],
        code:[
          {required:true,message:"code不能为空",trigger:"blur"}
        ]
      },
      addRole:{
        name:'',
        code:'',
      },
      //修改角色相关
      updateFormVisible:false,
      updateRoleRules:{
        name:[
          {required:true,message:"名称不能为空",trigger:"blur"}
        ],
        code:[
          {required:true,message:"code不能为空",trigger:"blur"}
        ]
      },
      updateRole:{
        id:0,
        name:'',
        code:'',
      },
      //角色授权相关
      roleAuthorityFormVisible:false,
      roleAuthority:{
        roleId:0,
        name:'',
        permissionIds:[2,4]
      },
      permissionMenu:[
        {id:1,name:"a"},
        {id:2,name:"b"},
        {id:3,name:"c",children:[{id:4,name:"c1"},{id:5,name:"c2"}]},
      ],  //所有权限菜单
      defaultProps:{
        children: 'children',
        label: 'name'
      },


    };
  },
  methods:{
    initRoleList(){

      let params = {
        currentPage:this.currentPage,
        pageSize: this.pageSize,
        keyword: this.keyword
      }

      this.$axios.postForm("/role/pageSearch",params)
          .then(response => {
            let responseData = response.data;
            console.log(responseData)

            if (responseData.code==200){
              let pageData=responseData.data;
              this.roleList=pageData.records;
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
      this.initRoleList();
    }
    ,
    handleCurrentChange(currentPage){
      this.currentPage=currentPage;
      this.initRoleList();
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

        console.log(this.addRole)

        this.$axios.post("/role/add",this.addRole)
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
    //修改角色相关
    showUpdateForm(role){
      console.log(role)

      this.updateRole.id = role.id;
      this.updateRole.name = role.name;
      this.updateRole.code = role.code;

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

        console.log(this.updateRole)

        this.$axios.post("/role/update",this.updateRole)
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
                this.initRoleList()
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

      this.$confirm('此操作将永久删除该角色,是否继续?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then( ()=>{
        //确定

        this.$axios.delete("role/delete/"+id)
            .then(response => {
              let responseData = response.data;

              if (responseData.code == 200){
                this.$message({
                  type: 'success',
                  message: '删除成功!',
                  duration:2000
                });

                //刷新当前页
                this.initRoleList();

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
    showRoleAuthorityForm(role){
      console.log(role)


      this.roleAuthorityFormVisible = true;  //显示异步的

      // $refs 包含的是已经在页面上显示的元素
      // 获取 this.$refs 内部是异步操作的
      setTimeout(()=>{

        console.log(this.$refs)
        console.log(this.$refs.rolePermissionTree)

        this.$refs.rolePermissionTree.setCheckedKeys(this.roleAuthority.permissionIds)
      },50)

    }
    ,
    closeRoleAuthorityForm(){
      this.roleAuthorityFormVisible = false;
    }
    ,
    submitRoleAuthorityForm(){

      let checkIds = this.$refs.rolePermissionTree.getCheckedKeys();
      console.log(checkIds)

      this.roleAuthorityFormVisible = false;
    }



  },
  created() {
    this.initRoleList();
  }

}
</script>

<style scoped>

</style>