// store.ts
import { InjectionKey } from 'vue'
import { createStore, useStore as baseUseStore, Store } from 'vuex'

import { defineStore, createPinia } from 'pinia';
// 引入pinia的持久化存储插件
import piniaPersist from 'pinia-plugin-persist';

export interface GlobalState {
  id: string,
  count: number,
  collapse: boolean,
  userInfo: {
    id:number,
    username: string,
    isValidate: boolean,
    usertype: number, // 用户类型 1-管理员 2-普通用户
    headurl: string,
    token: string 
  }
}

// export const key: InjectionKey<Store<State>> = Symbol()

export const GlobalState = defineStore('GlobalState',{
  state: () : GlobalState => ({
    token: 1, // token信息
    isCollapse: false, // 收缩菜单
    active: false , // 全屏激活
    main: null ,
    qx:[],
    userInfo: {
      id: 0,
      username: '',
      isValidate: false,
      headurl: '',
      token: '',
      menus: []
    }
  }),
  actions: {
    increment(){
      this.count++
    }
  }
})


// export const store = createStore<State>({
//   state: {
//     id: 'GlobalState',
//     count: 0,
//     collapse: false,
//     userInfo: {
//       id: 0,
//       username: "",
//       isValidate:false,
//       usertype: 2,
//       token:""
//     }
//   },
//   // actions即可以是同步函数也可以是异步函数
//   actions: {
//     setUserInfo(user:any) {
//       let v = this.state.userInfo;
//       if(user==null){
// 				localStorage.removeItem('GlobalState')
// 				console.log("清空用户信息")
//         v.id=0
// 				v.username=""
// 				v.isValidate=false
//         v.usertype=2
// 				v.token=""
// 			}else{
// 				console.log("更新用户状态.......用户名:",user)
//         v.id=user.id
// 				v.username=user.username
// 				v.isValidate=true
//         v.usertype=user.usertype
// 				v.token=user.token
//         //console.log(JSON.stringify(this),"======================")
// 				sessionStorage.setItem('GlobalState', JSON.stringify(this))
// 			}
//     }
//   },
//   mutations:{
//     setUserInfo(state:State ,user:any) { // 修改用户信息
//       let v = state.userInfo
//       if(user==null){
// 				localStorage.removeItem('GlobalState')
// 				console.log("清空用户信息")
//         v.id=0
// 				v.username=""
// 				v.isValidate=false
//         v.usertype=2
// 				v.token=""
// 			}else{
// 				console.log("更新用户状态.......用户名:",user)
//         v.id=user.id
// 				v.username=user.username
// 				v.token=user.token
// 				v.isValidate=true
//         v.usertype=user.usertype
//         //console.log(JSON.stringify(this),"======================")
// 				sessionStorage.setItem('GlobalState', JSON.stringify(this))
// 			}
//     },
//     setUserToken(state:State ,token:string) { // 刷新用户token
//       if(token==null || token==''){
//         state.userInfo.token=''
//       }else{
//         state.userInfo.token=token
//       }
//     },
//     setCount(state:State, count:number){
//       state.count = count;
//     },
//     setCollapse:(state:State,collapse:boolean)=>{
//       state.collapse = collapse;
//     },
//   },
//   getters:{
//     getCount(state:State){
//       return state.count;
//     },
//     getCollapse:(state:State)=>{
//       return state.collapse;
//     },
//     getUserInfo:(state:State)=>{
//       return state.userInfo;
//     }
//   },
//   // 持久化
//   // persist: {
//   //   enabled: true,
//   //   strategies: [
//   //     {
//   //       // 自定义key
//   //       key: 'GlobalState',
//   //       // 自定义存储方式，默认sessionStorage
//   //       //storage: localStorage,
//   //       // 指定要持久化的数据，默认所有 state 都会进行缓存，可以通过 paths 指定要持久化的字段，其他的则不会进行持久化。
//   //       //paths: ['curUsername'],
//   //     }
//   //   ]
//   // }
// })

// 定义自己的 `useStore` 组合式函数
export function useStore () {
  return baseUseStore(key)
}