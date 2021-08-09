<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>角色管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-row :gutter="30">
        <el-col :span="8">
         <el-card>
           <el-tree
               :data="this.treeData"
               node-key="id"
               :props="props"
               ref="editTree"
               accordion
               show-checkbox
               @check="currentChecked">
             >
           </el-tree>
         </el-card>
        </el-col>
        <el-col :span="16">
          <el-card>
            <div class="form-box">
              <el-form :model="menuDate">
                <el-form-item label="菜单名称" :label-width="formLabelWidth">
                  <input v-model="menuDate.menuName"></input>
                </el-form-item>
                <el-form-item label="菜单链接" :label-width="formLabelWidth">
                  <input v-model="menuDate.menuPath"></input>
                </el-form-item>
                <el-form-item label="菜单图标" :label-width="formLabelWidth">
                  <input v-model="menuDate.menuIcon"></input>
                </el-form-item>
                <el-form-item label="菜单父级" :label-width="formLabelWidth">
                  <input v-model="menuDate.parentId"></input>
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
} from "../../../api/api";
export default {
  data() {
    return {
      // 是否启动加载动画
      isShowloading: false,
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
      // 新增角色自定义规则
      addRoleRule: {
        roleName: [
          {required: true, message: "请填写角色名", trigger: "blur" },
          {max: 10, message: "不能超过10位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
        ],
        roleDescribe: [
          {required: true, message: "请给角色添加描述", trigger: "blur"},
          {max: 50, message: "不能超过50位",trigger: "blur" },
          {pattern: /^[\u4E00-\u9FA5A-Za-z0-9_]+$/,message: "不能有除下划线的特殊符号"},
        ],
        menu: [

        ]
      },
      // 修改角色规则
      editRule: {
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
      // 判断父级是否被选中
      if (checked.halfCheckedKeys === ''){
        this.addRoleMenes = checked.checkedKeys;
      }else{
        // concat:数据合并
        this.addRoleMenes = checked.halfCheckedKeys.concat(checked.checkedKeys);
      }
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


