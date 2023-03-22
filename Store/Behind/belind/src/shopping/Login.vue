<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <img src="@/assets/logo.png" />
      </div>
      <el-form
          ref="loginForm"
          :model="loginForm"
          :rules="loginFormRules"
          label-width="0px"
          class="login_form"
      >
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" placeholder="用户名" v-model="loginForm.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              prefix-icon="el-icon-lock"
              placeholder="密码"
              v-model="loginForm.password"
              type="password"
          ></el-input>
        </el-form-item>
        <el-form-item class="btn">
          <el-button type="primary" @click="submitLoginForm" >登录</el-button>
          <el-button type="info"  @click="resetLoginForm" >重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: "",
        password: ""
      },
      loginFormRules: {
        username: [
          { required: true, message: "用户名必填", trigger: "blur" },
          { min: 4, max: 8, message: "用户名在4到8个字符之间", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码必填", trigger: "blur" },
          { min: 4, max: 8, message: "密码在4到8个字符之间", trigger: "blur" }
        ]
      }
    };
  },
  methods: {

    resetLoginForm(){
      this.$refs.loginForm.resetFields();
    }
    ,
    submitLoginForm(){

      this.$refs.loginForm.validate((validate) => {
        console.log(this.loginForm)

        if(!validate){
          this.$message({
            message:"请正确填写表单",
            type:"error",
            duration:2000,
          })

          return;
        }

        //todo 请求后端接口进行校验

        this.$router.push("/Home")
      })


    }


  }
}
</script>

<style lang="less" scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  .avatar_box {
    height: 100px;
    width: 100px;
    border-radius: 50%;
    border: 1px solid #eee;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -30%);
    background-color: #fff;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}
.btn {
  display: flex;
  justify-content: center;
}
.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 10px;
  box-sizing: border-box;
}
</style>