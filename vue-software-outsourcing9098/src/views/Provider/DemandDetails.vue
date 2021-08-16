<template>
    <el-main>
      <div style="background-color: #f0f2f5">
        <div id="main">

          <div id="demandInfo">
            <div class="title-row">
              <span class="title">{{this.demand.demandTitle}}</span>
              <span class="border">No.{{this.demand.demandId}}</span>
              <span class="status">招募中</span>
            </div>
            <div style="margin-top: 15px;height: 30px;">
              <el-tag>{{this.demand.demandTypeName}}</el-tag>
            </div>
            <div style="margin-top: 20px;clear: left;zoom: 1;">
                <span class="detail-span">
                    <span class="darker">价格</span>
                    ￥{{this.demand.predictPrice}}
                </span>
              <span class="detail-span">
                    <span class="darker">工期</span>
                    {{this.demand.predictTime}}
                </span>
              <span class="detail-span">
                    <span class="darker">发布时间</span>
                    {{this.demand.releaseTime}}
                </span>
              <el-button style="float: right;" type="primary" @click="joinDemand()">参与项目</el-button>
            </div>
          </div>
          <div class="demender-title-div">
            <span style="font-size: 21px;">需求方信息</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color: #F5A623;vertical-align: 2px;padding-left: 6px;">
                    <i class="el-icon-close-notification" width="16px" height="16px" style=" vertical-align: -2px;"></i>
                    请谨慎甄别需求方信息，防止诈骗和非法项目</span>
          </div>
          <div id="employerInfo">
            <div style="float:left">
<!--              <img src="api/images/bg_000.png" alt="头像" style="width: 70px;height:70px;border-radius: 50%;">-->
              <el-image style="width: 70px;height:70px;border-radius: 50%;" :src="'api/images/' + employer.headPath">
                <div slot="error" class="image-slot">
                  <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" style="width: 70px;height:70px;border-radius: 50%;">
                </div>
              </el-image>
            </div>
            <div style="float: left;margin-left: 25px;">
              <div>
                <span style="font-size: 21px;">{{employer.employerName}}</span>
                <span style="font-size: 12px;color: #727F8F;margin-left: 10px;">(发布项目：{{employer.demandCount}} &nbsp;&nbsp;&nbsp;&nbsp; 信用分：{{employer.credit}} 注册时间：{{employer.regTime}})</span>
              </div>
              <div style="height: 50px;line-height: 50px;">
                <span>项目确认合作后可查看需求方联系方式</span>
              </div>
            </div>
          </div>
          <div id="introduce">
            <div style="margin: 28px auto 0;">
              <span style="font-size: 2rem;margin-bottom: 1rem;padding-top: 2rem;">项目描述</span>
            </div>
            <div class="description typo fold">
              {{this.demand.demandDescribe}}
            </div>
            <div style="margin: 28px auto 0;">
              <span style="font-size: 2rem;margin-bottom: 1rem;padding-top: 2rem;">需求文档</span>
            </div>
            <div class="description typo fold" style="padding: 10px;">
                <el-button type="primary" @click="viewWord(this.demand.annexPath)">在线预览</el-button>

                <a :href="'api/images/'+this.demand.annexPath" download="">
                    <el-button type="primary" style="margin-left: 30px;">下载</el-button>
                </a>
<!--              <a :href="'api/images/'+this.demand.annexPath" download="">{{this.demand.annexPath}}</a>-->
            </div>
          </div>
        </div>
      </div>
      <el-dialog title="申请参与项目" :visible.sync="dialogFormVisible">
        <el-form ref="applyForm" :model="form">
          <el-input v-model="form.providerId" type="hidden"></el-input>

          <el-form-item label="预算价格" :label-width="formLabelWidth">
            <el-input v-model="form.price" placeholder="我希望能获得多少报酬？" autocomplete="off" style="margin-left: 25px;width: 250px">
              <template slot="append">元</template>
            </el-input>

          </el-form-item>
          <el-form-item label="预算工期" :label-width="formLabelWidth">
            <el-input v-model="form.projectTime" placeholder="我有信心能在多少工期内完成" autocomplete="off"
                      style="margin-left: 25px;width: 250px;">
              <template slot="append">周</template>
            </el-input>

          </el-form-item>
          <el-form-item label="投标身份" :label-width="formLabelWidth">
            <el-radio-group v-model="form.unionStatus" style="margin-left: 20px;" size="mini">
              <el-radio label="个人"></el-radio>
              <el-radio label="联盟"></el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="简历信息" :label-width="formLabelWidth">
            <el-upload
                    ref="upload"
                    action="api/demand/join"
                    accept=".doc,.docx"
                    list-type="text"
                    drag
                    :file-list="fileList"
                    :auto-upload="false"
                    :data="form"
                    :on-success="uploadSuccess"
                    :before-upload="handleBeforeUpload"
                    :on-preview="handlePictureCardPreview"
                    :on-remove="handleRemove"
                    :on-exceed="handleExceed"
                    :on-change="uploadChange">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="uploadFile()">确 定</el-button>
        </div>
      </el-dialog>

    </el-main>





