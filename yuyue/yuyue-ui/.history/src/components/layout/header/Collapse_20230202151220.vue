<template>
    <el-icon class="fa-icons">
        <component class="icons" :is="status ? Expand : Fold"/>
    </el-icon>
</template>
<script setup lang='ts'>
   import { ref,reactive } from 'vue'
   import { Fold, Expand } from '@element-plus/icons-vue';

   const status = ref(false)

</script>
<style lang='scss' scoped>
.icons{
    font-size: 24px;
}
</style>