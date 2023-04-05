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
	  password: '123456',
	  browser: '' // 浏览器
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

		axios.post(http + "/login", ruleForm)
        .then(function (res) {
            //console.log(ruleForm, '登录密码')
            //登录验证
            //console.log("后台拿到的登录数据=%o", res)
            // if (res.data.data == '验证码错误') {
            //     ElMessage.error('验证码错误！')
            //     return
            // }
            // if (res.data.data == "用户名不存在") {
            //     ElMessage.error('用户名不存在')
            //     return

            // }
            // if (res.data.data == undefined) {
            //     ElMessage.error('账号密码错误')
            //     rizhi.login_letter='账号密码错误'
            //     rizhi.status=1
            //     rizhi.browser=browser
            //     rizhi.username=ruleForm.username
            //     loginlog()
            //     return
            // }
            store.updateUserInfo(res.data.data)

            //把数据存到 pinia
            var state=res.data.data
            ///console.log(res.data.data.userInfo.menus,'')
            //console.log(sessionStorage.getItem('GlobalState'), '---------登录sessionStorage-----------')
            //console.log(store.userInfo.menus, '---------登录pinia-----------')
            sessionStorage.setItem("refresh", "true")
            // for (var i = 0; i < state.menus.length; i++) {
            //         let comp = '../components/' + state.menus[i].component_path
            //         const rou : any = {
            //             path: state.menus[i].url,
            //             name: state.menus[i].component_name,
            //             component: modules[`${comp}`],
            //             children: []
            //         }
            //         //rou.redirect='/home'
            //         // console.log(rou,'游戏')
            //         let chm = state.menus[i].children
            //         for (var k = 0; k < chm.length; k++) {
            //             let cpath = '../components/' + chm[k].component_path
            //             const rouc : any = {
            //                 path: chm[k].url,
            //                 name: chm[k].component_name,
            //                 component: modules[`${cpath}`]
            //             }
            //             rou.children.push(rouc)
            //         }
            //         router.addRoute(rou)
            //     }
                
            //     getsdf()         
			// 	//console.log("-----------44466666-------")
			// 	router.push(state.menus[0].children[0].url)

        })
        .catch(function (err) {
            //console.log(JSON.parse( err.request.responseText))
			ElMessage.error({
				message: "账号异常或权限不足",
				type: 'success'
			});
        })
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