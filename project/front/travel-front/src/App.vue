<script setup>

</script>

<template>
    <div class="login">
        <el-container>
            <el-main>
                <el-row>
                    <el-col :span="12">
                        <a-image width="190px" height="190px"
                            src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/a8c8cdb109cb051163646151a4a5083b.png~tplv-uwbnlip3yd-webp.webp"
                            show-loader />
                    </el-col>

                    <el-col :span="12">
                        <a-form :modle="login" style="size: large;width: 190px;">
                        <a-form-item field="tel" label="tel"></a-form-item>
                        </a-form>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</template>

<style scoped>
.login {
    width: 400px;
    height: 200px;
    float: left;
    margin-left: 35%;
    margin-top: 10%;
    padding-left: 5px;

}
</style>
