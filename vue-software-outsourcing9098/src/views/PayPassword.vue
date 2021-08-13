<template>
<div>
  <div v-if="onePwd">
  <el-card class="box-card" shadow="always" style="border: 1px solid rgba(230,30,11,.5);">
    <div slot="header" class="clearfix" >
      <div >
        <div class="title"><span style="font-size: 20px;">您还未设置交易密码!</span></div>
      </div>
    </div>
    <div class="text item">
      <span>在提现、使用账户余额进行支付时需要输入交易密码，请妥善保管。</span>
    </div>
  </el-card>
    <div style="margin-top: 5%;margin-left: 18%;">
      <span style="font-size: 20px;">设置交易密码</span>
      <div style="width: 700px;"><el-divider></el-divider></div>
      <div>
        <p style="color: #8c939d">* 请确保交易密码与登录密码不同！</p>
        <p style="color: #8c939d">* 密码必须为 6位数字。</p>
        <p style="color: #8c939d">* 如果您收不到手机验证码，请先去个人信息页面验证工作手机。</p>
        <p style="margin-left: 15%;margin-top: 5%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱&nbsp;&nbsp;&nbsp;{{this.uEmail}}</p>
        <div style="margin-top: 5%">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm"  class="demo-ruleForm">
          <el-form-item prop="firstpwd" style="margin-left: 15%;">
            <span>交易密码&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <el-input v-model="ruleForm.firstpwd" class="email"  style="width: 220px;margin-top: -20%;"></el-input>
          </el-form-item>
          <el-form-item prop="confirmpwd" style="margin-left: 15%;">
            <span style="margin-left: -30px">确认交易密码&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <el-input v-model="ruleForm.confirmpwd" class="email"  style="width: 220px;margin-top: -20%;"></el-input>
          </el-form-item>
          <el-form-item prop="code" style="margin-left: 15%;">
            <span style="margin-left: -20px">邮箱验证码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <el-input prop="code" v-model="ruleForm.code" placeholder="验证码" style="width: 150px;margin-top: -20%;"></el-input>
            <button @click="getCode()" class="code-btn" :disabled="!show">
              <span v-show="show">发送验证码</span>
              <span v-show="!show" class="count">{{count}}</span>
            </button>
          </el-form-item>
        </el-form>
          <div style="margin-left: 20%;margin-top: 5%"><el-button type="primary" style="width: 200px;" @click="setUpPwd">确认</el-button></div>
        </div>
      </div>
    </div>
  </div>


  <!--修改交易密码-->
  <div v-if="twoPwd">
    <el-card class="box-card" shadow="always">
      <div slot="header" class="clearfix" >
        <div >
          <div class="title"><span style="font-size: 20px;"><i class="el-icon-check">您已完成交易密码设置!</i></span></div>
        </div>
      </div>
      <div class="text item">
        <span>在提现、使用账户余额进行支付时需要输入交易密码，请妥善保管。</span>
        <p style="margin-top: 5px;"><el-link type="primary" @click="editPWD">修改交易密码</el-link></p>
      </div>
    </el-card>
    <div style="margin-top: 5%;margin-left: 18%;" v-if="editShow">
      <span style="font-size: 20px;">设置交易密码</span>
      <div style="width: 700px;"><el-divider></el-divider></div>
      <div>
        <p style="color: #8c939d">* 请确保交易密码与登录密码不同！</p>
        <p style="color: #8c939d">* 密码必须为 6位数字。</p>
        <p style="color: #8c939d">* 如果您收不到手机验证码，请先去个人信息页面验证工作手机。</p>
        <div style="margin-top: 5%">
          <el-form :model="editForm" :rules="erules" ref="editForm"  class="demo-ruleForm">
            <el-form-item prop="oldpwd" style="margin-left: 15%;">
              <span>原交易密码&nbsp;&nbsp;&nbsp;&nbsp;</span>
              <el-input v-model="editForm.oldpwd" class="email"  style="width: 220px;margin-top: -20%;"></el-input>
            </el-form-item>
            <el-form-item prop="opwd" style="margin-left: 15%;">
              <span style="margin-left: 10px;">交易密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
              <el-input v-model="editForm.opwd" class="email"  style="width: 220px;margin-top: -20%;"></el-input>
            </el-form-item>
            <el-form-item prop="tpwd" style="margin-left: 15%;">
              <span style="margin-left: -15px">确认交易密码&nbsp;&nbsp;&nbsp;&nbsp;</span>
              <el-input v-model="editForm.tpwd" class="email"  style="width: 220px;margin-top: -20%;"></el-input>
            </el-form-item>
          </el-form>
          <div style="margin-left: 20%;margin-top: 5%"><el-button type="primary" style="width: 200px;" @click="editPwd">确认</el-button></div>
        </div>
      </div>
    </div>
  </div>

</div>
</template>

