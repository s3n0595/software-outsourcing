<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>角色管理</el-breadcrumb-item>
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
        <el-input v-model="searchInfo" placeholder="请输入角色名关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="getSearchRoles">搜索</el-button>
        <el-button type="primary" @click="addUserVisible=true">新建角色</el-button>
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
        <el-table-column prop="roleName" label="角色名" width="120"></el-table-column>
        <el-table-column prop="roleDescribe" label="角色描述"></el-table-column>
        <el-table-column prop="roleDate" label="创建时间"></el-table-column>
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
              <el-option label="管理员" :value="2"></el-option>
              <el-option label="系统管理员" :value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editUserVisible=false">取 消</el-button>
          <el-button type="primary" @click.native="editUser">确 定</el-button>
        </div>
      </el-dialog>
      <!-- 新建菜单 -->
      <!--  :visible:隐藏属性  .sync: 同步绑定   v-dialogDrag:拖拽-->
      <el-dialog title="新建角色" :visible.sync="addUserVisible" v-dialogDrag>
        <!--    :rules:自定义规则-->
        <el-form :model="addRoleForm" :rules="addRoleRule" ref="addRoleForm">
          <!--      label-width这个属性然后配合label-position可以设置标签对齐方式-->
          <el-form-item label="角色名称" :label-width="formLabelWidth" prop="roleName">
            <el-input v-model="addRoleForm.roleName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="角色描述" :label-width="formLabelWidth" prop="roleDescribe">
            <el-input v-model="addRoleForm.roleDescribe" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="配置菜单" :label-width="formLabelWidth" prop="menu">
            <el-tree
                :data="this.treeData"
                node-key="id"
                :props="props"
                rel="tree"
                show-checkbox
                @check="currentChecked">
            </el-tree>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addUserVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveRole">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import {
  updateUserInfo,
  getRoleList,
  addRole,
  getDate,
  deleteRole,
  getSearchRole,
  getMenuData,
} from "../../../api/api";
export default {
  data() {
    return {
      url:"",
      // 搜索关键字
      searchInfo: "",
      // 菜单列表
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
      // 添加角色的数据
      addRoleForm: {
        roleName: '',
        roleDescribe: '',
        roleDate: '',
      },
      // 菜单集合
      MenuData: '',
      // 树模型
      props: {
        label: 'name',
        children: 'zones',
      },
      treeData: [],
      // 新增角色自定义规则
      addRoleRule: {
        roleName: [
          {required: true, message: "请填写角色名", trigger: "blur" },
          {max: 10, message: "不能超过10位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
        ],
        roleDescribe: [
          {required: true, message: "请给角色添加描述", trigger: "blur"},
          {max: 11, message: "不能超过11位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
        ],
        menu: [
          {required: true, message: "请选择菜单", trigger: "blur"},
        ]
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
      return this.roles.slice((this.pageNo-1)*this.pageSize,this.pageNo*this.pageSize);
    },
  },
  methods: {
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
    saveRole() {
      //validate:表单验证,返回validate结果
      this.$refs['addRoleForm'].validate((validate)=>{
        if (validate){
          let roleDate = getDate();
          let params = {
            roleName: this.addRoleForm.roleName,
            roleDescribe: this.addRoleForm.roleDescribe,
            roleDate: roleDate,
          };
          this.addUserVisible = false;
          addRole(params).then(res=>{
            if ("添加成功" == res.data){
              this.$message({
                message: "添加成功",
                type: "success",
              });
              this.getRoleList();
            }else {
              this.$message({
                message: "添加失败",
                type: "error",
              });
            }
          });
          this.addRoleForm = {};
        }
      })
    },
    // 批量删除
    delAll() {
      this.$confirm("确认删除该用户吗?", "提示", {
        type: "warning"
      }).then(() => {
        this.isShowloading = true;
        let delIds = this.delData.map(item => item.roleId);
        // axios传递数组 在数组后加入''
        let params = {
          roleIds: delIds + '',
        };
        deleteRole(params).then(res => {
          this.isShowloading = false;
          this.$message({
            message: "删除成功",
            type: "success"
          });
          this.getRoleList();
        });
      });
    },
    // 删除按钮
    handleDelete(index, row) {
      let params = {
        roleIds: row.roleId,
      };
      this.$confirm("确认删除该用户？", "提示", {
        type: "warning"
      }).then(() => {
        deleteRole(params).then(res => {
          this.$message({
            type: "success",
            message: "删除成功"
          });
          this.getRoleList();
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
    // 角色名关键词搜索
    getSearchRoles(){
      this.isShowloading=true;
      let params = {
        roleName: this.searchInfo,
      }
      getSearchRole(params).then(res=>{
        this.roles = res.data;
        this.total = this.roles.length;
        this.isShowloading=false;
      })
    },
    // 获取角色列表以及对应的菜单
    getRoleList(){
      this.isShowloading=true;
      getRoleList().then(res=>{
        this.roles = res.data;
        this.total = this.roles.length;
        this.isShowloading=false;
      })
    },

    // 树复选框
    handleCheckChange(data,checked){
      console.log(data,checked);
    },
    currentChecked(data,checked){
      console.log(data,checked);
    },
    // 获取菜单列表 并转为tree树
    getMenuData(){
      getMenuData().then(res=>{
        let MenuDate = res.data;
        MenuDate.forEach((val1)=>{
          if (0 === val1.parentId){
            let systemItem = {};
            systemItem.id = val1.menuId;
            systemItem.name = val1.menuName;
            systemItem.Menu = val1;
            systemItem.zones = [];
            MenuDate.forEach((val2)=>{
              if (val1.menuId === val2.parentId){
                let systemZones = {};
                systemZones.id = val2.menuId;
                systemZones.name = val2.menuName;
                systemZones.menu = val2;
                systemItem.zones.push(systemZones);
              };
            });
            this.treeData.push(systemItem);
          };
        });
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
    this.getRoleList();
    // this.getMenuData();
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


