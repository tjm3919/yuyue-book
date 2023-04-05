<template>
    <div>
        <el-row :gutter="20" justify="space-between">
            <el-col :span="4">
                <Collapse></Collapse>
            </el-col>
            <el-col :span="5" class="down">
                <span>欢迎<span class="usernameColor">{{ user.username }}</span>使用愉悦图书管理系统</span>
            </el-col>
            <el-col :span="2">
                <el-button @click="toOut()" class="out down">退出登录</el-button>
            </el-col>
        </el-row>
    </div>
</template>
<script lang="ts" setup>
	import { reactive, ref } from 'vue'
    import Collapse from './Collapse.vue'
    import { useStore } from "@/store/index"
	import { useRouter } from 'vue-router'
	import axios from "axios"
	import { ElMessage } from 'element-plus'
    	
	const store = useStore().state
	const router = useRouter()

    const user = reactive(store.userInfo)

	// 退出登录
	const toOut = () => {
		axios.post('api/jwt/loginout?said='+store.userInfo.id+"&saname="+store.userInfo.username,{
            headers: { 'content-type': 'application/json' }
        })
        .then(function(res){
			if(res.data.code==200 && res.data.success && res.data.data){
                router.push('/login');
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
.down{
    margin-top: 10px;
}
.usernameColor{
    color: red;
}
</style>