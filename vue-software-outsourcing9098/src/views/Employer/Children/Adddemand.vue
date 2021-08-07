<template>
  <div class="news">
    <el-button type="primary" @click="openDialog()">新增需求</el-button>
    <el-table :data="tableData" border style="width: 100%" v-loading="loading">
      <el-table-column prop="demandId" label="序号" width="100"></el-table-column>
      <el-table-column prop="demandTitle" label="项目名称" width="180"></el-table-column>
      <el-table-column prop="predictTime" label="交付周期(天)" width="120"></el-table-column>
      <el-table-column prop="predictPrice" label="项目预算(元)" width="120"></el-table-column>
      <el-table-column prop="demandType.demandTypeName" label="项目类别" width="100"></el-table-column>

      <el-table-column prop="demandDescribe" label="项目描述">
        <template slot-scope="scope">
          <p v-if="scope.row.demandDescribe.length > 100">{{scope.row.Content.substring(0,50)}} ...</p>
          <p v-else>{{scope.row.demandDescribe}}</p>
        </template>
      </el-table-column>

      <el-table-column  label="操作">
        <template slot-scope="scope">
          <el-button
              type="primary"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
          ></el-button>
          <el-button
              type="danger"
              icon="el-icon-delete"
              @click="handleDelete(scope.$index, scope.row)"
          ></el-button>
          <el-button v-if="scope.row.annexPath!=null" :disabled="false"  icon="el-icon-download" type="primary" @click="downloadFile(scope.row.annexPath)"></el-button>
          <el-button v-else :disabled="true"  icon="el-icon-download" type="primary" @click="downloadFile(scope.row.annexPath)"></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--添加需求-->
    <el-dialog title="发布需求" :visible.sync="dialogFormVisible">
      <el-form
          ref="needForm"
          :model="form"
      >
        <el-form-item label="项目类型" :label-width="formLabelWidth" >
          <el-radio  v-model="form.demandTypeId" v-for="ck in checklist" :key="ck.demandTypeId"  :label="ck.demandTypeId" ><i v-bind:class="ck.icon">&nbsp;&nbsp;{{ck.demandTypeName}}</i></el-radio>
        </el-form-item>
        <el-form-item label="项目名称" :label-width="formLabelWidth">
          <el-input v-model="form.demandTitle" autocomplete="off" style="margin-left: 25px;width: 550px"></el-input>
        </el-form-item>
        <el-form-item label="项目预算" :label-width="formLabelWidth">
          <el-input v-model="form.predictPrice" autocomplete="off" style="margin-left: 25px;width: 230px;"></el-input>&nbsp;&nbsp;&nbsp;元
        </el-form-item>
        <el-form-item label="期望交付周期" label-width="145px">
          <el-input v-model="form.predictTime" autocomplete="off" style="width: 230px;"></el-input>&nbsp;&nbsp;&nbsp;天
        </el-form-item>


        <el-form-item label="项目描述" :label-width="formLabelWidth">
          <el-input type="textarea" :rows="10" v-model="form.demandDescribe" autocomplete="off" style="margin-left: 25px;width: 550px;"></el-input>
        </el-form-item>
        <el-form-item label="相关文档" :label-width="formLabelWidth">
          <el-upload
              ref="upload"
              action="api/empcenter/file"
              :limit=limitNum
              :auto-upload="false"
              :before-upload="beforeUploadFile"
              :on-change="fileChange"
              :on-exceed="exceedFile"
              :on-success="handleSuccess"
              :on-error="handleError"
              :file-list="fileList"
              :data="form">
            <el-button size="small" plain>选择文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传xlsx(Excel2007)文件，且不超过10M</div>
          </el-upload>
        </el-form-item>
          <el-form-item style="margin-left:40%;">
            <el-button size="small" type="primary" @click="uploadFile('needForm')">立即发布</el-button>
            <el-button size="small" @click="dialogFormVisible = false">取消</el-button>
          </el-form-item>
