import axios from 'axios'
import { getToken } from './token'
const API_PROXY_PREFIX='/api'


const BASE_URL = process.env.NODE_ENV === 'production' ? process.env.VUE_APP_API_BASE_URL : API_PROXY_PREFIX
axios.defaults.headers['Content-Type'] = 'multipart/form-data'
//创建axios实例
const uploadUtil=axios.create({
baseURL:BASE_URL,
//过期时间
timeout:10000
})

// 添加请求拦截器
uploadUtil.interceptors.request.use(function (config) {

    if(getToken()){
      config.headers['Authorization']='Bearer '+  getToken()
    }
     return config;
   }, function (error) {
      Promise.reject(error);
   });

   uploadUtil.interceptors.request.use(function (config) {
    if(getToken()){
      config.headers['Authorization']='Bearer '+  getToken()
    }
     return config;
   }, function (error) {
      Promise.reject(error);
   });
   
   // 添加响应拦截器
   uploadUtil.interceptors.response.use(function (response) {
     return response.data;
   }, function (error) {
     // 对响应错误做点什么
     return Promise.reject(error=>{
    
     })
   });
  
   export default uploadUtil