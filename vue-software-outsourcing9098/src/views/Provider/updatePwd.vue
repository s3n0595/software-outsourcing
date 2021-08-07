<template >

  <div class="form">
    <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="30%"
        class="demo-ruleForm"
        style="width:30%;margin:0 auto;padding-top: 3%"
    >
      <el-form-item label="原密码" prop="providerPassword">
        <el-input type="password" v-model="ruleForm.providerPassword" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="password">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="re_password">
        <el-input type="password" v-model="ruleForm.re_password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
      <el-form-item prop="employerId">
        <el-input type="hidden" v-model="ruleForm.providerId"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    //密码强弱验证
    const passwordValidator = (rule, value, callback) => {
      if (/^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\W_!@#$%^&*`~()-+=]+$)(?![0-9\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\W_!@#$%^&*`~()-+=]{8,30}$/
          .test(value)) {
        callback()
      } else {
        callback(new Error('密码至少包含大写字母，小写字母，数字，特殊字符中的三种，且长度为8到30位！'))
      }
    }
    // 确认密码校验器
    const comfirmPassword = (rule, value, callback) => {
      if (value !== this.ruleForm.password) {
        return callback(new Error('两次输入密码不一致'))
      }
      callback()
    }
    return {
      ruleForm: {
        providerPassword: '',
        password: '',
        re_password: '',
        providerId:''
      },
      rules: {
        providerPassword: [
          { required: true, trigger: 'blur', message: '密码不能为空' },
          { validator: passwordValidator, trigger: 'blur' },
        ],
        password: [
          { required: true, trigger: 'blur', message: '密码不能为空' },
          { validator: passwordValidator, trigger: 'blur' },
        ],
        re_password: [ // 重复密码验证
          { required: true, trigger: 'blur', message: '请再一次输入密码' },
          { validator: comfirmPassword, trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.$axios.post("/work/updatePwd",
              this.$qs.stringify({
                "providerPassword":this.ruleForm.providerPassword,
                "password":this.ruleForm.password,
                'providerId':this.user.providerId
              })).then(response=>{
            console.log(response);
            const res = response.data.code;
            if (res === 200) {
              this.$message.success("密码修改成功");
              this.$refs[formName].resetFields();
            } else {
              this.$message.error("原密码输入有误");
              this.$refs[formName].resetFields();
            }
          }).catch(error=>{
            console.log(error)
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  mounted() {
    this.user = JSON.parse(sessionStorage.getItem('user'));
  }
}
</script>

<style scoped lang="scss">

</style>
