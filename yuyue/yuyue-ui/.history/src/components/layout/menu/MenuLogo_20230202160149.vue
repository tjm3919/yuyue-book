<template>
 
    <router-link to='/'>
        <div class="logo" @click="togo">
        <img src="@/image/logo/愉悦.png" alt="logo走丢了" title="愉悦图书管理系统"/>
        <span class="title">愉悦图书管理系统</span>
    </div>
    </router-link>
</template>
<script lang="ts" setup>
    // const togo = () =>{
    //     window.location=/
    // }
</script>
<style lang="scss" scoped>
    .logo{
        background-color: #19424a;
        height: 50px;
        border: none;
        line-height: 50px;
        display: flex;
        align-items: center;
        padding-left: 10px;
        color: #fff;
        img{
            width: 35px;
            height: 32px;
            margin-right: 1px;
        }
        span{
            font-weight: 600;
            line-height: 50px;
            font-size: 16px;
            font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
            vertical-align: middle;
        }
    }
    .logo:hover{
        cursor: pointer;
    }
</style>