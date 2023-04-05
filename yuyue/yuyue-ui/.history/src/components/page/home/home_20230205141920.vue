<!--
 * @Author: tjm3919 2478525770@qq.com
 * @Date: 2023-02-04 13:45:48
 * @LastEditors: tjm3919 2478525770@qq.com
 * @LastEditTime: 2023-02-05 14:19:20
 * @FilePath: \yuyue-ui\src\components\page\home\home.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
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
      console.log("User:",user)
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