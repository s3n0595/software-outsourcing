<template>
  <div style="padding: 30px;">
    <div style="padding: 30px;">
      <span></span>
      <el-select value-key="demandId" v-model="demand" placeholder="请选择" @change="selectDemand" style="margin-left: 10%;">
        <el-option
                v-for="item in myDemand"
                :key="item.demandId"
                :label="item.demandTitle"
                :value="item">
        </el-option>
      </el-select>
    </div>
    <div style="margin-top: 30px;" v-show="showSteps">
      <el-row>
        <el-col span="8">
          <span>需求编号：No.{{demand.demandId}}</span>
        </el-col>
        <el-col span="8">
          <span>需求名称：{{demand.demandTitle}}</span>
        </el-col>
        <el-col span="8">
          <span>需求类型：{{demand.demandTypeName}}</span>
        </el-col>
      </el-row>
      <el-row>
        <el-col span="8">
          <span>预算价格：{{demand.predictPrice}}</span>
        </el-col>
        <el-col span="8">
          <span>预算工期：{{demand.predictTime}}</span>
        </el-col>
        <el-col span="8"></el-col>
      </el-row>
      <el-row>
        <el-col span="8"></el-col>
        <el-col span="8"></el-col>
        <el-col span="8"></el-col>
      </el-row>

    </div>
    <div style="margin-top: 30px;" v-show="showSteps">
      <el-steps :active="active" finish-status="success">
        <el-step title="待审核"></el-step>
        <el-step :title="status" :status="statu"></el-step>
        <el-step title="招募中"></el-step>
        <el-step title="开发中"></el-step>
        <el-step title="已完成"></el-step>
      </el-steps>
    </div>
    <div style="margin-top: 30px" v-show="showTable">
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
          <template slot-scope="scope" >
            <el-button>预览</el-button>
<!--            <a :href="'api/images/'+scope.row.annexPath" download="">下载</a>-->
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template slot-scope="scope">
            <el-button
                    type="text"
                    @click="handlePass(scope.$index, scope.row)"
            >通过
            </el-button>
            <el-button
                    type="text"
                    @click="handleRefuse(scope.$index, scope.row)"
            >拒绝
            </el-button>
          </template>
        </el-table-column>

      </el-table>
    </div>
    <div style="margin-top: 30px;" v-show="showDetails">
      <el-steps :active="active" finish-status="success" simple direction="vertical">
        <el-step title="预付款"></el-step>
        <el-step title="等待交付"></el-step>
        <el-step title="核验项目"></el-step>
        <el-step title="付尾款"></el-step>
        <el-step title="交易完成"></el-step>
      </el-steps>
    </div>
  </div>

</template>

<script>
export default {
  name: "Accountpreview",
  data() {
    return{
      user:'',
      balance:'',
      status:"审核成功",
      myDemand:[],
      demand:{},
      employer:{},
      tenderList:[],
      active:0,
      showSteps:false,
      showTable:false,
      statu:"",
      showDetails:false,
    };
  },
  methods:{
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
    getTenderList(){
      this.$axios.get("/demand/tenderList", {params: {demandId: this.demand.demandId}}).then(response => {
        console.log(response.data)
        this.tenderList = response.data;
      });
    },
    handlePass(index, row) {
      console.log(row)
      this.$confirm("确定把项目交给该开发商吗？", "提示", {
        type: "info"
      }).then(() => {
        this.$axios.get("/demand/acceptTender", {params: row}).then(response => {
          this.showTable = false;
        });
      });
    },
    handleRefuse(index, row) {
      this.$confirm("确定拒绝该开发商的申请吗？", "提示", {
        type: "warning"
      }).then(() => {
        this.$axios.get("/demand/refuseTender", {params: row}).then(response => {
          this.getTenderList();
        });
      });
    },

  },
  mounted() {
    this.employer = JSON.parse(sessionStorage.getItem("user"));
    this.$axios.get("/demand/myDemand", {params: {employerId: this.employer.employerId}}).then(response => {
      console.log(response.data)
      this.myDemand = response.data;
    });
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
.title{
  margin-top: 10px;
  text-align: center;
}
.je{
  text-align: center;margin-top: 15px;
}
</style>
