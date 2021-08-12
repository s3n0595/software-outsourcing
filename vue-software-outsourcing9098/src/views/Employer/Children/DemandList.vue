<template>
  <div>
    <div>需求列表</div>

    <div id="list">
      <el-collapse accordion :value="activeName" @change="handleChange">
        <el-collapse-item  class="goods" v-for="(item,index) in myDemand" :key="index" :name="index + 1">
          <template slot="title">
            <span>No.{{item.demandId}}</span><span style="margin-left: 30px;">{{item.demandTitle}}</span>
          </template>
          <div>

            <el-row :span="24">
              <el-col :span="6">
                <img :src="'api/images/bg_00' + (item.demandId%5) +'.png'" alt="" style="width: 50%;height: 50%;margin: 5%">
<!--                 <img src="http://img.ithome.com/newsuploadfiles/thumbnail/2021/6/555782_240.jpg" alt="" style="width: 50%;height: 50%;margin: 5%">-->
              </el-col>
              <el-col :span="9">
                <p>需求类型：{{item.demandTypeName}}</p>
                <p>发布时间：{{item.releaseTime}}</p>
              </el-col>
              <el-col :span="9">
                <p>预算价格：{{item.predictPrice}}（元）</p>
                <p>预算工期：{{item.predictTime}}（周）</p>
              </el-col>
            </el-row>
          </div>
          <div style="margin: 30px auto;width: 80%;">
            <el-steps :active="delStatus(item.demandStatus)" finish-status="success" >
              <el-step title="待审核" v-if="item.demandStatus==0"></el-step>
              <el-step title="审核失败" status="error" v-else-if="item.demandStatus==2"></el-step>
              <el-step title="审核成功" v-else status="success"></el-step>
              <el-step title="招募开发商"></el-step>
              <el-step title="项目开发"></el-step>
              <el-step title="项目交付"></el-step>
              <el-step title="项目完成"></el-step>
            </el-steps>
          </div>
          <div style="margin-top: 30px" v-show="item.demandStatus==3">
            <el-table
                    :data="tenderList"
                    border
                    class="table"
                    ref="multipleTable">
              <el-table-column prop="tenderId" label="服务商ID"></el-table-column>
              <el-table-column prop="menuName" label="开发者类型"></el-table-column>
              <el-table-column prop="existTime" label="申请时间"></el-table-column>
              <el-table-column prop="projectTime" label="完成工期"></el-table-column>
              <el-table-column prop="price" label="投标价格"></el-table-column>
              <el-table-column label="申请材料" align="center">
                <template slot-scope="scope">
                  <el-button>预览</el-button>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="180" align="center">
                <template slot-scope="scope" >
                  <el-button type="text" disabled v-if="scope.row.applyStatus == 1">已通过</el-button>
                  <el-button type="text" disabled v-if="scope.row.applyStatus == 2">已拒绝</el-button>
                  <el-button type="success" @click="handlePass(scope.$index, scope.row)"  v-if="scope.row.applyStatus == 0">通过</el-button>
                  <el-button type="danger" @click="handleRefuse(scope.$index, scope.row)" v-if="scope.row.applyStatus == 0">拒绝</el-button>
                </template>
              </el-table-column>

            </el-table>

          </div>
          <div style="margin-top: 30px" v-show="item.demandStatus == 1">
            <el-button @click="recruitProvider(item.demandId)">开始招募服务商</el-button>
          </div>
          <div style="margin-top: 30px" v-show="item.demandStatus == 4 || item.demandStatus == 5">
            <el-steps :active="tenderProvider.tradeStatus" finish-status="success" simple direction="vertical">
              <el-step title="预付款"></el-step>
              <el-step title="等待交付"></el-step>
              <el-step title="核验项目"></el-step>
              <el-step title="付尾款"></el-step>
              <el-step title="交易完成"></el-step>
            </el-steps>
            <div style="margin-top: 30px">
              <el-row>
                <el-col :span="6">
                  <p>承接价格：{{tenderProvider.price}}</p>
                  <p>承接工期：{{tenderProvider.projectTime}}</p>
                  <p>开发商类型：个体</p>
                  <p>开发商名称：{{}}</p>

                </el-col>
                <el-col :span="12">
                 <p>开发商联系方式：{{}}</p>
                 <p>开发商信用分：{{}}</p>

                </el-col>
                <el-col :span="6">
                  <p>
                    <el-button @click="">联系开发商</el-button>
                  </p>
                  <p v-show="tenderProvider.tradeStatus==0">
                    <el-button @click="advanceCharge">预付款</el-button>
                  </p>
                  <p v-show="tenderProvider.tradeStatus==2">
                    <el-button @click="checkProject">核验项目</el-button>
                  </p>
                  <p v-show="tenderProvider.tradeStatus==3">
                    <el-button @click="restCharge">付尾款</el-button>
                  </p>
                </el-col>
              </el-row>
          </div>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>


<!--    <div style="margin-top: 30px" v-show="showTable">-->
<!--      <el-table-->
<!--              :data="tenderList"-->
<!--              border-->
<!--              class="table"-->
<!--              ref="multipleTable">-->
<!--        <el-table-column prop="tenderId" label="服务商ID"></el-table-column>-->
<!--        <el-table-column prop="menuName" label="开发者类型"></el-table-column>-->
<!--        <el-table-column prop="existTime" label="申请时间"></el-table-column>-->
<!--        <el-table-column prop="projectTime" label="完成工期"></el-table-column>-->
<!--        <el-table-column prop="price" label="投标价格"></el-table-column>-->
<!--        <el-table-column label="申请材料" align="center">-->
<!--          <template slot-scope="scope" >-->
<!--            <el-button>预览</el-button>-->
<!--&lt;!&ndash;            <a :href="'api/images/'+scope.row.annexPath" download="">下载</a>&ndash;&gt;-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column  label="操作" width="180" align="center">-->
<!--          <el-button type="success">通过</el-button>-->
<!--            <el-button type="danger">拒绝</el-button>-->
<!--        </el-table-column>-->
<!--        <el-table-column label="操作" width="180" align="center">-->
<!--          <template slot-scope="scope">-->
<!--            <el-button type="success" @click="handlePass(scope.$index, scope.row)">通过</el-button>-->
<!--            <el-button type="danger" @click="handleRefuse(scope.$index, scope.row)">拒绝</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->

