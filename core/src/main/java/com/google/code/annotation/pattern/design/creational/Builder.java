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
 * @see <a href="http://c2.com/cgi/wiki?BuilderPattern">Builder</a>
 * 
 * @author Nabeel Mukhtar
 */
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
@TargetDesignator( {
		@Constraint(role = "DIRECTOR", condition = "class(!abstract *)", bind = "D"),
		@Constraint(role = "BUILDER", condition = "interface(*)", bind = "B"),
		@Constraint(role = "CONCRETE_BUILDER", condition = "class(!abstract *)", bind = "CB"),
		@Constraint(role = "BUILD_PART_METHOD", condition = "method(*())", bind = "BPM"),
		@Constraint(role = "GET_RESULT_METHOD", condition = "method(!abstract * bind $P *()", bind = "GRM"),
		@Constraint(role = "PRODUCT", condition = "class(!abstract *)", bind = "P"),
		@Constraint(condition = "implementation($CB, $B)"),
		@Constraint(condition = "parent($BPM, $B)"),
		@Constraint(condition = "parent($BPM, $CB)"),
		@Constraint(condition = "parent($GRM, $CB)"),
		@Constraint(condition = "implementation($CB, $B)"),
		@Constraint(condition = "parent($B, $D)") })
public @interface Builder {
	String id() default "";

	Role[] roles();

	Class<?>[] collaborators() default {};

	/**
	 * Enum description
	 * 
	 */
	public enum Role {
		BUILDER, CONCRETE_BUILDER, DIRECTOR, PRODUCT, BUILD_PART_METHOD, GET_RESULT_METHOD;
	}
}
