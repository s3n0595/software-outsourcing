<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">后台管理系统</div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="ms-content">
        <el-form-item prop="userAccount">
          <el-input v-model="ruleForm.userAccount" placeholder="account">
            <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item prop="userPassword">
          <!--                @keyup.enter.native:回车事件-->
          <el-input type="password" placeholder="password" v-model="ruleForm.userPassword" @keyup.enter.native="submitForm('ruleForm')">
            <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
          </el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        </div>
        <p class="login-tips">Tips : 用户名和密码随便填随便填。</p>
      </el-form>
    </div>
  </div>
</template>

<script>
import {
  userInfoLogin,
  updateLoginDate,
  getDate
} from "../../api/api"
import bus from "@/components/common/bus";
export default {
  data: function(){
    return {
      user: '',
      ruleForm: {
        userAccount: '',
        userPassword: '',
      },
      rules: {
        userAccount: [
          {required: true, message: '请输入账号', trigger: 'blur' },
          {max: 11, message: "不能超过11位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
        ],
        userPassword: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          {max: 11, message: "不能超过11位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let params = {
            userAccount: this.ruleForm.userAccount,
            userPassword: this.ruleForm.userPassword,
          }
          userInfoLogin(params).then(res=>{
            console.log(res.data);
            if ("" == res.data){
              this.$message({
                message: "登录失败，账号或密码错误！",
                type: "error",
              })
            }else
            if (0 == res.data.state) {
              this.$message({
                message: "您登录的账号已被封禁，请联系管理员进行处理",
                type: "error",
              });
            }else {
              this.$message({
                message: "欢迎["+res.data.userName+"]登录系统",
                type: "success"
              })
              let params = {
                userId: res.data.userId,
                loginNumber: res.data.loginNumber+1,
                loginDate: getDate(),
              }
              updateLoginDate(params);
              sessionStorage.setItem('user',JSON.stringify(res.data));
              this.$router.push('/');
            }
          })
        }
      });
    }
  },
  // beforeDestroy() {
  //   bus.$emit('user',this.user);
  // }
}
</script>

<style scoped>
.login-wrap{
  position: relative;
  width:100%;
  height:100%;
  background-image: url(../../assets/img/login-bg.jpg);
  background-size: 100%;
}
.ms-title{
  width:100%;
  line-height: 50px;
  text-align: center;
  font-size:20px;
  color: #000;
  border-bottom: 1px solid #ddd;
}
.ms-login{
  position: absolute;
  left:50%;
  top:50%;
  width:350px;
  margin:-190px 0 0 -175px;
  border-radius: 5px;
  background: rgba(255,255,255, 1);
  overflow: hidden;
}
.ms-content{
  padding: 30px 30px;
}
.login-btn{
  text-align: center;
}
.login-btn button{
  width:100%;
  height:36px;
  margin-bottom: 10px;
}
.login-tips{
  font-size:12px;
  line-heigh:#000;
}
</style>