<script>
const TIME_COUNT = 120 // 设置一个全局的倒计时的时间
export default {
  name: "PayPassword",
  data(){
    // 邮箱校验
    let validateMobile = (rule, value, callback) => {
      if (!value) {
        callback(new Error('交易密码不能为空'))
      } else {
        if (value !== '') {
          let reg = /^[0-9]{6}$/
          if (!reg.test(value)) {
            callback(new Error('请输入6位数字交易密码'))
          }
        }
        callback()
      }
    }
    // 验证码校验
    let validateCheckCode = (rule, value, callback) => {
      if (!value) {
        callback(new Error('验证码不可为空'))
      } else {
        if (value !== '') {
          let reg = /^[0-9]{6}$/
          if (!reg.test(value)) {
            callback(new Error('请输入收到的6位随机验证码'))
          }
        }
        callback()
      }
    }
    return{
      user:'',
      ruleForm: {
        code:'',
        firstpwd:'',
        confirmpwd:''
      },
      editForm: {
        oldpwd:'',
        opwd:'',
        tpwd:''
      },
      rules: {
        firstpwd: [
          { validator: validateMobile, trigger: 'blur' }
        ],
        confirmpwd: [
          { validator: validateMobile, trigger: 'blur' }
        ],
        code: [
          { validator: validateCheckCode, trigger: 'blur' }
        ]
      },
      erules: {
        oldpwd: [
          { validator: validateMobile, trigger: 'blur' }
        ],
        opwd: [
          { validator: validateMobile, trigger: 'blur' }
        ],
        tpwd: [
          { validator: validateMobile, trigger: 'blur' }
        ]
      },
      show: true,
      count: '',
      timer: null,
      onePwd:false,
      twoPwd:false,
      editShow:false,
      role:'',
      userId:'',
      uEmail:'',
      upayPwd:''
    };
  },
  methods: {
    // 向后端发请求的点击事件
    getCode() {
      let _this = this
      if (this.uEmail === '' || this.uEmail===null) {
        _this.$message.error('请先前往个人信息界面绑定邮箱')
      } else if (this.ruleForm.firstpwd === '') {
        _this.$message.error('请输入交易密码')
      } else {
        this.$axios({
          method: 'post',
          url: '/mail/getCheckCode',
          data: {
            'email': this.uEmail
          }
        }).then(function (res) {
          sessionStorage.setItem('checkCode', res.data.data)
        })
        // 验证码倒计时
        if (!this.timer) {
          this.count = TIME_COUNT
          this.show = false
          this.timer = setInterval(() => {
            if (this.count > 0 && this.count <= TIME_COUNT) {
              this.count--
            } else {
              this.show = true
              clearInterval(this.timer)
              this.timer = null
            }
          }, 1000)
        }
      }
    },
    editPWD(){
      this.editShow=true
    },
    judgeRole(){
      this.role=this.user.role;
      if(this.role==="emp"){
        this.userId=this.user.employerId;
      }else{
        this.userId=this.user.providerId;
      }
    },
    selPwd(){
      this.$axios.post('empcenter/selPwd',this.$qs.stringify({
        role:this.user.role,
        userID:this.userId
      })).then(res=>{
          this.uEmail=res.data.email;
          if(res.data.transactionPwd!='' && res.data.transactionPwd!=null){
            this.twoPwd=true;
          }else{
            this.onePwd=true;
          }
      })
    },
    setUpPwd(){
      this.$refs['ruleForm'].validate((valid)=>{
        if(valid){
          if(this.ruleForm.firstpwd!=this.ruleForm.confirmpwd){
            this.$notify.success({
              title: '两次交易密码不一致',
            });
          }else{
            if(this.ruleForm.code===sessionStorage.getItem("checkCode")){
              this.$axios.post('empcenter/setPwd',this.$qs.stringify({
                role:this.user.role,
                userID:this.userId,
                transactionPwd:this.ruleForm.firstpwd
              })).then(res=>{
                if(res.data==1){
                  this.$notify.success({
                    title: '交易密码设置成功',
                  });
                  this.onePwd=false;
                  this.twoPwd=true;
                }
              })
            }else{
              this.$notify.error({
                title: '验证码有误',
              });
            }
          }
        }
      })
    },
    editPwd(){
      this.$refs['editForm'].validate((valid)=>{
        if(valid){
          if(this.editForm.opwd===this.editForm.tpwd){
              this.$axios.post('empcenter/editPwd',this.$qs.stringify({
                role:this.user.role,
                userID:this.userId,
                oldpwd:this.editForm.oldpwd,
                opwd:this.editForm.opwd
              })).then(res=>{
                if(res.data==1){
                  this.$notify.success({
                    title: '交易密码修改成功',
                  });
                  this.editShow=false;
                  this.selPwd();
                }else{
                  this.$notify.error({
                    title: '交易密码修改失败',
                  });
                }
              })
          }else{
            this.$notify.error({
              title: '两次交易密码不一致',
            });
          }
        }
      })
    }
  },
  mounted() {
    this.user=JSON.parse(sessionStorage.getItem("user"));
    this.judgeRole();
    this.selPwd();
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 700px;
  background-color: rgb(248,249,251);
  margin-top: 2%;
  margin-left: 18%;
}
.title{
  margin-top: 10px;
}
</style>
