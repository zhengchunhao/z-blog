import request from '../../utils/request'

export function blogs(params,tagId){
   return request({
       url:'/blogs/'+tagId,
       method:'get',
       params:params
   }) 
}

export function delBlog(blogId,tagId){
   return request({
      url:'/blog/'+blogId+'/'+tagId,
      method:'delete' 
   }) 
}
export default {
    blogs,
    delBlog, 
}