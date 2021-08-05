import axios from 'axios';
let publicPath = 'business';

//获取雇主列表
export const getEmpList=params=>{
    return axios.get(`${publicPath}/employer/list`,{params:params})
}
export const getEditemp=params=>{
    return axios.get(`${publicPath}/employer/edit`,{params:params})
}
export const getDeleteOne = params=>{
    return axios.get(`${publicPath}/employer/DeleteOne`,{params:params})
}
export const getDeleEmp = params=>{
    return axios.get(`${publicPath}/employer/DeleteAll`,{params:params})
}
export const getSelEmpList = params=>{
    return axios.get(`${publicPath}/employer/selEmpList`,{params:params})
}

// 用户管理
// 获取用户列表
export const getUserInfoList = params=>{
    return axios.get(`${publicPath}/userinfo/getUserList`,{params:params})
}
// 关键字查找
export const getSearchUser = params=>{
    return axios.get(`${publicPath}/userinfo/getSearchUser`,{params:params})
}
// 删除用户列表
export const deleteUserInfoList = parms=>{
    return axios.get(`${publicPath}/userinfo/deleteUserList`,{params:parms})
export const deleteUserInfoList = params=>{
    return axios.get(`${publicPath}/userinfo/deleteUserList`,{params:params})
}
// 添加用户
export const addUserInfo = params=>{
    return axios.get(`${publicPath}/userinfo/addUserInfo`,{params:params})
}
// 修改状态
export const updateState = params=>{
    return axios.get(`${publicPath}/userinfo/updateState`,{params:params})
}
// 修改用户信息
export const updateUserInfo = params=>{
    return axios.get(`${publicPath}/userinfo/updateUserInfo`,{params:params})
}
// 后台登录
// 登录验证
export const userInfoLogin = params =>{
    return axios.get(`${publicPath}/userinfo/userInfoLogin`,{params:params})
}
// 获取菜单列表
export const getMenuData = params =>{
    return axios.get(`${publicPath}/userinfo/getMenuData`,{params: params})
}
// 角色管理
// 获取角色列表
export const getRoleList = params =>{
    return axios.get(`${publicPath}/userinfo/getRoleList`,{params: params});
}
// 关键字查找
export const getSearchRole = params=>{
    return axios.get(`${publicPath}/userinfo/getSearchRole`,{params:params})
}
// 添加新角色
export const addRole = params =>{
    return axios.get(`${publicPath}/userinfo/addRole`,{params: params});
}
// 删除角色
export const deleteRole = params =>{
    return axios.get(`${publicPath}/userinfo/deleteRole`,{params: params});
}
export const getUserList = params=>{
    return axios.get(`${publicPath}/user/list`,{params:params})
}
export const getProviderList = params=>{
    return axios.get(`${publicPath}/provider/list`,{params:params})
}
export const editProvider = params=>{
    return axios.get(`${publicPath}/provider/edit`,{params:params})
}

export const getSysmenu = ()=>{
    return axios.get('../../menu.json')
}
export const getDeleUser = params=>{
    return axios.get('user/deletUsers',{params:params});
}
export const getEditUser = params=>{
    return axios.get(`${publicPath}/user/editUser`,{
        params:params
    })
}
export const getAddUser = params=>{
    return axios.get(`${publicPath}/user/addUser`,{
        params:params
    })
}
// export const getDeleteOne = params=>{
//     return axios.get(`${baseUrl}/user/DeleteOne`,{params:params})
// }
// export const testApi = ()=>{
//     return axios.get("/testApi/getkeywhereisddata?CITY_NAME=全省&S_id=B08I00136&callback=")
// }
// export const testApi = ()=>{
//     return axios.get("/testApi/getkeywhereisddata?CITY_NAME=全省&S_id=B08I00136&callback=")
// }
// 获取当前时间
export const getDate = ()=>{
        let date = new Date();
        let y = date.getFullYear();
        let m = date.getMonth()+1;
        let m2 = m < 10 ? "0"+m : m;
        let d = date.getDate();
        let d2 = d < 10 ? "0"+d : d;
        return y+"-"+m2+"-"+d2;
}
