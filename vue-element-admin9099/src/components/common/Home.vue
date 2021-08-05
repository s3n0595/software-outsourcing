<template>
  <div class="wrapper">
    <v-head></v-head>
    <v-sidebar></v-sidebar>
    <div class="content-box" :class="{'content-collapse':collapse}">
      <v-tags></v-tags>
      <div class="content">
        <transition name="move" mode="out-in">
          <keep-alive :include="tagsList">
            <router-view></router-view>
          </keep-alive>
        </transition>
      </div>
    </div>
    <el-dialog title="密码修改" :visible.sync="isShow" v-dialogDrag :before-close="handleClose">
      <el-form
          :model="form"
          :rules="rules"
          label-width="30%"
          ref="form"
      >
        <el-form-item label="原密码" prop="userPassword">
          <el-input type="password" v-model="form.userPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="password">
          <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="re_password">
          <el-input type="password" v-model="form.re_password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="hideDailog">取 消</el-button>
          <el-button type="primary" @click="submitForm">确 定</el-button>
        </el-form-item>
        <el-form-item prop="userId">
          <el-input type="hidden" v-model="form.userId"></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import vHead from "./Header.vue";
import vSidebar from "./Sidebar.vue";
import vTags from "./Tags.vue";
import bus from "./bus";
import {mapGetters} from "vuex";
import {mapActions} from "vuex";

export default {
  data() {
    //密码强弱验证
    const passwordValidator = (rule, value, callback) => {
      if (/^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\W_!@#$%^&*`~()-+=]+$)(?![0-9\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\W_!@#$%^&*`~()-+=]{8,30}$/
          .test(value)) {
        callback()
      } else {
        callback(new Error('密码至少包含大写字母，小写字母，数字，特殊字符中的三种，且长度为8到30位！'))
      }
    }
    // 确认密码校验器
    const comfirmPassword = (rule, value, callback) => {
      if (value !== this.form.password) {
        return callback(new Error('两次输入密码不一致'))
      }
      callback()
    }
    return {
      tagsList: [],
      collapse: false,
      form: {
        userPassword: '',
        password: '',
        re_password: '',
        userId:''
      },
      rules: {
        userPassword: [
          { required: true, trigger: 'blur', message: '密码不能为空' },
          { validator: passwordValidator, trigger: 'blur' },
        ],
        password: [
          { required: true, trigger: 'blur', message: '密码不能为空' },
          { validator: passwordValidator, trigger: 'blur' },
        ],
        re_password: [ // 重复密码验证
          { required: true, trigger: 'blur', message: '请再一次输入密码' },
          { validator: comfirmPassword, trigger: 'blur' }
        ],
      }
    };
  },
  computed: {
    ...mapGetters("dailog", {
      isShow: "isShow"
    })
  },
  methods: {
    ...mapActions("dailog", ["hideDailog", "showDailog"]),
    handleClose() {
      this.$confirm('确认关闭？')
          .then(_ => {
            this.hideDailog();
            //this.$store.dispatch('dailog/hideDailog')
          }).catch(_ => {
          });
    },

    submitForm(formName) {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          let publicPath = 'business';
          this.$axios.post(`${publicPath}/userinfo/updatePwd`,
              this.$qs.stringify({
                "userPassword":this.form.userPassword,
                "password":this.form.password,
                'userId':this.user
              })).then(response=>{
            console.log(response);
            const res = response.data.code;
            if (res === 200) {
              this.hideDailog();
              this.$message.success("密码修改成功")
            } else {
              this.$message.error("原密码输入有误")
            }
          }).catch(error=>{
            console.log(error)
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
  },
  components: {
    vHead,
    vSidebar,
    vTags
  },
  created() {
    bus.$on("collapse", msg => {
      this.collapse = msg;
    });

    // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
    bus.$on("tags", msg => {
      let arr = [];
      for (let i = 0, len = msg.length; i < len; i++) {
        msg[i].name && arr.push(msg[i].name);
      }
      this.tagsList = arr;
    });
  },
  mounted() {
    this.user = sessionStorage.getItem('user')
  }
};
</script>
