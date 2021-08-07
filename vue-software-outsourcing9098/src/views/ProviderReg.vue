<template>

  <div>
    <h3 style="margin: 2% auto;text-align: center">注册服务商账号</h3>
    <el-form
        ref="registerForm"
        :model="data"
        style="width:300px;margin: 0 auto"
        label-position="center"
        label-suffix=":"
        :rules="rules"
        status-icon
        hide-required-asterisk
    >
      <el-form-item prop="providerName" >
        <el-input clearable v-model="data.providerName" placeholder="用户名（即个性后缀，注册后无法修改）" type="text" ></el-input>
      </el-form-item>
      <el-form-item prop="phoneNumber">
        <el-input clearable v-model="data.phoneNumber" placeholder="手机号" type="text"></el-input>
      </el-form-item>
      <el-form-item prop="code">
        <div class="code">
          <el-input clearable v-model="data.code" placeholder="请输入手机验证码" type="text"></el-input>
          <el-button class="btn-orange" :disabled="disabled" @click="getCode">{{valiBtn}}</el-button>
        </div>
      </el-form-item>
      <el-form-item prop="providerPassword">
        <el-input clearable v-model="data.providerPassword" placeholder="请输入密码" type="password"></el-input>
      </el-form-item>
      <el-form-item prop="re_password">
        <el-input clearable v-model="data.re_password" placeholder="请确认密码" type="password"></el-input>
      </el-form-item>
<!--      <el-form-item style="text-align: left">-->
<!--        <el-checkbox v-model="checked">我同意遵守-->
<!--          <router-link to="/userAgreement">《用户服务协议》</router-link>-->
<!--        </el-checkbox>-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" @click="onSubmit('registerForm')" style="width: 300px">注册</el-button>
      </el-form-item>
      <el-form-item prop="role">
        <el-input type="hidden" v-model="data.role"></el-input>
      </el-form-item>

    </el-form>
  </div>


</template>

<script>
export default {
  name: "ProviderReg",
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
    };
    //用户名校验器
    const usernameValidator = (rule, value, callback) => {
      if (/^[a-zA-Z]\w{5,17}$/.test(value)) {
        callback()
      } else {
        callback(new Error('用户名须以字母开头，且只能包含字母、数字、横线及下划线'))
      }
    }
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
      if (value !== this.data.providerPassword) {
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
      checked: '',
      data: {
        providerName: '',
        providerPassword: '',
        re_password: '',
        phoneNumber: '',
        code: '',
        role: ''
      },
      // 所有校验规则
      rules: {
        providerName: [ // 用户名验证
          { required: true, trigger: 'blur', message: '用户名必须填写' },
          // { min: 3, max: 12, trigger: 'blur', message: '用户名长度不能小于3'},
          { validator: usernameValidator, trigger: 'blur' },
          { validator: validatorMethod(3, '用户名长度不能小于3'), trigger: 'blur' }
        ],
        providerPassword: [ // 密码验证
          { required: true, trigger: 'blur', message: '密码不能为空' },
          { validator: passwordValidator, trigger: 'blur' },
          // { validator: validatorMethod(8, '密码长度不能小于8'), trigger: 'blur' }
        ],
        re_password: [ // 重复密码验证
          { required: true, trigger: 'blur', message: '请再一次输入密码' },
          { validator: comfirmPassword, trigger: 'blur' }
        ],
        phoneNumber: [ // 手机号验证
          { required: true, trigger: 'blur', message: '手机号不能为空' },
          { min: 11, max: 11, trigger: 'blur', message: '请输入11位手机号码' },
          { validator: phoneValidator, trigger: 'blur' }
        ],
        code: [ //手机验证码
          {required: true, message: '请输入验证码', trigger: 'blur' }
        ],
      },
      valiBtn:'获取验证码',
      disabled:false,
    }
  },
  methods: {
    //获取验证码
    //获取验证码 并只验证手机号 是否正确
    getCode(){
      this.$refs['registerForm'].validateField('phoneNumber', (err) =>{
        if(err){
          // eslint-disable-next-line no-console
          console.log('未通过')
          return;
        }else{
          // eslint-disable-next-line no-console
          console.log('已通过')
          this.$axios.post("/sendSms",
              this.$qs.stringify({
                "phoneNumber":this.data.phoneNumber,
              })
          ).then(response => {
            // eslint-disable-next-line no-console
            console.log(response)
            const code = response.data.code;
            if (code !== 200) {
              this.$message.error("太频繁了，休息一下");

            } else {
              this.tackBtn();   //验证码倒数60秒
              this.$message.success("验证码发送成功")
            }
            // eslint-disable-next-line no-console
            console.log(response);
          })
        }
      })
    },
    tackBtn(){       //验证码倒数60秒
      let time = 60;
      let timer = setInterval(() => {
        window.sessionStorage.setItem("X_no_time",this.time);//存入本地
        if(window.sessionStorage.getItem("X_no_time")<="0"){//等于0时清空
          window.sessionStorage.removeItem('X_no_time');
        }
        if(time == 0){
          clearInterval(timer);
          this.valiBtn = '获取验证码';
          this.disabled = false;
        }else{
          this.disabled = true;
          this.valiBtn = time + '秒后重试';
          time--;
        }
      }, 1000);
    },
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
    // eslint-disable-next-line no-unused-vars
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.$axios.post("/checkCode",
              this.$qs.stringify({
                "phoneNumber":this.data.phoneNumber,
                "code":this.data.code
              })).then(response=>{
            // eslint-disable-next-line no-console
            console.log(response)
            const codeRes = response.data.code
            if (codeRes === 200) {
              this.$axios.post("/register/provider",
                  this.$qs.stringify({
                    "providerName":this.data.providerName,
                    "phoneNumber":this.data.phoneNumber,
                    "providerPassword":this.data.providerPassword,
                    "role":"pro"
                  })).then(response=>{
                // eslint-disable-next-line no-console
                console.log(response);
                const res = response.data.code
                if (res !== 200) {
                  this.$message.error("手机号已注册")
                } else {
                  this.$message.success("注册成功")
                  this.$router.push("/")
                }
              }).catch(error=>{
                // eslint-disable-next-line no-console
                console.log(error)
              });
            } else {
              this.$message.error("验证码有误，请重新输入")
            }
          })

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

<style lang="scss" scoped>
.employerReg{
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
  /*margin: 0 auto;*/
  text-align: center;
}
.code{
  .el-input {
    width: 55%;
    border-radius: 0px;
    float: left;
    display: inline-block;
  }
  .el-button {
    width: 45%;
    border-top-left-radius: 0px;
    border-bottom-left-radius: 0px;
    border-left: 0px;
    float: left;
    display: inline-block;
  }
}

</style>


