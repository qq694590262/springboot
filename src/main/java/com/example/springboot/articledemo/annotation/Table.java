package com.example.springboot.articledemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // ע���Ŀ������
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	public String tableName();
}