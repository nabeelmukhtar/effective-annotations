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
package com.google.code.annotation.pattern.design.structural;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.code.annotation.meta.Constraint;
import com.google.code.annotation.meta.TargetDesignator;

/**
 * @see <a href="http://c2.com/cgi/wiki?IteratorPattern">Iterator</a>
 * 
 * @author Nabeel Mukhtar
 */
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
@TargetDesignator( {
		@Constraint(role = "ITERATOR", condition = "interface(*)", bind = "I"),
		@Constraint(role = "CONCRETE_ITERATOR", condition = "class(!abstract *)", bind = "CI"),
		@Constraint(role = "AGGREGATE", condition = "interface(*)", bind = "A"),
		@Constraint(role = "CONCRETE_AGGREGATE", condition = "class(!abstract *)", bind = "CA"),
		@Constraint(role = "CREATE_ITERATOR_METHOD", condition = "method(* bind $I *())", bind = "CIM"),
		@Constraint(condition = "implementation($CI, $I)"),
		@Constraint(condition = "implementation($CA, $A)"),
		@Constraint(condition = "parent($CIM, $A)"),
		@Constraint(condition = "parent($CIM, $CA)") })
public @interface Iterator {
	String id() default "";

	Role[] roles();

	Class<?>[] collaborators() default {};

	public enum Role {
		ITERATOR, CONCRETE_ITERATOR, AGGREGATE, CONCRETE_AGGREGATE, CREATE_ITERATOR_METHOD;
	}
}
