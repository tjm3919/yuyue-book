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
		}, 1000)
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
		}, 500)
	}
	
	const ruleForm = reactive({
	  account: 'admin',
	  password: '123456'
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
		console.log("V:",ruleForm);
		
	}



	const toLogin = () => {}
	
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