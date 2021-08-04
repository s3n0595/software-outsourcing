import Vue from 'vue'
import Vuex from 'vuex'
import dailog from './modules/dailog'
import userInfo from './modules/userInfo'
Vue.use(Vuex);
const store = new Vuex.Store({
    modules:{
        dailog,
        userInfo
    }
});
export default store;
