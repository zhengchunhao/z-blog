
import request from '@/utils/request'
import { removeToken } from '@/utils/token'

/**
 * 登录服务
 * @param name 账户名
 * @param password 账户密码
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function login(name, password,uuid,code) {
   return request({
     url:'/login',
     method:'post',
     data:{
       username:name,
       password:password,
       uuid:uuid,
       code:code
     }
   })
}

export function userInfo(){
  return request({
    url:'/userInfo',
    method:'get',
  })
}

export function users(query){
  return request({
    url:'/users',
    method:'get',
    params:query,
  })
}

export function addUser(data){
  return request({
    url:'/user',
    method:'post',
    data:data,
  })
}

export  function updateUser(data){
  return request({
    url:'/user',
    method:'put',
    data:data,
  })
}
export function queryUserById(id){
  return request({
  url:'/user/'+id,
  method:'get',

  })
}

export function deleteUsers(ids){
  console.log(ids)
  return request({
   url:'/users/'+ids,
   method:'delete',
 
  })
}

export function CheckUserNameUnique(params){
  return request({
    url:'/userNameUnique',
    method:'get',
    params:params,
  })
   
}
export function CheckPhoneUnique(params){
  return request({
    url:'/phoneUnique',
    method:'get',
    params:params,
  })
}


  export function CheckEmailUnique(params){
    return request({
      url:'/emailUnique',
      method:'get',
      params:params,
    })
  }

  export function roleOptions(){
   return request({
   url:'/allRoles',
   method:'get'
   }) 
  }

  export function userHaveRoleIds(id){
    return request({
      url:'/user/'+id+'/rolesId',
      method:'get'
    })
  }

/***
 * 路由配置信息
 */
export async function getRoutesConfig() {
  return request({
  url:'/userRoutes',
  method:'get'
  })
}


/**
 * 获得全部路由
 * 
 */
export async function getAllRoute(){
return request({
url:'/allRouets',
method:'get'

})

}
/**
 * 验证码图片
 */
 export function getCode(){
   return request({
     url:'/Code',
     method:'get',
   })
 }
 

/**
 * 退出登录
 */
export function logout() {
  localStorage.removeItem(process.env.VUE_APP_ROUTES_KEY)
  localStorage.removeItem(process.env.VUE_APP_PERMISSIONS_KEY)
  localStorage.removeItem(process.env.VUE_APP_ROLES_KEY)
  removeToken()
}
export default {
  login,
  logout,
  getRoutesConfig,
  users,
  deleteUsers,
  getCode,
  addUser,
  updateUser,
  roleOptions,
  userHaveRoleIds,
  queryUserById,
  CheckUserNameUnique,
  CheckPhoneUnique,
  CheckEmailUnique,

}
