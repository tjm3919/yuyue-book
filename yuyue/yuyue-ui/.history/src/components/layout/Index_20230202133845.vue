<template>
    <div class="common-layout">
        <el-container class="container">
            <el-aside width="auto" class="aside">
                <Menu></Menu>
            </el-aside>
                <el-container>
                    <el-header class="header">
                        <Header></Header>
                    </el-header>
                    <el-main class="main">
                        <router-view></router-view>
                    </el-main>
                    <el-footer class="footer">
                        <Footer></Footer>
                    </el-footer>
                </el-container>
        </el-container>
    </div>
</template>
  
<script lang="ts" setup>
    import Header from './header/Header.vue'
    import Footer from './footer/Footer.vue'
    import Menu from './menu/Menu.vue'

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
            background-color: white;
        }
        .footer{
            height: 100px;
            border-top: 1px solid #e5e5e5;
        }
    }
}
</style>