<template>
    <el-main>
      <div>投标列表</div>
      <div id="list">
        <el-collapse accordion>
          <el-collapse-item class="goods" v-for="(item,index) in myTender" :key="index">
            <template slot="title">
              <span>No.{{item.demandId}}</span><span style="margin-left: 30px;">{{item.demandTitle}}</span>
            </template>
            <div>
              <el-row :span="24">
                <el-col :span="6">
                  <img :src="'api/images/bg_00' + (item.demandId%5) +'.png'" alt=""
                       style="width: 50%;height: 50%;margin: 5%">
                </el-col>
                <el-col :span="9">
                  <p>需求类型：{{item.demandTypeName}}</p>
                  <p>发布时间：{{item.releaseTime}}</p>
                  <p>预算价格：{{item.predictPrice}}（元）</p>
                  <p>预算工期：{{item.predictTime}}（周）</p>
                </el-col>
                <el-col :span="9">
                  <p>雇主姓名：{{item.employerName}}</p>
                  <p>投标人身份：{{item.providerName}}</p>
                  <p>投标价格：{{item.price}}（元）</p>
                  <p>投标工期：{{item.projectTime}}（周）</p>
                </el-col>
              </el-row>
            </div>
            <div style="margin-top: 30px">
              <el-steps :active="dealStatus(item.applyStatus, item.tradeStatus)" finish-status="success" simple>
                <el-step title="申请中" v-if="item.applyStatus==0"></el-step>
                <el-step title="投标失败" status="error" v-else-if="item.applyStatus==2"></el-step>
                <el-step title="投标成功" v-else></el-step>
                <el-step title="雇主预付款"></el-step>
                <el-step title="开发项目"></el-step>
                <el-step title="交付项目"></el-step>
                <el-step title="雇主付尾款"></el-step>
                <el-step title="交易完成"></el-step>
              </el-steps>
            </div>
            <div style="margin-top: 30px" v-if="item.tradeStatus < 4">
              <el-button @click="">联系雇主</el-button>
            </div>

            <div style="margin-top: 30px" v-show="item.tradeStatus == 1">
                <el-button @click="deliveryProject(item.tradeRecordId, item.demandId)">交付项目</el-button>
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
    </el-main>





</template>


<script>
export default {
  name: "GoodsList",
  data () {
    return {
      myTender:[],
      provider:{},
      count: 0,
      demand:{},
      searchInfo:"",
      demandList:{},
      status:false,
      fileList: [],
      dialogFormVisible: false,
      formLabelWidth: "120px",
      radio: "",
      form: {
        demandId:"",
        price: "",
        projectTime: "",
        tenderId: "",
      },
    }
  },
  methods: {
    getTender() {
    },

    getTenderList() {
    },
    dealStatus(applyStatus, tradeStatus){
      if(applyStatus == 1) {
        if(tradeStatus == 4) {
          return 6;
        } else {
          return tradeStatus + 1;
        }
      } else {
        return 0;
      }
    },
    deliveryProject(tradeRecordId, demandId){
      let param = {
        tradeRecordId: tradeRecordId,
        demandId: demandId
      }
       this.$axios.get("/demand/deliveryProject", {params: param}).then(response => {
         this.getMyTender();
      });

    },
    getMyTender(){
      this.$axios.get("/demand/myTender", {params: {providerId: this.provider.providerId}}).then(response => {
        this.myTender = response.data;
      });
    }
  },
  mounted() {
    this.provider = JSON.parse(sessionStorage.getItem("user"));
    this.getMyTender();
  }
}
</script>

<style scoped>
  p{
    padding-top: 20px;
  }
</style>
