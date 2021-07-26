<template>
  <div class="root">
    <el-form :model="Register" ref="RegisterForm" label-width="0" class="RegisterForm">
      <el-form-item prop="phone">
        <el-input v-model="Register.phone" placeholder="请输入手机号"></el-input>
      </el-form-item>
      <el-form-item prop="验证码" class="code">
        <el-input v-model="Register.sendcode" placeholder="请输入验证码"></el-input>
        <el-button type="button" @click="sendcode" :disabled="disabled" v-if="disabled==false">发送验证码
        </el-button>
        <el-button type="button" @click="sendcode" :disabled="disabled" v-if="disabled==true">{
          {btntxt}}
        </el-button>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>

      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "ProviderReg",
  data() {
    return {
      Register: {
        phone: '',
        sendcode: '',
      },
      disabled: false,
      time: 0,
      btntxt: "重新发送",
    }
  },
  methods: {
    //手机验证发送验证码
    sendcode() {
      const reg = 11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/
      if (this.Register.phone == '') {
        this.$message({
          message:'手机号不能为空',
          center: true
        })
        return
      }
      if (!reg.test(this.Register.phone)) {
        this.$message({
          message:'请输入正确的手机号',
          center:true
        })
        return
      } else {
        console.log(this.Register.phone);
        this.$message({
          message: '发送成功',
          type: 'success',
          center:true
        });
        this.time = 60;
        this.disabled = true;
        this.timer();
      }
    },
    //60S倒计时
    timer() {
      if (this.time > 0) {
        this.time--;
        this.btntxt = this.time + "s后重新获取";
        setTimeout(this.timer, 1000);
      } else {
        this.time = 0;
        this.btntxt = "获取验证码";
        this.disabled = false;
      }
    },
    submitForm(){

    }
  }
}
</script>

<style scoped >
.root {
  background: url("http://img.hena360.cn/login_bj.jpg") no-repeat center;
  background-size: 100% 100%;
  height: 100vh;
  width: 100vw;
  display: table-cell;
  vertical-align: middle;
  form {
    width: 80%;
    margin: auto;
    margin-top: 90%;
    .code {
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
    .el-button--primary {
      width: 100%;
      background-color: #ffe86a;
      border: none;
      color: #000;
    }
  }
}
</style>

