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
              action="api/empcenter/uploadhead"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              :data="userForm">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          </div>
          <div>
            <div class="avatar-upload-BW5_P"><input></div>
          </div>
        </div>
      <div class="info-2gxAC" style="margin-left: 10%;">
        <p>{{infoList.employerName}}</p>
        <div style="margin-top: 20px;"><p>{{infoList.phoneNumber}}</p></div>
      </div>

    </div>
    <div class="section-21WOI clearfix" style="margin-left: 10%">
      <p style="font-size: 20px;" >账户信息</p>
      <el-divider style="width: 20%"></el-divider>
      <div class="tips-h1eUF">
        <p>请验证邮箱，以便我们联系您。</p>
        <p>您的个人信息不会透露给任何第三方，请放心填写。</p>
      </div>
      <div>
        <div style="margin-top: 30px;">
            <span>工作邮箱:</span>
            <span v-if="emailShow" style="margin-left: 100px;">{{infoList.email}}</span>
            <el-link v-if="emailEdit" icon="el-icon-edit" @click="editEm" style="margin-left: 83%;margin-top: -50px;">编辑</el-link>
        </div>
            <div style="margin-top: -3%;" v-if="emailForm">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm"  class="demo-ruleForm">
              <el-form-item prop="email" style="margin-left: 20%;">
              <el-input v-model="ruleForm.email" class="email" placeholder="邮箱" style="width: 220px;margin-top: -20%;"></el-input>
                <span style="margin-left: 20px;"><el-link  @click="emailCancel" type="primary">取消</el-link></span>
              </el-form-item>
              <el-form-item prop="code" style="margin-left: 20%;">
                <el-input prop="code" v-model="ruleForm.code" placeholder="验证码" style="width: 150px;margin-top: -20%;"></el-input>
                <button @click="getCode()" class="code-btn" :disabled="!show">
                  <span v-show="show">发送验证码</span>
                  <span v-show="!show" class="count">{{count}} s</span>
                </button>
              </el-form-item>
            </el-form>
              <span style="margin-left: 22%;color: dodgerblue"><el-link  @click="editconfirm" type="primary">确定</el-link></span>
            </div>
      </div>
      <div style="margin-top: 3%">
        <span>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:</span>
        <span v-if="nameShow" style="margin-left: 100px;">{{infoList.employerName}}</span>
        <el-link v-if="nameEdit" icon="el-icon-edit" @click="editName" style="margin-left: 83%;margin-top: -50px;">编辑</el-link>
       <div v-if="nameInput" style="margin-top: -3%;margin-left: 20%">
         <el-input v-model="oldName"  placeholder="昵称" style="width: 220px;"></el-input>
         <span style="margin-left: 20px;"><el-link  @click="nameCancel" type="primary">取消</el-link></span>
         <div  style="margin-left: 5%;margin-top: 20px;"><span><el-link  @click="nameconfirm" type="primary">确定</el-link></span></div>
       </div>
      </div>
      <p style="font-size: 20px;margin-top: 20px;" >我的信用分</p>
      <el-divider style="width: 20%"></el-divider>
      <div style="margin-top: 3%">
        <span>信用分&nbsp;&nbsp;&nbsp;:</span>
        <span  style="margin-left: 100px;">{{infoList.credit}}</span>
        <div style="margin-top: -2%"><span style="margin-left: 80%;"><el-link @click="readCredit" type="primary">查看信用详情</el-link></span></div>
      </div>
      <div>
        <el-table
            v-if="creditShow"
            :data="tableData"
            style="width: 100%"
            :default-sort = "{prop: 'date', order: 'descending'}">
          <el-table-column
              prop="creditTime"
              label="日期"
              sortable
              width="180">
          </el-table-column>
          <el-table-column
              prop="remarks"
              label="备注"
              width="180">
          </el-table-column>
          <el-table-column
              prop="userId"
              label="地址">
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>

</template>

<script>
const TIME_COUNT = 120 // 设置一个全局的倒计时的时间
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
      tableData: [],
      user:'',
      imageUrl: '',
      emailInput:false,
      emailShow:true,
      emailEdit:true,
      emailForm:false,
      nameShow:true,
      nameEdit:true,
      nameInput:false,
      creditShow:false,
      ruleForm: {
        email: '',
        code: ''
      },
      userForm:{
        Id:'',
        role:''
      },
      infoList:{},
      oldName:'',
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
      const isJPG = file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 PNG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    editEm(){
      this.emailShow=false,
      this.emailEdit=false,
      this.emailForm=true
    },
    emailCancel(){
      this.emailShow=true,
      this.emailEdit=true,
      this.emailForm=false
    },
    editName(){
      this.nameShow=false,
      this.nameEdit=false,
      this.nameInput=true,
      this.oldName=this.infoList.employerName
    },
    nameCancel(){
      this.nameShow=true,
      this.nameEdit=true,
      this.nameInput=false
    },
    // 向后端发请求的点击事件
    getCode () {
      let _this = this
      if (this.ruleForm.email === '') {
        _this.$message.error('请先输入邮箱再点击获取验证码')
      } else {
        this.$axios({
          method: 'post',
          url: '/mail/getCheckCode',
          data: {
            'email': this.ruleForm.email
          }
        }).then(function (res) {
          sessionStorage.setItem('checkCode', res.data.data)  // 这里我没用redis做缓存，用的浏览器sessionStorage+md5加密存下来的
          console.log("验证码"+res.data.data)
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
    editconfirm(){
    this.$refs['ruleForm'].validate((valid)=>{
      if(valid){
        if(this.ruleForm.code===sessionStorage.getItem("checkCode")){
         this.$axios.post('empcenter/editEmail',this.$qs.stringify({
           employerId:this.user.employerId,
           email:this.ruleForm.email
         })).then(res=>{
           if(res.data==1){
             this.$notify.success({
               title: '邮箱更改成功',
             });
             this.seleInfo();
             this.emailCancel();
           }
         })
        }else{
          this.$notify.error({
            title: '验证码有误',
          });
        }
      }
    })
    },
    nameconfirm(){
      this.$axios.post('empcenter/editEmpName',this.$qs.stringify({
        employerId:this.user.employerId,
        employerName:this.oldName
      })).then(res=>{
        if(res.data==1){
          this.$notify.success({
            title: '名称修改成功',
          });
          this.seleInfo();
          this.nameCancel();
        }else{
          this.$notify.success({
            title: '名称修改失败',
          });
        }
      })
    },
    readCredit(){
      this.$axios.post('empcenter/redCredit',this.$qs.stringify({
        userId:this.user.employerId,
        type:this.user.role
      })).then(res=>{
        this.tableData=res.data;
        this.creditShow=true;
      })
    },
    seleInfo(){
      this.$axios.post('empcenter/centerInfo',this.$qs.stringify({
        employerId:this.user.employerId
      })).then(res=>{
          this.infoList=res.data;
          this.imageUrl='api/images/'+res.data.headPath
      })
    }
  },
  mounted() {
    this.user=JSON.parse(sessionStorage.getItem("user"));
    this.userForm.Id=this.user.employerId;
    this.userForm.role=this.user.role;
    this.seleInfo();
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
  height: 120px;
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
