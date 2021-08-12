<template>

  <div style="background-color: #f0f2f5">
    <div id="main">
      <div id="condition">
        <div style="padding-top: 35px;">
          <span style="margin-left: 5%;">作品类型:</span>


          <el-radio-group v-model="radio1" style="margin-left: 20px;" size="mini" @change="conditionChange">
            <el-radio-button label="全部"></el-radio-button>
            <el-radio-button v-model="item.demandTypeId" v-for="item in checklist" :key="item.demandTypeId" :label="item.demandTypeName" ></el-radio-button>

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

      <div id="sort" style="padding-top: 20px;">
        <span style="margin-left: 20px;">排序：</span>
        <span class="sort" @click="sortTotal()">综合</span>
        <span class="sort">发布时间<i class="el-icon-d-caret"></i></span>
        <span class="sort">价格排序<i class="el-icon-d-caret"></i></span>
      </div>
      <div id="list">
        <el-row v-for="(item,index) in demandList" :key="index">
          <div class="goods" @click="viewDetails(item)">
            <el-col :span="6">
              <img :src="'api/images/bg_00' + (item.demandId%5) +'.png'" alt=""
                   style="width: 80%;height: 80%;margin: 5%">
            </el-col>
            <el-col :span="15">
              <p>
                <span>No.{{ item.demandId }}&nbsp;</span>
                <span>&nbsp;{{ item.demandTitle }}</span>
                <el-tag>{{ item.demandTypeName }}</el-tag>
              </p>

              <p>工期：{{ item.predictTime }}</p>
              <p>发布时间：{{ item.releaseTime }}</p>
            </el-col>
            <el-col :span="3">
              <p>￥{{ item.predictPrice }}</p>
            </el-col>
          </div>
        </el-row>
      </div>
      <div style="text-align: center;padding: 30px">
        <el-button type="primary" plain @click="addMore()">加载更多</el-button>
      </div>
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
    }
  },
  methods: {
    load() {
      console.log(this.count)
      let params = {
        count: 0,
        type: this.radio1,
        price: this.radio2,
        time: this.radio3
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
        time: this.radio3
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
      this.$attrs("color", "blue");
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
  width: 50%;
  height: auto;
  margin: auto
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
  height: 100px;
  border-bottom: 1px solid #eff2f7;
}

.el-col {
  border-radius: 4px;
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
