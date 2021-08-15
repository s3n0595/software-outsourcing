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
              <el-table-column prop="tenderName" label="服务商名称"></el-table-column>
              <el-table-column prop="unionStatus" label="开发者类型"></el-table-column>
              <el-table-column prop="existTime" label="申请时间"></el-table-column>
              <el-table-column prop="projectTime" label="完成工期"></el-table-column>
              <el-table-column prop="price" label="投标价格"></el-table-column>
              <el-table-column label="申请材料" align="center">
                <template slot-scope="scope">
                  <el-button type="primary" @click="viewWord">在线预览</el-button>
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


                </el-col>
                <el-col :span="12">
                  <p>开发商名称：{{tenderProvider.providerName}}</p>
                 <p>开发商联系方式：{{tenderProvider.phoneNumber}}</p>
                 <p>开发商信用分：{{tenderProvider.credit}}</p>

                </el-col>
                <el-col :span="6">
                  <p>
                    <el-button @click="openChatRoom(item.demandId)">联系开发商</el-button>
                  </p>
                  <p v-show="tenderProvider.tradeStatus==0">
                    <el-button @click="getPayPwd('advance')">预付款</el-button>
                  </p>
                  <p v-show="tenderProvider.tradeStatus==2">
                    <el-button @click="checkProject">核验项目</el-button>
                  </p>
                  <p v-show="tenderProvider.tradeStatus==3">
                    <el-button @click="getPayPwd('rest')">付尾款</el-button>
                  </p>
                </el-col>
              </el-row>
          </div>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
  <div id="chatRoom" v-show="showChatRoom">
        <div id="roomTitle" >
          <span>={{this.chatRoom.roomName}}=开发交流群</span>
          <el-button size="mini" icon="el-icon-close" type="button" circle style="float: right;" @click="closeRoom"></el-button>
          <div style="clear: both;"></div>
        </div>

        <div style="overflow: auto;display: flex;flex-direction: column-reverse;height: 300px">

          <div v-for="item in historyList" style="height: auto;">
            <div style="margin-top: 10px;">
              <el-tag type="success" v-if="item.memberRole == 'provider'">服务商</el-tag>
              <el-tag type="warning" v-else>雇主</el-tag>
              <span v-else style="color:orange"></span>
              {{item.memberName}} {{item.sendTime}}</div>
            <div style="margin-top: 10px;">{{item.message}}</div>
          </div>
          <div style="text-align: center">
            <el-button type="text" size="mini" @click="loadMore">加载更多</el-button>
          </div>
        </div>
<!--        <el-divider></el-divider>-->
        <el-input type="textarea" autosize v-model="context" :autosize="{ minRows: 3, maxRows: 3}">
        </el-input>
        <div>
          <el-button type="primary" style="float: right;" @click="sendMessage">发送</el-button>
        </div>
      </div>
  <pay-box :dialogShow='flag' @closeDialog='comparePayPwd' style="z-index: 1000"></pay-box>
   <el-dialog title="友情提示" :visible.sync="showHint" width="30%" z-index="100">
  <span>即将支付预付款给服务商，请确认服务商信息以及项目信息无误。</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="showHint = false">取 消</el-button>
    <el-button type="primary" @click="flag = true , showHint = false">确 定</el-button>
  </span>
