
/**
 * 转换时间格式
 * @param row 
 * @param column 
 * @param cellValue 
 * @param index 
 * @returns 主用于 el-table > el-table-column 中时间的转换
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