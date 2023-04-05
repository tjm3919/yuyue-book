/**
 * 转换时间格式 
 * @param datetime 要转换的时间
 * @param type 转换的时间类型 默认
 * @returns 
 */
const timeFormatter = (datetime:any,type:String) => {
    if(type==null){type='YYYY-mm-dd HH:MM:SS'}
    let format = type;
    let date = new Date(datetime);
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