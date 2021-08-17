<template>
  <div id="app">
    <el-container>
      <el-header>
        <div class="logo">
          <img src="./assets/img/java.jpeg" alt/>
        </div>
        <el-menu
            :default-active="defaultActive"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
        >
          <el-menu-item @click="home">首页</el-menu-item>
          <el-menu-item @click="goodsList">找需求</el-menu-item>
          <el-menu-item @click="worksList">作品广场</el-menu-item>
          <el-menu-item @click="realList">行业资讯</el-menu-item>
          <el-menu-item @click="caseList">成功案例</el-menu-item>
        </el-menu>


        <div class="logReg" v-if="log">
          <div class="header-right">
            <div class="login">
              <el-dropdown>
              <span class="el-dropdown-link">
                登陆<i class="el-icon-arrow-down el-icon--right"></i>
                <!--            <i class="el-icon-caret-bottom"></i>-->
              </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="employerLogin">雇主登陆</el-dropdown-item>
                  <el-dropdown-item @click.native="providerLogin">服务商登陆</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>
            <div class="register">
              <el-dropdown>
                <span class="el-dropdown-link">
                  注册<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="employerRegister">注册成为雇主</el-dropdown-item>
                  <el-dropdown-item @click.native="providerRegister">注册成为服务商</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </div>



          </div>
        </div>

        <div class="header-right" v-if="info">
          <div class="header-user-con">
            <!-- 用户头像 -->
            <div class="user-avator">
<!--              <img :src="require(headImg)">-->
              <img src="./assets/img/img.jpg">
            </div>
            <!-- 用户名下拉菜单 -->
            <el-dropdown class="user-name" trigger="click">
              <span class="el-dropdown-link">
<!--                {{username}}-->
                <i class="el-icon-caret-bottom"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="personCenter">个人中心</el-dropdown-item>
                <el-dropdown-item divided @click.native="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>

          </div>
        </div>


      </el-header>
      <el-main>
        <router-view/>
      </el-main>
<!--      <div class="footer" v-show="isShow">-->
<!--        <div class="footer-content">-->
<!--          <ul class="content-nav">-->
<!--            <li>-->
<!--              <p>走进科建</p>-->
<!--              <span>发展历程</span>-->
<!--              <span>企业文化</span>-->
<!--              <span>资质荣誉</span>-->
<!--              <span>合作伙伴</span>-->
<!--            </li>-->
<!--            <li>-->
<!--              <p>新闻资讯</p>-->
<!--              <span>公司新闻</span>-->
<!--              <span>行业动态</span>-->
<!--            </li>-->
<!--            <li>-->
<!--              <p>产品中心</p>-->
<!--              <span>介绍视频</span>-->
<!--              <span>管理模式</span>-->
<!--              <span>平台目标</span>-->
<!--              <span>功能模块</span>-->
<!--            </li>-->
<!--            <li>-->
<!--              <p>联系我们</p>-->
<!--              <span>邮箱：kejianlml@163.com</span>-->
<!--              <span>电话：021-55802368</span>-->
<!--              <span>地址：上海市杨浦区翔殷路128号12号楼101</span>-->
<!--            </li>-->
<!--          </ul>-->
<!--          <img src="./assets/img/ercode.png" alt/>-->
<!--        </div>-->
<!--        <div class="copyright">-->
<!--          <span>科建版权所有</span>-->
<!--        </div>-->
<!--      </div>-->
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      router: true,
      defaultActive: "/",
      isShow: false,
      log: false,
      info: false,
      headImg:'',
      // username:''
    };
  },
  created() {
    if (this.token == null) {
      this.log = true;
    } else {
      this.log = false;
    };
  },
  updated() {

    this.user = !!sessionStorage.getItem('token')
    console.log("********************" + this.user)
    if (this.user) {
      this.log = false
      this.info = true
      this.headImg = this.headPath
      // this.username = this.username
    } else {
      this.log = true
      this.info = false
    }
  },
  methods: {

    home() {
      this.$router.push('/Home')
    },
    goodsList() {
      this.$router.push('/goodsList')
    },
    caseList() {
      this.$router.push('/case')
    },
    worksList() {
      this.$router.push('/worksList')
    },
    realList(){
      this.$router.push('/realinfo')
    },
    employerRegister() {
      this.$router.push('/employerReg')
    },
    providerRegister() {
      this.$router.push('/providerReg')
    },
    employerLogin() {
      this.$router.push('/employerLog')
    },
    providerLogin() {
      this.$router.push('/providerLog')
    },
    handleSelect(key) {
      this.isShow = this.defaultActive != key;
      window.console.log(this.isShow);
    },
    logout() {
      this.$confirm('确定要退出登录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '退出成功!'
        });
        //确认退出，清除token
        sessionStorage.removeItem('user');
        sessionStorage.removeItem('token');
        // sessionStorage.removeItem('username')
        sessionStorage.removeItem('headPath')
        this.$router.push("/");

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出'
        });
      })

    },
    personCenter() {
      this.user = JSON.parse(sessionStorage.getItem('user'))
      console.log("***********user=*********" + this.user)
      if ((this.user.role) === "emp") {
        console.log(this.user.employerName)
        console.log(this.user.employerId)
        this.$router.push('/employerCenter')
      } else {
        this.$router.push('/providerCenter')
      }
    },
  },
  mounted() {
    this.token = JSON.parse(sessionStorage.getItem('token'))
    this.headPath = JSON.parse(sessionStorage.getItem('headPath'))
    // this.username = JSON.parse(sessionStorage.getItem('username'))
  }
};

