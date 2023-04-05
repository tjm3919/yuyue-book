<template>
    <div>
        <Collapse></Collapse>
        <el-button @click="toOut()">退出登录</el-button>
    </div>
</template>
<script lang="ts" setup>
    import Collapse from './Collapse.vue'
    import { useStore } from "@/store/index"
	import { useRouter } from 'vue-router'
    	
	const store = useStore()
	const router = useRouter()

	// 退出登录
	const toOut = () => {
		axios.post('api/jwt/loginout?said='+st+"&saname="+ruleForm.saName,qs.parse(ruleForm),{
            headers: { 'content-type': 'application/json' }
        })
        .then(function(res){
			if(res.data.code==200 && res.data.success && res.data.data){
				ElMessage({
					message: '退出成功',
					type: 'success',
				})
			}else{
				ElMessage.error('退出失败！')
			}
		}).catch(function(error){
            ElMessage.error('退出失败:',error)
        })
	}
</script>
<style lang="scss" scoped>
</style>