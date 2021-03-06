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

import static com.google.code.annotation.pattern.design.structural.TemplateMethod.Role.TEMPLATE_METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.code.annotation.meta.Constraint;
import com.google.code.annotation.meta.TargetDesignator;

/**
 * @see <a href="http://c2.com/cgi/wiki?TemplateMethodPattern">Template Method</a>
 * 
 * @author Nabeel Mukhtar
 */
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
@TargetDesignator( {
		@Constraint(role = "ABSTRACT_CLASS", condition = "class(abstract *)", bind = "AC"),
		@Constraint(role = "CONCRETE_CLASS", condition = "class(!abstract *)", bind = "CC"),
		@Constraint(role = "TEMPLATE_METHOD", condition = "method(abstract *())", bind = "TM"),
		@Constraint(condition = "parent($TM, $AC)"),
		@Constraint(condition = "implementation($CC, $AC)") })
public @interface TemplateMethod {
	String id() default "";

	Role[] roles() default TEMPLATE_METHOD;

	Class<?>[] collaborators() default {};

	public enum Role {
		ABSTRACT_CLASS, CONCRETE_CLASS, TEMPLATE_METHOD;
	}
}
