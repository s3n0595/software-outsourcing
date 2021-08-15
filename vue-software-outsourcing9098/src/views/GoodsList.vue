<template>

  <div style="background-color: #f0f2f5">
    <div id="main">
      <el-row>
        <el-col :span="5"> <div style="border: 1px solid transparent;"></div></el-col>
        <el-col :span="12">
          <div>
            <div id="condition">
              <div style="padding-top: 35px;">
                <span style="margin-left: 5%;">作品类型:</span>


                <el-radio-group v-model="radio1" style="margin-left: 20px;" size="mini" @change="conditionChange">
                  <el-radio-button label="全部"></el-radio-button>
                  <el-radio-button v-model="item.demandTypeId" v-for="item in checklist" :key="item.demandTypeId"
                                   :label="item.demandTypeName"></el-radio-button>

                </el-radio-group>
              </div>

              <div style="padding-top: 20px;">
                <span style="margin-left: 5%;">项目预算:</span>
                <el-radio-group v-model="radio2" style="margin-left: 20px;" size="mini" @change="conditionChange">
                  <el-radio-button label="全部"></el-radio-button>
                  <el-radio-button label="0-5K"></el-radio-button>
                  <el-radio-button label="5K-10K"></el-radio-button>
                  <el-radio-button label="10K-50K"></el-radio-button>
                  <el-radio-button label="50K以上"></el-radio-button>
                </el-radio-group>
              </div>
              <div style="padding-top: 20px;">
                <span style="margin-left: 5%;">项目工期:</span>
                <el-radio-group v-model="radio3" style="margin-left: 20px;" size="mini" @change="conditionChange">
                  <el-radio-button label="全部"></el-radio-button>
                  <el-radio-button label="小于10周"></el-radio-button>
                  <el-radio-button label="10周到30周"></el-radio-button>
                  <el-radio-button label="大于30周"></el-radio-button>
                </el-radio-group>
              </div>
            </div>

            <div id="sort" style="padding: 10px;">
              <el-row style="padding: 10px;">
                <el-col :span="18">
                  <span style="margin-left: 20px;">排序：</span>
                  <span class="sort" @click="sortTotal()" ref="total" style="color:#4289dc;font-weight:bold;">综合</span>
                  <span class="sort" @click="sortTime()" ref="time">
                  发布时间
                  <i class="el-icon-d-caret" v-show="this.time == 0"></i>
                  <i class="el-icon-caret-bottom" v-show="this.time == 1"></i>
                  <i class="el-icon-caret-top" v-show="this.time == 2"></i>
                  </span>
                  <span class="sort" @click="sortPrice()" ref="price">
                    价格排序
                  <i class="el-icon-d-caret" v-show="this.price == 0"></i>
                  <i class="el-icon-caret-bottom" v-show="this.price == 1"></i>
                  <i class="el-icon-caret-top" v-show="this.price == 2"></i>
                 </span>
                </el-col>
                <el-col :span="6">
                  <el-input
                          placeholder="请输入内容"
                          size="mini"
                          v-model="searchInfo"
                  >
                    <el-button slot="append" icon="el-icon-search" @click="load"></el-button>
                  </el-input>
                </el-col>
              </el-row>


            </div>
            <div id="list">
              <el-row v-for="(item,index) in demandList" :key="index">
                <div class="goods" @click="viewDetails(item)">
                  <el-col :span="6">
                    <img :src="'api/images/bg_00' + (item.demandId%5) +'.png'" alt=""
                         style="width: 80%;height: 80%;margin: 5%">
                  </el-col>
                  <el-col :span="12">
                    <p style="padding: 10px;">
                      <span>No.{{ item.demandId }}&nbsp;</span>
                      <span style="margin-left: 5px;">&nbsp;{{ item.demandTitle }}</span>
                      <el-tag style="margin-left: 10px;">{{ item.demandTypeName }}</el-tag>
                    </p>
                    <p style="padding: 10px;">
                      <span>工期：{{ item.predictTime }}周  </span>
                      <span style="margin-left: 5px">|</span>
                      <span style="margin-left: 5px;">￥{{ item.predictPrice }}</span>
                    </p>
                    <p style="padding: 10px;"><span>发布时间：{{ item.releaseTime }}</span></p>
                  </el-col>
                  <el-col :span="6">
                    <p style="padding: 30px;">
                      <span><i class="el-icon-view"></i><span style="margin-left: 10px">{{item.trafficNumber}}</span></span>
                    </p>
                  </el-col>
                </div>
              </el-row>
            </div>
            <div style="text-align: center;padding: 30px">
              <el-button type="primary" plain @click="addMore()">加载更多</el-button>
            </div>
          </div>
        </el-col>
        <el-col :span="2">
          <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>开发者工作指南</span>
          </div>
            <div style="height: 300px;">
              <el-steps direction="vertical">
                <el-step title="注册并认证身份"></el-step>
                <el-step title="报名参与项目"></el-step>
                <el-step title="确认合作需求方"></el-step>
                <el-step title="项目阶段划分并启动"></el-step>
                <el-step title="阶段交付收款"></el-step>
              </el-steps>
            </div>

        </el-card>

        </el-col>
        <el-col :span="5"><div style="border: 1px solid transparent;"></div></el-col>
      </el-row>

    </div>
  </div>