<!--        </el-form>-->
      </el-form>
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button @click="dialogFormVisible = false">取 消</el-button>-->
<!--        <el-button type="primary" @click="handleCreateOrModify()">确 定</el-button>-->
<!--      </div>-->
    </el-dialog>

    <!--编辑-->
    <el-dialog title="需求编辑" :visible.sync="editFormVisible">
      <el-form
          ref="editForm"
          :model="editform"
      >
        <el-form-item label="项目类型" :label-width="formLabelWidth" >
          <el-radio  v-model="editform.demandTypeId" v-for="ck in checklist" :key="ck.demandTypeId"  :label="ck.demandTypeId" ><i v-bind:class="ck.icon">&nbsp;&nbsp;{{ck.demandTypeName}}</i></el-radio>
        </el-form-item>
        <el-form-item label="项目名称" :label-width="formLabelWidth">
          <el-input v-model="editform.demandTitle" autocomplete="off" style="margin-left: 25px;width: 550px"></el-input>
        </el-form-item>
        <el-form-item label="项目预算" :label-width="formLabelWidth">
          <el-input v-model="editform.predictPrice" autocomplete="off" style="margin-left: 25px;width: 230px;"></el-input>&nbsp;&nbsp;&nbsp;元
        </el-form-item>
        <el-form-item label="期望交付周期" label-width="145px">
          <el-input v-model="editform.predictTime" autocomplete="off" style="width: 230px;"></el-input>&nbsp;&nbsp;&nbsp;天
        </el-form-item>

        <el-form-item label="项目描述" :label-width="formLabelWidth">
          <el-input type="textarea" :rows="10" v-model="editform.demandDescribe" autocomplete="off" style="margin-left: 25px;width: 550px;"></el-input>
        </el-form-item>
        <el-form-item label="相关文档" :label-width="formLabelWidth">
          <el-upload
              ref="editupload"
              action="api/empcenter/editfile"
              :limit=limitNum
              :auto-upload="false"
              :before-upload="beforeUploadFile"
              :on-change="fileChange"
              :on-exceed="exceedFile"
              :on-success="handleSuccess"
              :on-error="handleError"
              :data="editform"
              :file-list="editfileList">
            <el-button size="small" plain>选择文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传xlsx(Excel2007)文件，且不超过10M</div>
          </el-upload>
        </el-form-item>
        <el-form-item style="margin-left:40%;">
          <el-button size="small" type="primary" @click="edituploadFile(editform)">修改</el-button>
          <el-button @click="editFormVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "loginNews",
  data() {
    return {
      options: {},
      headers: {},
      tableData: [],
      checklist:[],
      formData: {
        Id: 0,
        Title: "",
        Img: "",
        Type: 1,
        Content: "",
        CreateTime: new Date(),
      },
      dialogFormVisible: false,
      editFormVisible:false,
      formLabelWidth: "120px",
      formLabelWidth1: "80px",
      loading: true,
      fileList: [],
      editfileList:[
        {name:'',url:''}
      ],
      limitNum: 1,
      form: {
        demandTitle: "",
        predictPrice:"",
        predictTime:"",
        demandTypeId:"",
        releaseTime:"",
        demandDescribe:"",
        employerId:""
      },
      editform:{
        demandId:"",
        demandTitle: "",
        predictPrice:"",
        predictTime:"",
        demandTypeId:"",
        releaseTime:"",
        demandDescribe:""
      },
      user:""
    };
  },
  mounted() {
    let token = "Browser " + sessionStorage.getItem("token");
    this.options = {
      headers: {
        Authorization: token
      }
    };
    this.headers = {
      Authorization: token
    };
    this.user=JSON.parse(sessionStorage.getItem('user'));//获取登录储存的雇主
    this.selcheckList();
    this.loadData();

  },
  methods: {
    // 文件超出个数限制时的钩子
    exceedFile(files, fileList) {
      this.$notify.warning({
        title: '警告',
        message: `只能选择 ${this.limitNum} 个文件，当前共选择了 ${files.length + fileList.length} 个`
      });
    },
    // 文件状态改变时的钩子
    fileChange(file, fileList) {
      console.log('change')
      console.log(file)
      this.form.file = file.raw
      console.log(this.form.file)
      console.log(fileList)
    },
    // 上传文件之前的钩子, 参数为上传的文件,若返回 false 或者返回 Promise 且被 reject，则停止上传
    beforeUploadFile(file) {
      console.log('before upload')
      console.log(file)
      let extension = file.name.substring(file.name.lastIndexOf('.')+1)
      let size = file.size / 1024 / 1024
      // if(extension !== 'xlsx') {
      //   this.$notify.warning({
      //     title: '警告',
      //     message: `只能上传Excel2017（即后缀是.xlsx）的文件`
      //   });
      // }
      if(size > 10) {
        this.$notify.warning({
          title: '警告',
          message: `文件大小不得超过10M`
        });
      }
    },
    // 文件上传成功时的钩子
    handleSuccess(res, file, fileList) {
      this.loadData();
      this.$notify.success({
        title: '发布成功',
        message: `文件上传成功`
      });
    },
    // 文件上传失败时的钩子
    handleError(err, file, fileList) {
      this.$notify.error({
        title: '错误',
        message: `文件上传失败`
      });
    },
    uploadFile() {//提交文件、form表单
      if(this.fileList.length<0){
        this.$axios.post("/empcenter/file",this.$qs.stringify({
          demandId:this.form.demandId,
          demandTitle: this.form.demandTitle,
          predictPrice:this.form.predictPrice,
          predictTime:this.form.predictTime,
          demandTypeId:this.form.demandTypeId,
          demandDescribe:this.form.demandDescribe,
          employerId:this.form.employerId
        })).then(response=>{
          this.$notify.error({
            title: response.data,
            message: '需求发布'
          });
        })
      }else{
        this.$refs.upload.submit();
      }
      this.dialogFormVisible=false;

    },
    selcheckList(){
      this.$axios.post("/empcenter/ckList").then(response =>{
        this.checklist=response.data;
      }).catch(e => {
        this.$message({
          message: "网络或程序异常！" + e,
          type: "error"
        });
      });
    },
    loadData() {
      this.loading = true;
      this.$axios.post("/empcenter/needList",this.$qs.stringify({
        "employerId":this.user.employerId,
      })).then(response => {
            this.tableData = response.data;
            this.loading = false;
          })
          .catch(e => {
            this.$message({
              message: "网络或程序异常！" + e,
              type: "error"
            });
          });
    },

    async downloadFile (name) {
      window.location.href="http://127.0.0.1:9093/empcenter/download?name="+name;
    },

    openDialog() {
      this.form.employerId=this.user.employerId
      // 清除数据
      this.form.demandTitle = "";
      this.form.predictPrice = "";
      this.form.predictTime = "";
      this.form.demandTypeId = "";
      this.form.releaseTime="",
      this.form.demandDescribe = "";
      this.fileList=[];
      this.dialogFormVisible = true;
    },
    //编辑
    handleEdit(index, row) {
      window.console.log(index, row);
      this.editform= row;
      this.editfileList.name=row.annexPath;
      this.editFormVisible = true;
    },
    edituploadFile(){
      this.$axios.post("/empcenter/editfile",
          this.$qs.stringify({
            demandId:this.editform.demandId,
            demandTitle: this.editform.demandTitle,
            predictPrice:this.editform.predictPrice,
            predictTime:this.editform.predictTime,
            demandTypeId:this.editform.demandTypeId,
            demandDescribe:this.editform.demandDescribe
          })).then(response=> {
        this.$message({
          message:response.data,
          type: "success"
        });
        this.editFormVisible = false;
        this.selcheckList();
        this.loadData();
      }).catch(e => {
        this.$message({
          message: "网络或程序异常！" + e,
          type: "error"
        });
      });
    },

    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
            // 已确认删除
            // 调接口删除
            this.loading = true;
            console.log();
            this.$axios.post(`/empcenter/delfile`, this.$qs.stringify({
              demandId:row.demandId
            })).then(response => {
                  this.loading = false;
                  this.$message({
                    message: "删除成功！",
                    type: "success"
                  });
                  this.loadData();
                })
                .catch(e => {
                  this.$message({
                    message: "网络或程序异常！" + e,
                    type: "error"
                  });
                });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });
    },
  }
};
</script>

<style lang="scss" scoped>
.el-table {
  margin-top: 20px;
}
</style>
