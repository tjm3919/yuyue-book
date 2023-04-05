<template>
    <div class="common-layout">
        <el-container class="container">
            <el-aside width="200px" class="aside">
                <Menu></Menu>
            </el-aside>
                <el-container>
                    <el-header class="header">
                        <Header></Header>
                    </el-header>
                    <el-main class="main">Main</el-main>
                    <el-footer class="footer">Footer</el-footer>
                </el-container>
        </el-container>
    </div>
</template>
  
<script lang="ts" setup>
    import Header from '../layout/header/Header.vue'
    import Menu from '../layout/menu/Menu.vue'

</script>

<style lang="scss" scoped>
.common-layout{
    height: 100%;
    .container{
        height: 100%;
        .aside{
            background-color: rgb(48, 65, 86);
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
}
</style>