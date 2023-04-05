<template>
    <div>
        <el-row>
            <el-col>
                <Collapse></Collapse>
            </el-col>
        </el-row>
        <el-col>
            <el-button @click="toOut()">退出登录</el-button>
        </el-col>
    </div>
</template>
<script lang="ts" setup>
    import Collapse from './Collapse.vue'
    import { useStore } from "@/store/index"
	import { useRouter } from 'vue-router'
	import axios from "axios"
	import { ElMessage } from 'element-plus'
    	
	const store = useStore().state
	const router = useRouter()

	// 退出登录
	const toOut = () => {
		axios.post('api/jwt/loginout?said='+store.userInfo.id+"&saname="+store.userInfo.username,{
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