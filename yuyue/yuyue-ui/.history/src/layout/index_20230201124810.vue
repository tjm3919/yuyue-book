<template>
    <div class="commen">
        <el-container class="layout">
            <el-aside width="200px" class="aside">Aside</el-aside>
                <el-container>
                    <el-header class="header">Header</el-header>
                    <el-main class="main">Main</el-main>
                    <el-footer class="footer">Footer</el-footer>
                </el-container>
        </el-container>
    </div>
</template>
  
<script lang="ts" setup>

</script>

<style lang="scss" scoped>
.commen{
    height: 100%;
}
.layout{
    height: 100%;
    .aside{
        background-color: aqua;
    }
    .header{
        background-color: aquamarine;
    }
    .main{
        background-color: blueviolet;
    }
    .footer{
        background-color: gold;
    }
}
</style>