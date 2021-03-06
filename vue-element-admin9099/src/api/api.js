import axios from 'axios';
let baseUrl = 'baseUrl';

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

// ====================================用户管理===============================//
// 获取用户列表
export const getUserInfoList = params=>{
    return axios.get(`${baseUrl}/userinfo/getUserList`,{params:params})
}
// 关键字查找
export const getSearchUser = params=>{
    return axios.get(`${baseUrl}/userinfo/getSearchUser`,{params:params})
}
// 删除用户列表
export const deleteUserInfoList = parms=>{
    return axios.get(`${baseUrl}/userinfo/deleteUserList`,{params:parms})
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
// 用户名查重
export const userAccountExist = params=>{
    return axios.get(`${baseUrl}/userinfo/userAccountExist`,{params:params})
}
// 后台登录
// 登录验证
export const userInfoLogin = params =>{
    return axios.get(`${baseUrl}/userinfo/userInfoLogin`,{params:params})
}
// 登陆后修改最后登录时间，次数
export const updateLoginDate = params =>{
    return axios.get(`${baseUrl}/userinfo/updateLoginDate`,{params:params})
}
// 登录后对应用户的动态菜单列表
export const getMenu = params =>{
    return axios.get(`${baseUrl}/userinfo/getMenu`,{params: params})
}
// ====================================角色管理===============================//
// 获取角色列表
export const getRoleList = params =>{
    return axios.get(`${baseUrl}/userinfo/getRoleList`,{params: params});
}
// 关键字查找
export const getSearchRole = params=>{
    return axios.get(`${baseUrl}/userinfo/getSearchRole`,{params:params})
}
// 添加新角色
export const addRole = params =>{
    return axios.get(`${baseUrl}/userinfo/addRole`,{params: params});
}
// 删除角色
export const deleteRole = params =>{
    return axios.get(`${baseUrl}/userinfo/deleteRole`,{params: params});
}
// 修改角色
export const updateRole = params =>{
    return axios.get(`${baseUrl}/userinfo/updateRole`,{params: params});
}
// 角色名查重
export const roleNameExist = params =>{
    return axios.get(`${baseUrl}/userinfo/roleNameExist`,{params: params});
}
// 获取全部的菜单列表
export const getMenuData = params =>{
    return axios.get(`${baseUrl}/userinfo/getMenuData`,{params: params});
}
// ====================================菜单管理===============================//
// 菜单名名查重
export const menuNameExist = params =>{
    return axios.get(`${baseUrl}/userinfo/menuNameExist`,{params: params});
}
// 修改菜单信息
export const updateMenu = params =>{
    return axios.get(`${baseUrl}/userinfo/updateMenu`,{params: params});
}
// 添加新菜单
export const addMenu = params =>{
    return axios.get(`${baseUrl}/userinfo/addMenu`,{params: params});
}
// 删除
export const deleteMenuList = params =>{
    return axios.get(`${baseUrl}/userinfo/deleteMenuList`,{params: params});
}
// ====================================业务管理===============================//
// 获取需求列表
export const getDemandList = params =>{
    return axios.get(`${baseUrl}/Demand/getDemandList`,{params: params});
}
// 修改审核状态
export const updateDemandState = params =>{
    return axios.get(`${baseUrl}/Demand/updateDemandState`,{params: params});
}
// 删除
export const deleteDemandList = params =>{
    return axios.get(`${baseUrl}/Demand/deleteDemandList`,{params: params});
}
// 雇主名称关键字
export const getSearchDemand = params =>{
    return axios.get(`${baseUrl}/Demand/getSearchDemand`,{params: params});
}
// 审核状态查找
export const getSearchState = params =>{
    return axios.get(`${baseUrl}/Demand/getSearchState`,{params: params});
}
// ====================================订单管理===============================//
// 获取作品交易列表
export const getTradeWordList = params =>{
    return axios.get(`${baseUrl}/tradeManage/getTradeWordList`,{params: params});
}
// 获取投标交易列表
export const getTradeRecord = params =>{
    return axios.get(`${baseUrl}/tradeManage/getTradeRecord`,{params: params});
}
// 通过providerInfoId 查找对应的服务商
export const getProvider = params =>{
    return axios.get(`${baseUrl}/tradeManage/getProvider`,{params: params});
}
// 获取退款列表getTradeRetreatList
export const getTradeRetreatList = params =>{
    return axios.get(`${baseUrl}/tradeManage/getTradeRetreatList`,{params: params});
}
// 修改退款列表状态
export const updateTreatStatus = params =>{
    return axios.get(`${baseUrl}/tradeManage/updateTreatStatus`,{params: params});
}
// 删除退款列表deleteTradeRetreat
export const deleteTradeRetreat = params =>{
    return axios.get(`${baseUrl}/tradeManage/deleteTradeRetreat`,{params: params});
}
//=====================================登录日志================================//
// 获取日志列表
export const getLogInfoList = params =>{
    return axios.get(`${baseUrl}/userinfo/getLogInfoList`,{params: params});
}
// 关键字查找
export const getSearchLogInfo = params=>{
    return axios.get(`${baseUrl}/userinfo/getSearchLogInfo`,{params:params})
}
// 删除日志
export const deleteLogInfo = params =>{
    return axios.get(`${baseUrl}/userinfo/deleteLogInfo`,{params: params});
}
// 添加日志
export const addLogInfo = params =>{
    return axios.get(`${baseUrl}/userinfo/addLogInfo`,{params: params});
}

export const getUserList = params=>{
    return axios.get(`${baseUrl}/user/list`,{params:params})
}
export const getProviderList = params=>{
    return axios.get(`${baseUrl}/provider/list`,{params:params})
}
export const editProvider = params=>{
    return axios.get(`${baseUrl}/provider/edit`,{params:params})
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
        let hh = date.getHours() < 10 ? "0"+date.getHours() : date.getHours();
        let mm = date.getMinutes() < 10 ? "0"+date.getMinutes() : date.getMinutes();
        let ss = date.getSeconds() < 10 ? "0"+date.getSeconds() : date.getSeconds();
        return y+"-"+m2+"-"+d2+" "+hh+":"+mm+":"+ss;
}
