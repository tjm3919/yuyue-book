/*
 * @Author: tjm3919 2478525770@qq.com
 * @Date: 2023-02-02 13:09:46
 * @LastEditors: tjm3919 2478525770@qq.com
 * @LastEditTime: 2023-02-02 17:47:04
 * @FilePath: \yuyue\src\http\config\Dates.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */

/**
 * 转换时间格式
 * @param row 
 * @param column 
 * @param cellValue 
 * @param index 
 * @returns 
 */
const timeFormatter = (row:any, column:any, cellValue:any, index:any) => {
    // YYYY-mm-dd HH:MM:SS
    let format = 'YYYY-mm-dd'
    let date = new Date(cellValue);
    const dataItem = {
        'Y+': date.getFullYear().toString(),
        'm+': (date.getMonth() + 1).toString(),
        'd+': date.getDate().toString(),
        'H+': date.getHours().toString(),
        'M+': date.getMinutes().toString(),
        'S+': date.getSeconds().toString(),
    };
    Object.keys(dataItem).forEach((item) => {
        const ret = new RegExp(`(${item})`).exec(format);
        if (ret) {
            format = format.replace(ret[1], ret[1].length === 1 ? dataItem[item] : dataItem[item].padStart(ret[1].length, '0'));
        }
    });
    return format
}

export default timeFormatter