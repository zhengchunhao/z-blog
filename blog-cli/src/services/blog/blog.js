import request from '../../utils/request'

export function blogs(params,tagId){
   return request({
       url:'/blogs/'+tagId,
       method:'get',
       params:params
   }) 
}
export default {
    blogs, 
}