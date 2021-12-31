
package com.generator.annotation;  
  
import java.lang.annotation.*;

import org.springframework.stereotype.Component;  
  

  
/**
* @ClassName: AdminControllerLog
* @Description: 自定义注解 拦截Controller 
* @author zhuzq
* @date 2020年5月14日 下午9:26:29
*/
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface AdminControllerLog {  
  
    String description()  default "";  
  
  
}