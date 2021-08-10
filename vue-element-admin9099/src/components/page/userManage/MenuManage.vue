<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>角色管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container" >
      <div class="handle-box">
        <!--    批量删除按钮-->
        <el-button
            type="primary"
            icon="delete"
            class="handle-del mr10"
            @click="delAll"
            :disabled="this.delData.length===0"
        >批量删除</el-button>
      </div>
      <el-row :gutter="30" v-loading="isShowloading">
        <el-col :span="8">
         <el-card>
           <el-tree
               :data="this.treeData"
               node-key="id"
               :props="props"
               ref="editTree"
               accordion
               show-checkbox
               @check="currentChecked"
               @node-click="nodeClick"
             >
           </el-tree>
         </el-card>
        </el-col>
        <el-col :span="16">
          <el-card>
            <div class="form-box">
              <el-form :model="this.menuDate" label-width="80px" ref="menuDate" :rules="menuRule">
                <el-form-item label="菜单名称" prop="menuName">
                  <el-input v-model="menuDate.menuName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="设置图标" prop="menuIcon">
                  <el-input v-model="menuDate.menuIcon" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="菜单链接" prop="menuPath">
                  <el-input v-model="menuDate.menuPath" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="菜单父级">
                  <el-select v-model="menuDate.parentId" placeholder="请选择">
                    <template v-if="menuDate.parentId === 0 || menuDate.parentId===''">
                      <el-option label="作为父级" :value="menuDate.parentId"></el-option>
                    </template>
                    <template v-else-if="menuDate.parentId !== 0" v-for="menu in this.treeData">
                      <el-option :label="menu.name" :value="menu.menu.menuId"></el-option>
                    </template>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" :disabled="this.disabled" @click="editMenu">确认修改</el-button>
                  <el-button type="primary" @click="addMenu">新建菜单</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import {
  getMenuData,
  menuNameExist,
  updateMenu,
  addMenu,
  deleteMenuList,
} from "../../../api/api";
export default {
  data() {
    return {
      // 是否启动加载动画
      isShowloading: false,
      disabled:true,
      // 选中的菜单Id集合
      delData: [],
      // form表单显示的菜单属性
      menuDate:{
        menuName:'',
        menuPath:'',
        parentId:'',
        menuIcon:'',
      },
      // 树模型
      props: {
        label: 'name',
        children: 'zones',
      },
      // 所有的菜单列表
      treeData: [],
      // 菜单自定义规则
      menuRule: {
        menuName: [
          {required: true, message: "请填写菜单", trigger: "blur" },
          {max: 10, message: "不能超过10位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
          {validator:(rule,value,callback)=>{
              let params = {
                menuName: this.menuDate.menuName,
              }
              menuNameExist(params).then(res=>{
                if ('' !== res.data){
                  callback(new Error("该菜单名已被占用"));
                }else {
                  callback();
                }
              })
            },trigger: 'blur'}
        ],
        menuIcon: [
          {required: true, message: "请设置菜单图标", trigger: "blur"},
          {max: 20, message: "不能超过20位",trigger: "blur" },
        ],
        menuPath: [
          {required: true, message: "请设置菜单链接", trigger: "blur"},
          {max: 30, message: "不能超过30位",trigger: "blur" },
        ]
      },
      formLabelWidth: "120px"
    };
  },
  // 计算属性
  computed:{
  },
  methods: {
    // checked四个属性
    // checkedKeys:选中id的集合，如果子节点全部选中，父节点自动添加进来
    // checkedNodes:选中对象的集合
    // halfCheckedKeys:选中父级id，如果子节点全部选中，该方法获取为空
    // halfCheckedNodes:选中父级选中父级对象集合
    currentChecked(data,checked){
        // concat:数据合并
        this.delData = checked.checkedKeys;
      console.log(this.delData);
    },
    // 节点点击事件
    nodeClick(data){
      this.disabled = false;
      this.menuDate = data.menu;
    },
    // 修改菜单信息
    editMenu(){
      this.$refs.menuDate.validate(validate=>{
        if (validate){
          this.isShowloading = true;
          let params = {
            menuId: this.menuDate.menuId,
            menuName: this.menuDate.menuName,
            menuIcon: this.menuDate.menuIcon,
            menuPath: this.menuDate.menuPath,
            parentId: this.menuDate.parentId,
          }
          updateMenu(params).then(res=>{
              this.$message({
                message: "修改成功",
                type: "success",
              });
          });
          this.isShowloading = false;
          this.getMenuData();
        }
      })

    },
    // 新建菜单
    addMenu(){
      this.$refs.menuDate.validate(validate=>{
        if (validate){
          this.isShowloading = true;
          let params = {
            menuName: this.menuDate.menuName,
            menuIcon: this.menuDate.menuIcon,
            menuPath: this.menuDate.menuPath,
            parentId: this.menuDate.parentId,
          }
          addMenu(params).then(res=>{
            this.$message({
              message: "添加成功",
              type: "success",
            });
            this.isShowloading = false;
            this.getMenuData();
          })
        }
      })
    },
    // 批量删除
    delAll() {
      this.$confirm("确认删除该用户吗?", "提示", {
        type: "warning"
      }).then(() => {
        this.isShowloading = true;
        let delIds = this.delData;
        // axios传递数组 在数组后加入''
        let params = {
          menuIds: delIds + '',
        };
        deleteMenuList(params).then(res => {
          this.$message({
            message: "删除成功",
            type: "success"
          });
          this.isShowloading = false;
          this.getMenuData();
        });
      });
    },
    // 获取菜单列表 并转为tree树
    getMenuData(){
      getMenuData().then(res=>{
        this.isShowloading = true;
        this.treeData = [];
        let MenuDate = res.data;
        MenuDate.forEach((val1)=>{
          if (0 === val1.parentId){
            let systemItem = {};
            systemItem.id = val1.menuId;
            systemItem.name = val1.menuName;
            systemItem.menu = val1;
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
            this.isShowloading = false;
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
    this.getMenuData();
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


