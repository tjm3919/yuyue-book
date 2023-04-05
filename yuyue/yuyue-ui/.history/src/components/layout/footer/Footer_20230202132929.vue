<template>
   <div class="big_div">
        {{ date }}
   </div>
</template>
<script setup lang='ts'>
    import { ref,reactive,onBeforeMount } from 'vue'
    import datetime from '@/http/config/DateTime'
    
    // 进入页面直接运行的方法
    onBeforeMount(()=>{
        showTimes
    })
    
    let date = ref(datetime(new Date(),'YYYY-mm-dd HH:MM:SS'))
    function showTimes(){
        date = ref(datetime(new Date(),'YYYY-mm-dd HH:MM:SS'))
        setInterval(showTimes,1000)
    }

    function clear(){
        clearInterval();
    }
</script>
<style lang='scss' scoped>
</style>