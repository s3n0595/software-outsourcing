<template>

  <el-container>
    <el-header>Header</el-header>
    <el-main>
      <h3>注册雇主账号</h3>
      <el-form
        ref="registerForm"
        :model="data"
        style="width:500px"
        label-position="center"
        label-width="80px"
        label-suffix=":"
        :rules="rules"
        status-icon
        hide-required-asterisk
      >
        <el-form-item prop="employerName" :validate-status="status">
          <el-input clearable v-model="data.employerName" placeholder="用户名（即个性后缀，注册后无法修改）" ></el-input>
        </el-form-item>
        <el-form-item prop="phoneNumber">
          <el-input clearable v-model="data.phoneNumber" placeholder="手机号" type="text"></el-input>
        </el-form-item>
        <el-form-item prop="code">
          <el-input clearable v-model="data.code" placeholder="请输入手机验证码" type="text"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input clearable v-model="data.password" placeholder="请输入密码" type="password"></el-input>
        </el-form-item>
        <el-form-item prop="re_password">
          <el-input clearable v-model="data.re_password" placeholder="请确认密码" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-checkbox v-model="checked">我同意遵守
            <router-link to="/userAgreement">《用户服务协议》</router-link>
          </el-checkbox>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="onSubmit('registerForm')">注册</el-button>
        </el-form-item>

      </el-form>
    </el-main>
    <el-footer>Footer</el-footer>
  </el-container>

</template>

<script>
export default {
  name: "EmployerReg",
  data () {
    // 封装一下相似功能的校验器
    const validatorMethod = function (length, message) {
      return (rule, value, callback) => {
        if (value.length < length) {
          callback(new Error(message))
        } else {
          callback()
        }
      }
    }
    //用户名校验器
    const usernameValidator = (rule, value, callback) => {
      if (/^[a-zA-Z]\w{5,17}$/.test(value)) {
        callback()
      } else {
        callback(new Error('用户名须以字母开头，且只能包含字母、数字、横线及下划线'))
      }
    }
    // 确认密码校验器
    const comfirmPassword = (rule, value, callback) => {
      if (value !== this.data.password) {
        return callback(new Error('两次输入密码不一致'))
      }
      callback()
    }
    // 手机号校验器
    const phoneValidator = (rule, value, callback) => {
      if (/^1[3456789]\d{9}$/.test(value)) { // 利用正则表达式校验手机号
        callback()
      } else {
        callback(new Error('请输入正确手机号'))
      }
    }
    return {
      status: '',
      data: {
        employerName: '',
        password: '',
        re_password: '',
        phoneNumber: ''
      },
      // 所有校验规则
      rules: {
        employerName: [ // 用户名验证
          { required: true, trigger: 'change', message: '用户名必须填写' },
          // { min: 3, max: 12, trigger: 'change', message: '用户名长度不能小于3'},
          { validator: usernameValidator, trigger: 'blur' },
          { validator: validatorMethod(3, '用户名长度不能小于3'), trigger: 'change' }
        ],
        password: [ // 密码验证
          { required: true, trigger: 'change', message: '密码不能为空' },
          { validator: validatorMethod(8, '密码长度不能小于8'), trigger: 'change' }
        ],
        re_password: [ // 重复密码验证
          { required: true, trigger: 'change', message: '请再一次输入密码' },
          { validator: comfirmPassword, trigger: 'blur' }
        ],
        phoneNumber: [ // 手机号验证
          { required: true, trigger: 'change', message: '手机号不能为空' },
          { min: 11, max: 11, trigger: 'change', message: '请输入11位手机号码' },
          { validator: phoneValidator, trigger: 'blur' }
        ]
      }
    }
  },
  components: {

  },
  methods: {
    validateName (e) {
      if (e.target.value.trim() !== '') { // 输入框为空不校验
        // status的四个值：error(失败)，success(成功)，validating(验证中)，null
        this.status = 'validating'
        setTimeout(() => { // 模拟发送ajax请求，检查用户名是否重复
          if (e.target.value === 'wjg') {
            this.status = 'success'
          } else {
            this.status = 'error'
          }
        }, 1000)
      }
    },
    onSubmit(formName) {
      this.$axios.get("/admin/login",{
        params:{
          // "action":"login",
          "employerName":this.form.employerName,
          "phoneNumber":this.form.phoneNumber,
          "code":this.form.code,
          "password":this.form.password
        }

      }).then(response=>{
        console.log(response);
        // 为表单绑定验证功能
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
            this.$router.push("/backman");
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

<style scoped>
</style>
