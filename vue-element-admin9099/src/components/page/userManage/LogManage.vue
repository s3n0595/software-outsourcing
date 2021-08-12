<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>需求管理</el-breadcrumb-item>
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
        <el-input v-model="searchInfo" placeholder="请输入用户名称关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="getSearchLogInfo">搜索</el-button>
      </div>
      <el-table
          :data="logInfoData"
          class="table"
          ref="multipleTable"
          @selection-change="handleSelectionChange"
          v-loading="isShowloading"
      >
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column type="index" label="序号" sortable></el-table-column>
        <el-table-column prop="userInfo.userName" label="用户名称"></el-table-column>
        <el-table-column prop="operation" label="操作名称"></el-table-column>
        <el-table-column prop="state" label="状态" align="center">
          <template slot-scope="scope">
            <el-tag type="success" v-if="scope.row.state === 1">登录成功</el-tag>
            <el-tag type="danger" v-if="scope.row.state === 0">登录失败</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="time" label="操作时间"></el-table-column>
        <el-table-column prop="logDescribe" label="操作描述"></el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import {
  deleteLogInfo,
  getLogInfoList,
  getSearchLogInfo,
} from "../../../api/api";
export default {
  data() {
    return {
      url:"",
      // 日志列表
      logInfoData: [],
      // 搜索关键字
      searchInfo: "",
      // 是否启动加载动画
      isShowloading: false,
      // 选中的数据
      delData: [],
      formLabelWidth: "120px"
    };
  },
  // 计算属性
  computed:{

  },
  methods: {
    // 当选择项发送变化时，执行一下方法
    handleSelectionChange(delData) {
      // 将复选框选中的结果集合赋值给delData
      this.delData = delData;
    },
    // 批量删除
    delAll() {
      this.$confirm("确认删除该用户吗?", "提示", {
        type: "warning"
      }).then(() => {
        this.isShowloading = true;
        let delIds = this.delData.map(item => item.logId);
        // axios传递数组 在数组后加入''
        let params = {
          logIds: delIds + '',
        };
        deleteLogInfo(params).then(res => {
          this.isShowloading = false;
          this.$message({
            message: "删除成功",
            type: "success"
          });
          this.getLogInfoList();
        });
      });
    },
    // 用关键词搜索
    getSearchLogInfo(){
      this.isShowloading=true;
      let params = {
        userName: this.searchInfo,
      }
      getSearchLogInfo(params).then(res=>{
        this.logInfoData = res.data;
        this.isShowloading=false;
      })
    },
    // 获取需求列表
    getLogInfoList(){
      this.isShowloading=true;
      getLogInfoList().then(res=>{
        this.logInfoData = res.data;
        this.isShowloading=false;
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
   this.getLogInfoList();
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


