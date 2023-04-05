<template>
   <img src="@/image/logo/愉悦.png" class="logo"/>

</template>
<script setup lang='ts'>
   import { ref,reactive,onBeforeMount } from 'vue'
   import { useStore } from '@/store/index' 
	import { useRouter } from 'vue-router'

   onBeforeMount(()=>{
      islogin()
   })

   const store = useStore();
	const router = useRouter()
   const islogin = () =>{
      const user = store.state.userInfo;
      if(user.id<=0 || user.token==""){
         // 跳转到登录页面
		   router.push('/login');
      }
   }

</script>
<style lang='scss' scoped>
.logo{
   width: 460px;
   height: 400px;
}
</style>