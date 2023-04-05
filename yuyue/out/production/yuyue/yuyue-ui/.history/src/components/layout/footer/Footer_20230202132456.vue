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
        RefreshDate()
        timer
    })
    
    let date = ref(datetime(new Date(),'YYYY-mm-dd HH:MM:SS'))
    function RefreshDate(){
        setInterval(function(){
            date = ref(datetime(new Date(),'YYYY-mm-dd HH:MM:SS'))
        },1000)
    }

    let timer = setInterval(() => {
        let timeDate = new Date();
        let year = timeDate.getFullYear();
        let mounth = timeDate.getMonth() + 1;
        let day = timeDate.getDate();
        let hours = timeDate.getHours();
        hours = hours >= 10 ? hours : "0" + hours;
        let minutes = timeDate.getMinutes();
        minutes = minutes >= 10 ? minutes : "0" + minutes;
        let seconds = timeDate.getSeconds();
        seconds = seconds >= 10 ? seconds : "0" + seconds;
        let week = timeDate.getDay();
        let weekArr = [
            "星期日",
            "星期一",
            "星期二",
            "星期三",
            "星期四",
            "星期五",
            "星期六",
        ];

        let nowTime = `${year}/${mounth}/${day} ${hours}:${minutes}:${seconds} ${weekArr[week]}`
    }, 1000);
</script>
<style lang='scss' scoped>
</style>