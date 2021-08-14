<template>

  <div style="background-color: #f0f2f5">
    <div id="main">
      <div id="condition">
        <div style="padding-top: 50px;">
          <span style="margin-left: 5%;">作品类型:</span>
          <el-radio-group v-model="radio1" style="margin-left: 20px;" size="mini" @change="type">
            <el-radio-button label="全部"></el-radio-button>
            <el-radio-button label="Web 网站"></el-radio-button>
            <el-radio-button label="App 开发"></el-radio-button>
            <el-radio-button label="微信公众号"></el-radio-button>
            <el-radio-button label="HTML5 应用"></el-radio-button>
            <el-radio-button label="小程序"></el-radio-button>
            <el-radio-button label="其他应用"></el-radio-button>
          </el-radio-group>
        </div>

        <div style="padding-top: 20px;">
          <span style="margin-left: 5%;">作品价格:</span>
          <el-radio-group v-model="radio2" style="margin-left: 20px;" size="mini" @change="priceo">
            <el-radio-button label="全部"></el-radio-button>
            <el-radio-button label="0-5K"></el-radio-button>
            <el-radio-button label="5K-1万"></el-radio-button>
            <el-radio-button label="1万-5万"></el-radio-button>
            <el-radio-button label="5万以上"></el-radio-button>
          </el-radio-group>
        </div>
<!--        <div style="padding-top: 20px;">-->
<!--          <span style="margin-left: 5%;">角色领域:</span>-->
<!--          <el-radio-group v-model="radio3" style="margin-left: 20px;" size="mini">-->
<!--            <el-radio-button label="全部"></el-radio-button>-->
<!--            <el-radio-button label="前端开发"></el-radio-button>-->
<!--            <el-radio-button label="后端开发"></el-radio-button>-->
<!--            <el-radio-button label="微信应用开发"></el-radio-button>-->
<!--            <el-radio-button label="全栈开发"></el-radio-button>-->
<!--          </el-radio-group>-->
<!--        </div>-->
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
          <el-col :span="6" style="padding-right: 20px;">
            <el-input
                placeholder="请输入内容"
                size="mini"
                v-model="searchInfo"
               >
              <el-button slot="append" icon="el-icon-search" @click="find"></el-button>
            </el-input>

          </el-col>
        </el-row>

      </div>
      <div id="list">
        <el-row v-for="item in worksList">
          <div class="goods">
            <el-col :span="6">
              <!--                  v-image-preview-->
              <img :src="'api/images/' + item.annexPath" alt="" style="width: 80%;height: 90%;margin: 5%">
            </el-col>
            <el-col :span="15">
              <p style="padding-top: 20px;">
                <span @click="details(item)">No.{{item.worksId}}&nbsp;</span>
                <span @click="details(item)">&nbsp;{{item.worksTitle}}</span>
              </p>
              <!--                  </a>-->
              <p>
                <el-tag :type="miStatusColor(item.demandTypeName)">{{item.demandTypeName}}</el-tag>
              </p>
              <p>发布时间：{{item.releaseTime}}</p>
            </el-col>
            <el-col :span="3">
              <p style="padding-top: 20px;">￥{{item.worksPrice}}</p>
              <span>热度：{{item.trafficNumber}}</span>
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
  name: "WorkList",
  data() {
    return {
      count: 0,
      searchInfo: "",
      worksList: {},
      radio1: '全部',
      radio2: '全部',
      radio3: '全部',
      total: 1,
      price: 0,
      time: 0,
      sort:"综合排序",
      miStatusColor: function (val) {
        if (val == 'Web 网站') {
          return 'lightpink'
        } else if (val == 'App 开发') {
          return 'warning'
        } else if (val == '微信公众号') {
          return 'success'
        } else if (val == 'HTML5 应用') {
          return 'danger'
        } else if (val == '小程序') {
          return 'success'
        } else {
          return 'info'
        }
      }
    }
  },
  methods: {
    load() {
      let params = {
        count: 0,
        demandTypeName:this.radio1,
        price:this.radio2,
        searchInfo:this.searchInfo,
        sort:this.sort
      };
      console.log(params)
      this.$axios.get('qworks/worksList', {params: params}).then(res => {
        this.worksList = res.data;
        this.count = res.data.length;
      })
    },
    addMore() {
      let params = {
        count: this.count,
        demandTypeName:this.radio1,
        price:this.radio2,
        searchInfo:this.searchInfo,
        sort:this.sort
      };
      this.$axios.get('qworks/worksList', {params: params}).then(res => {
        if (res.data.length == 0) {
          this.$message({
            message: "暂无更多",
            type: "warning"
          });
        } else {
          this.count += res.data.length;
          for (let i = 0; i < res.data.length; i++) {
            this.worksList.push(res.data[i]);
          }
        }
      })
    },
    details(item) {
      sessionStorage.setItem("work", JSON.stringify(item))
      let routeData = this.$router.resolve({
        name: 'worksdetails'
      });
      window.open(routeData.href, '_blank');
    },
    priceo(){
      this.load();
    },
    type(){
      this.load();
    },
    find(){
      this.load();
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
  },
  mounted() {
    this.load();
  }
}
</script>

<style scoped>
#main {
  width: 70%;
  height: auto;
  margin: auto;
  padding-top: 20px
}

#condition {
  height: 200px;
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

a {
  text-decoration: none;
  color: black;
}

a:hover {
  text-decoration: none;
  cursor: pointer
}

/*a标签鼠标经过mouseover时的样式*/
a:link {
  text-decoration: none;
  cursor: pointer;
}

/*a标签未访问时的样式*/
a:visited {
  text-decoration: overline;
  cursor: pointer
}
.sort{
  margin-left: 20px;
}

/*a标签访问过之后样式*/

</style>
