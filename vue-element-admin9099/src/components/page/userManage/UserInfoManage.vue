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
        <el-input v-model="searchInfo" placeholder="请输入用户名关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="getSearchUsers">搜索</el-button>
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
        <el-table-column prop="state" label="状态" align="center" width="180">
          <template slot-scope="scope" >
              <el-switch
                  v-model="scope.row.state"
                  :active-value="1"
                  :inactive-value="0"
                  active-text="启用"
                  inactive-text="禁用"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  @change="editState(scope.row.userId,scope.row.state)"
              >
              </el-switch>
          </template>
        </el-table-column>
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
          :before-close="confirmClose"
          v-dialogDrag
      >
        <el-form :model="userForm" :rules="editRule" ref="editUserForm">
          <el-form-item label="用户名" :label-width="formLabelWidth" prop="userName">
            <el-input v-model="userForm.userName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="角色名称" :label-width="formLabelWidth" prop="roleId">
            <el-select v-model="userForm.roleId">
              <template  v-for="role in this.roles">
                <el-option :label="role.roleName" :value="role.roleId"></el-option>
              </template>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editUserVisible=false">取 消</el-button>
          <el-button type="primary" @click.native="editUser">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 新建用户 -->
<!--  :visible:隐藏属性  .sync: 同步绑定   v-dialogDrag:拖拽-->
      <el-dialog title="新建用户" :visible.sync="addUserVisible" v-dialogDrag>
<!--    :rules:自定义规则-->
        <el-form :model="addUserForm" :rules="addUserRule" ref="addUserForm">
<!--      label-width这个属性然后配合label-position可以设置标签对齐方式-->
          <el-form-item label="用户名称" :label-width="formLabelWidth" prop="userName">
            <el-input v-model="addUserForm.userName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="用户账号" :label-width="formLabelWidth" prop="userAccount">
            <el-input v-model="addUserForm.userAccount" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="用户密码" :label-width="formLabelWidth" prop="userPassword">
            <el-input v-model="addUserForm.userPassword" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="角色名称" :label-width="formLabelWidth" prop="roleId">
            <el-select v-model="addUserForm.roleId">
              <template  v-for="role in this.roles">
                <el-option :label="role.roleName" :value="role.roleId"></el-option>
              </template>
            </el-select>
          </el-form-item>
          <el-form-item label="状态" :label-width="formLabelWidth">
            <el-radio v-model="addUserForm.state" label="1">正常</el-radio>
            <el-radio v-model="addUserForm.state" label="0">禁用</el-radio>
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
  getUserInfoList,
  deleteUserInfoList,
  addUserInfo,
  updateState,
  updateUserInfo,
  getSearchUser,
  getRoleList,
  getDate,
} from "../../../api/api";
export default {
  data() {
    return {
      url:"",
      // 搜索关键字
      searchInfo: "",
      // 用户列表
      users: '',
      // 角色列表
      roles: '',
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
      // 是否显示编辑
      editUserVisible: false,
      // 新建用户框
      addUserVisible: false,
      // 编辑数据
      userForm: {},
      // 添加用户的数据
      addUserForm: {
        userName: "",
        userAccount: "",
        userPassword: "",
        roleId: "",
        state: "1",
      },
      // 新增用户自定义规则
      addUserRule: {
        userName: [
          {required: true, message: "请输入用户名", trigger: "blur" },
          {max: 10, message: "不能超过10位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
        ],
        userAccount: [
          {required: true, message: "请设置账号", trigger: "blur"},
          {max: 11, message: "不能超过11位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
        ],
        userPassword: [
          {required: true, message: "请设置密码", trigger: "blur"},
          {max: 11, message: "不能超过11位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
        ],
        roleId: [
          {
            // pattern:匹配验证
            required: true,
            message: "请选择角色",
            trigger: "blur"
          }
        ],
      },
      // 修改用户规则
      editRule: {
        userName: [
          {required: true, message: "请输入用户名", trigger: "blur" },
          {max: 10, message: "不能超过10位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
        ],
      },
      formLabelWidth: "120px"
    };
  },
  // 计算属性
  computed:{
    // 动态获取分页data
    tableDataList(){
      return this.users.slice((this.pageNo-1)*this.pageSize,this.pageNo*this.pageSize);
    },
  },
  methods: {
    editState(userId,state){
      let params = {
        userId: userId,
        state: state,
      }
      updateState(params).then(res=>{
        this.$message({
          message: "修改成功",
          type: "success",
        });
        this.getUserList();
      })
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
    // 添加用户按钮
    saveUser() {
      //validate:表单验证,返回validate结果
      this.$refs['addUserForm'].validate((validate)=>{
        if (validate){
          let params = {
            userName: this.addUserForm.userName,
            userAccount: this.addUserForm.userAccount,
            userPassword: this.addUserForm.userPassword,
            roleId: this.addUserForm.roleId,
            state: this.addUserForm.state,
          };
          this.addUserVisible = false;
          addUserInfo(params).then(res=>{
            if ("添加成功" == res.data){
              this.$message({
                message: "添加成功",
                type: "success",
              });
              this.getUserList();
            }else {
              this.$message({
                message: "添加失败",
                type: "error",
              });
            }
          });
          this.addUserForm = {};
        }
      })
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
    // 编辑按钮
    handleEdit(index, row) {
      this.editUserVisible = true;
      // 获取选中数据
      this.userForm = Object.assign({}, row);
    },
    // 编辑用户
    editUser() {
      this.$refs['editUserForm'].validate((valid) => {
        if (valid) {
          let params = {
            userId: this.userForm.userId,
            userName: this.userForm.userName,
            roleId: this.userForm.roleId,
          };
          updateUserInfo(params).then(res => {
                this.$message({
                  type: "success",
                  message: "修改成功"
                });
                this.getUserList();

              });
          this.editUserVisible = false;
        }
      });
    },
    // 用户名关键词搜索
    getSearchUsers(){
      this.isShowloading=true;
      let params = {
        userName: this.searchInfo,
      }
      getSearchUser(params).then(res=>{
        this.users = res.data;
        this.total = this.users.length;
        this.isShowloading=false;
      })
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
    // 获取角色列表
    getRoleList(){
      getRoleList().then(res=>{
        this.roles = res.data;
        console.log(this.roles)
      })
    },
    // 关闭提示
    confirmClose(done) {
      this.$confirm("确认关闭将丢失已编辑内容？", "提示", {
        type: "warning"
      }).then(() => {
        // this.userForm = {};
        done();
      });
    },
  },
  mounted() {
    this.getUserList();
    this.getRoleList();
  }
};
</script>
<style>
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
.el-switch__core:after {
  content: "";
  position: absolute;
  top: 1px;
  left: 1px;
  border-radius: 100%;
  transition: all .3s;
  width: 16px;
  height: 16px;
  background-color: #fff;
}

.el-switch.is-checked .el-switch__core:after {
  left: 100%;
  margin-left: -17px;
}
</style>


