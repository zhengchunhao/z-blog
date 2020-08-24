import uploadUtil from '@/utils/uploadUtil'
import request from '../../utils/request'
import { da } from 'date-fns/locale'


export function upload(data){
    return uploadUtil({
        url:'/commom/upload',
        method:'post',
        data:data 
       })

}
export function deleteFile(id){
  return request({
      url:"/deleteImg/"+id,
      method:'delete'
  })
}
  export function add(data){
   return request({
       url:'/blog',
       method:'post',
       data:data
   })
  }
   

  export function update(data){
    return request({
      url:'/blog',
      method:'put',
      data:data
    })
  } 

  export function blog(id){
    return request({
      url:'/blog/'+id,
      method:'get'
    })
  }



export default {
    uploadUtil,
    deleteFile,
    update,
    blog,
    
}