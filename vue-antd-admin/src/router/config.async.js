import routerMap from './router.map'
import {parseRoutes} from '@/utils/routerUtil'




// 异步路由配置
const routesConfig = [
  'login',
  'root',                       //匹配 router.map.js 中注册的 registerName = root 的路由
  {
    router: 'exp404',           //匹配 router.map.js 中注册的 registerName = exp404 的路由
    path: '*',                  //重写 exp404 路由的 path 属性
    name: '404'                 //重写 exp404 路由的 name 属性
  },
  {
    router: 'exp403',           //匹配 router.map.js 中注册的 registerName = exp403 的路由
    path: '/403',               //重写 exp403 路由的 path 属性
    name: '403'                 //重写 exp403 路由的 name 属性
  }
  
]

const options = {
  routes: parseRoutes(routesConfig, routerMap)
}



export default options
