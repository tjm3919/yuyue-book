// store.ts
import { InjectionKey } from 'vue'
import { createStore, useStore as baseUseStore, Store } from 'vuex'

// import {defineStore} from 'pinia';
// import {createPinia} from 'pinia';
// // 引入pinia的持久化存储插件
// import piniaPersist from 'pinia-plugin-persist';

export interface State {
  id: string,
  count: number,
  collapse: boolean,
  userInfo: {
    id:number,
    username: string,
    isValidate: boolean,
    headurl: string,
    token: string
  }
}

export const key: InjectionKey<Store<State>> = Symbol()

export const store = createStore<State>({
  state: {
    id: 'GlobalState',
    count: 0,
    collapse: false,
    userInfo: {
      id: 0,
      username: "",
      isValidate:false,
      headurl:'',
      token:""
    }
  },
  // actions即可以是同步函数也可以是异步函数
  actions: {
    setUserInfo(user:any) {
      if(user==null){
				localStorage.removeItem('GlobalState')
				console.log("清空用户信息")
        this.state.userInfo.id=0
				this.state.userInfo.username=""
				this.state.userInfo.isValidate=false
				this.state.userInfo.token=""
        this.state.userInfo.headurl=""
			}else{
				console.log("更新用户状态.......用户名:",user)
        this.state.userInfo.id=user.id
				this.state.userInfo.username=user.username
				this.state.userInfo.isValidate=true
				this.state.userInfo.token=user.token
        this.state.userInfo.headurl=user.headurl
				console.log("更新头像*****..用户信息:%o",user.headurl)
        //console.log(JSON.stringify(this),"======================")
				sessionStorage.setItem('GlobalState', JSON.stringify(this))
			}
    }
  },
  mutations:{
    setUserInfo(state:State ,user:any) {
      if(user==null){
				localStorage.removeItem('GlobalState')
				console.log("清空用户信息")
        state.userInfo.id=0
				state.userInfo.username=""
				state.userInfo.isValidate=false
				state.userInfo.token=""
        state.userInfo.headurl=""
			}else{
				console.log("更新用户状态.......用户名:",user)
        state.userInfo.id=user.id
				state.userInfo.username=user.username
				state.userInfo.token=user.token
				state.userInfo.isValidate=true
        state.userInfo.headurl=user.headurl
				console.log("更新头像*****..用户信息:%o",user.headurl)
        //console.log(JSON.stringify(this),"======================")
				sessionStorage.setItem('GlobalState', JSON.stringify(this))
			}
    },
    setCount(state:State, count:number){
      state.count = count;
    },
    setCollapse:(state:State,collapse:boolean)=>{
      state.collapse = collapse;
    },
  },
  getters:{
    getCount(state:State){
      return state.count;
    },
    getCollapse:(state:State)=>{
      return state.collapse;
    }
  },
  // 持久化
  // persist: {
  //   enabled: true,
  //   strategies: [
  //     {
  //       // 自定义key
  //       key: 'GlobalState',
  //       // 自定义存储方式，默认sessionStorage
  //       //storage: localStorage,
  //       // 指定要持久化的数据，默认所有 state 都会进行缓存，可以通过 paths 指定要持久化的字段，其他的则不会进行持久化。
  //       //paths: ['curUsername'],
  //     }
  //   ]
  // }
})

// 定义自己的 `useStore` 组合式函数
export function useStore () {
  return baseUseStore(key)
}