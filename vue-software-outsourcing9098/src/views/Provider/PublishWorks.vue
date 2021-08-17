<template>
  <div class="news">
    <el-button type="primary" @click="openDialog()">添加更多作品</el-button>
    <p style="margin-top: 10px;"> 作品列表：</p>
    <div style="display:flex;flex-flow: wrap;">
      <div v-for="item in worksList" style="margin: 20px;" @click="editWorks()">
        <img :src="'api/images/'+item.annexPath" alt="" style="width: 150px;height: 150px;border: 1px solid black;"/>
        <p style="margin: 20px"> {{item.worksTitle}} </p>
      </div>
    </div>

<!--    <el-table :data="tableData" border style="width: 100%" v-loading="loading">-->
<!--      <el-table-column prop="Id" label="序号" width="180"></el-table-column>-->
<!--      <el-table-column prop="Title" label="新闻标题" width="180"></el-table-column>-->
<!--      <el-table-column prop="Img" label="图片">-->
<!--        <template slot-scope="scope">-->
<!--          <img style="width:100%" :src="imgserver + scope.row.Img" alt />-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="Content" label="新闻内容">-->
<!--        <template slot-scope="scope">-->
<!--          <p v-if="scope.row.Content.length > 100">{{scope.row.Content.substring(0,100)}} ...</p>-->
<!--          <p v-else>{{scope.row.Content}}</p>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="Type" label="新闻类别">-->
<!--        <template slot-scope="scope">{{scope.row.Type == 1 ? '公司新闻':'行业动态'}}</template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="操作">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--              type="primary"-->
<!--              icon="el-icon-edit"-->
<!--              @click="handleEdit(scope.$index, scope.row)"-->
<!--          ></el-button>-->
<!--          <el-button-->
<!--              type="danger"-->
<!--              icon="el-icon-delete"-->
<!--              @click="handleDelete(scope.$index, scope.row)"-->
<!--          ></el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->
    <!--  -->
    <el-dialog title="添加案例作品" :visible.sync="dialogFormVisible">
      <el-form ref="workForm" :model="form">
        <el-input v-model="form.providerId" type="hidden"></el-input>
        <el-form-item label="作品图片" :label-width="formLabelWidth">
          <el-upload
            ref="upload"
            action="file/upload/file"
            accept="image/png,image/gif,image/jpg,image/jpeg"
            list-type="picture-card"
            :auto-upload="false"
            :data="form"
            :file-list="fileList"
            :on-exceed="handleExceed"
            :on-change="uploadChange"
            :on-success="uploadSuccess"
            :before-upload="handleBeforeUpload"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="项目类型" :label-width="formLabelWidth" >
          <el-radio  v-model="form.worksTypeId" v-for="ck in checklist" :key="ck.demandTypeId"  :label="ck.demandTypeId" ><i v-bind:class="ck.icon">&nbsp;&nbsp;{{ck.demandTypeName}}</i></el-radio>
        </el-form-item>
        <el-form-item label="作品名称" :label-width="formLabelWidth">
          <el-input v-model="form.worksTitle" autocomplete="off" style="margin-left: 25px;width: 550px"></el-input>
        </el-form-item>
        <el-form-item label="作品描述" :label-width="formLabelWidth">
          <el-input v-model="form.worksDescribe" autocomplete="off" style="margin-left: 25px;width: 550px;"></el-input>
        </el-form-item>
        <el-form-item label="作品定价" :label-width="formLabelWidth">
          <el-input v-model="form.worksPrice" autocomplete="off" style="margin-left: 25px;width: 230px;"></el-input>&nbsp;&nbsp;&nbsp;元
        </el-form-item>
        <el-form-item label="作品链接" :label-width="formLabelWidth">
          <el-input v-model="form.worksAddress" autocomplete="off" style="margin-left: 25px;width: 550px;"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="uploadFile()">确 定</el-button>
      </div>
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
      formData: {
        Id: 0,
        Title: "",
        Img: "",
        Type: 1,
        Content: "",
        CreateTime: new Date(),
      },
      dialogFormVisible: false,
      formLabelWidth: "120px",
      formLabelWidth1: "80px",
      loading: true,
      fileList: [],
      limitNum: 1,
      checklist:[],
      providerId:"",
      form: {
        worksTitle: "",
        worksDescribe:"",
        worksPrice:"",
        worksAddress:"",
        providerId:"",
        workTypeId:"",
        annexPath:"",
        imgUrl:""
      },
      worksList: [],
      baseURL: "",
    };
  },
  mounted() {
    console.log("=========");
    console.log(JSON.parse(sessionStorage.getItem("user")).providerId);
    console.log("=========");



    this.selcheckList();
    let token = "Browser " + sessionStorage.getItem("token");
    this.providerId = JSON.parse(sessionStorage.getItem("user")).providerId;
    //window.console.log(token);
    this.options = {
      headers: {
        Authorization: token
      }
    };
    this.headers = {
      Authorization: token
    };
    this.loadWorkList();
    this.loadData();
    let params=this.$qs.stringify( {
      employerId:"1",
    })

  },
  methods: {
    loadWorkList(){
       this.$axios.get('work/list',{params:{providerId:this.providerId}}).then(res =>{
        console.log(res.data);
        this.worksList = res.data;
      });
    },
      // 上传文件之前的钩子
    handleBeforeUpload(file){
      console.log('before')
      if(!(file.type === 'image/png' || file.type === 'image/gif' || file.type === 'image/jpg' || file.type === 'image/jpeg')) {
        this.$notify.warning({
          title: '警告',
          message: '请上传格式为image/png, image/gif, image/jpg, image/jpeg的图片'
        })
      }
      let size = file.size / 1024 / 1024 / 2;
      if(size > 2) {
        this.$notify.warning({
          title: '警告',
          message: '图片大小必须小于2M'
        })
      }
    },
     uploadChange(file, fileList) {
      this.fileList = fileList;
      console.log(this.fileList);
      // this.form.MultipartFile = fileList[0].raw;
      // console.log(this.form.MultipartFile);
    },
    // 文件超出个数限制时的钩子
    handleExceed(files, fileList) {

    },
    // 文件列表移除文件时的钩子
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    // 点击文件列表中已上传的文件时的钩子
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    uploadFile() {
      // this.$refs.upload.submit();

      let formData = new FormData();  //  用FormData存放上传文件

        for(let i=0;i< this.fileList.length;i++){
            let fileList = this.fileList[i].raw;
            console.log(fileList);
            formData.append('file', fileList);
        }
         let config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
        this.$axios.post('work/upload',formData,config).then(response => {
            this.form.annexPath = response.data.annexPath;
            this.form.imgUrl = response.data.imgUrl;
            this.form.providerId = this.providerId;
            this.$axios.get('work/insert',{params:this.form}).then(response => {
              this.dialogFormVisible = false;
              this.$refs.upload.clearFiles();
              this.loadWorkList();
            });
        });


      // this.$refs['uploadFile'].validate(valid =>{
      //   if(valid) {
      //     let params = this.form;
      //     uploadFile(params).then(res => {
      //         console.log(res);
      //     })
      //   }
      // })
    },
    // 上传文件成功后的回调
    uploadSuccess(res, file) {
      console.log("上传成功")
      // console.log(res);

      // this.form = {};
      // this.$refs.upload.clearFiles();
      // this.form.providerId = JSON.parse(sessionStorage.getItem("user")).providerId;
      // this.$axios.get('work/list').then(res =>{
      //   console.log(res.data);
      //   this.worksList = res.data;
      // })
    },
    getImg(path){
      // this.$axios.request({
      //   url: "/work/img?path=" + path,
      //   headers: {
      //     token: localStorage.getItem("token"),
      //   },
      //     responseType: "arraybuffer",
      //   })
      //   .then((res) => {
      //     //将后端的图片转换为base64去显示
      //     // console.log(res.data);
      //     baseURL = "data:image/png;base64," + btoa(new Uint8Array(res.data).reduce((data, byte) => data + String.fromCharCode(byte), ""));
      //     return baseURL;
      //     // console.log(res);
      //   });
      let str = path.split("/");
      return str[str.length - 1];

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
      this.$http
          .get("News/GetNewsAll?type=0&num=10")
          .then(response => {
            // window.console.log(response);
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
    openDialog() {
      // 清除数据
      this.formData.Id = 0;
      this.formData.Title = "";
      this.formData.Img = "";
      this.formData.Type = 1;
      this.formData.Content = "";
      this.formData.CreateTime = new Date();

      this.dialogFormVisible = true;
      this.form = {
        worksTitle: "",
        worksDescribe:"",
        worksPrice:"",
        worksAddress:"",
        providerId:"",
        workTypeId:"",
        annexPath:"",
        imgUrl:""
      };

    },
    handleCreateOrModify() {
      if (!this.formData.Id) {
        this.loading = true;
        this.$http
            .post("News/CreateNews", this.formData, this.options)
            .then(response => {
              window.console.log(response);
              this.loading = false;
              this.$message({
                message: "创建成功！",
                type: "success"
              });
              this.dialogFormVisible = false;
              this.loadData();
            })
            .catch(e => {
              this.$message({
                message: "网络或程序异常！" + e,
                type: "error"
              });
            });
      } else {
        this.loading = true;
        this.$http
            .post("News/ModifiedNews", this.formData, this.options)
            .then(response => {
              this.loading = false;
              window.console.log(response);
              this.$message({
                message: "修改成功！",
                type: "success"
              });
              this.dialogFormVisible = false;
              this.loadData();
            })
            .catch(e => {
              this.$message({
                message: "网络或程序异常！" + e,
                type: "error"
              });
            });
      }
    },
    //编辑
    handleEdit(index, row) {
      //index:第几行   row:这一行的数据
      window.console.log(index, row);
      this.formData = row;
      this.dialogFormVisible = true;
    },
    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
          .then(() => {
            // 已确认删除
            // 调接口删除
            this.loading = true;
            this.$http
                .post(`News/DeleteNews?id=${row.Id}`, null, this.options)
                .then(response => {
                  this.loading = false;
                  window.console.log(response);
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
