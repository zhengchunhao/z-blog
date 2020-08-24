import request from '@/utils/request'

export function roleList(params) {
   return request({
      url: '/roles',
      method: 'get',
      params: params
   })
}

export function role(id) {
   return request({
      url: '/role/' + id,
      method: 'get',

   })

}

export function add(data) {
   return request({
      url: '/role',
      method: 'post',
      data: data
   })
}

export function update(data) {
   return request({
      url: '/role',
      method: 'put',
      data: data
   })
}


export function remove(ids) {
   return request({
      url: '/roles/' + ids,
      method: 'delect',

   })
}
export function permsTree() {
   return request({
      url: '/permsTree',
      method: 'get'
   })
}

export function rolePermsTree(roleId){
  return  request({
     url:'/rolePermsTree/'+roleId,
     method:'get'
  })
}

export function checkRoleKeyUnique(params) {
   return request({
      url: '/checkRoleKeyUnique',
      method: 'get',
      params: params,
   })

}

export function authorize(roleId,ids){
   return request({
   url:'/role/'+roleId+'/perms/'+ids,
   method:'get',
   })
}



export default {
   roleList,
   add,
   update,
   remove,
   role,
   authorize,
   permsTree,
   rolePermsTree,
   checkRoleKeyUnique,
}