<!--      </el-table>-->
<!--    </div>-->
<!--    <div style="margin-top: 30px;" v-show="showDetails">-->

<!--    </div>-->
  </div>

</template>

<script>
export default {
  name: "Accountpreview",
  data() {
    return{
      user:'',
      balance:'',
      activeName:"",
      status:"审核成功",
      tenderProvider: {},
      myDemand:[],
      employer:{},
      tenderList:[],
      active:0,
      showSteps:false,
      showTable:false,
      statu:"",
      showDetails:false,
      currentDemand:{},
      succeed:false
    };
  },
  methods:{

    delStatus(status) {
      if(status <= 2) {
        return 0;
      } else {
        if(status == 6) return status-1;
        else return status - 2;
      }
    },
    handleChange(activeName){

      if(activeName != null && activeName != '') {
        this.currentDemand = this.myDemand[activeName - 1];
        switch (this.currentDemand.demandStatus) {
          case 3:
            this.getTenderList(this.currentDemand.demandId);
            break;
          case 4:
          case 5:
            this.getTenderProvider(this.currentDemand.demandId);
            break;



        }
      }
    },
    advanceCharge(){
      let param = {
        tradeRecordId: this.tenderProvider.tradeRecordId,
        employerId: this.employer.employerId
      };
      this.$axios.get("/demand/advanceCharge", {params: param}).then(response => {
        this.getTenderProvider(this.currentDemand.demandId);
      });
    },
    restCharge(){
      let param = {
        tradeRecordId: this.tenderProvider.tradeRecordId,
        employerId: this.employer.employerId
      };
      this.$axios.get("/demand/restCharge", {params: param}).then(response => {
        this.getMyDemandList();
      });
    },
    checkProject(){
      let param = {
        tradeRecordId: this.tenderProvider.tradeRecordId,
      };
      this.$axios.get("/demand/checkProject", {params: param}).then(response => {
        this.getTenderProvider(this.currentDemand.demandId);
      });
    },
    toEvaluate(){
      let param = {
        tradeRecordId: this.tenderProvider.tradeRecordId,
        demandId: this.currentDemand.demandId
      };
      this.$axios.get("/demand/toEvaluate", {params: param}).then(response => {
        this.getMyDemandList();
      });
    },
    recruitProvider(demandId){
      this.$axios.get("/demand/recruitProvider", {params: {demandId: demandId}}).then(response => {
        // console.log(response.data)
        // this.tenderList = response.data;
        this.getMyDemandList();
      });
    },
    selectDemand() {
      this.showSteps = true;

      console.log(this.demand.demandStatus);
      if(this.demand.demandStatus == 2) {
        this.status = "审核失败";
        this.statu = "error";
      } else {
        this.status = "审核成功";
        this.statu = "";
      }
      switch (this.demand.demandStatus) {
        case 0:
          this.active = 0;
          this.showTable = false;
          this.showDetails = false;

          break;
        case 1:
        case 2:
          this.showTable = false;
          this.showDetails = false;
          this.active = 1;
          break;
        case 3:
          this.active = 2;
          this.showTable = true;
          this.showDetails = false;
          break;
        case 4:
          this.active = 3;
          this.showTable = false;
          this.showDetails = true;
          break;
        case 5:
          this.active = 4;
          this.showDetails = false;
          this.showTable = false;
          break;
        default:
          break;
      }
      this.getTenderList();
    },
    getTenderList(demandId){
      this.$axios.get("/demand/tenderList", {params: {demandId: demandId}}).then(response => {
        this.tenderList = response.data;
      });
    },
    getTenderProvider(demandId) {
      this.$axios.get("/demand/tenderProvider", {params: {demandId: demandId}}).then(response => {
        this.tenderProvider = response.data;
        if(this.tenderProvider.tradeStatus == 4) {
          this.succeed = true;
        }
      });
    },
    handlePass(index, row) {
      console.log(row)
      this.$confirm("确定把项目交给该开发商吗？", "提示", {
        type: "info"
      }).then(() => {
        this.$axios.get("/demand/acceptTender", {params: row}).then(response => {
          this.getTenderList(row.demandId);
        });
      });
    },
    handleRefuse(index, row) {
      this.$confirm("确定拒绝该开发商的申请吗？", "提示", {
        type: "warning"
      }).then(() => {
        this.$axios.get("/demand/refuseTender", {params: row}).then(response => {
          this.getTenderList(row.demandId);
        });
      });
    },
    getMyDemandList() {
      this.$axios.get("/demand/myDemand", {params: {employerId: this.employer.employerId}}).then(response => {
        this.myDemand = response.data;
      });
    }
  },
  mounted() {
    this.employer = JSON.parse(sessionStorage.getItem("user"));
    this.getMyDemandList();
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 600px;
  background-color: rgb(248,249,251);
  margin-top: 12%;
  margin-left: 22%;
}

  p{
    padding-top: 20px;
  }
</style>
