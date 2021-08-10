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
                :data="tradeDate"
                class="table"
                v-loading="isShowloading"
                ref="refTable"
                @row-click="clickTable"
            >
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="订单编号">
                      <span>{{ scope.row.tradeOrder }}</span>
                    </el-form-item>
                    <el-form-item label="雇主名称">
                      <span>{{ scope.row.employerAccount.employerName }}</span>
                    </el-form-item>
                    <el-form-item label="售卖单位">
                      <span>{{ scope.row.providerAccount.providerName }}</span>
                    </el-form-item>
                    <el-form-item label="交易时间">
                      <span>{{ scope.row.tradeTime }}</span>
                    </el-form-item>
                    <el-form-item label="商品名称">
                      <span>{{ scope.row.works.worksTitle }}</span>
                    </el-form-item>
                    <el-form-item label="交易状态">
                      <el-tag v-if="scope.row.tradeStatus === 1">买家付款</el-tag>
                      <el-tag type="info" v-if="scope.row.tradeStatus === 2">确认作品</el-tag>
                      <el-tag type="warning" v-if="scope.row.tradeStatus === 3">交付作品</el-tag>
                      <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易成功</el-tag>
                    </el-form-item>
                    <el-form-item label="商品描述">
                      <span>{{ scope.row.works.worksDescribe }}</span>
                    </el-form-item>
                    <el-form-item label="成交金额">
                      <span>{{ scope.row.works.worksPrice }}</span>
                    </el-form-item>
                    <el-form-item label="上传时间">
                      <span>{{ scope.row.works.releaseTime }}</span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column type="index" label="序号" sortable></el-table-column>
              <el-table-column prop="tradeOrder" label="订单编号"></el-table-column>
              <el-table-column prop="employerAccount.employerName" label="雇主名称"></el-table-column>
              <el-table-column prop="providerAccount.providerName" label="服务商名称"></el-table-column>
              <el-table-column prop="works.worksTitle" label="项目名称"></el-table-column>
              <el-table-column prop="tradeTime" label="交易时间"></el-table-column>
              <el-table-column prop="tradeStatus" label="交易状态">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.tradeStatus === 1">买家付款</el-tag>
                  <el-tag type="info" v-if="scope.row.tradeStatus === 2">确认作品</el-tag>
                  <el-tag type="warning" v-if="scope.row.tradeStatus === 3">交付作品</el-tag>
                  <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易成功</el-tag>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="付款成功" name="second">
            <el-table
                :data="this.tradeDate_1"
                class="table"
                v-loading="isShowloading"
                ref="refTable1"
                @row-click="clickTable1"
            >
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="订单编号">
                      <span>{{ scope.row.tradeOrder }}</span>
                    </el-form-item>
                    <el-form-item label="雇主名称">
                      <span>{{ scope.row.employerAccount.employerName }}</span>
                    </el-form-item>
                    <el-form-item label="售卖单位">
                      <span>{{ scope.row.providerAccount.providerName }}</span>
                    </el-form-item>
                    <el-form-item label="交易时间">
                      <span>{{ scope.row.tradeTime }}</span>
                    </el-form-item>
                    <el-form-item label="商品名称">
                      <span>{{ scope.row.works.worksTitle }}</span>
                    </el-form-item>
                    <el-form-item label="交易状态">
                      <el-tag v-if="scope.row.tradeStatus === 1">买家付款</el-tag>
                      <el-tag type="info" v-if="scope.row.tradeStatus === 2">确认作品</el-tag>
                      <el-tag type="warning" v-if="scope.row.tradeStatus === 3">交付作品</el-tag>
                      <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易成功</el-tag>
                    </el-form-item>
                    <el-form-item label="商品描述">
                      <span>{{ scope.row.works.worksDescribe }}</span>
                    </el-form-item>
                    <el-form-item label="成交金额">
                      <span>{{ scope.row.works.worksPrice }}</span>
                    </el-form-item>
                    <el-form-item label="上传时间">
                      <span>{{ scope.row.works.releaseTime }}</span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column type="index" label="序号" sortable></el-table-column>
              <el-table-column prop="tradeOrder" label="订单编号"></el-table-column>
              <el-table-column prop="employerAccount.employerName" label="雇主名称"></el-table-column>
              <el-table-column prop="providerAccount.providerName" label="服务商名称"></el-table-column>
              <el-table-column prop="works.worksTitle" label="项目名称"></el-table-column>
              <el-table-column prop="tradeTime" label="交易时间"></el-table-column>
              <el-table-column prop="tradeStatus" label="交易状态">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.tradeStatus === 1">买家付款</el-tag>
                  <el-tag type="info" v-if="scope.row.tradeStatus === 2">确认作品</el-tag>
                  <el-tag type="warning" v-if="scope.row.tradeStatus === 3">交付作品</el-tag>
                  <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易成功</el-tag>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="确认作品" name="third">
            <el-table
                :data="this.tradeDate_2"
                class="table"
                v-loading="isShowloading"
                ref="refTable2"
                @row-click="clickTable2"
            >
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="订单编号">
                      <span>{{ scope.row.tradeOrder }}</span>
                    </el-form-item>
                    <el-form-item label="雇主名称">
                      <span>{{ scope.row.employerAccount.employerName }}</span>
                    </el-form-item>
                    <el-form-item label="售卖单位">
                      <span>{{ scope.row.providerAccount.providerName }}</span>
                    </el-form-item>
                    <el-form-item label="交易时间">
                      <span>{{ scope.row.tradeTime }}</span>
                    </el-form-item>
                    <el-form-item label="商品名称">
                      <span>{{ scope.row.works.worksTitle }}</span>
                    </el-form-item>
                    <el-form-item label="交易状态">
                      <el-tag v-if="scope.row.tradeStatus === 1">买家付款</el-tag>
                      <el-tag type="info" v-if="scope.row.tradeStatus === 2">确认作品</el-tag>
                      <el-tag type="warning" v-if="scope.row.tradeStatus === 3">交付作品</el-tag>
                      <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易成功</el-tag>
                    </el-form-item>
                    <el-form-item label="商品描述">
                      <span>{{ scope.row.works.worksDescribe }}</span>
                    </el-form-item>
                    <el-form-item label="成交金额">
                      <span>{{ scope.row.works.worksPrice }}</span>
                    </el-form-item>
                    <el-form-item label="上传时间">
                      <span>{{ scope.row.works.releaseTime }}</span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column type="index" label="序号" sortable></el-table-column>
              <el-table-column prop="tradeOrder" label="订单编号"></el-table-column>
              <el-table-column prop="employerAccount.employerName" label="雇主名称"></el-table-column>
              <el-table-column prop="providerAccount.providerName" label="服务商名称"></el-table-column>
              <el-table-column prop="works.worksTitle" label="项目名称"></el-table-column>
              <el-table-column prop="tradeTime" label="交易时间"></el-table-column>
              <el-table-column prop="tradeStatus" label="交易状态">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.tradeStatus === 1">买家付款</el-tag>
                  <el-tag type="info" v-if="scope.row.tradeStatus === 2">确认作品</el-tag>
                  <el-tag type="warning" v-if="scope.row.tradeStatus === 3">交付作品</el-tag>
                  <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易成功</el-tag>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="交付作品" name="fourth">
            <el-table
                :data="this.tradeDate_3"
                class="table"
                v-loading="isShowloading"
                ref="refTable3"
                @row-click="clickTable3"
            >
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="订单编号">
                      <span>{{ scope.row.tradeOrder }}</span>
                    </el-form-item>
                    <el-form-item label="雇主名称">
                      <span>{{ scope.row.employerAccount.employerName }}</span>
                    </el-form-item>
                    <el-form-item label="售卖单位">
                      <span>{{ scope.row.providerAccount.providerName }}</span>
                    </el-form-item>
                    <el-form-item label="交易时间">
                      <span>{{ scope.row.tradeTime }}</span>
                    </el-form-item>
                    <el-form-item label="商品名称">
                      <span>{{ scope.row.works.worksTitle }}</span>
                    </el-form-item>
                    <el-form-item label="交易状态">
                      <el-tag v-if="scope.row.tradeStatus === 1">买家付款</el-tag>
                      <el-tag type="info" v-if="scope.row.tradeStatus === 2">确认作品</el-tag>
                      <el-tag type="warning" v-if="scope.row.tradeStatus === 3">交付作品</el-tag>
                      <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易成功</el-tag>
                    </el-form-item>
                    <el-form-item label="商品描述">
                      <span>{{ scope.row.works.worksDescribe }}</span>
                    </el-form-item>
                    <el-form-item label="成交金额">
                      <span>{{ scope.row.works.worksPrice }}</span>
                    </el-form-item>
                    <el-form-item label="上传时间">
                      <span>{{ scope.row.works.releaseTime }}</span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column type="index" label="序号" sortable></el-table-column>
              <el-table-column prop="tradeOrder" label="订单编号"></el-table-column>
              <el-table-column prop="employerAccount.employerName" label="雇主名称"></el-table-column>
              <el-table-column prop="providerAccount.providerName" label="服务商名称"></el-table-column>
              <el-table-column prop="works.worksTitle" label="项目名称"></el-table-column>
              <el-table-column prop="tradeTime" label="交易时间"></el-table-column>
              <el-table-column prop="tradeStatus" label="交易状态">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.tradeStatus === 1">买家付款</el-tag>
                  <el-tag type="info" v-if="scope.row.tradeStatus === 2">确认作品</el-tag>
                  <el-tag type="warning" v-if="scope.row.tradeStatus === 3">交付作品</el-tag>
                  <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易成功</el-tag>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="确认收货" name="fourth1">
            <el-table
                :data="this.tradeDate_4"
                class="table"
                v-loading="isShowloading"
                ref="refTable4"
                @row-click="clickTable4"
            >
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-form-item label="订单编号">
                      <span>{{ scope.row.tradeOrder }}</span>
                    </el-form-item>
                    <el-form-item label="雇主名称">
                      <span>{{ scope.row.employerAccount.employerName }}</span>
                    </el-form-item>
                    <el-form-item label="售卖单位">
                      <span>{{ scope.row.providerAccount.providerName }}</span>
                    </el-form-item>
                    <el-form-item label="交易时间">
                      <span>{{ scope.row.tradeTime }}</span>
                    </el-form-item>
                    <el-form-item label="商品名称">
                      <span>{{ scope.row.works.worksTitle }}</span>
                    </el-form-item>
                    <el-form-item label="交易状态">
                      <el-tag v-if="scope.row.tradeStatus === 1">买家付款</el-tag>
                      <el-tag type="info" v-if="scope.row.tradeStatus === 2">确认作品</el-tag>
                      <el-tag type="warning" v-if="scope.row.tradeStatus === 3">交付作品</el-tag>
                      <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易成功</el-tag>
                    </el-form-item>
                    <el-form-item label="商品描述">
                      <span>{{ scope.row.works.worksDescribe }}</span>
                    </el-form-item>
                    <el-form-item label="成交金额">
                      <span>{{ scope.row.works.worksPrice }}</span>
                    </el-form-item>
                    <el-form-item label="上传时间">
                      <span>{{ scope.row.works.releaseTime }}</span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column type="index" label="序号" sortable></el-table-column>
              <el-table-column prop="tradeOrder" label="订单编号"></el-table-column>
              <el-table-column prop="employerAccount.employerName" label="雇主名称"></el-table-column>
              <el-table-column prop="providerAccount.providerName" label="服务商名称"></el-table-column>
              <el-table-column prop="works.worksTitle" label="项目名称"></el-table-column>
              <el-table-column prop="tradeTime" label="交易时间"></el-table-column>
              <el-table-column prop="tradeStatus" label="交易状态">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.tradeStatus === 1">买家付款</el-tag>
                  <el-tag type="info" v-if="scope.row.tradeStatus === 2">确认作品</el-tag>
                  <el-tag type="warning" v-if="scope.row.tradeStatus === 3">交付作品</el-tag>
                  <el-tag type="success" v-if="scope.row.tradeStatus === 4">交易成功</el-tag>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </template>
    </div>
  </div>
