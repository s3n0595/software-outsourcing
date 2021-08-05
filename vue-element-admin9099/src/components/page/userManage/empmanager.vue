<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>雇主管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-button
          type="primary"
          icon="delete"
          class="handle-del mr10"
          @click="delAll"
          :disabled="this.delData.length===0"
        >批量删除</el-button>
        <el-input v-model="searchInfo" placeholder="请输入用户名查询" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="empList">搜索</el-button>
<!--        <el-button type="primary" @click="addUserVisible=true">新建用户</el-button>-->
      </div>
      <el-table
        :data="this.emps"
        border
        class="table"
        ref="multipleTable"
        @selection-change="handleSelectionChange"
        v-loading="isShowloading"
      >
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column type="index" label="序号" sortable width="100"></el-table-column>
        <el-table-column prop="employerName" label="用户名" width="120"></el-table-column>
        <el-table-column prop="phoneNumber" label="手机号"></el-table-column>
<!--        <el-table-column prop="isable" label="状态" :formatter="formatterState"></el-table-column>-->
        <el-table-column prop="employerInfo.email" label="邮箱"></el-table-column>
        <el-table-column prop="employerInfo.credit" label="信用分"></el-table-column>
        <el-table-column prop="employerInfo.balance" label="账户余额"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template slot-scope="scope">
            <el-button
              type="text"
              icon="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button>
            <el-button
              type="text"
              icon="el-icon-delete"
              class="red"
              @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            layout="total, prev, pager, next, sizes, jumper"
            :total="total"
            :page-size="pageSize"
            :page-sizes="[5,10,15]"
        ></el-pagination>
      </div>
      <!-- 编辑框 -->
      <el-dialog
        title="修改信息"
        :visible.sync="editUserVisible"
        ref="editEmpForm"
        :before-close="confirmClose"
        v-dialogDrag
      >
        <el-form :model="userForm" :rules="editRule" ref="editEmpForm" >
          <div style="display: none"><el-input v-model="userForm.employerId" autocomplete="off"></el-input></div>
          <el-form-item label="用户名" :label-width="formLabelWidth" prop="employerName">
            <el-col :span="12">
            <el-input v-model="userForm.employerName" autocomplete="off"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="手机号" :label-width="formLabelWidth" prop="phoneNumber">
            <el-col :span="12">
            <el-input v-model="userForm.phoneNumber" autocomplete="off" ></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="邮箱" :label-width="formLabelWidth" prop="employerInfo.email">
            <el-col :span="12">
            <el-input v-model="userForm.employerInfo.email" autocomplete="off"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="信用分" :label-width="formLabelWidth" prop="employerInfo.credit">
            <el-col :span="12">
            <el-input v-model="userForm.employerInfo.credit" autocomplete="off"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="账户余额" :label-width="formLabelWidth" prop="employerInfo.balance">
            <el-col :span="12">
            <el-input v-model="userForm.employerInfo.balance" autocomplete="off"></el-input>
            </el-col>
          </el-form-item>
