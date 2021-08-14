<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>交易管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container" >
      <template>
        <el-tabs  @tab-click="" value="first">
          <el-tab-pane label="所有交易记录" name="first">
            <el-table
                :data="tradeRecordDate"
                class="table"
                v-loading="isShowloading"
                ref=""
                @row-click=""
            >
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="投标项目">
                      <span>{{ scope.row.tenderRecord.demand.demandTitle }}</span>
                    </el-form-item>
                    <el-form-item label="雇主名称">
                      <span>{{ scope.row.tenderRecord.demand.employerAccount.employerName }}</span>
                    </el-form-item>
                    <el-form-item label="承接信息">
                      <span>{{ scope.row.tenderRecord.unionStatus }}</span>
                    </el-form-item>
                    <el-form-item label="项目佣金">
                      <span>{{ scope.row.tenderRecord.demand.predictPrice }}</span>
                    </el-form-item>
                    <el-form-item label="承接价格">
                      <span>{{ scope.row.tenderRecord.price }}</span>
                    </el-form-item>
                    <el-form-item label="规定工期">
                      <span>{{ scope.row.tenderRecord.projectTime }}</span>
                    </el-form-item>
                    <el-form-item label="承接时间">
                      <span>{{ scope.row.tradeTime }}</span>
                    </el-form-item>
                    <el-form-item label="交易状态">
                      <el-tag v-if="scope.row.tradeStatus === 0">已预付款</el-tag>
                      <el-tag type="info" v-if="scope.row.tradeStatus === 1">等待付款</el-tag>
                      <el-tag type="warning" v-if="scope.row.tradeStatus === 2">项目交付</el-tag>
                      <el-tag type="warning" v-if="scope.row.tradeStatus === 3">核验项目</el-tag>
                      <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易完成</el-tag>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column type="index" label="序号" sortable></el-table-column>
              <el-table-column prop="tenderRecord.demand.demandTitle" label="投标项目"></el-table-column>
              <el-table-column prop="tenderRecord.demand.employerAccount.employerName" width="100" label="雇主名称"></el-table-column>
              <el-table-column prop="tenderRecord.unionStatus" align="center" label="承接信息">
                <template slot-scope="scope">
                  <el-popover
                      placement="top-start"
                      :title="title"
                      :ref="scope.row.tenderRecordId"
                      width="200"
                      trigger="hover"
                  >
                    <el-table :data="providerDate">
                      <el-table-column label="服务商名" prop="providerName"></el-table-column>
                      <el-table-column label="信誉积分" prop="providerInfo.credit"></el-table-column>
                    </el-table>
                    <el-button slot="reference" type="info" v-if="scope.row.tenderRecord.unionStatus === '0' " icon="el-icon-lx-people">个人</el-button>
                    <el-button slot="reference" type="warning" v-if="scope.row.tenderRecord.unionStatus !== '0'" icon="el-icon-lx-group">联盟</el-button>
                  </el-popover>
                </template>
              </el-table-column>
              <el-table-column prop="tradeTime" label="承接时间"></el-table-column>
              <el-table-column prop="tenderRecord.price" label="承接价格"></el-table-column>
              <el-table-column prop="tradeStatus" label="交易状态">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.tradeStatus === 0">已预付款</el-tag>
                  <el-tag type="info" v-if="scope.row.tradeStatus === 1">等待付款</el-tag>
                  <el-tag type="warning" v-if="scope.row.tradeStatus === 2">项目交付</el-tag>
                  <el-tag type="warning" v-if="scope.row.tradeStatus === 3">核验项目</el-tag>
                  <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易完成</el-tag>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="已完成" name="second">配置管理</el-tab-pane>
          <el-tab-pane label="进行中" name="third">角色管理</el-tab-pane>
          <el-tab-pane label="已取消" name="fourth">定时任务补偿</el-tab-pane>
          <el-tab-pane label="申述中" name="fourth1">定时任务补偿</el-tab-pane>
        </el-tabs>
      </template>
    </div>
  </div>
</template>
<script>
import {
  getTradeRecord,
  getProvider,
} from "../../../api/api";
export default {
  data() {
    return {
      // 是否启动加载动画
      isShowloading: false,
      // 获取到的投标交易列表
      tradeRecordDate: [],
      // 标题
      title: '',
      // 获取对应的服务商
      providerDate: [],
      // 联盟状态
      unionStatus: '',
    };
  },
  // 计算属性
  computed:{
  },
  methods: {
    // 关闭提示
    confirmClose(done) {
      this.$confirm("确认关闭将丢失已编辑内容？", "提示", {
        type: "warning"
      }).then(() => {
        done();
      });
    },
    // 获取投标交易列表
    getTradeRecord(){
      this.isShowloading = true;
      getTradeRecord().then(res=>{
        this.tradeRecordDate = res.data;
        this.isShowloading = false;
      });
    },
  },
  mounted() {
    this.getTradeRecord();
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


