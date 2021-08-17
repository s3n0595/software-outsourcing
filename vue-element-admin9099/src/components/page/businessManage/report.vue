<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card shadow="hover">
          <div style="height: 300px;">
            <div slot="header" class="clearfix">
              <span>作品总类</span>
            </div>
            Web 网站<el-progress :percentage=webPer color="#42b983"></el-progress>
            App 开发<el-progress :percentage=appPer color="#f1e05a"></el-progress>
            微信公众号<el-progress :percentage=weChatPer></el-progress>
            HTML5 应用<el-progress :percentage=html5Per color="#f56c6c"></el-progress>
            小程序<el-progress :percentage=appletPer color="#f56c6c"></el-progress>
            其他应用<el-progress :percentage=otherPer ></el-progress>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <schart
              ref="bar"
              class="schart"
              canvasId="bar"
              :data="data"
              type="bar"
              :options="options"
          ></schart>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card shadow="hover">
          <div id='pieChart' style="width:100%;height:300px;"></div>
        </el-card>
      </el-col>
<!--      <el-col :span="12">-->
<!--        <el-card shadow="hover">-->
<!--          <schart-->
<!--              ref="line"-->
<!--              class="schart"-->
<!--              canvasId="line"-->
<!--              :data="data"-->
<!--              type="line"-->
<!--              :options="options2"-->
<!--          ></schart>-->
<!--        </el-card>-->
<!--      </el-col>-->
    </el-row>
  </div>
</template>