</el-dialog>
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
      flag:false,
      showHint:false,
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
      succeed:false,
      websocket:{},
      chatRoom:{
        chatRoomId:"",
        roomName:"",
      },
      context:"",
      showChatRoom:false,
       historyList:{},
      type:"",
      payPwd:""
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
    getPayPwd(typ) {
      this.type = typ;
      let param = {
        employerId: this.employer.employerId,
      };
      this.$axios.get("/demand/comparePayPwd", {params: param}).then(response => {
          if(response.data == 'none') {
            this.$notify.error({
              title:'提示',
              message:`您还未设置交易密码，请前往个人中心设置`
            })
          } else {
            this.payPwd = response.data;
            this.showHint = true;
          }
      });
    },
    viewWord() {
          window.open("http://view.officeapps.live.com/op/view.aspx?src=http://www.voeqmuh.icu:8080/file/日报.docx", '_blank');
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
        employerId: this.employer.employerId,
      };
      this.$axios.get("/demand/advanceCharge", {params: param}).then(response => {
        if(response.data == "success") {
          // this.getMyDemandList();
        this.getTenderProvider(this.currentDemand.demandId);
          this.$message({
            message: "付款成功",
            type: "success",
            offset: 150,
            duration: 3000,
          });
        } else {
          this.$message({
            message: "开发宝余额不足，请前往个人中心充值",
            type: "warning",
            offset: 150,
            duration: 2000,
          });
        }
      });
    },
    comparePayPwd() {
      this.showHint = false;
      this.flag = false;
      let paypwd = sessionStorage.getItem("paypwd");
      console.log(this.payPwd);
      if(paypwd == this.payPwd) {
        if (this.type == 'rest') {
          this.restCharge();
        } else {
          this.advanceCharge();
        }
      } else {
        this.$message({
              message: "支付密码错误，付款失败",
              type: "warning",
              offset:150,
              duration:2000,
        });
      }

      // this.$message({
      //         message: val,
      //         type: "success",
      //         offset:150,
      //         duration:2000,
      // });
    },
    restCharge(){
      let param = {
        tradeRecordId: this.tenderProvider.tradeRecordId,
        employerId: this.employer.employerId,
      };
      this.$axios.get("/demand/restCharge", {params: param}).then(response => {
        if(response.data == "success") {
          this.getMyDemandList();
          this.$message({
            message: "付款成功",
            type: "success",
            offset: 150,
            duration: 3000,
          });
        } else {
          this.$message({
            message: "开发宝余额不足，请前往个人中心充值",
            type: "warning",
            offset: 150,
            duration: 2000,
          });
        }

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
        console.log("*****************");
        console.log(this.tenderProvider);
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
          this.getMyDemandList();
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
        console.log(response.data)
        this.myDemand = response.data;
      });
    },
        openChatRoom(demandId){
      let that = this;
      //判断当前浏览器是否支持WebSocket
      if ("WebSocket" in window) {
        this.websocket = new WebSocket("ws://localhost:9093/webSocket/employer/" + this.employer.employerId + "/" + demandId);
      } else {
        alert("不支持建立socket连接");
      }

      //连接发生错误的回调方法
      this.websocket.onerror = function () {};
      //连接成功建立的回调方法
      this.websocket.onopen = function (event) {
      };
      //接收到消息的回调方法
      this.websocket.onmessage = function (event) {
        console.log("===========服务器发来消息===========");
        console.log(event.data);
        let msg = event.data.split("::");
        switch (msg[0]) {
          case "room":
            that.chatRoom.chatRoomId = msg[1];
            that.chatRoom.roomName = msg[2];
            that.showChatRoom = true;
            let message = {
              "option": "history"
              , "chatRoomId": that.chatRoom.chatRoomId
              , "current": 0 + ""
            };
            that.websocket.send(JSON.stringify(message));
            break;
          case "loadMore":
            let list = JSON.parse(msg[1]);
            for (let i = 0; i < list.length; i++) {
              that.historyList.push(list[i]);
            }

            break;
            case "append":
              that.historyList.unshift(JSON.parse(msg[1])[0]);
            break;
          case "init":
            that.historyList = JSON.parse(msg[1]);
            break;
        }
      };
      //连接关闭的回调方法
      this.websocket.onclose = function () {};
      //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function () {
        this.websocket.close();
      };

    },
    closeRoom(){
      this.showChatRoom = false;
    },

    loadMore(){
      let message = {
        "option": "loadMore"
        , "chatRoomId": this.chatRoom.chatRoomId
        , "current": this.historyList.length + ""
      };
      this.websocket.send(JSON.stringify(message));
    },

    sendMessage(){
      let message = {
        "option": "sendMessage"
        , "chatRoomId": this.chatRoom.chatRoomId
        , "memberRole": "employer"
        , "memberId": this.employer.employerId + ""
        , "memberName": this.employer.employerName
        , "data": this.context
      };
      console.log(message);
      this.websocket.send(JSON.stringify(message));
      this.context = "";
    },
  },
  mounted() {
    this.employer = JSON.parse(sessionStorage.getItem("user"));
    this.getMyDemandList();
    dragElement(document.getElementById(("chatRoom")));

    function dragElement(elmnt) {
      var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
      if (document.getElementById(elmnt.id + "header")) {
        /* if present, the header is where you move the DIV from:*/
        document.getElementById(elmnt.id + "header").onmousedown = dragMouseDown;
      } else {
        /* otherwise, move the DIV from anywhere inside the DIV:*/
        elmnt.onmousedown = dragMouseDown;
      }

      function dragMouseDown(e) {
        e = e || window.event;
        // get the mouse cursor position at startup:
        pos3 = e.clientX;
        pos4 = e.clientY;
        document.onmouseup = closeDragElement;
        // call a function whenever the cursor moves:
        document.onmousemove = elementDrag;
      }

      function elementDrag(e) {
        e = e || window.event;
        // calculate the new cursor position:
        pos1 = pos3 - e.clientX;
        pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;
        // set the element's new position:
        elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
        elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
      }

      function closeDragElement() {
        /* stop moving when mouse button is released:*/
        document.onmouseup = null;
        document.onmousemove = null;
      }
    }
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
    #chatRoom {
      position: fixed;
      z-index: 9;
      background-color: #f1f1f1;

      border: 1px solid #d3d3d3;
      width: 300px;
      height: auto;
      right: 10px;
      top: 200px;
  }

  #roomTitle {
      /*padding: 10px;*/
      height: auto;
      cursor: move;
      z-index: 10;
      background-color: #2196F3;
      color: #fff;
    text-align: center;
  }
</style>
