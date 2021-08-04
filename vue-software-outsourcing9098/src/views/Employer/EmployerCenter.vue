<template>
  <div class="admin">

    <el-container>
      <el-header>
        <PersonCenterNav></PersonCenterNav>
      </el-header>
      <el-container>
        <el-aside width="240px">
          <el-menu default-active="2" class="el-menu-vertical-demo" router>
            <el-card class="box-card" >
              <div class="clearfix" style="font-size: 15px">
                <span>基本信息</span>
              </div>
              <el-menu-item index="/admin/user" style="height: 50px;">
                <i class="el-icon-star-off"></i>
                <span slot="title" >账户信息</span>
              </el-menu-item>
              <el-menu-item index="/admin/user" style="height: 50px;">
                <i class="el-icon-star-off"></i>
                <span slot="title" >码市认证</span>
              </el-menu-item>
            </el-card>

            <el-card class="box-card" >
              <div class="clearfix" style="font-size: 15px">
                <span>开发者信息</span>
              </div>
              <el-menu-item index="/admin/user" style="height: 50px;">
                <i class="el-icon-star-off"></i>
                <span slot="title" >技能展示</span>
              </el-menu-item>
              <el-menu-item index="/employerCenter/adddemand" style="height: 50px;">
                <i class="el-icon-star-off"></i>
                <span slot="title" >案例作品</span>
              </el-menu-item>
              <el-menu-item index="/admin/user" style="height: 50px;">
                <i class="el-icon-star-off"></i>
                <span slot="title" >码市测试</span>
              </el-menu-item>
            </el-card>

            <el-card class="box-card" >
              <div class="clearfix" style="font-size: 15px">
                <span>增值信息</span>
              </div>
              <el-menu-item index="/admin/user" style="height: 50px;">
                <i class="el-icon-star-off"></i>
                <span slot="title" >保证金计划</span>
              </el-menu-item>
            </el-card>

            <el-card class="box-card" >
              <div class="clearfix" style="font-size: 15px">
                <span>我的开发宝</span>
              </div>
              <el-menu-item index="/employerCenter/accountview" style="height: 50px;">
                <i class="el-icon-star-off"></i>
                <span slot="title" >账户概览</span>
              </el-menu-item>
              <el-menu-item index="/admin/user" style="height: 50px;">
                <i class="el-icon-star-off"></i>
                <span slot="title" >交易记录</span>
              </el-menu-item>
              <el-menu-item index="/admin/user" style="height: 50px;">
                <i class="el-icon-star-off"></i>
                <span slot="title" >交易密码</span>
              </el-menu-item>
            </el-card>
          </el-menu>
        </el-aside>
        <el-container>
          <el-main>
            <div class="base-padding">
              <router-view />
            </div>
          </el-main>
          <el-footer>2019@科建版权所有</el-footer>
        </el-container>
      </el-container>
    </el-container>


  </div>
</template>

<script>
import PersonCenterNav from "@/views/PersonCenterNav";
export default {
  name: "EmployerCenter",
  components: {
    PersonCenterNav
  }
};
</script>

<style lang="scss" soped>
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 230px;
}


.admin {
  border-top: 1px solid rgb(238, 238, 238);
  margin-left: 10%;

}

.base-padding {
  padding: 20px;
}

.el-footer {
  line-height: 60px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
