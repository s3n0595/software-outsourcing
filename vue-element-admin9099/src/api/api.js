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
// 删除用户列表
export const deleteUserInfoList = parms=>{
    return axios.get(`${publicPath}/userinfo/deleteUserList`,{params:parms})
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
