<template>
  <div>
    <div class="info-header-18z8">
        <div class="avatar-1fLVZ">
            <div class="avatar-modal-1W-YB">
                <i class="fa fa-pencil-square-o"></i>
            </div>
          <div style="margin-top: -5px;margin-left: 20px;">
          <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          </div>
          <div>
            <div class="avatar-upload-BW5_P"><input></div>
          </div>
        </div>
      <div class="info-2gxAC" style="margin-left: 10%;">
        <span>张景达</span>
        <small>zzzjd</small>
      </div>
    </div>
    <div class="section-21WOI clearfix" style="margin-left: 10%">
      <p style="font-size: 20px;" >账户信息</p>
      <el-divider style="width: 20%"></el-divider>
      <div class="tips-h1eUF">
        <p>标有星号 (*) 的栏目为必填栏目, 并请验证邮箱和手机。</p>
        <p>您的个人信息不会透露给任何第三方，请放心填写。</p>
      </div>
      <div>
          <p>
            <span>工作邮箱</span>
            <span v-if="email" style="margin-left: 100px;">782294155@qq.com</span>
            <span style="margin-left: 50%;">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm"  class="demo-ruleForm">
                <el-input v-model="ruleForm.email" class="email" placeholder="邮箱" style="width: 200px;"></el-input>
                <el-input prop="code" v-model="ruleForm.code" placeholder="验证码" style="width: 200px;"></el-input>
                <button @click="getCode()" class="code-btn" :disabled="!show">
                  <span v-show="show">发送验证码</span>
                  <span v-show="!show" class="count">{{count}} s</span>
                </button>
            </el-form>
            </span>
            <el-link icon="el-icon-edit" @click="abcd">编辑</el-link>
          </p>


      </div>

    </div>
  </div>

</template>

<script>
const TIME_COUNT = 60 // 设置一个全局的倒计时的时间
export default {
  name: "Information",
  data() {
    // 邮箱校验
    let validateMobile = (rule, value, callback) => {
      if (!value) {
        callback(new Error('邮箱不可为空'))
      } else {
        if (value !== '') {
          let reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
          if (!reg.test(value)) {
            callback(new Error('请输入格式正确有效的邮箱号'))
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
    return {
      imageUrl: '',
      emailInput:false,
      email:true,
      ruleForm: {
        email: '',
        code: ''
      },
      show: true,
      count: '',
      timer: null,
      rules: {
        email: [
          { validator: validateMobile, trigger: 'blur' }
        ],
        code: [
          { validator: validateCheckCode, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    abcd(){
      this.emailInput=true;
      this.email=false
    }
  }
}
</script>

<style  scoped>
.info-header-18z8{
  font-size: 1rem;
  display: -ms-flexbox;
  display: flex;
  padding: 1.5em;
  border: 1px solid #dde3eb;
  border-radius: 4px;
  margin-bottom: 2.5rem;
  width: 80%;
  margin-left: 10%;
}
 .avatar-1fLVZ {
  position: relative;
  width: 7em;
  height: 7em;
  -ms-flex: 1;
  flex: 1;
}
 .avatar-modal-1W-YB {
  display: none;
  width: 7em;
  height: 7em;
  background: #1b3649;
  position: absolute;
  border-radius: 50%;
  opacity: .7;
  text-align: center;
}
.info-header-18z8- .avatar-modal-1W-YB i {
  font-size: 1.1rem;
  line-height: 7em;
  color: #fff;
}
.avatar-img-2Tn2a {
  width: 7em;
  border-radius: 50%;
}
img {
  border-style: none;
}
*, :after, :before {
  box-sizing: border-box;
}
* {
  -webkit-tap-highlight-color: rgba(0,0,0,0);
}
.avatar-upload-BW5_P {
  font-size: 1rem;
  position: absolute;
  height: 7em;
  width: 7em;
  left: 0;
  top: 0;
  z-index: 100;
  overflow: hidden;
  opacity: 0;
}
button, input, select, textarea {
  font-family: inherit;
  font-size: inherit;
  line-height: inherit;
  color: inherit;
}
.info-2gxAC {
  font-size: 1.5rem;
  -ms-flex: 4;
  flex: 4;
  font-weight: 400;
  margin-left: .5em;
  position: relative;
}
.info-header-18z8- .info-2gxAC span {
  display: block;
  margin-top: .2em;
}
small {
  font-size: 80%;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.clearfix {
  zoom: 1;
}
.container-1weyJ p {
  padding: 0;
  margin: 0;
}
.el-divider{
  width:89%;
}
.tips-h1eUF p {
  font-size: 1rem;
  color: #8796a8;
  margin-bottom: .6em;
}
</style>
