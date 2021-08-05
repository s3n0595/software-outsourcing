const state = {
    user:'',
}
const getters = {
    getUser(state){
        return state.user;
    }
}
const mutations = {
    setUser(state,user){
        state.user = user;
    }
}
const actions = {
    getSetUser(context,user){
        context.commit('setUser',user);
    }
}

export default {
    state,
    getters,
    mutations,
    actions
}
