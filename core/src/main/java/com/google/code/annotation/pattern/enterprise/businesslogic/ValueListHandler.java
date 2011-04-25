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
package com.google.code.annotation.pattern.enterprise.businesslogic;

import static com.google.code.annotation.pattern.enterprise.businesslogic.ValueListHandler.Role.VALUE_LIST_HANDLER;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see <a href="http://www.corej2eepatterns.com/Patterns2ndEd/ValueListHandler.htm">Value List Handler</a>
 *
 * @author Nabeel Mukhtar
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface ValueListHandler {
	String id() default "";
	Role[] roles() default VALUE_LIST_HANDLER;
	Class<?>[] collaborators() default {};
	public enum Role {
		VALUE_LIST_ITERATOR, VALUE_LIST_HANDLER;
	}
}
