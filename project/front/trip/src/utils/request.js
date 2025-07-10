import axios from 'axios'
import router from '@/router'
import { ElMessage } from 'element-plus'

const service = axios.create({
  baseURL: '/api', // 代理地址或生产地址
  timeout: 5000
})

// 请求拦截器：添加 token
service.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器：统一错误处理
service.interceptors.response.use(
  (response) => {
    const res = response.data
    if (res.code !== 200) {
      ElMessage.error(res.message || '请求失败')
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res.data
    }
  },
  (error) => {
    // 处理 HTTP 错误状态
    if (error.response) {
      const status = error.response.status
      switch (status) {
        case 401:
          ElMessage.error('未登录或登录过期，请重新登录')
          localStorage.removeItem('token')
          router.push('/login')
          break
        case 403:
          ElMessage.error('无权限访问该资源')
          break
        case 500:
          ElMessage.error('服务器错误')
          break
        default:
          ElMessage.error(`请求错误：${status}`)
      }
    } else {
      ElMessage.error('网络连接失败')
    }
    return Promise.reject(error)
  }
)

export default service