<!--          <el-form-item label="状态" :label-width="formLabelWidth">-->
<!--            <el-radio v-model="userForm.isable" label="1">正常</el-radio>-->
<!--            <el-radio v-model="userForm.isable" label="0">禁用</el-radio>-->
<!--          </el-form-item>-->
        </el-form>
        <div slot="footer" class="dialog-footer" style="margin-right: 45%;">
          <el-button @click="cancel(),editUserVisible=false">取 消</el-button>
          <el-button type="primary" @click.native="editUser">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 新建用户 -->
      <el-dialog title="新建用户" :visible.sync="addUserVisible" ref="addUserForm" v-dialogDrag>
        <el-form :model="addUserForm" :rules="addUserRule">
          <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
            <el-input v-model="addUserForm.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="角色名称" :label-width="formLabelWidth" prop="rolename">
            <el-select v-model="addUserForm.rolename">
              <el-option label="admin" value="admin"></el-option>
              <el-option label="管理员" value="管理员"></el-option>
              <el-option label="超级管理员" value="超级管理员"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="状态" :label-width="formLabelWidth">
            <el-radio v-model="addUserForm.isable" label="1">正常</el-radio>
            <el-radio v-model="addUserForm.isable" label="0">禁用</el-radio>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addUserVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveUser">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import {
  getAddUser,
  // testApi,

  getEmpList,
  getEditemp,
  getSelEmpList,
  getDeleteOne,
  getDeleEmp
} from "../../../api/api";
export default {
  data() {
    return {
      url:"",
      searchInfo: "",
      emps: [],
      total: 0,
      pageNo: 1,
      pageSize: 5,
      isShowloading: false,
      delData: [], //删除的数据
      editUserVisible: false, //是否显示编辑
      addUserVisible: false, //新建用户框
      eId:"",
      userForm: {
        employerName:'',
        phoneNumber:'',
        employerInfo:{
          email:'',
          credit:'',
          balance:''
        }
      }, //编辑数据
      employerInfo:[],

      addUserForm: {
        username: "",
        rolename: "",
        isable: "0"
      }, //添加用户数据
      addUserRule: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        rolename: [
          {
            required: true,
            message: "请选择角色",
            trigger: "change"
          }
        ]
      },
      editEmpForm:{
        employerName:"",
        credit:"",
        balance:"",
        phoneNumber:"",
        email:"",
        employerInfoId:""
      },
      //编辑的正则判断
      editRule: {
        employerName:[
          { required: true, message: '请填写用户名称', trigger: 'blur' },
        ],
        phoneNumber:[
          { required: true, message: '请填写手机号', trigger: 'blur' },
          {
            pattern:/^1[12345789]\d{9}$/,
            message: '请输入正确的手机号码',
          }
        ],
        'employerInfo.email': [
          { required: true, message: '请填写E-mail', trigger: 'blur' },
          { pattern: /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/,
            message: '请输入正确的E-mail'
          }
        ],
        'employerInfo.credit': [
          { required: true, message: '请填写信用分', trigger: 'blur' },
          { pattern: /^[0-9]*$/,
            message: '请输入正确的信用分'
          }
        ],
        'employerInfo.balance': [
          { pattern: /^\d+(\.\d{1,2})?$/,
            message: '请输入正确的账户余额（保留2位小数）'
          }
        ],
      },
      formLabelWidth: "230px"
    };
  },
  methods: {
    formatterState(row, cloumn) {
      return row.isable == "1" ? "正常" : "禁用";
    },
    getUsers() {
      this.isShowloading = true;
      let params = {
        searchInfo: this.searchInfo.trim(),
      };
      getSelEmpList(params).then(res => {
        this.emps = res.data;
        this.isShowloading = false;
      });
    },
    handleCurrentChange:function (cPage){
      this.pageNo=cPage;
      this.empList();
    },
    handleSizeChange:function (val){
      this.pageSize=val;
      this.empList();
    },
    handleSelectionChange(delData) {
      this.delData = delData;
    },
    saveUser() {
      let params = Object.assign({}, this.addUserForm);
      params.username = params.username.trim();
      getAddUser(params).then(res => {
        this.$message({
          message: "添加成功",
          type: "success"
        });
        this.addUserVisible = false;
        this.addUserForm = {};
        this.getUsers();
      });
    },
    delAll() {
      this.$confirm("确认删除所选中的用户吗?", "提示", {
        type: "warning"
      }).then(() => {
        this.isShowloading = true;
        let delIds = this.delData.map(item => item.employerId).toString();
        let params = {
          delIds: delIds
        };
        getDeleEmp(params).then(res => {
          this.editUserVisible = false;
          this.$message({
            message: res.data,
            type: "success"
          });
          this.empList();
        });
      });
    },
    handleEdit(index, row) {
      this.editUserVisible = true;
      this.userForm = Object.assign({}, row);
      console.log("abc"+index);
      console.log(row);
    },
    handleDelete(index, row) {
      let params = {
        employerId: row.employerId
      };
      this.$confirm("确认删除该用户？", "提示", {
        type: "warning"
      }).then(() => {
        getDeleteOne(params).then(res => {
          this.$message({
            type: "success",
            message: res.data
          });
          this.empList();
        });
      });
    },
    editUser() {
      this.$refs['editEmpForm'].validate((valid) => {
        if (valid) {
          let params =
              {
            employerId:this.userForm.employerId,
            employerName:this.userForm.employerName,
            phoneNumber:this.userForm.phoneNumber,
            'employerInfo.email': this.userForm.employerInfo.email,
            'employerInfo.balance': this.userForm.employerInfo.balance,
            'employerInfo.credit': this.userForm.employerInfo.credit,
            'employerInfo.employerInfoId': this.userForm.employerInfo.employerInfoId,
          }
          getEditemp(params).then(res => {
            this.$message({
                message: res.data,
                type: "success",
              });
            this.empList();
            this.editUserVisible = false;
            this.userForm = {
              employerName:'',
              phoneNumber:'',
              employerInfo:{
                email:'',
                credit:'',
                balance:''
              }};
            }).bind(this);
        }
      });
    },
    cancel(){
      this.empList();
    },
    confirmClose(done) {
      this.$confirm("确认关闭将丢失已编辑内容？", "提示", {
        type: "warning"
      }).then(() => {
        this.userForm ={
          employerName:'',
          phoneNumber:'',
          employerInfo:{
            email:'',
            credit:'',
            balance:''
          }
        };
        done();
        this.empList();
      });
    },
    // testFn(){
    //   this.url = '/ms/table/list';
    //   this.$axios.get(this.url).then(res=>{
    //     console.log(res)
    //   })
      // testApi().then(res=>{
      //   console.log(res)
      // })
    // },
    //获取雇主列表信息
    empList(){
      this.isShowloading=true;
      let params =
          {
            searchInfo:this.searchInfo.trim(),
            pageNo:this.pageNo,
            pageSize:this.pageSize,
          }
      getEmpList(params).then(res=>{
        console.log(res);
        this.isShowloading=false;
        this.emps=res.data.emps;
        this.total=res.data.count;
      })
    }
  },
  mounted() {
    //this.getUsers();
    this.empList();
    // this.testFn();
  }
};
</script>
<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-select {
  width: 120px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}
.del-dialog-cnt {
  font-size: 16px;
  text-align: center;
}
.table {
  width: 100%;
  font-size: 14px;
}
.red {
  color: #ff0000;
}
.mr10 {
  margin-right: 10px;
}
</style>

