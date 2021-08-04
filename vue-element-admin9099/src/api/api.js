import axios from 'axios';
let baseUrl = 'test';
//获取雇主列表
export const getEmpList=params=>{
    return axios.get(`${baseUrl}/employer/list`,{params:params})
}
export const getEditemp=params=>{
    return axios.get(`${baseUrl}/employer/edit`,{params:params})
}
export const getDeleteOne = params=>{
    return axios.get(`${baseUrl}/employer/DeleteOne`,{params:params})
}
export const getDeleEmp = params=>{
    return axios.get(`${baseUrl}/employer/DeleteAll`,{params:params})
}
export const getSelEmpList = params=>{
    return axios.get(`${baseUrl}/employer/selEmpList`,{params:params})
}

// 用户管理
// 获取用户列表
export const getUserInfoList = params=>{
    return axios.get(`${baseUrl}/userinfo/getUserList`,{params:params})
}
export const getSearchUser = params=>{
    return axios.get(`${baseUrl}/userinfo/getSearchUser`,{params:params})
}
// 删除用户列表
export const deleteUserInfoList = params=>{
    return axios.get(`${baseUrl}/userinfo/deleteUserList`,{params:params})
}
// 添加用户
export const addUserInfo = params=>{
    return axios.get(`${baseUrl}/userinfo/addUserInfo`,{params:params})
}
// 修改状态
export const updateState = params=>{
    return axios.get(`${baseUrl}/userinfo/updateState`,{params:params})
}
// 修改用户信息
export const updateUserInfo = params=>{
    return axios.get(`${baseUrl}/userinfo/updateUserInfo`,{params:params})
}
// 登录验证
export const userInfoLogin = params =>{
    return axios.get(`${baseUrl}/userinfo/userInfoLogin`,{params:params})
}
// 动态菜单
export const getMenuData = params =>{
    return axios.get(`${baseUrl}/userinfo/getMenuData`,{params: params})
}
export const getUserList = params=>{
    return axios.get(`${baseUrl}/user/list`,{params:params})
}
export const getProviderList = params=>{
    return axios.get('business/provider/list',{params:params})
}
export const getSysmenu = ()=>{
    return axios.get('../../menu.json')
}
export const getDeleUser = params=>{
    return axios.get('user/deletUsers',{params:params});
}
export const getEditUser = params=>{
    return axios.get(`${baseUrl}/user/editUser`,{
        params:params
    })
}
export const getAddUser = params=>{
    return axios.get(`${baseUrl}/user/addUser`,{
        params:params
    })
}
export const testApi = ()=>{
    return axios.get("/testApi/getkeywhereisddata?CITY_NAME=全省&S_id=B08I00136&callback=")
}
