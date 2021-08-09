<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>需求管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-table
          :data="demandList"
          border
          class="table"
          ref="multipleTable"
          v-loading="isShowloading"
      >
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column type="demandId" label="需求序号" sortable width="100"></el-table-column>
        <el-table-column prop="demandTitle" label="需求标题" width="120"></el-table-column>
        <el-table-column prop="demandTypeName" label="需求类型"></el-table-column>
        <el-table-column prop="predictTime" label="工期"></el-table-column>
        <el-table-column prop="predictPrice" label="价格"></el-table-column>
        <el-table-column prop="demandDescribe" label="描述"></el-table-column>
        <el-table-column prop="releaseTime" label="时间"></el-table-column>
        <el-table-column prop="employerId" label="发布者ID"></el-table-column>
        <el-table-column prop="demandStatus" label="需求状态"></el-table-column>
      </el-table>
<!--      <div class="pagination">-->
<!--        <el-pagination-->
<!--            background-->
<!--            @current-change="currentChange"-->
<!--            @size-change="handleSizeChange"-->
<!--            layout="total, prev, pager, next, sizes, jumper"-->
<!--            :total="total"-->
<!--            :page-size="pageSize"-->
<!--            :page-sizes="[5,10,15]"-->
<!--        ></el-pagination>-->
<!--      </div>-->
    </div>
  </div>
</template>
<script>
import {getDemandList} from "../../../api/api";
export default {
  data() {
    return {
      url:"",
      demandList: [],
      // 总条数
      total: 0,
      // 当前页数
      pageNo: 1,
      // 每页的条数
      pageSize: 5,
      isShowloading: false,
      formLabelWidth: "120px"
    };
  },
  // 计算属性
  // computed:{
  //   // 动态获取分页data
  //   tableDataList(){
  //     return this.users.slice((this.pageNo-1)*this.pageSize,this.pageNo*this.pageSize);
  //   },
  // },
  methods: {
    // getUsers() {
    //   this.isShowloading = true;
    //   let params = {};
    //   getDemandList(params).then(res => {
    //     console.log("res********"+res)
    //     console.log("res********"+res)
    //     console.log("res********"+res)
    //     console.log("res********"+res)
    //     this.users = res.data;
    //     this.total = this.users.total;
    //     this.isShowloading=false;
    //   });
    // },
    // 当前页数发送改变
    // currentChange(val) {
    //   console.log("当前页"+val);
    //   this.pageNo = val;
    // },
    // // 每页显示条数
    // handleSizeChange(val){
    //   console.log("每页条数更改为"+val);
    //   this.pageSize = val;
    // },
    // // 当选择项发送变化时，执行一下方法
    // handleSelectionChange(delData) {
    //   // 将复选框选中的结果集合赋值给delData
    //   this.delData = delData;
    // },
    // 获取需求列表
    getDemand(){
      this.isShowloading=true;

      getDemandList().then(res=>{
        this.demandList = res.data;
        this.isShowloading = false;

      })
    },

  },
  mounted() {
    this.getDemand();
    console.log("************"+this.getDemand())
    console.log("************"+this.getDemand())
    console.log("************"+this.getDemand())
    console.log("************"+this.getDemand())
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

