
package com.generator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
* @ClassName: AdminServiceLog
* @Description: 自定义注解 拦截service
* @author zhuzq
* @date 2020年5月14日 下午9:26:11
*/
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminServiceLog {

	String description() default "";

}