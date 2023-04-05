<template>
   <div class="big_div">
        {{ date }}
        <button onclick="showTimes()">开始</button>
   </div>
</template>
<script setup lang='ts'>
    import { ref,reactive,onBeforeMount } from 'vue'
    import datetime from '@/http/config/DateTime'
    
    // 进入页面直接运行的方法
    onBeforeMount(()=>{
        getdate()
    })
    
    let date = ref(new Date())
    function showTimes(){
        date = new Date()
    }

    function getdate(){
        setInterval(showTimes,1000)
    }
    
    let t=setInterval(showTimes,1000)

    function clear(){
        clearInterval(t);
    }
</script>
<style lang='scss' scoped>
</style>