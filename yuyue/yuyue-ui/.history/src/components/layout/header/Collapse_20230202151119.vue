<template>
    <el-icon class="fa-icons">
        <component class="icons" :is="status ? Expand : Fold"/>
    </el-icon>
</template>
<script setup lang='ts'>
   import { ref,reactive } from 'vue'
</script>
<style lang='scss' scoped>
</style>