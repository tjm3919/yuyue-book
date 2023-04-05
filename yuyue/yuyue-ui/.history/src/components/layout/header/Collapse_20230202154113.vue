<template>
    <el-icon class="fa-icons" @click="changeIcon">
        <component :is="status1 ? Expand : Fold"/>
    </el-icon>
</template>
<script setup lang='ts'>
    import { ref,reactive,computed } from 'vue'
    import { Fold, Expand } from '@element-plus/icons-vue';
    import { useStore } from 'vuex' 

    const store = useStore()
    cosnt status1 = computed(()=>{
        return store.getters['getCollapse']
    })
    const changeIcon = ()=> {
        store.commit['setCollapse']
        status.value = !status.value 
    }
</script>
<style lang='scss' scoped>

.fa-icons {
    display: flex;
    align-items: center;
    font-size: 20px;
    color: #303133;
    cursor: pointer;
    margin-top: 14px;
}
</style>