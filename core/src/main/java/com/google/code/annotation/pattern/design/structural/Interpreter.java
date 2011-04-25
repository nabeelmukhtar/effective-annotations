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
 * @see <a href="http://c2.com/cgi/wiki?InterpreterPattern">Interpreter</a>
 * 
 * @author Nabeel Mukhtar
 */
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.SOURCE)
@TargetDesignator( {
		@Constraint(role = "ABSTRACT_EXPRESSION", condition = "interface(*)", bind = "AE"),
		@Constraint(role = "TERMINAL_EXPRESSION", condition = "class(!abstract *)", bind = "TE"),
		@Constraint(role = "NONTERMINAL_EXPRESSION", condition = "class(!abstract *)", bind = "NTE"),
		@Constraint(role = "CONTEXT", condition = "class(!abstract *)", bind = "C"),
		@Constraint(role = "INTERPRET_METHOD", condition = "method(* *(bind $C))", bind = "IM"),
		@Constraint(condition = "implementation($TE, $AE)"),
		@Constraint(condition = "implementation($NTE, $AE)"),
		@Constraint(condition = "parent($IM, $AE)"),
		@Constraint(condition = "parent($IM, $TE)"),
		@Constraint(condition = "parent($IM, $NTE)") })
public @interface Interpreter {
	String id() default "";

	Role[] roles();

	Class<?>[] collaborators() default {};

	public enum Role {
		ABSTRACT_EXPRESSION, TERMINAL_EXPRESSION, NONTERMINAL_EXPRESSION, CONTEXT, INTERPRET_METHOD;
	}
}
