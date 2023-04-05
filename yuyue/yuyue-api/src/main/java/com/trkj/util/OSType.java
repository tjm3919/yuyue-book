package com.trkj.util;

import cn.hutool.core.collection.CollUtil;
import java.util.List;

/**
 * 操作系统类型
 */
public class OSType {

    public static final String Windows = "Windows";
    public static final String Linux = "Linux";
    /**
     * OSX(Mac OSX)是美国苹果公司于2013年公布的OS X操作系统
     */
    public static final String OSX = "OSX";
    public static final String Mac = "Mac";
    public static final String Unix = "Unix";
    public static final String Android = "Android";
    /**
     * Wii是任天堂公司2006年11月19日推出的家用游戏机
     */
    public static final String Wii = "Wii";
    public static final String PS3 = "PS3";
    public static final String PSP = "PSP";
    public static final String iPod = "iPod";
    public static final String iPad = "iPad";
    public static final String iPhone = "iPhone";
    public static final String YPod = "YPod";
    public static final String YPad = "YPad";
    public static final String YPhone = "YPhone";
    public static final String Symbian = "Symbian";
    public static final String Darwin = "Darwin";
    public static final String Adobe_Air = "Adobe Air";
    public static final String Java = "Java";
    public static final String GoogleTV = "GoogleTV";
    public static final String Windows_Phone = "Windows Phone";

    /**
     * Linux系统，主要分debian系和redhat系，还有其它自由的发布版本。
     */
    public static final List<String> linuxs = CollUtil.newArrayList("Debian","Ubuntu","Mint","RedHat","Fedora","CentOs","Slackware","Gentoo","Arch linux","LFS","SUSE");

    /**
     * Windows系统，主要的发布版本。
     */
    public static final List<String> windows = CollUtil.newArrayList("Windows 11","Windows 10","Windows 8.1","Windows 8","Windows 7","Windows 2003","Windows 2000","Windows 98","Windows Vista","Windows XP","Windows Server 2008R2","Windows Server 2012","Windows Server 2012R2","Windows Server 2016");

    /**
     * 移动平台类型
     */
    public static final List<String> mobiles = CollUtil.newArrayList(Android,Windows_Phone,iPod,iPad,iPhone,GoogleTV,"htcFlyer","Symbian","Blackberry");

    /**
     * 客户端系统是否为Windows --> 系统为Windows或主要的发布版本
     * @param os Windows
     * @return
     */
    public static boolean isWindows(String os){
        return (OSType.Windows.equals(os) || windows.contains(os)) ? true : false;
    }

    /**
     * 客户端系统是否为Linux --> 系统为Linux或各发布版本
     * debian系:Debian,Ubuntu,Mint,
     * redhat系:RedHat,Fedora,CentOs
     * 其它自由的发布版本:Slackware,Gentoo,Arch linux,LFS,SUSE
     * @param os Linux
     * @return
     */
    public static boolean isLinux(String os){
        return (OSType.Linux.equals(os) || linuxs.contains(os)) ? true : false;
    }

}
