import request from '@/utils/request'

export async function getCaptcha(phone) {
  const res = await request({
    url: `/user/captcha?phone=${phone}`,
    method: 'get'
  })
  if ('IMG' in res) {
    return res
  }
  throw new Error('响应结构不符合预期')
}


export async function login(data) {
  const res = await request({
    url: '/user/login',
    method: 'post',
    headers:{
      'Content-Type': 'application/json'
    },
    data
  })
  if ('token' in res) return res
  throw new Error('登录响应结构异常')
}

