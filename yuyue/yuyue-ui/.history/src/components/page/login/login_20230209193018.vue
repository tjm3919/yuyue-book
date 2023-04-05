<template>
	<div class="big_div">
		<img src="@/image/img/钟馗.png" class="bg"/>
		<div class="login_div">
			<!-- 注册 -->
			<el-form ref="ruleFormRef" :model="ruleForm" status-icon :rules="rules" label-width="120px" class="demo-ruleForm" 
					:hide-required-asterisk=true v-if="show==1" >
				<el-form-item label="账号:" prop="saName">
					<el-input v-model="ruleForm.saName" type="text" autocomplete="off" />
				</el-form-item>
				<el-form-item label="密码:" prop="saPassword">
					<el-input v-model="ruleForm.saPassword" type="password" autocomplete="off" />
				</el-form-item>
				<el-form-item>
					<el-row :gutter="40" justify="center">
						<el-col :span="10" :offset="6">
							<el-button type="primary" @click="submitForm(ruleFormRef)">登录</el-button>
						</el-col>
						<el-col :span="8"> 
							<el-button @click="show=2">注册</el-button>
						</el-col>
					</el-row>
				</el-form-item>
			</el-form>
			<!-- 注册 -->
			<el-form ref="ruleFormRef2" :model="Register" status-icon :rules="rules" label-width="120px" class="demo-ruleForm" 
				:hide-required-asterisk=true v-if="show==2" >
				<el-form-item label="账号:" prop="saName">
					<el-input v-model="Register.saName" type="text" autocomplete="off" />
				</el-form-item>
				<el-form-item label="密码:" prop="saPassword">
					<el-input v-model="Register.saPassword" type="password" autocomplete="off" />
				</el-form-item>
				<!-- <el-form-item label="确认密码:" prop="saPassword2">
					<el-input v-model="pass2" type="password" autocomplete="off" />
				</el-form-item> -->
				<el-form-item label="用户名:" prop="suName">
					<el-input v-model="Register.sysUserDto.suName" type="text" autocomplete="off" />
				</el-form-item>
				<!-- <el-form-item label="用户类型:" prop="suType">
					<el-radio-group v-model="Register.sysUserDto.suType" class="ml-4">
						<el-radio style="margin-top:-5px;" label="读者" size="large">读者</el-radio>
						<el-radio style="margin-top:-5px;" label="作者" size="large">作者</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="昵称:" prop="suNickname">
					<el-input v-model="Register.sysUserDto.suNickname" type="text" autocomplete="off" />
				</el-form-item>
				<el-form-item label="电话:" prop="suPhone">
					<el-input v-model="Register.sysUserDto.suPhone" type="text" maxlength="11" autocomplete="off" />
				</el-form-item> -->
				<el-form-item>
					<el-col :span="24">
						<div>
							<el-button style="width: 100px;" type="success" plain @click="resetForm(ruleFormRef2)">注册</el-button>
							<el-button style="width: 100px;" @click="show=1">返回</el-button>
						</div>
					</el-col>
				</el-form-item>
	  		</el-form>
		</div>
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

	// 1-登录 2-注册
	let show = ref(1) 

    const ruleFormRef = ref<FormInstance>()
	
	// 验证账号
	const validateAccount = (rule: any, value: any, callback: any) => {
		console.log(value)
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

	// 验证密码1
	const validatePassword = (rule: any, value: any, callback: any) => {
		if (!value) {
			return callback(new Error('请输入密码'))
		}
		setTimeout(() => {
			if (value.length<2 || value.length>20) {
				callback(new Error('密码长度在2-20之间'))
			}else{
				callback()
			}
		}, 100)
	}

	// 登录
	const ruleForm = reactive({
		"browser": "", // 浏览器
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
 
	// 注册
	const Register= reactive({
		"saName": "",
		"saPassword": "",
		"saType": 2,  // 账号类型 1-管理员账号 2-用户账号 3-作者账号
		"saState": 1,
		"isExpired": 1,
		"suId": 0,
		"sysUserDto": {
			"suName": "", // 用户名
			"sumNum": 10, // 注册赠送的积分
			"suNickname": "", // 昵称
			"suPhone": "", // 电话
			"suType": "读者", // 用户类型 读者 作者
			"suState": 1,
			"saId": 0
		}	
	})
	
    const ruleFormRef2 = ref<FormInstance>()
    
	// 验证密码2
	const pass2 = ref("")
	const validatePassword2 = (rule: any, value: any, callback: any) => {
		if (!value) {
			return callback(new Error('请输入验证密码'))
		}
		setTimeout(() => {
			if(pass2.value!=Register.saPassword) {
				callback(new Error('密码验证错误'))
			}else{
				callback()
			}
		}, 100)
	}

	// 验证用户名
	const validatesuName = (rule: any, value: any, callback: any) => {
		console.log(value)
		if (!value) {
			return callback(new Error('请输入用户名'))
		}
		setTimeout(() => {
			if (value.length<2 || value.length>20) {
				callback(new Error('用户名长度在2-20之间'))
			}else{
				callback()
			}
		}, 100)
	}

	// 修改用户类型
	const validatesuType = (rule: any, value: any, callback: any) => {
		console.log(value)
		setTimeout(() => {
			
		}, 100)
	}

	// 验证昵称
	const validatesuNickname = (rule: any, value: any, callback: any) => {
		if (!value) {
			return callback(new Error('请输入昵称'))
		}
		setTimeout(() => {
			if (value.length<2 || value.length>20) {
				callback(new Error('昵称长度在2-20之间'))
			}else{
				callback()
			}
		}, 100)
	}

	// 验证电话格式
	const validatesuPhone = (rule: any, value: any, callback: any) => {
		if (!value) {
			return callback(new Error('请输入电话'))
		}
		setTimeout(() => {
			const pat_phone = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
			if (!pat_phone.test(value)) {
				callback(new Error('电话格式错误'))
			}else{
				callback()
			}
		}, 100)
	}

	
	// 验证规则
	const rules = reactive({
		saName: [{ required: true,validator: validateAccount, trigger: 'blur' }],
		saPassword: [{ required: true,validator: validatePassword,trigger: 'blur' },
    		// { min: 6, max: 20, message: '密码长度在6-20之间', trigger: 'blur' },
		],
		saPassword2: [{ required: true,validator: validatePassword2,trigger: 'blur' }],
		suName: [{ required: true,validator: validatesuName,trigger: 'blur' }],
		suType: [{ required: true,validator: validatesuType,trigger: 'change' }],
		suNickname: [{required: true,validator:validatesuNickname,trigger: 'blur'}],
		suPhone: [{required: true,validator:validatesuPhone,trigger: 'blur'}],
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

	// 登录
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
			// console.log("Res:",res)
			interface userIn{
				id:number,
				username:string,
				usertype:string,
				token:string
			}
			let user = reactive(<userIn>{})
			if(res.status==200 && res.data.data!=null){
				const v = res.data.data;
				user = {
					id: v.saId,
					username: v.saName,	
					usertype: v.saType,
					token: v.token
				}
				store.commit('setUserInfo',user)
				// 跳转到首页
				router.push('/');
			}else if(res.data.message=='密码错误'){
				ElMessage.error('密码错误!')
			}else if(res.data.code==500 && res.data.message!=null){
				// 刷新token
				store.commit('setUserToken',user.token=res.data.message)
				// 跳转到首页
				router.push('/');
			}else{
				ElMessage.error('登录失败!')
			}
        }).catch(function(error){
            ElMessage.error('登录失败:',error)
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

	// 注册
	const toRegister = () => {
        axios.post('api/jwt/add',qs.parse(ruleForm),{
            headers: { 'content-type': 'application/json' }
        })
        .then(function(res){
			console.log("Res:",res)
			if(res.status==200 && res.data.data!=null){
				
				
			}else{
				ElMessage.error('注册失败!')
			}
        }).catch(function(error){
            ElMessage.error('注册失败:',error)
        })
	}

	const resetForm = (formEl: FormInstance | undefined) => {
		if (!formEl) return
	  	formEl.validate((valid) => {
			if (valid) { // 开始注册
				toRegister()
			} else { // 注册失败
				console.log('注册失败!')
				return false
			}
		})
	}
</script>

<style lang="scss" scoped>
	.big_div{
		width: 100%;
		height: 100%;
		position: relative;
		.bg{
			width: 100%;
			height: 100%;
			top:0px;
			z-index: 1;
		}
		.login_div{
			position: absolute;
			top: 0px;
			left: 0px;
			width: 400px;
			padding: 0px;
		}
		// border: 1px solid red;
	}

</style>