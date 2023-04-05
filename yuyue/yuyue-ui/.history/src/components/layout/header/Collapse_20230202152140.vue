<template>
    <el-icon class="fa-icons" @click="changeIcon">
        <component class="icons" :is="status ? Expand : Fold"/>
    </el-icon>
</template>
<script setup lang='ts'>
    import { ref,reactive } from 'vue'
    import { Fold, Expand } from '@element-plus/icons-vue';

    const status = ref(false)
    const changeIcon = ()=> {
        status.value = !status.value 
    }
</script>
<style lang='scss' scoped>

.fa-icons {
    display: flex;
    align-items: center;
    .icons{
        font-size: 24px;
        color: #303133;
        cursor: pointer;
    }
}
</style>