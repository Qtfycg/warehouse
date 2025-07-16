<script lang="ts">
import { ref } from 'vue'

const login = ref({
  phone:'',
  password:'',
  code:''
})

const rules = {
  phone: [{ required: true, message: '请输入手机号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  captcha: [{ required: true, message: '请输入验证码', trigger: 'blur' }]}
</script>

<template>
  <div class="login">
    <div class="login-card">
      <h2 class="login-text">欢迎使用xxx</h2>
      <el-form
      :model="login"
      :rules="rules"
      :inline-message="true"
      >
        <!--手机号-->
        <el-form-item
          prop="phone"
        >
          <el-input
            type="number"
            v-model="login.phone"
            placeholder="请输入手机号"
            @blur="getCode"
          />
        </el-form-item>
        <!--请输入密码-->
        <el-form-item
        prop="password"
        >
          <el-input
          v-model="login.padding"
          placeholder="请输入密码"
          show-password
          />
        </el-form-item>
        <!--请输入验证码-->
        <el-form-item
        prop="code"
        >
          <el-row
            :gutter="10"
            style="width: 100%">
            <el-col :span="14">
              <el-input v-model="form.code" placeholder="验证码" />
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
      </el-form>
    </div>

  </div>

</template>

<style scoped>
.login {
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

.login-text {
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
@media (max-width: 480px) {
  .login-card {
    width: 100%;
    padding: 20px;
  }
}
</style>