<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">后台管理系统</div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="ms-content"
               v-loading="isShowloading">
        <el-form-item prop="userAccount">
          <el-input v-model="ruleForm.userAccount" placeholder="account">
            <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item prop="userPassword">
          <!--                @keyup.enter.native:回车事件-->
          <el-input type="password" placeholder="password" v-model="ruleForm.userPassword"
                    @keyup.enter.native="submitForm('ruleForm')">
            <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item style="text-align: left">
          <el-checkbox v-model="checked">记住我
          </el-checkbox>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        </div>
        <!--        <p class="login-tips">Tips : 用户名和密码随便填随便填。</p>-->
      </el-form>
    </div>
  </div>
</template>

<script>
import {
  userInfoLogin,
  updateLoginDate,
  getDate,
  addLogInfo
} from "../../api/api"
import bus from "@/components/common/bus";

export default {
  data: function () {
    return {
      user: '',
      checked: true,
      ruleForm: {
        userAccount: '',
        userPassword: '',
      },
      isShowloading: false,
      rules: {
        userAccount: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {max: 11, message: "不能超过11位", trigger: "blur"},
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/, message: "不能有除下划线的特殊符号"},
        ],
        userPassword: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {max: 11, message: "不能超过11位", trigger: "blur"},
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/, message: "不能有除下划线的特殊符号"},
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {

      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.isShowloading = true;
          let params = {
            userAccount: this.ruleForm.userAccount,
            userPassword: this.ruleForm.userPassword,
          }
          userInfoLogin(params).then(res => {
            if ("" == res.data) {
              this.$message({
                message: "登录失败，账号或密码错误！",
                type: "error",
              })
            } else if (0 == res.data.state) {
              this.$message({
                message: "您登录的账号已被封禁，请联系管理员进行处理",
                type: "error",
              });
              let param1 = {
                userId: res.data.userId,
                time: getDate(),
                operation: "登录系统",
                state: 0,
                LogDescribe: "登录的账号已被封禁",
              }
              addLogInfo(param1);
            } else {
              this.$message({
                message: "欢迎[" + res.data.userName + "]登录系统",
                type: "success"
              })
              let params = {
                userId: res.data.userId,
                loginNumber: res.data.loginNumber + 1,
                loginDate: getDate(),
              }
              updateLoginDate(params);
              let param1 = {
                userId: res.data.userId,
                time: getDate(),
                operation: "登录系统",
                state: 1,
                LogDescribe: "已成功登录管理系统",
              }
              addLogInfo(param1);
              sessionStorage.setItem('user', JSON.stringify(res.data));
              this.$router.push('/');
            }
          })
          this.isShowloading = false;
        }
      });

      //判断复选框是否被勾选 勾选则调用配置cookie方法
      if (this.checked == true) {
        console.log("checked == true");
        //传入账号名，密码，和保存天数3个参数
        this.setCookie(this.ruleForm.userAccount, this.ruleForm.userPassword, 7);

      } else {
        console.log("清空Cookie");
        //清空Cookie
        this.clearCookie();
      }

    },

    //设置cookie
    setCookie(c_name, c_pwd, exdays) {
      var exdate = new Date(); //获取时间
      exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * exdays); //保存的天数
      //字符串拼接cookie
      window.document.cookie = "userName" + "=" + c_name + ";path=/;expires=" + exdate.toGMTString();
      window.document.cookie = "userPwd" + "=" + c_pwd + ";path=/;expires=" + exdate.toGMTString();
    },
    //读取cookie
    getCookie: function () {
      if (document.cookie.length > 0) {
        var arr = document.cookie.split('; '); //这里显示的格式需要切割一下自己可输出看下
        for (var i = 0; i < arr.length; i++) {
          var arr2 = arr[i].split('='); //再次切割
          //判断查找相对应的值
          if (arr2[0] == 'userName') {
            this.ruleForm.userAccount = arr2[1]; //保存到保存数据的地方
          } else if (arr2[0] == 'userPwd') {
            this.ruleForm.userPassword = arr2[1];
          }
        }
      }
    },
    //清除cookie
    clearCookie: function () {
      this.setCookie("", "", -1); //修改2值都为空，天数为负1天就好了
    },
  },

  mounted() {
    this.getCookie();
  }
  // beforeDestroy() {
  //   bus.$emit('user',this.user);
  // }
}
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url(../../assets/img/login-bg.jpg);
  background-size: 100%;
}

.ms-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: #000;
  border-bottom: 1px solid #ddd;
}

.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 350px;
  margin: -190px 0 0 -175px;
  border-radius: 5px;
  background: rgba(255, 255, 255, 1);
  overflow: hidden;
}

.ms-content {
  padding: 30px 30px;
}

.login-btn {
  text-align: center;
}

.login-btn button {
  width: 100%;
  height: 36px;
  margin-bottom: 10px;
}

.login-tips {
  font-size: 12px;
  line-heigh: #000;
}
</style>
