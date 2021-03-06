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
 * @see <a href="http://c2.com/cgi/wiki?ObserverPattern">Observer</a>
 * 
 * @author Nabeel Mukhtar
 */
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
@TargetDesignator( {
		@Constraint(role = "SUBJECT", condition = "interface(*)", bind = "S"),
		@Constraint(role = "CONCRETE_SUBJECT", condition = "class(!abstract *)", bind = "CS"),
		@Constraint(role = "OBSERVER", condition = "interface(*)", bind = "O"),
		@Constraint(role = "CONCRETE_OBSERVER", condition = "class(!abstract *)", bind = "CO"),
		@Constraint(role = "ATTACH_METHOD", condition = "method(* bind *($I))", bind = "AM"),
		@Constraint(role = "DETACH_METHOD", condition = "method(* bind *($I))", bind = "DM"),
		@Constraint(role = "NOTIFY_METHOD", condition = "method(* *())", bind = "NM"),
		@Constraint(condition = "implementation($CS, $S)"),
		@Constraint(condition = "implementation($CO, $O)"),
		@Constraint(condition = "parent($AM, $S)"),
		@Constraint(condition = "parent($DM, $S)"),
		@Constraint(condition = "parent($NM, $S)"),
		@Constraint(condition = "parent($AM, $CS)"),
		@Constraint(condition = "parent($DM, $CS)"),
		@Constraint(condition = "parent($NM, $CS)") })
public @interface Observer {
	String id() default "";

	Role[] roles();

	Class<?>[] collaborators() default {};

	public enum Role {
		SUBJECT, OBSERVER, CONCRETE_SUBJECT, CONCRETE_OBSERVER, ATTACH_METHOD, DETACH_METHOD, NOTIFY_METHOD;
	}
}
