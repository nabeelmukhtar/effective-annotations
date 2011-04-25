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
package com.google.code.annotation.pattern.design.behavioral;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.code.annotation.meta.Constraint;
import com.google.code.annotation.meta.TargetDesignator;

/**
 * @see <a href="http://c2.com/cgi/wiki?AdapterPattern">Adapter</a>
 * 
 * @author Nabeel Mukhtar
 */
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
@TargetDesignator( {
		@Constraint(role = "TARGET", condition = "interface(*)", bind = "T"),
		@Constraint(role = "ADAPTEE", condition = "class(!abstract *)", bind = "AE"),
		@Constraint(role = "ADAPTER", condition = "class(!abstract *)", bind = "AR"),
		@Constraint(condition = "implementation($AR, $T)"),
		@Constraint(condition = "parent($AE, $AR) or implementation($AR, $AE)") })
public @interface Adapter {
	String id() default "";

	Class<?>[] collaborators();

	Role[] roles();

	public enum Role {
		TARGET, ADAPTEE, ADAPTER;
	}
}
