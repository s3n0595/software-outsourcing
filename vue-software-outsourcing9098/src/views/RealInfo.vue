<template>
  <div style="background-color: #f0f2f5">
    <div id="main">
      <div id="condition">
        <el-carousel height="200px">
          <el-carousel-item v-for="item in dataList" :key="item.id">
            <img :src="item.src" style="height: 200px;width: 100%">
          </el-carousel-item>
        </el-carousel>
      </div>
      <div id="list">
      <div class="fl">
        <ul class="bl">
          <li v-for="(item, index) in infoList" >
            <a class="img">
              <img alt="iOS 15 降临！苹果 WWDC21 全球开发者大会直播（回放）" :src="item.img">
            </a>
            <div class="c" data-ot="2021/6/6 17:10:48">
              <h2 class="t"  v-if="index===0">
                <a title="iOS 15 降临！苹果 WWDC21 全球开发者大会直播（回放）" target="_blank" href="https://live.ithome.com/item/555782.htm" class="title">{{item.title}}</a>
              </h2>
              <h2 v-else>
                <a title="iOS 15 降临！苹果 WWDC21 全球开发者大会直播（回放）" target="_blank" href="https://live.ithome.com/item/555782.htm" class="title">{{item.title}}</a>
              </h2>
              <div class="m">{{item.content}}</div>
              <div class="o">
                <div class="tags">
                  <strong>Tags：</strong><a href="http://www.ithome.com/tag/pingguo/" target="_blank">{{item.tags1}}</a>，
                  <a href="http://www.ithome.com/tag/wwdc21/" target="_blank">{{item.tags2}}</a>
                  <a href="http://www.ithome.com/tag/wwdc21/" target="_blank">{{item.tags3}}</a>
                </div>
                <div class="d"><span class="state other">{{item.time}}</span></div>
              </div>
            </div>
          </li>
        </ul>
      </div>
      </div>
    </div>
    <div style="position:fixed;margin-left: 75%;">
      <img src="../assets/img/i5.jpg" style="width: 70%;height: 50%;padding-bottom: 15%;">
      <img src="../assets/img/i6.jpg" style="width: 70%;height: 50%;padding-bottom: 7%;">
    </div>
  </div>


</template>


<script>
export default {
  name: "RealInfo",
  data() {
    return {
      dataList:[
        {id: '1',src:require('../assets/img/i4.png')},
        {id: '2',src:require('../assets/img/i2.jpg')},
        {id: '3',src:require('../assets/img/i3.jpg')},
        {id: '4',src:require('../assets/img/i1.png')}
      ],
      infoList:[],
    }
  },
  methods: {
      getInfo(){
        this.$axios.post('empcenter/getrealinfo').then(res=>{
            this.infoList=res.data;
        })
      }
  },
  mounted() {
    this.getInfo();
  }
}
</script>

<style scoped>
.fl {
  margin-top: -40px;
  width: 820px;
  float: left;
}
ul.bl {
  padding: 0;
  min-height: 1000px;
  margin: 0;
  list-style: none;
}
ul.bl li {
  margin-top: 40px;
  overflow: hidden;
}
li {
  display: list-item;
  text-align: -webkit-match-parent;
}
ul.bl li .img {
  display: block;
  float: left;
  width: 180px;
  height: 135px;
}
a {
  color: #222;
  text-decoration: none;
}
ul.bl li .img img {
  width: 180px;
  height: 135px;
}
img {
  border: none;
}
ul.bl li .c {
  float: right;
  width: 620px;
}
ul.bl li .c h2.t {
  background: url(https://img.ithome.com/images/v2.1/top_icon.svg) no-repeat;
  background-position: left center;
  background-size: 22px 22px;
  padding-left: 28px;
}
ul.bl li .c h2 {
  margin: 0 0 20px 0;
  font-size: 20px;
  height: 26px;
  font-weight: 600;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 620px;
}
h2 {
  display: block;
  font-size: 1.5em;
  margin-block-start: 0.83em;
  margin-block-end: 0.83em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
}
ul.bl li .c .m {
  font-size: 14px;
  color: #888;
  height: 60px;
  line-height: 20px;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
}
ul.bl li .c .o {
  height: 29px;
  line-height: 40px;
  font-size: 14px;
}
ul.bl li .c .o .tags {
  display: block;
  float: left;
  width: 500px;
  height: 40px;
  overflow: hidden;
}
ul.bl li .c .o .d {
  float: right;
  text-align: right;
}

#main {
  width: 70%;
  height: auto;
  margin: auto;
}

#condition {
  height: 200px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff
}

#list {
  margin-top: 30px;
  height: auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  background-color: #ffffff
}

#list div {

}
.goods:hover {
  background: #eff2f7;
}


/*a标签访问过之后样式*/

</style>
