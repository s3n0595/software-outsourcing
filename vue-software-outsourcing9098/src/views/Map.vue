<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span style="font-size: 20px;">联系我们</span>
      </div>
      <div class="text item">
        <p style="font-size: 20px;margin-bottom: 10px;">如需签订合同、商务合作请与我们联系：</p>
        <p style="font-size: 15px;margin-bottom: 50px;">邮箱：support@codemart.com</p>
        <p style="font-size: 20px;margin-bottom: 10px;">公司地址：</p>
        <div class="contact">
          <baidu-map class="map" :center="markerPoint" :zoom="16">
            <bm-marker :position="markerPoint" :dragging="true" @click="infoWindowOpen">
              <bm-info-window :show="show" @close="infoWindowClose" @open="infoWindowOpen" style="font-size:13px">
                传一之光 <br><br>
                地址：福建省厦门市湖里区软件园二期望海路25号楼之二5楼
              </bm-info-window>
            </bm-marker>
          </baidu-map>
        </div>
      </div>
    </el-card>

  </div>
</template>

<script>
export default {
  data() {
    return {
      markerPoint:{lng: 118.18481, lat: 24.4986},
      show:true
    };
  },
  methods: {
    infoWindowClose () {
      this.show = false
    },
    infoWindowOpen () {
      this.show = true
    }
  }
};
</script>

<style>
.map {
  width: 800px;
  height: 398px;
}
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
  width: auto;
  background-color: rgb(248,249,251);
}
</style>
