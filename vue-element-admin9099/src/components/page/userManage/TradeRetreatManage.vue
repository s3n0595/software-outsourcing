<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>作品退款</el-breadcrumb-item>
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
      </div>
      <el-table
          :data="tableDataList"
          class="table"
          @selection-change="handleSelectionChange"
          v-loading="isShowloading"
          ref="refTable"
          @row-click="clickTable"
      >
        <el-table-column type="expand">
          <template slot-scope="scope">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="订单编号">
                <span>{{ scope.row.tradeWork.tradeOrder }}</span>
              </el-form-item>
              <el-form-item label="作品名称">
                <span>{{ scope.row.tradeWork.works.worksTitle }}</span>
              </el-form-item>
              <el-form-item label="申请方名">
                <span>{{ scope.row.tradeWork.employerAccount.employerName }}</span>
              </el-form-item>
              <el-form-item label="售卖方名">
                <span>{{ scope.row.tradeWork.providerAccount.providerName }}</span>
              </el-form-item>
              <el-form-item label="交易日期">
                <span>{{ scope.row.tradeWork.tradeTime }}</span>
              </el-form-item>
              <el-form-item label="交易金额">
                <span>{{ scope.row.tradeWork.works.worksPrice }}</span>
              </el-form-item>
              <el-form-item label="申述时间">
                <span>{{ scope.row.tradeRetreatTime }}</span>
              </el-form-item>
              <el-form-item label="申述详情">
                <span>{{ scope.row.remarks }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column type="index" label="序号" sortable></el-table-column>
        <el-table-column prop="tradeWork.tradeOrder" label="订单编号" width="180"></el-table-column>
        <el-table-column prop="tradeWork.works.worksTitle" label="作品名称"></el-table-column>
        <el-table-column prop="tradeWork.employerAccount.employerName" label="申请方名" width="120"></el-table-column>
        <el-table-column prop="tradeRetreatTime" label="申述日期" width="180"></el-table-column>
        <el-table-column prop="tradeWork.works.worksPrice" label="交易金额" width="120"></el-table-column>
        <el-table-column prop="retreatStatus" label="退款状态" align="center">
          <template slot-scope="scope">
            <el-tag type="info" v-if="scope.row.retreatStatus === 1">等待审核</el-tag>
            <el-tag type="success" v-if="scope.row.retreatStatus === 2">退款成功</el-tag>
            <el-tag type="danger" v-if="scope.row.retreatStatus === 3">退款失败</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <!--      slot-scope="scope" 可以获取到父组件传递的参数，将这些参数使用到子组件插槽里-->
          <template slot-scope="scope">
            <el-button
                type="text"
                icon="el-icon-lx-roundcheck"
                :disabled="scope.row.retreatStatus !== 1"
                @click="handleEdit(scope.$index, scope.row)"
            >通过申述</el-button>
            <el-button
                type="text"
                icon="el-icon-lx-roundclose"
                :disabled="scope.row.retreatStatus !== 1"
                class="red"
                @click="handleDelete(scope.$index, scope.row)"
            >回绝申述</el-button>
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
  deleteDemandList, getDate,
  getSearchDemand,
  getTradeRetreatList,
  updateTreatStatus,
} from "../../../api/api";
export default {
  data() {
    return {
      url:"",
      // 需求列表
      TradeRetreatList: [],
      // 搜索关键字
      searchInfo: "",
      // 总条数
      total: 0,
      // 当前页数
      pageNo: 1,
      // 每页的条数
      pageSize: 10,
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
      return this.TradeRetreatList.slice((this.pageNo-1)*this.pageSize,this.pageNo*this.pageSize);
    },
  },
  methods: {
    // 点击行关闭
    clickTable(row,index,e) {
      //调用,table的方法,展开/折叠 行
      this.$refs.refTable.toggleRowExpansion(row);
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
        tradeRetreatId: row.tradeRetreatId,
        retreatStatus: 3
      }
      updateTreatStatus(params).then(res=>{
        this.$message({
          message: "修改成功",
          type: "success",
        });
        this.getTradeRetreatList();
      })
    },
    // 通过按钮
    handleEdit(index, row) {
      let params = {
        tradeRetreatId: row.tradeRetreatId,
        retreatStatus: 2,
        providerId: row.tradeWork.providerId,
        employerId: row.tradeWork.employerId,
        PPhoneNumber:row.tradeWork.employerAccount.phoneNumber,
        EPhoneNumber:row.tradeWork.employerAccount.phoneNumber,
        balance:row.tradeWork.works.worksPrice,
        creditTime: getDate(),
      }
      updateTreatStatus(params).then(res=>{
        this.$message({
          message: "修改成功",
          type: "success",
        });
        this.getTradeRetreatList();
      })
    },
    // 用雇主关键词搜索
    getSearchDemand(){
      this.isShowloading=true;
      let params = {
        employerName: this.searchInfo,
      }
      getSearchDemand(params).then(res=>{
        this.pageNo = 1;
        this.demandList = res.data;
        this.total = this.demandList.length;
        this.isShowloading=false;
      })
    },
    // 获取退款列表
    getTradeRetreatList(){
      this.isShowloading=true;
      getTradeRetreatList().then(res=>{
        console.log(res.data)
        this.TradeRetreatList = res.data;
        this.total = this.TradeRetreatList.length;
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
    this.getTradeRetreatList();
  }
};
</script>
<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}.demo-table-expand {
   font-size: 0;
 }
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
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


