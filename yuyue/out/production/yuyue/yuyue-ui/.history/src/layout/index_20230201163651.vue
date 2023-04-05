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
            height: 50px;
            border-bottom: 1px solid #e5e5e5;
        }
        .main{
        }
        .footer{
        }
    }
}
</style>