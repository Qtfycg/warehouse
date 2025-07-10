<template>
  <div class="login-page">
    <div class="login-card">
      <h2 class="login-title">欢迎登录</h2>
      <el-form
        :model="form"
        :rules="rules"
        ref="loginForm">
        <!-- 用户名 -->
        <el-form-item prop="phone">
          <el-input
            v-model="form.phone"
            placeholder="请输入手机号"
            @blur="getCode"
          />
        </el-form-item>

        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input
            v-model="form.password"
            placeholder="请输入密码" show-password
          />
        </el-form-item>
        <!-- 验证码输入框 + 图片 -->
        <el-form-item prop="captcha">
          <el-row
            :gutter="10"
            style="width: 100%">
            <el-col :span="14">
              <el-input v-model="form.captcha" placeholder="验证码" />
            </el-col>
            <el-col :span="10">
              <img
                :src="captchaImg"
                alt="验证码"
                class="captcha-img"
                @click="loadCaptcha"
              />
            </el-col>
          </el-row>
        </el-form-item>
        <!-- 登录按钮 -->
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="submitLogin">
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getCaptcha } from '@/api/user'  // 假设这里封装了验证码接口

const form = ref({
  phone: '',
  password: '',
  captcha: ''
})

const captchaImg = ref('')

const rules = {
  phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  captcha: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
}

const getCode = () => {
  if (!form.value.phone) {
    ElMessage.warning('请输入手机号')
    return
  }
  loadCaptcha()
}


// 获取验证码图片
const loadCaptcha = async () => {
  if (!form.value.phone) {
    ElMessage.warning('请先输入用户名')
    return
  }
  try {
    const { IMG } = await getCaptcha(form.value.phone)
    captchaImg.value = IMG
  } catch (err) {
    ElMessage.error('验证码获取失败')
  }
}

// 登录方法（这里仅演示）
const submitLogin = () => {

}
</script>

<style scoped>
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
  padding: 20px;
}

.login-card {
  width: 360px;
  max-width: 100%;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
}

.captcha-img {
  width: 100%;
  height: 38px;
  border: 1px solid #dcdfe6;
  cursor: pointer;
  border-radius: 4px;
}

/* 响应式调整 */
@media (max-width: 480px) {
  .login-card {
    width: 100%;
    padding: 20px;
  }
}
</style>