</template>


<script>
export default {
  name: "GoodsList",
  data() {
    return {
      count: 0,
      searchInfo: "",
      demandList: {},
      checklist:[],
      radio1: '全部',
      radio2: '全部',
      radio3: '全部',
      total: 1,
      price: 0,
      time: 0,
      sort:"综合排序"
    }
  },
  methods: {
    load() {
      console.log(this.count)
      let params = {
        count: 0,
        type: this.radio1,
        price: this.radio2,
        time: this.radio3,
        searchInfo:this.searchInfo,
        sort: this.sort
      };
      console.log(params)
      this.$axios.get('demand/list', {params: params}).then(res => {
        this.demandList = res.data;
        this.count = res.data.length;
      })
    },
    conditionChange(){
      this.load();
    },
    addMore() {
      let params = {
        count: this.count,
         type: this.radio1,
        price: this.radio2,
        time: this.radio3,
        searchInfo:this.searchInfo,
         sort: this.sort
      };
      this.$axios.get('demand/list', {params: params}).then(res => {
        if (res.data.length == 0) {
          this.$message({
            message: "暂无更多",
            type: "warning"
          });
        } else {
          this.count += res.data.length;
          for (let i = 0; i < res.data.length; i++) {
            this.demandList.push(res.data[i]);
          }
        }
      })
    },
    sortTotal(){
      if(this.total == 0) {
        this.total = 1;
        this.time = 0;
        this.price = 0;
        this.sort = "综合排序";
        this.load();
      }
      this.$refs.total.style = "color:#4289dc;font-weight:bold;";
      this.$refs.time.style = "";
      this.$refs.price.style = "";
    },
    sortTime(){
      if(this.time == 0) {
        this.time = 1;
        this.price = 0;
        this.total = 0;
        this.sort = "时间降序";
      } else if (this.time == 1) {
        this.time = 2;
        this.sort = "时间升序";
      } else if (this.time == 2) {
        this.time = 1;
        this.sort = "时间降序";
      }
      this.$refs.time.style = "color:#4289dc;font-weight:bold;";
      this.$refs.total.style = "";
      this.$refs.price.style = "";
      this.load();
    },
    sortPrice(){
      if (this.price == 0) {
        this.price = 1;
        this.time = 0;
        this.total = 0;
        this.sort = "价格降序";
      } else if (this.price == 1) {
        this.price = 2;
        this.sort = "价格升序";
      } else if (this.price == 2) {
        this.price = 1;
        this.sort = "价格降序";
      }
      this.$refs.time.style = "";
      this.$refs.total.style = "";
      this.$refs.price.style = "color:#4289dc;font-weight:bold;";
      this.load();
    },
    selcheckList(){
      this.$axios.post("/empcenter/ckList").then(response =>{
        this.checklist=response.data;
      }).catch(e => {
        this.$message({
          message: "网络或程序异常！" + e,
          type: "error"
        });
      });
    },
    viewDetails(item) {
      sessionStorage.setItem("demand", JSON.stringify(item));
      let routeData = this.$router.resolve({
        name: 'demandDetails'
      });
      window.open(routeData.href, '_blank');
    }
  },
  mounted() {
    this.load();
    this.selcheckList();
  }
}
</script>

<style scoped>
#main {
  /*width: 100%;*/
  /*height: auto;*/
  /*margin: auto*/
}

#condition {
  height: 200px;
  margin-top: 30px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff
}

#sort {
  height: 50px;
  margin-top: 30px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff;

}

#list {
  margin-top: 30px;
  height: auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff
}

#list div {
  height: 150px;
  border-bottom: 1px solid #eff2f7;
}

.el-col {
  border-radius: 4px;
}
.box-card {
    width: 250px;
    margin-top: 30px;
    margin-left: 10px;
    position: fixed;
}
.bg-white {
  background: #ffffff;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.goods:hover {
  background: #eff2f7;

}

.sort{
  margin-left: 20px;
}

</style>
