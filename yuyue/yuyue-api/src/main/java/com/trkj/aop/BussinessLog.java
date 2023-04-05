package com.trkj.aop;

import java.lang.annotation.*;

/**
 * 标记需要做业务日志的方法
 *
 * @author fengshuonan
 * @date 2017-03-31 12:46
 */
@Inherited // 如果一个类用上了@Inherited修饰的注解，那么其子类也会继承这个注解
@Retention(RetentionPolicy.RUNTIME) // 当用了@Inherited修饰的注解的@Retention是RetentionPolicy.RUNTIME，则增强了继承性，在反射中可以获取得到
@Target({ElementType.METHOD})
// @Target 说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、
// 类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。
// 在Annotation类型的声明中使用了target可更加明晰其修饰的目标。
// 取值(ElementType)有：
//　　　　1.CONSTRUCTOR:用于描述构造器
//　　　　2.FIELD:用于描述域
//　　　　3.LOCAL_VARIABLE:用于描述局部变量
//　　　　4.METHOD:用于描述方法
//　　　　5.PACKAGE:用于描述包
//　　　　6.PARAMETER:用于描述参数
//　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
public @interface BussinessLog {

    /**
     * 业务的名称,例如:"修改菜单"
     */
    String value() default "";

    /**
     * 被修改的实体的唯一标识,例如:菜单实体的唯一标识为"id"
     */
    String key() default "id";

    /**
     * 操作类型,例如:Add
     */
    String type() default "SELECT";


}