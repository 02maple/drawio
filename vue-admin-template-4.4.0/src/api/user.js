import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/auditors/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/auditors/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/auditors/logout',
    method: 'post'
  })
}
