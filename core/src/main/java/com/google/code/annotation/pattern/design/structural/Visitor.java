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
 * @see <a href="http://c2.com/cgi/wiki?VisitorPattern">Visitor</a>
 * 
 * @author Nabeel Mukhtar
 */
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
@TargetDesignator( {
		@Constraint(role = "VISITOR", condition = "interface(*)", bind = "V"),
		@Constraint(role = "CONCRETE_VISITOR", condition = "class(!abstract *)", bind = "CV"),
		@Constraint(role = "ELEMENT", condition = "interface(*)", bind = "E"),
		@Constraint(role = "CONCRETE_ELEMENT", condition = "class(!abstract *)", bind = "CE"),
		@Constraint(role = "VISIT_METHOD", condition = "method(* bind *($I))", bind = "VM"),
		@Constraint(role = "ACCEPT_METHOD", condition = "method(* bind *($I))", bind = "AM"),
		@Constraint(condition = "implementation($CV, $V)"),
		@Constraint(condition = "implementation($CE, $E)"),
		@Constraint(condition = "parent($VM, $V)"),
		@Constraint(condition = "parent($VM, $CV)"),
		@Constraint(condition = "parent($AM, $E)"),
		@Constraint(condition = "parent($AM, $CE)") })
public @interface Visitor {
	String id() default "";

	Role[] roles();

	Class<?>[] collaborators() default {};

	public enum Role {
		VISITOR, CONCRETE_VISITOR, ELEMENT, CONCRETE_ELEMENT, OBJECT_STRUCTURE, VISIT_METHOD, ACCEPT_METHOD;
	}
}
