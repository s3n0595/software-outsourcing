<template>

  <div>
    <h3 style="margin: 3% auto;text-align: center">登陆服务商账号</h3>
    <el-form
        ref="logForm"
        :model="data"
        style="width:300px;margin: 0 auto"
        label-position="center"
        label-suffix=":"
        :rules="rules"
        status-icon
        hide-required-asterisk
    >
      <el-form-item prop="phoneNumber">
        <el-input clearable type="text" v-model="data.phoneNumber" placeholder="手机号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input clearable type="password" v-model="data.password" placeholder="请输入密码" ></el-input>
      </el-form-item>
      <el-form-item style="text-align: left">
        <el-checkbox v-model="checked">记住我
        </el-checkbox>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('logForm')" style="width: 300px">登陆</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>

export default {
  name: "ProviderLog",
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        return callback(new Error("请输入密码"))
      }
      callback()
    };
    const validatephoneNum = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'));
      } else {
        callback();
      }
    };
    return {
      checked: '',
      data: {
        phoneNumber: '',
        password: '',
      },
      rules: {
        phoneNumber: [
          {required: true, validator: validatephoneNum, trigger: 'blur' }
        ],
        password: [
          {require: true, validator: validatePass, trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    // eslint-disable-next-line no-unused-vars
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.$axios.post("/login/provider",
              this.$qs.stringify({
                "phoneNumber":this.data.phoneNumber,
                "providerPassword":this.data.password
              })).then(reponse => {
            // eslint-disable-next-line no-console
            console.log(reponse);
            const res = reponse.data
            if (res.code !== 200) {
              this.$message.error("账号或密码错误")
            } else {
              sessionStorage.setItem('user',JSON.stringify(res.data))
              this.user = JSON.parse(sessionStorage.getItem('user'))
              sessionStorage.setItem('token',JSON.stringify(res.data))
              console.log(this.user)
              console.log(this.user.role)
              this.$message.success("登陆成功")
              this.$router.push("/home")
            }
          }).catch(error => {
            // eslint-disable-next-line no-console
            console.log(error)
          });
        } else {
          // eslint-disable-next-line no-console
          console.log('error submit!!');
          return false;
        }
      });
    },
  }
}
</script>

<style scoped>
</style>