</script>

<style lang="scss">
* {
  padding: 0;
  margin: 0;
}

html,
body {
  height: 100%;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.el-header {
  display: flex;
  justify-content: space-between;
  align-content: center;
  width: 1240px;
  margin: 0 auto;
  //background-color: #fff;

  .logo {
    width: 180px;
    //padding: 10px;
    height: 60px;

    img {
      width: 100%;
      height: 60px;
    }
  }
}

.el-main {
  padding: 0 !important;
}

.user-avator {
  margin-left: 20px;
}

.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.header {
  background-color: #23262e !important;
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: #fff;
}

.collapse-btn {
  float: left;
  padding: 0 21px;
  cursor: pointer;
  line-height: 70px;
}

.header .logo {
  float: left;
  width: 250px;
  line-height: 70px;
}

.header-right {
  float: right;
  padding-right: 50px;
}

.header-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}

.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}

.btn-bell,
.btn-fullscreen {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}

.btn-bell-badge {
  position: absolute;
  right: 0;
  top: -2px;
  width: 8px;
  height: 8px;
  border-radius: 4px;
  background: #f56c6c;
  color: #fff;
}

.btn-bell .el-icon-bell {
  color: #fff;
}

.user-name {
  margin-left: 10px;
}

.user-avator {
  margin-left: 20px;
}

.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}

.el-dropdown-link {
  color: #000000;
  cursor: pointer;
}

.el-dropdown-menu__item {
  text-align: center;
}

.collapse-btn:hover {
  background-color: #009688 !important;
}


.footer {
  width: 100%;
  height: 216px;
  overflow: hidden;
  background-color: #14679f;

  &-content {
    width: 1240px;
    margin: 0 auto;
    padding-top: 20px;
    display: flex;
    justify-content: space-between;

    .content-nav {
      display: flex;
      justify-content: space-around;

      li {
        display: flex;
        flex-direction: column;
        padding: 0 20px;
        //justify-content: center;
        align-items: flex-start;

        p {
          font-size: 20px;
          color: #d4edff;
          padding: 10px 0;
        }

        span {
          color: #f7f7f7;
          font-weight: 300;
          padding: 5px 0;
        }
      }
    }

    img {
      width: 170px;
      height: 170px;
      padding: 10px;
    }
  }

  .copyright {
    height: 30px;
    background: #125688;

    span {
      color: #fff;
      line-height: 30px;
    }
  }
}

.el-dropdown-link {
  cursor: pointer;
  //color: #409EFF;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.logReg {
  //float:right;
  //margin: 0 auto;
}
.login{
  float: left;
  padding-top: 20%;
  padding-right: 15px;
}
.register{
  float: right;
  padding-top: 20%;
}

</style>