</template>
<script>
import {
  getTradeWordList,
} from "../../../api/api";
export default {
  data() {
    return {
      // 是否启动加载动画
      isShowloading: false,
      disabled:true,
      // 所有交易记录
      tradeDate: [],
      // 已支付记录
      tradeDate_1: [],
      // 确认作品记录
      tradeDate_2: [],
      // 交付作品记录
      tradeDate_3: [],
      // 确认收货记录
      tradeDate_4: [],
      // 展开
      expend: [],
      formLabelWidth: "120px"
    };
  },
  // 计算属性
  computed:{
  },
  methods: {

    // 点击行关闭
    clickTable(row,index,e) {
      //调用,table的方法,展开/折叠 行
      this.$refs.refTable.toggleRowExpansion(row);
    },
    clickTable1(row,index,e) {
      //调用,table的方法,展开/折叠 行
      this.$refs.refTable1.toggleRowExpansion(row);
    },
    clickTable2(row,index,e) {
      //调用,table的方法,展开/折叠 行
      this.$refs.refTable2.toggleRowExpansion(row);
    },
    clickTable3(row,index,e) {
      //调用,table的方法,展开/折叠 行
      this.$refs.refTable3.toggleRowExpansion(row);
    },
    clickTable4(row,index,e) {
      //调用,table的方法,展开/折叠 行
      this.$refs.refTable4.toggleRowExpansion(row);
    },
      // 获取作品交易列表
    getTradeWordList(){
      this.isShowloading = true;
      getTradeWordList().then(res=>{
        this.tradeDate = res.data;
        res.data.forEach(val=>{
          if (val.tradeStatus === 1){
            this.tradeDate_1.push(val);
          }else if (val.tradeStatus === 2){
            this.tradeDate_2.push(val);
          }else if (val.tradeStatus === 3){
            this.tradeDate_3.push(val);
          }else if (val.tradeStatus === 4){
            this.tradeDate_4.push(val);
          }
        });
        this.isShowloading = false;
      })
    },
  },
  mounted() {
    this.getTradeWordList();
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