</template>


<script>
export default {
  name: "GoodsList",
  data () {
    return {
      flag: false,
      count: 0,
      demand:{},
      searchInfo:"",
      demandList:{},
      status:false,
      fileList: [],
      dialogFormVisible: false,
      formLabelWidth: "120px",
      employer:{},
      radio: "",
      form: {
        demandId:"",
        price: "",
        projectTime: "",
        tenderId: "",
          unionStatus:""
      },
    }
  },
  methods: {


    // 上传文件之前的钩子
    handleBeforeUpload(file){},
    handlePictureCardPreview(){},
    handleRemove(){},
    handleExceed(){},
    uploadChange(){},
    joinDemand() {
      console.log(JSON.parse(sessionStorage.getItem("user")))
      if(JSON.parse(sessionStorage.getItem("user")) == null) {
        this.$message({
          message: "请先登录",
          type: "warning"
        });
      }
      else {
        this.dialogFormVisible = true;
      }

    },
    uploadFile() {
      this.form.tenderId = JSON.parse(sessionStorage.getItem("user")).providerId;
      this.form.demandId = this.demand.demandId;
      console.log(this.form);
      console.log(this.fileList);
      this.$refs.upload.submit();
    },

    // 上传文件成功后的回调
    uploadSuccess() {
      this.$message({
          message: "参与成功",
          type: "success"
        });
      this.dialogFormVisible = false;
      this.form = {tenderId: JSON.parse(sessionStorage.getItem("user")).providerId, demandId: this.demand.demandId};
      this.$refs.upload.clearFiles();
    },
      viewWord(annexPath) {
          window.open("http://view.officeapps.live.com/op/view.aspx?src=http://www.voeqmuh.icu:9093/images/" + annexPath, '_blank');
      },
  },

  mounted() {
    console.log(JSON.parse(sessionStorage.getItem("demand")));
    this.demand = JSON.parse(sessionStorage.getItem("demand"));
    this.$axios.get('demand/findEmployerInfo',{params:{demandId: this.demand.demandId}}).then(res =>{
      console.log(res.data)
        this.employer = res.data;
    });
    this.$axios.get('traffic/increase',{params:{id: this.demand.demandId,type:"demand"}}).then(res =>{
    });

  }
}
</script>

<style scoped>
#main{
  width:50%;
  margin: 28px auto 0;
  padding-bottom: 50px;
  background: #fff;
  min-height: 500px;
  font-size: 14px;
  color: #333;
  border-radius: 4px;
}
.demender-title-div {
  border-bottom: 1px solid #EDEDED;
  padding-left: 25px;
  height: 49px;
  line-height: 50px;
}
#demandInfo{
  padding: 30px 25px;
  background: #F7FAFC;
  border-bottom: 1px solid #ededed;
  border-top: 1px solid #fff;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  height: 120px;
}
.title-row{
  min-height: 40px;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  display: -webkit-flex;
}
.title{
  font-size: 28px;
  color: #2D3238;
  -ms-word-break: break-all;
  word-break: break-all;
}
.description{
  font-size: 14px;
  color: #333;
  line-height: 23px;
  word-wrap: break-word;

}
.status{
  font-size: 16px;
  -webkit-box-flex: 1;
  -ms-flex-positive: 1;
  flex-grow: 1;
  -webkit-flex-grow: 1;
  white-space: nowrap;
  text-align: right;
  margin-top: 4px;
  float: right;
  color: #65C279;
}
#employerInfo{
  padding-left: 25px;
  height: 100px;
  padding-top: 31px;
  border-bottom: 1px solid #EDEDED;

}
#introduce{
  padding-left: 25px;
  padding-right: 25px;
}
#list div{
  height:100px;
  border-bottom: 1px solid #eff2f7;
}
.border{
  border-radius: 3px;
  background-color: #8796A8;
  color: #fff;
  font-size: 20px;
  padding: 8px;
  margin-left: 26px;
  height: 25px;
  line-height: 25px;
}
.detail-span{
  float: left;
  padding: 0 14px;
  color: #2D3238;
  font-size: 14px;
  border-right: 1px solid #D9D9D9;
}
.detail-span:first-child {
  padding-left: 0;
}
.darker{
  color: #979FA8;
  margin-right: 8px;
  outline: 0;
}
.el-col {
  border-radius: 4px;
}
.bg-white{
  background: #ffffff;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.goods:hover{
  background: #eff2f7;
}
</style>
