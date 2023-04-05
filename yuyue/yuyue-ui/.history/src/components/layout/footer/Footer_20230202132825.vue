<template>
   <div class="big_div">
        {{ date }}
        {{ timer }}
   </div>
</template>
<script setup lang='ts'>
    import { ref,reactive,onBeforeMount } from 'vue'
    import datetime from '@/http/config/DateTime'
    
    // 进入页面直接运行的方法
    onBeforeMount(()=>{
        RefreshDate()
    })
    
    let date = ref(datetime(new Date(),'YYYY-mm-dd HH:MM:SS'))
    function showTimes(){
        setInterval(showTimes,1000)
            date = ref(datetime(new Date(),'YYYY-mm-dd HH:MM:SS'))
    }

    function clear(){
        clearInterval(showTimes);
    }
</script>
<style lang='scss' scoped>
</style>