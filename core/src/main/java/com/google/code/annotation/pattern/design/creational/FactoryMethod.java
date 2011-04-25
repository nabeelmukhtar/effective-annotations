/*
 * Copyright 2010 Nabeel Mukhtar 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 */
package com.google.code.annotation.pattern.design.creational;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.code.annotation.meta.Constraint;
import com.google.code.annotation.meta.TargetDesignator;

/**
 * @see <a href="http://c2.com/cgi/wiki?FactoryMethodPattern">Factory Method</a>
 * 
 * @author Nabeel Mukhtar
 */
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
@TargetDesignator( {
		@Constraint(role = "CREATOR", condition = "interface(*)", bind = "C"),
		@Constraint(role = "CONCRETE_CREATOR", condition = "class(!abstract *)", bind = "CC"),
		@Constraint(role = "FACTORY_METHOD", condition = "method(* bind $P *())", bind = "FM"),
		@Constraint(role = "PRODUCT", condition = "interface(*)", bind = "P"),
		@Constraint(role = "CONCRETE_PRODUCT", condition = "class(!abstract *)", bind = "CP"),
		@Constraint(condition = "implementation($CC, $C)"),
		@Constraint(condition = "parent($FM, $C)"),
		@Constraint(condition = "parent($FM, $CC)"),
		@Constraint(condition = "implementation($CP, $P)") })
public @interface FactoryMethod {
	String id() default "";

	Role[] roles();

	Class<?>[] collaborators() default {};

	public enum Role {
		PRODUCT, CONCRETE_PRODUCT, CREATOR, CONCRETE_CREATOR, FACTORY_METHOD;
	}
}
