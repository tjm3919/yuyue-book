<template>
   <div class="big_div">
        {{ datetime(date,'YYYY-mm-dd HH:MM:SS') }}
   </div>
</template>
<script setup lang='ts'>
    import { ref,reactive } from 'vue'
    import datetime from '@/http/config/DateTime'
    
   let date = ref(new Date())

</script>
<style lang='scss' scoped>
</style>