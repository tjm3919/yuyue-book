<template>
	<!-- <img src="./zhongkui.png" class="bg"/> -->
	<div class="big_div">
		<el-form ref="ruleFormRef" :model="ruleForm" status-icon :rules="rules" label-width="120px" class="demo-ruleForm" 
			:hide-required-asterisk=true>
			<el-form-item label="账号:" prop="account">
				<el-input v-model="ruleForm.account" type="text" autocomplete="off" />
			</el-form-item>
			<el-form-item label="密码:" prop="password">
				<el-input v-model="ruleForm.password" type="password" autocomplete="off" />
			</el-form-item>
			<el-form-item>
				<el-row :gutter="40" justify="center">
					<el-col :span="10">
						<el-button type="primary" @click="submitForm(ruleFormRef)">登录</el-button>
					</el-col>
					<el-col :span="10"> 
						<el-button @click="resetForm(ruleFormRef)">注册</el-button>
					</el-col>
				</el-row>
			</el-form-item>
	  </el-form>
	</div>
</template>

<script lang="ts" setup>
	import { reactive, ref } from 'vue'
	import type { FormInstance } from 'element-plus'
	import { getLogoinBrowser } from '@/http/api/Browser'
	import { ElMessage } from 'element-plus'
	import { useStore } from "@/store/index"
	import { useRouter } from 'vue-router'
	import http from '@/http/index'
	import axios from "axios"
	import qs from 'qs' // qs.stringify()是将对象序列化成url的形式，用&进行拼接 qs.parse()是将url解析成对象的形式
	import DateTime from '@/http/config/DateTime'
	// 引入pinia的持久化存储插件
	// npm install pinia-plugin-persist
	
	const store = useStore()
	const router = useRouter()

    const ruleFormRef = ref<FormInstance>()
	
	const validateAccount = (rule: any, value: any, callback: any) => {
		if (!value) {
			return callback(new Error('请输入账号'))
		}
		setTimeout(() => {
			if (value.length<2 || value.length>20) {
				callback(new Error('账号长度在2-20之间'))
			}else{
				callback()
			}
		}, 100)
	}

	const validatePassword = (rule: any, value: any, callback: any) => {
		if (!value) {
			return callback(new Error('请输入密码'))
		}
		setTimeout(() => {
			if (value.length<6 || value.length>20) {
				callback(new Error('密码长度在6-20之间'))
			}else{
				callback()
			}
		}, 100)
	}
	

	const ruleForm = reactive({
		account: 'admin',
		password: '123456',
		browser: '', // 浏览器
		"saName": "admin",
		"saPassword": "123456",
		"logDto": {
			"suId": 1,
			"saId": 1001,
			"llUpTime": DateTime(new Date(),'YYYY-mm-dd HH:MM:SS'),
			"llOutTime": "2023-1-25 12:12:12",
			"llIp": "登录IP",
			"llBrowserType": "浏览器类型",
			"llClientsideType": "客户端类型",
			"llLoginType": 1
		}
	})
	
	// 验证规则
	const rules = reactive({
		account: [{ required: true,validator: validateAccount, trigger: 'blur' }],
		password: [{ required: true,validator: validatePassword,trigger: 'blur' },
    		// { min: 6, max: 20, message: '密码长度在6-20之间', trigger: 'blur' },
		]
	})
	
	const submitForm = (formEl: FormInstance | undefined) => {
	  if (!formEl) return
	  formEl.validate((valid) => {
	    if (valid) { // 登录成功
			toLogin();
	    } else { // 登录失败
	      console.log('登录失败!')
	      return false
	    }
	  })
	}

	const toLogin = () => {
		// 获取浏览器类型
		const browser = getLogoinBrowser()
		ruleForm.browser=browser as any
		// axios.get(('api/jwt/queryById/1001?said=1001'),{
        //     headers: { 'content-type': 'application/json'} //store.state.userInfo.token
        // })
        // .then(function(response){
        //     console.log("response:",response)
		// 	let user = reactive({
		// 		id: 1,
		// 		username: "",
		// 		isValidate:false,
		// 		headurl:'',
		// 		token:""
		// 	})
		// 	store.commit('setUserInfo',user)
		// 	// 跳转到首页
		// 	router.push('/');
		// }).catch(function(error){
		// 	console.log(error)
		// })

        axios.post('api/jwt/validateAccount',qs.parse(ruleForm),{
            headers: { 'content-type': 'application/json' }
        })
        .then(function(res){
            console.log("response:",res)
			if(res.status==200 && res.data.data!=null){
				const v = res.data.data;
				let user = reactive({
					id: res.said,
					username: res.saName,										
					isValidate:false,
					token:res.token
				})
			}
			store.commit('setUserInfo',user)
			// 跳转到首页
			router.push('/');
        }).catch(function(error){
            console.log(error)
        })





		
		//带参数跳转
		// this.$router.push({path:'/testDemo',query:{setid:123456}});
		// this.$router.push({name:'testDemo',params:{setid:111222}});

		// axios.post(http + "/login", ruleForm)
        // .then(function (res) {
        //     // store.updateUserInfo(res.data.data)
		// 	store.commit('setUserInfo',ruleForm)
        //     //把数据存到 pinia
        //     var state=res.data.data
        //     sessionStorage.setItem("refresh", "true")
        // })
        // .catch(function (err) {
        //     //console.log(JSON.parse( err.request.responseText))
		// 	ElMessage.error({
		// 		message: "账号异常或权限不足",
		// 		type: 'success'
		// 	});
        // })
		console.log("V:",ruleForm);
		
	}

	const resetForm = (formEl: FormInstance | undefined) => {
	  if (!formEl) return
	  formEl.resetFields()
	}
</script>

<style lang="scss" scoped>
	.big_div{
		width: 400px;
		margin: 12% auto;
		padding: 0px;
	}
</style>