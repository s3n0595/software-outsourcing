<template>
  <div class="case">
    <banner img="../assets/img/bgtop.jpg" title="成功案例" />
    <div class="case-section" >
      <div class="case-section-content">
        <div class="case-section-content-list" v-for="(cas,index) in caseList" :key="index">
<!--          <img v-lazy="imgserver+cas.Img" />-->
          <img :src="'api/images/'+cas.works.annexPath">
          <div class="content-list-abstract" :class="{'abstract-active' : index%2!=1}">
            <p class="abstract-title">{{cas.works.worksTitle}}</p>
            <p class="abstract-content">{{cas.works.worksDescribe}}</p>
            <p style="font-size: 15px">服务商：{{str(cas.providerAccount.providerName)}}</p>
            <p style="font-size: 15px">雇主：{{str(cas.employerAccount.employerName)}}</p>
<!--            <p class="">金额：{{str(String.valueOf(cas.works.worksPrice))}}</p>-->
<!--            <div class="more">-->
<!--              <router-link-->
<!--                  class="text-decoration"-->
<!--                  :to="{ name: 'casedetails', params: { id: cas.Id }}"-->
<!--              >-->
<!--                <span>more</span>-->
<!--                <img src="../assets/img/sanjiao.png" />-->
<!--              </router-link>-->
<!--            </div>-->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Banner from "../components/Banner";
export default {
  components: {
    Banner
  },
  data() {
    return {
      // loading: true,
      caseList: []
    };
  },
  methods: {
    str(value) {
      return value.replace(value.substring(2, value.length - 5), '*****')
    }
  },
  mounted() {
    this.$axios.get("work/success").then(response => {
          //console.log(response);
          console.log(response)
          const code = response.data
          this.caseList = code.data;
          //window.console.log(this.caseList);
          // this.loading = false;
        })
        .catch(function(error) {
          console.log(error);
        });
  }
};
</script>

 <style lang="scss" scoped>
.case {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
  background-color: #14679f;

  &-section {
    width: 100%;
    &-content {
      width: 1240px;
      min-height: 1000px;
      margin: 0 auto;
      background-color: #fff;

      &-list {
        width: 100%;
        height: 500px;
        display: flex;
        justify-content: center;
        align-items: center;
        border: 1px solid pink;

        img {
          width: 612px;
          height: 314px;
        }
        .content-list-abstract {
          width: 290px;
          height: 350px;
          padding: 0 20px;
          display: flex;
          flex-direction: column;
          justify-content: space-around;
          .abstract-title {
            line-height: 30px;
            font-size: 22px;
            color: #e13834;
          }
          .abstract-content {
            height: 150px;
            color: #484848;
            font-size: 15px;


            overflow: hidden;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 7;
            -webkit-box-orient: vertical;
            white-space: normal !important;
            word-wrap: break-word;
            //border: 1px solid pink;
          }
          .more {
            display: flex;
            justify-content: flex-start;
            .text-decoration {
              text-decoration: none;

              span {
                color: #000;
              }
              img {
                width: 12px;
                height: 12px;
              }
            }
          }
        }
        .abstract-active {
          order: -1;
        }
      }
    }
  }
}
</style>
