package com.trkj.yuyuejwt.aop;

import java.lang.annotation.*;

@Documented // jdk生成API文档
@Inherited  // 如果一个类用上了@Inherited修饰的注解，那么其子类也会继承这个注解
@Target(ElementType.METHOD) // 可以加在哪个方法上面
@Retention(RetentionPolicy.RUNTIME) // 保留这个注解在运行的时候
public @interface LoginLog {

    /**
     * 是否保存当前用户参数或参数的对象
     * 如果是true就保存到参数对象中
     * @return
     */
    boolean saveParamObjecte() default true;

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
