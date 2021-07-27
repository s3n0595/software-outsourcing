<template>
  <el-container>
    <el-header>Header</el-header>
    <el-main>
      <el-button type="primary" plain>
        <router-link to="/providerLog">服务商登陆</router-link>
      </el-button>
      <el-button type="primary" plain>
        <router-link to="/employerLog">雇主登陆</router-link>
      </el-button>
    </el-main>
    <el-footer>Footer</el-footer>
  </el-container>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: {
        phoneNumber:"",
        password:""
      },

      // 表单验证，需要在 el-form-item 元素中增加 prop 属性
      rules: {
        phoneNumber: [
          {required: true, message: '手机号不可为空', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不可为空', trigger: 'blur'}
        ]
      },

      // 对话框显示和隐藏
      dialogVisible: false
    }
  },
  methods: {
    onSubmit(formName) {
      this.$axios.post("/login",{
        params:{
          // "action":"login",
          "phoneNumber":this.form.phoneNumber,
          "password":this.form.password
        }

      }).then(response=>{
        console.log(response);
        // 为表单绑定验证功能
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
            this.$router.push("/");
          } else {
            this.dialogVisible = true;
            return false;
          }
        });
      }).catch(error=>{
        console.log(error)
      });
    }
  }
}
</script>

<style lang="css" scoped>
.login-box {
  border: 1px solid #DCDFE6;
  width: 350px;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}

.login-title {
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
}
</style>
