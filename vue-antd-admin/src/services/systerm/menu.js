import request from '@/utils/request'

/**
 * 获取菜单列表
 */
export function getMenu() {
  return request({
    url: '/menus',
    method: 'get'
  })

}

export function getMenuById(id) {
  return request({
    url: '/menu/' + id,
    method: 'get'

  })
}

export function addMenu(data) {
  console.log(data)
  return request({
    url: '/menu',
    method: 'post',
    data: data

  })
}
export function update(data) {
  console.log(data)
  return request({
    url: '/menu',
    method: 'put',
    data: data
  })
}
export function delMenuByid(id) {
  return request({
    url: '/menu/' + id,
    method: 'delect'
  })
}



export default {
  getMenu,
  getMenuById,
  addMenu,
  delMenuByid,
  update,

}