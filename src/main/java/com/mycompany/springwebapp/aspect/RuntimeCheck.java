package com.mycompany.springwebapp.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//runtime:실행할떄만 쓸거야~
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RuntimeCheck {
	
	
}
