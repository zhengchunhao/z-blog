import request from '@/utils/request'
import uploadUtil from '@/utils/uploadUtil'

export function getAllTags(){
    return request({
    url:'/tags',
    method:'get',
    })
}
export function tag(id){
return request({
   url:'/tag/'+id,
   method:'get', 
})
}

 export function updateTag(data){
    return request({
        url:'/tag',
        method:'put',
        data:data
    })
 }

 export function addTag(data){
     return request({
     url:'/tag',
     method:'post',
     data:data
     })
 }

  export function upload(data){
   return uploadUtil({
    url:'/commom/upload',
    method:'post',
    data:data 
   })

  }

  export function image(id){
   return request({
       url:'/image/'+id,
       method:'get'
   })
  }
  export function delImage(id){
   return request ({
    url:'/image/'+id,
    method:'delete',
   })
  }

  export function delTage(tagId){
  return request({
      url:'/tag/'+tagId,
      method:'delete'
  })
  }
     

 export default {
    getAllTags,
    updateTag,
    addTag,
    upload,
    image,
    delImage,
    delTage,
 }