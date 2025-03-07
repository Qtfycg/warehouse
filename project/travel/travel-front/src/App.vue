<script setup>
/*JS/TS*/
</script>

<template>
<!--HTML-->
  <div class="common-login">
    <a-layout style="height: 100%">
      <a-layout-content>
        <div class="login">
          <a-form
              style="width: 300px"
              layout="horizontal"
              label-align="left">

            <!--账号-->
            <a-form-item label="账号">
              <a-input size="medium" placeholder="请输入账号"></a-input>
            </a-form-item>
            <!--密码-->
            <a-form-item label="密码">
              <a-input size="medium"  placeholder="请输入密码"></a-input>
            </a-form-item>
            <a-form
                style="width: 300px"
                layout="inline"
            >
              <!--验证码-->
              <a-form-item label="验证码">
                <a-input style="width: 110px" size="medium"  placeholder="请输入验证码"></a-input>
              </a-form-item>

          </a-form>

            <!--按钮-->
            <a-form style="width: 300px" layout="inline" direction="horizontal">
              <a-form-item style="width: 50px"><a-button type="text">登录</a-button></a-form-item>
              <a-form-item style="width: 50px"><a-button type="text">忘记密码</a-button></a-form-item>
            </a-form>

          </a-form>

        </div>

      </a-layout-content>
    </a-layout>
  </div>

</template>

<style scoped>
/*CSS*/
.login{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;

}
</style>
