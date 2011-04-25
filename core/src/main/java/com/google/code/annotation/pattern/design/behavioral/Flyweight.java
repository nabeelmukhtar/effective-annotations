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
 * @see <a href="http://c2.com/cgi/wiki?FlyweightPattern">Flyweight</a>
 * 
 * @author Nabeel Mukhtar
 */
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
@TargetDesignator( {
		@Constraint(role = "FLYWEIGHT", condition = "interface(*)", bind = "F"),
		@Constraint(role = "CONCRETE_FLYWEIGHT", condition = "class(!abstract *)", bind = "CF"),
		@Constraint(role = "UNSHARED_CONCRETE_FLYWEIGHT", condition = "class(!abstract *)", bind = "UCF"),
		@Constraint(role = "FLYWEIGHT_FACTORY", condition = "class(!abstract *)", bind = "FF"),
		@Constraint(role = "GET_FLYWEIGHT_METHOD", condition = "method(* bind $F *())", bind = "GFM"),
		@Constraint(condition = "implementation($CF, $F)"),
		@Constraint(condition = "implementation($UCF, $F)"),
		@Constraint(condition = "parent($GFM, $FF)") })
public @interface Flyweight {
	String id() default "";

	Role[] roles();

	Class<?>[] collaborators() default {};

	public enum Role {
		FLYWEIGHT, CONCRETE_FLYWEIGHT, UNSHARED_CONCRETE_FLYWEIGHT, FLYWEIGHT_FACTORY, GET_FLYWEIGHT_METHOD;
	}
}
