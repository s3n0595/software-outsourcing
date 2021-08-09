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
        <el-input v-model="searchInfo" placeholder="请输入雇主名称关键词" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="search" @click="getSearchDemand">搜索</el-button>
        <el-button type="primary" @click="getSearchState(0)">未审核</el-button>
        <el-button type="primary" @click="getSearchState(1)">已通过</el-button>
        <el-button type="primary" @click="getSearchState(2)">未通过</el-button>
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
        <el-table-column type="index" label="序号" sortable></el-table-column>
        <el-table-column prop="employerAccount.employerName" label="雇主名称" width="120"></el-table-column>
        <el-table-column prop="demandType.demandTypeName" label="需求类型"></el-table-column>
        <el-table-column prop="demandStatus" label="审核状态" align="center">
          <template slot-scope="scope">
            <el-tag type="info" v-if="scope.row.demandStatus === 0">未审核</el-tag>
            <el-tag type="success" v-if="scope.row.demandStatus === 1">已通过</el-tag>
            <el-tag type="danger" v-if="scope.row.demandStatus === 2">未通过</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="releaseTime" label="发布时间" width="180"></el-table-column>
        <el-table-column prop="predictTime" label="预定时间" width="180"></el-table-column>
        <el-table-column prop="predictPrice" label="发布金额"></el-table-column>
        <el-table-column prop="predictNumber" label="预定人数" :formatter="formatterNumber"></el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <!--      slot-scope="scope" 可以获取到父组件传递的参数，将这些参数使用到子组件插槽里-->
          <template slot-scope="scope">
            <el-button
                type="text"
                icon="el-icon-lx-roundcheck"
                :disabled="scope.row.demandStatus !== 0"
                @click="handleEdit(scope.$index, scope.row)"
            >通过</el-button>
            <el-button
                type="text"
                icon="el-icon-lx-roundclose"
                :disabled="scope.row.demandStatus !== 0"
                class="red"
                @click="handleDelete(scope.$index, scope.row)"
            >未通过</el-button>
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
    </div>
  </div>
</template>
<script>
import {
  getSearchUser,
  getDemandList,
  updateDemandState,
  deleteDemandList,
  getSearchDemand,
  getSearchState,
} from "../../../api/api";
export default {
  data() {
    return {
      url:"",
      // 需求列表
      demandList: '',
      // 搜索关键字
      searchInfo: "",
      // 总条数
      total: 0,
      // 当前页数
      pageNo: 1,
      // 每页的条数
      pageSize: 5,
      // 是否启动加载动画
      isShowloading: false,
      // 选中的数据
      delData: [],
      formLabelWidth: "120px"
    };
  },
  // 计算属性
  computed:{
    // 动态获取分页data
    tableDataList(){
      return this.demandList.slice((this.pageNo-1)*this.pageSize,this.pageNo*this.pageSize);
    },
  },
  methods: {
    formatterNumber(row,cloumn){
      return row.predictNumber == null ? "暂未设定" : row.predictNumber;
    },
    formatterState(state){
      return state == 0 ? "未通过" : "以通过";
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
    // 批量删除
    delAll() {
      this.$confirm("确认删除该用户吗?", "提示", {
        type: "warning"
      }).then(() => {
        this.isShowloading = true;
        let delIds = this.delData.map(item => item.demandId);
        // axios传递数组 在数组后加入''
        let params = {
          demandIds: delIds + '',
        };
        deleteDemandList(params).then(res => {
          this.isShowloading = false;
          this.$message({
            message: "删除成功",
            type: "success"
          });
          this.getDemandList();
        });
      });
    },
    // 未通过按钮
    handleDelete(index, row) {
      let params = {
        demandId: row.demandId,
        demandStatus: 2
      }
      updateDemandState(params).then(res=>{
        this.$message({
          message: "修改成功",
          type: "success",
        });
        this.getDemandList();
      })
    },
    // 通过按钮
    handleEdit(index, row) {
      let params = {
        demandId: row.demandId,
        demandStatus: 1
      }
      updateDemandState(params).then(res=>{
        this.$message({
          message: "修改成功",
          type: "success",
        });
        this.getDemandList();
      })
    },
    // 审核状态按钮
    getSearchState(State){
      this.isShowloading=true;
      let params = {
        demandStatus: State,
      }
      getSearchState(params).then(res=>{
        this.demandList = res.data;
        this.total = this.demandList.length;
        this.isShowloading=false;
      })
    },
    // 用雇主关键词搜索
    getSearchDemand(){
      this.isShowloading=true;
      let params = {
        employerName: this.searchInfo,
      }
      getSearchDemand(params).then(res=>{
        this.demandList = res.data;
        this.total = this.demandList.length;
        this.isShowloading=false;
      })
    },
    // 获取需求列表
    getDemandList(){
      this.isShowloading=true;
      getDemandList().then(res=>{
        this.demandList = res.data;
        this.total = this.demandList.length;
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
    this.getDemandList();
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


