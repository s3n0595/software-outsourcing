<template>
    <el-main>
      <div>投标列表</div>
      <div id="list">
        <el-collapse accordion @change="showChatRoom = false">
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
              <el-button @click="openChatRoom(item.demandId)">联系雇主</el-button>
            </div>

            <div style="margin-top: 30px" v-show="item.tradeStatus == 1">
                <el-button @click="deliveryProject(item.tradeRecordId, item.demandId)">交付项目</el-button>
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
      websocket:{},
      historyList:{},
      dialogFormVisible: false,
      formLabelWidth: "120px",
      radio: "",
      form: {
        demandId:"",
        price: "",
        projectTime: "",
        tenderId: "",
      },
      chatRoom:{
        chatRoomId:"",
        roomName:"",
      },
      context:"",
      showChatRoom:false
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
      };
       this.$axios.get("/demand/deliveryProject", {params: param}).then(response => {
         this.getMyTender();
      });

    },
    getMyTender(){
      this.$axios.get("/demand/myTender", {params: {providerId: this.provider.providerId}}).then(response => {
        this.myTender = response.data;
      });
    },
    openChatRoom(demandId){
      console.log("providerId : " + this.provider.providerId);
      let that = this;
      //判断当前浏览器是否支持WebSocket
      if ("WebSocket" in window) {
        this.websocket = new WebSocket("ws://localhost:9093/webSocket/provider/" + this.provider.providerId + "/" + demandId);
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
        , "memberRole": "provider"
        , "memberId": this.provider.providerId + ""
        , "memberName": this.provider.providerName
        , "data": this.context
      };
      console.log(message);
      this.websocket.send(JSON.stringify(message));
      this.context = "";
    },


  },
  mounted() {
    this.provider = JSON.parse(sessionStorage.getItem("user"));
    this.getMyTender();
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
