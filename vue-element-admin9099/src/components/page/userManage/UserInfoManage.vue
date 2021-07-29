<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
<!--    批量删除按钮-->
        <el-button
            type="primary"
            icon="delete"
            class="handle-del mr10"
            @click="delAll"
            :disabled="this.delData.length===0"
        >批量删除</el-button>
        <el-input v-model="searchInfo" placeholder="筛选关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="getUsers">搜索</el-button>
        <el-button type="primary" @click="addUserVisible=true">新建用户</el-button>
      </div>
      <el-table
          :data="tableDataList"
          border
          class="table"
          ref="multipleTable"
          @selection-change="handleSelectionChange"
          v-loading="isShowloading"
      >
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column type="index" label="序号" sortable width="150"></el-table-column>
        <el-table-column prop="userName" label="用户名" width="120"></el-table-column>
        <el-table-column prop="role.roleName" label="角色名称"></el-table-column>
        <el-table-column prop="state" label="状态" :formatter="formatterState"></el-table-column>
        <el-table-column prop="loginNumber" label="登录次数"></el-table-column>
        <el-table-column prop="loginDate" label="最近登录时间"></el-table-column>
        <el-table-column prop="creator" label="创建者"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
<!--      slot-scope="scope" 可以获取到父组件传递的参数，将这些参数使用到子组件插槽里-->
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
            @current-change="currentChange"
            @size-change="handleSizeChange"
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
          ref="editUserForm"
          :before-close="confirmClose"
          v-dialogDrag
      >
        <el-form :model="userForm" :rules="editRule" ref="editUserForm">
          <el-form-item label="用户名" :label-width="formLabelWidth" prop="username">
            <el-input v-model="userForm.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="状态" :label-width="formLabelWidth">
            <el-radio v-model="userForm.isable" label="1">正常</el-radio>
            <el-radio v-model="userForm.isable" label="0">禁用</el-radio>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editUserVisible=false">取 消</el-button>
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
  getUserList,
  getDeleUser,
  getEditUser,
  getAddUser,
  getDeleteOne,
  testApi,
  getUserInfoList,
  deleteUserInfoList,
} from "../../../api/api";
export default {
  data() {
    return {
      url:"",
      // 搜索关键字
      searchInfo: "",
      // 用户列表
      users: '',
      // 总条数
      total: '',
      // 当前页数
      pageNo: 1,
      // 每页的条数
      pageSize: 5,
      // 是否启动加载动画
      isShowloading: false,
      // 选中的数据
      delData: [],
      editUserVisible: false, //是否显示编辑
      addUserVisible: false, //新建用户框
      userForm: {}, //编辑数据
      addUserForm: {
        username: "",
        rolename: "",
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
      editRule: {
        username: [
          {
            required: true,
            message: "请输入姓名",
            trigger: "blur"
          }
        ]
      },
      formLabelWidth: "120px"
    };
  },
  // 计算属性
  computed:{
    // 动态获取分页data
    tableDataList(){
      return this.users.slice((this.pageNo-1)*this.pageSize,this.pageNo*this.pageSize);
    }
  },
  methods: {
    // 判断状态
    formatterState(row) {
      return row.state == "1" ? "正常" : "禁用";
    },
    getUsers() {
      this.isShowloading = true;
      let params = {
        searchInfo: this.searchInfo.trim(),
        page: this.pageNo
      };
      getUserList(params).then(res => {
        this.users = res.data.users;
        this.total = res.data.total;
        this.isShowloading = false;
      });
    },
    // 当前页数发送改变
    currentChange(val) {
      console.log("当前页"+val);
      this.pageNo = val;
    },
    // 每页显示条数
    handleSizeChange(val){
      console.log("每页条数更改为"+val);
      this.pageSize = val;
    },

    // 当选择项发送变化时，执行一下方法
    handleSelectionChange(delData) {
      // 将复选框选中的结果集合赋值给delData
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
    // 批量删除
    delAll() {
      this.$confirm("确认删除该用户吗?", "提示", {
        type: "warning"
      }).then(() => {
        this.isShowloading = true;
        let delIds = this.delData.map(item => item.userId);
        // axios传递数组 在数组后加入''
        let params = {
          userIds: delIds + '',
        };
        deleteUserInfoList(params).then(res => {
          this.isShowloading = false;
          this.$message({
            message: "删除成功",
            type: "success"
          });
          this.getUserList();
        });
      });
    },
    // 删除按钮
    handleDelete(index, row) {
      let userIds = {
        userIds: row.userId
      };
      this.$confirm("确认删除该用户？", "提示", {
        type: "warning"
      }).then(() => {
        deleteUserInfoList(userIds).then(res => {
          this.$message({
            type: "success",
            message: "删除成功"
          });
          this.getUserList();
        });
      });
    },
    // 编辑
    handleEdit(index, row) {
      this.editUserVisible = true;
      this.userForm = Object.assign({}, row);
    },
    // 获取用户列表
    getUserList(){
      this.isShowloading=true;
      getUserInfoList().then(res=>{
        this.users = res.data;
        this.total = this.users.length;
        this.isShowloading=false;
      })
    },

    editUser() {
      debugger
      this.$refs['editUserForm'].validate((valid) => {
        debugger
        if (valid) {
          let params = this.userForm;
          getEditUser(params)
              .then(res => {
                this.$message({
                  type: "success",
                  message: res.data.msg
                });
                this.getUsers();
                this.editUserVisible = false;
                this.userForm = {};
              })
              .bind(this);
        }
      });
    },
    confirmClose(done) {
      this.$confirm("确认关闭将丢失已编辑内容？", "提示", {
        type: "warning"
      }).then(() => {
        this.userForm = {};
        done();
      });
    },
    // testFn(){
    //   this.url = '/ms/table/list';
    //   this.$axios.get(this.url).then(res=>{
    //     console.log(res)
    //   })
    //   testApi().then(res=>{
    //     console.log(res)
    //   })
    // },
  },
  mounted() {
    // this.getUsers();
    // this.testFn();
    this.getUserList();
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


