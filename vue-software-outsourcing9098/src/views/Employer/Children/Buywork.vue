<template>
  <div>
    <div>作品列表</div>
  <div id="list">
    <el-row v-for="item in buylist">
      <div class="goods" >
        <el-col :span="5" style="border: none">
          <img src="../../../assets/img/bell.png"  alt="" style="width: 50%;height: 50%;margin: 5%">
        </el-col>
        <el-col :span="7" style="border: none">
          <p style="padding-top: 20px;">作品编号：<span>No.{{item.worksId}}&nbsp;</span></p>
          <p style="padding-top: 20px;">作品类型：<span style="padding-right: 50px;">{{item.demandTypeName}}</span></p>
          <p style="padding-top: 20px;">交易时间：{{item.tradeTime}}</p>
          <p style="padding-top: 20px;padding-bottom: 20px;">服务商联系方式：{{item.phoneNumber}}</p>
        </el-col>
        <el-col :span="9" style="border: none">
          <p style="padding-top: 20px;">作品名称：<span>&nbsp;{{item.worksTitle}}</span></p>
          <p style="padding-top: 20px;">交易金额：<span>&nbsp;￥{{item.worksPrice}}</span></p>
          <p style="padding-top: 20px;">订单编号：<span>&nbsp;{{item.tradeOrder}}</span></p>
          <p style="padding-top: 20px;">服务商名称：<span>&nbsp;{{item.providerName}}</span></p>
        </el-col>
        <el-col :span="3" style="border: none">
          <p style="padding-top: 20px;margin-left: 40%;">
            <el-link v-if="item.tradeStatus==1 || item.tradeStatus==2 || item.tradeStatus==3" type="success" disabled><span style="font-size: 20px;">交易中...</span></el-link>
            <el-link v-else-if="item.tradeStatus==4" type="success" disabled><span style="font-size: 20px;">交易成功</span></el-link>
            <el-link v-else type="danger" disabled><span style="font-size: 20px;">交易失败</span></el-link>
          </p>
          <p style="margin-top: 20px;margin-left: 20%;">
            <el-button v-if="item.tradeStatus==1 || item.tradeStatus==2 || item.tradeStatus==3" type="primary" @click="confirmRec(item)">确认收货</el-button>
            <el-button v-else-if="item.tradeStatus==4"  type="success" disabled>&nbsp;已收货</el-button>
            <el-button v-else-if="item.tradeStatus==5"  type="success" disabled>&nbsp;退货中</el-button>
            <el-button v-else-if="item.tradeStatus==6"  type="success" disabled>&nbsp;退货成功</el-button>
          </p>
          <p style="margin-top: 20px;margin-left: 20%;">
            <el-button v-if="item.tradeStatus==1 || item.tradeStatus==2 || item.tradeStatus==3" type="warning" @click="returnList(item.tradeWorksId)">申请退货</el-button>
          </p>

        </el-col>
        <div>
        <el-col :span="24" style="border: none">
            <div style="border: none;"> <el-divider content-position="center" ><span style="font-size: 20px;">交付进度</span></el-divider></div>
          <div style="margin-left: 15%;margin-top: 20px;margin-bottom: 20px;" v-if="item.tradeStatus<5">
          <el-steps :space="200" :active="item.tradeStatus" finish-status="success" align-center>
            <el-step title="支付成功"></el-step>
            <el-step title="服务方确认"></el-step>
            <el-step title="作品交付"></el-step>
            <el-step title="交易完成"></el-step>
          </el-steps>
          </div>
          <div style="margin-left: 32%;margin-top: 20px;margin-bottom: 20px;" v-if="item.tradeStatus>4">
            <el-steps :space="200" :active="item.tradeStatus==5" finish-status="success" align-center>
              <el-step title="申请退款"></el-step>
              <el-step title="平台同意，退款成功"></el-step>
            </el-steps>
          </div>
        </el-col>
        </div>
      </div>
    </el-row>
  </div>

    <!--申请退货-->
    <el-dialog title="申请退货" :visible.sync="returnFormVisible">
      <el-input
          type="textarea"
          :rows="10"
          placeholder="请输入退货理由"
          v-model="textarea">
      </el-input>
      <div style="margin-top: 20px;margin-left: 45%">
      <el-button size="small" type="primary" @click="returnSubmit">提交</el-button>
      <el-button size="small" @click="returnFormVisible = false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Buywork",
  data(){
    return{
      user:'',
      buylist:{},
      formLabelWidth: "120px",
      textarea: '',
      returnFormVisible:false,
      publicID:''
    }
  },
  methods:{
      seltradeWork(){
        this.$axios.post('buy/buylist',this.$qs.stringify({
          employerId:this.user.employerId
        })).then(res=>{
           this.buylist=res.data;
        })
      },
    confirmRec(item){
      this.$confirm("是否确认收货?（如确认收货后，产生的一系列问题平台不予负责）", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$axios.post('buy/confirm',this.$qs.stringify({
          tradeWorksId:item.tradeWorksId,
          employerId:this.user.employerId,
          tradeContent:"作品《"+item.worksTitle+"》出售",
          tradeType:"开发宝",
          type:"服务商",
          tradeState:"ACQ.TRADE_HAS_SUCCESS",
          tradeCapital:item.worksPrice,
          providerId:item.providerId,
          phoneNumber:item.phoneNumber
        })).then(res=>{
            if(res.data==1){
              this.$message({
                message: "确认收货成功！",
                type: "success"
              });
              this.seltradeWork();
            }else{
              this.$message({
                message: "确认收货失败！",
                type: "error"
              });
          }
        })
      }).catch(() => {
        this.$message({
          type: "info",
          message: "已取消确认收货"
        });
      });
    },
    returnList(tradeWorksId){
        this.publicID=tradeWorksId;
        this.returnFormVisible=true
    },
    returnSubmit(){
      this.$axios.post('buy/returnBuy',this.$qs.stringify({
        tradeWorksId:this.publicID,
        remarks:this.textarea,
        tradeStatus:5
      })).then(res=>{
        if(res.data===1){
          this.$notify.success({
            title: '提示',
            message: '申请退款成功'
          });
        }else{
          this.$notify.error({
            title: '提示',
            message: '申请退款失败'
          });
        }
        this.returnFormVisible=false;
        this.seltradeWork();
      })
      }

  },
  mounted() {
    this.user=JSON.parse(sessionStorage.getItem("user"));
    this.seltradeWork();
  },

}
</script>

<style scoped>
#list {
  margin-top: 30px;
  height: auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff
}

#list div {
  height: auto;
  border-bottom: 1px solid #eff2f7;
}
.goods:hover {
  background: #eff2f7;
}

</style>
