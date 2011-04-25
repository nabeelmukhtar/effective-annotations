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

import static com.google.code.annotation.pattern.design.creational.AbstractFactory.Role.ABSTRACT_FACTORY;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.code.annotation.meta.Constraint;
import com.google.code.annotation.meta.TargetDesignator;

/**
 * @see <a href="http://c2.com/cgi/wiki?AbstractFactoryPattern">Abstract Factory</a>
 * 
 * @author Nabeel Mukhtar
 */
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
@TargetDesignator( {
		@Constraint(role = "ABSTRACT_FACTORY", condition = "interface(*)", bind = "AF"),
		@Constraint(role = "CONCRETE_FACTORY", condition = "class(!abstract *)", bind = "CF"),
		@Constraint(role = "CREATE_METHOD", condition = "method(* bind $AP *())", bind = "CM"),
		@Constraint(role = "ABSTRACT_PRODUCT", condition = "interface(*)", bind = "AP"),
		@Constraint(role = "CONCRETE_PRODUCT", condition = "class(!abstract *)", bind = "CP"),
		@Constraint(condition = "implementation($CF, $AF)"),
		@Constraint(condition = "parent($CM, $AF)"),
		@Constraint(condition = "parent($CM, $CF)"),
		@Constraint(condition = "implementation($CP, $AP)") })
public @interface AbstractFactory {
	String id() default "";

	Role[] roles() default ABSTRACT_FACTORY;

	Class<?>[] collaborators() default {};

	public enum Role {
		ABSTRACT_FACTORY, CONCRETE_FACTORY, ABSTRACT_PRODUCT, CONCRETE_PRODUCT, CREATE_METHOD;
	}
}
