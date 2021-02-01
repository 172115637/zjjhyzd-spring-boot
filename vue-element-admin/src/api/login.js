import request from '@/utils/request'

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  return request({
    url: '/auth/login',
    method: 'post',
    data: data
  })
}

export function logout() {
  return request({
    url: '/auth/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/auth/userinfo',
    method: 'get',
    data: {
      token
    }
  })
}

export function editPassword(data) {
  return request({
    url: '/auth/editPassword',
    method: 'post',
    data: data
  })
}