<script>
import Schart from "vue-schart";
import echarts from 'echarts';
export default {
  name: "dashboard",
  data() {
    return {
      Echarts:null,
      data: [
        {
          name: "2018/09/04",
          value: ''
        },
        {
          name: "2018/09/05",
          value: ''
        },
        {
          name: "2018/09/06",
          value: ''
        },
        {
          name: "2018/09/07",
          value: ''
        },
        {
          name: "2018/09/08",
          value: ''
        },
        {
          name: "2018/09/09",
          value: ''
        },
        {
          name: "2018/09/10",
          value: ''
        }
      ],
      options: {
        title: "最近七天交易数量",
        showValue: false,
        fillColor: "rgb(45, 140, 240)",
        bottomPadding: 30,
        topPadding: 30
      },
      // options2: {
      //   title: "最近七天用户访问趋势",
      //   fillColor: "#FC6FA1",
      //   axisColor: "#008ACD",
      //   contentColor: "#EEEEEE",
      //   bgColor: "#F5F8FD",
      //   bottomPadding: 30,
      //   topPadding: 30
      // },
      option3: {
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: "vertical",
          x: "left",
          data: [
            "直达",
            "营销广告",
            "搜索引擎",
            "邮件营销",
            "联盟广告",
            "视频广告",
            "百度",
            "谷歌",
            "必应",
            "其他"
          ]
        },
        series: [
          {
            name: "访问来源",
            type: "pie",
            selectedMode: "single",
            radius: [0, "30%"],

            label: {
              normal: {
                position: "inner"
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: [
              { value: 335, name: "直达", selected: true },
              { value: 679, name: "营销广告" },
              { value: 1548, name: "搜索引擎" }
            ]
          },
          {
            name: "访问来源",
            type: "pie",
            radius: ["40%", "55%"],
            label: {
              normal: {
                show:false,
                formatter: "{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ",
                backgroundColor: "#eee",
                borderColor: "#aaa",
                borderWidth: 1,
                borderRadius: 4,
                // shadowBlur:3,
                // shadowOffsetX: 2,
                // shadowOffsetY: 2,
                // shadowColor: '#999',
                // padding: [0, 7],
                rich: {
                  a: {
                    color: "#999",
                    lineHeight: 22,
                    align: "center"
                  },
                  // abg: {
                  //     backgroundColor: '#333',
                  //     width: '100%',
                  //     align: 'right',
                  //     height: 22,
                  //     borderRadius: [4, 4, 0, 0]
                  // },
                  hr: {
                    borderColor: "#aaa",
                    width: "100%",
                    borderWidth: 0.5,
                    height: 0
                  },
                  b: {
                    fontSize: 16,
                    lineHeight: 33
                  },
                  per: {
                    color: "#eee",
                    backgroundColor: "#334455",
                    padding: [2, 4],
                    borderRadius: 2
                  }
                }
              }
            },
            data: [
              { value: 335, name: "直达" },
              { value: 310, name: "邮件营销" },
              { value: 234, name: "联盟广告" },
              { value: 135, name: "视频广告" },
              { value: 1048, name: "百度" },
              { value: 251, name: "谷歌" },
              { value: 147, name: "必应" },
              { value: 102, name: "其他" }
            ]
          }
        ]
      },
      webPer:0,
      appPer:0,
      weChatPer:0,
      html5Per:0,
      appletPer:0,
      otherPer:0,
      total:0,
      weeks:'',
      tradeD0:'',
      tradeD1:'',
      tradeD2:'',
      tradeD3:'',
      tradeD4:'',
      tradeD5:'',
      tradeD6:'',
    };
  },
  components: {
    Schart
  },
  created() {
    this.handleListener();
    this.changeDate();
  },
  activated() {
    this.handleListener();
  },
  deactivated() {
    window.removeEventListener("resize", this.renderChart);
    // bus.$off("collapse", this.handleBus);
  },
  methods: {
    showEcharts(){
      this.Echarts=echarts.init(document.getElementById("pieChart"));
      this.Echarts.setOption(this.option3)
    },
    changeDate() {
      const now = new Date().getTime();
      this.data.forEach((item, index) => {
        const date = new Date(now - (6 - index) * 86400000);
        item.name = `${date.getFullYear()}/${date.getMonth() +
        1}/${date.getDate()}`;
      });
    },
    handleListener() {
      // bus.$on("collapse", this.handleBus);
      // 调用renderChart方法对图表进行重新渲染
      window.addEventListener("resize", this.renderChart);
    },
    renderChart() {
      this.$refs.bar.renderChart();
      this.$refs.line.renderChart();
    },
    getWeb() {
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/web`,{params:{}}).then(getWeb=>{
        console.log(getWeb)
        const code = getWeb.data
        this.webPer = code.data
      }).catch(err=>{
        console.log(err)
      })
    },
    getApp() {
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/app`,{params:{}}).then(getApp=>{
        console.log(getApp)
        const code = getApp.data
        this.appPer = code.data
      }).catch(err=>{
        console.log(err)
      })
    },
    getWechat() {
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/weChat`,{params:{}}).then(getWechat=>{
        console.log(getWechat)
        const code = getWechat.data
        this.weChatPer = code.data
      }).catch(err=>{
        console.log(err)
      })
    },
    getHtml() {
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/html`,{params:{}}).then(getHtml=>{
        console.log(getHtml)
        const code = getHtml.data
        this.html5Per = code.data
      }).catch(err=>{
        console.log(err)
      })
    },
    getApplet() {
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/applet`,{params:{}}).then(getApplet=>{
        console.log(getApplet)
        const code = getApplet.data
        this.appletPer = code.data
      }).catch(err=>{
        console.log(err)
      })
    },
    getOther() {
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/other`,{params:{}}).then(getOther=>{
        console.log(getOther)
        const code = getOther.data
        this.otherPer = code.data
      }).catch(err=>{
        console.log(err)
      })
    },
    getTotal() {
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/total`,{params:{}}).then(res=>{
        console.log(res)
        const code = res.data
        this.total = code.data
      }).catch(err=>{
        console.log(err)
      })
    },
    getWeekData() {
      let baseUrl = 'baseUrl';
      this.$axios.get(`${baseUrl}/portal/week`,{params:{}}).then(res=>{
        console.log(res)
        const code = res.data
        const weeksList = code.data
        // this.weeks = code.data
        for (var i = 0; i < weeksList.length; i++) {
          // this.tradeD0 = this.weeks.count;
          this.tradeD0 = weeksList[6]
          this.tradeD1 = weeksList[5]
          this.tradeD2 = weeksList[4]
          this.tradeD3 = weeksList[3]
          this.tradeD4 = weeksList[2]
          this.tradeD5 = weeksList[1]
          this.tradeD6 = weeksList[0]
          // eval('tradeD' + i + '=' + JSON.stringify(weeksList[i]));
          // this.tradeD0 = eval('tradeD'+ i)
        }
        this.$set(this.data, 0, {name:'',value:this.tradeD0});
        this.$set(this.data, 1, {name:'',value:this.tradeD1});
        this.$set(this.data, 2, {name:'',value:this.tradeD2});
        this.$set(this.data, 3, {name:'',value:this.tradeD3});
        this.$set(this.data, 4, {name:'',value:this.tradeD4});
        this.$set(this.data, 5, {name:'',value:this.tradeD5});
        this.$set(this.data, 6, {name:'',value:this.tradeD6});
        this.changeDate()
      }).catch(err=>{
        console.log(err)
      })
    },
    // getSuccess() {
    //   let baseUrl = 'baseUrl';
    //   this.$axios.get(`${baseUrl}/portal/successs`,{params:{}}).then(res=>{
    //     console.log(res)
    //     const code = res.data
    //     this.total = code.data
    //   }).catch(err=>{
    //     console.log(err)
    //   })
    // },


  },
  mounted(){
    this.showEcharts();
    this.getWeb();
    this.getApp();
    this.getWechat();
    this.getApplet();
    this.getHtml();
    this.getOther();
    this.getTotal();
    this.getWeekData();
    // this.getSuccess();

  },
};
</script>


<style scoped>
.el-row {
  margin-bottom: 20px;
}

.grid-content {
  display: flex;
  align-items: center;
  height: 100px;
}

.grid-cont-right {
  flex: 1;
  text-align: center;
  font-size: 14px;
  color: #999;
}

.grid-num {
  font-size: 30px;
  font-weight: bold;
}

.grid-con-icon {
  font-size: 50px;
  width: 100px;
  height: 100px;
  text-align: center;
  line-height: 100px;
  color: #fff;
}

.grid-con-1 .grid-con-icon {
  background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
  background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
  color: rgb(45, 140, 240);
}

.grid-con-3 .grid-con-icon {
  background: rgb(242, 94, 67);
}

.grid-con-3 .grid-num {
  color: rgb(242, 94, 67);
}

.user-info {
  display: flex;
  align-items: center;
  padding-bottom: 20px;
  border-bottom: 2px solid #ccc;
  margin-bottom: 20px;
}

.user-avator {
  width: 120px;
  height: 120px;
  border-radius: 50%;
}

.user-info-cont {
  padding-left: 50px;
  flex: 1;
  font-size: 14px;
  color: #999;
}

.user-info-cont div:first-child {
  font-size: 30px;
  color: #222;
}

.user-info-list {
  font-size: 14px;
  color: #999;
  line-height: 25px;
}

.user-info-list span {
  margin-left: 70px;
}

.mgb20 {
  margin-bottom: 20px;
}

.todo-item {
  font-size: 14px;
}

.todo-item-del {
  text-decoration: line-through;
  color: #999;
}

.schart {
  width: 100%;
  height: 300px;
}
</style>
