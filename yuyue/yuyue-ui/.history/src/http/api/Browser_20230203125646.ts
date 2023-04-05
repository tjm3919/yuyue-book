/*
 * @Author: tjm3919 2478525770@qq.com
 * @Date: 2023-02-03 12:56:23
 * @LastEditors: tjm3919 2478525770@qq.com
 * @LastEditTime: 2023-02-03 12:56:43
 * @FilePath: \yuyue\src\http\api\Browser.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
/**
 * @returns 获取当前登录的浏览器
 */
export function getLogoinBrowser(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    // console.log("loginuserAgent:", userAgent)
    //判断是否Opera浏览器
    if (userAgent.indexOf("Opera") > -1) {
      return "Opera"
    };
   
    //判断是否Edge浏览器
    if (userAgent.indexOf("Edg") > -1){
      return 'Edge'
    }
   
    //判断是否Firefox浏览器
    if (userAgent.indexOf("Firefox") > -1) {
      return "firefox";
    }
   
    //判断是否Chrome浏览器
    if (userAgent.indexOf("Chrome") > -1){
      return "Chrome";
    }
   
    //判断是否Safari浏览器
    if (userAgent.indexOf("Safari") > -1) {
      return "Safari";
    }
   
    //判断是否IE浏览器
    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
      return "IE";
    }
    if ( userAgent.indexOf("Trident") > -1){
      return "IE";
    }
  }