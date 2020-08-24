import axios from 'axios'
import { getToken } from './token'
import {notification} from 'ant-design-vue';
import {logout}from  '@/services/user'




const API_PROXY_PREFIX='/api'
const goLogin= function () {
logout()
location.href='/index'
}
const BASE_URL = process.env.NODE_ENV === 'production' ? process.env.VUE_APP_API_BASE_URL : API_PROXY_PREFIX
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
//创建axios实例
const request=axios.create({
baseURL:BASE_URL,
//过期时间
timeout:10000
})

// 添加请求拦截器
request.interceptors.request.use(function (config) {
 if(getToken()){
   config.headers['Authorization']='Bearer '+  getToken()
 }
  return config;
}, function (error) {
   Promise.reject(error);
});

// 添加响应拦截器
request.interceptors.response.use(function (response) {

  //状态码
  const code=response.data.code   ||  200
  //返回信息
  const msg=response.data.msg;
  if(code==401){
    console.log('过期')
    notification.open({
      message: '系统提示',
      description: '登陆过期，请点击返回登录按钮进行登录！！！',
      duration: null,
      btn: h => {
        return h(
          'a-button',
          {
            props: {
              type: 'primary',
              size: 'small',
            },
            on: {
              click: goLogin
            },
          },
          '返回登录',
        );
      },
     
    
    });
  }

  return response.data;
}, function (error) {
  // 对响应错误做点什么
  return Promise.reject(error=>{
 
  })
});





export default request





