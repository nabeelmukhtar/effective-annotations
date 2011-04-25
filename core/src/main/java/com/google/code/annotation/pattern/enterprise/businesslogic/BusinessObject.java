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

import static com.google.code.annotation.pattern.enterprise.businesslogic.BusinessObject.Role.PARENT_BUSINESS_OBJECT;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see <a href="http://www.corej2eepatterns.com/Patterns2ndEd/BusinessObject.htm">Business Object</a>
 *
 * @author Nabeel Mukhtar
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface BusinessObject {
	String id() default "";
	Role[] roles() default PARENT_BUSINESS_OBJECT;
	Class<?>[] collaborators() default {};
	public enum Role {
		PARENT_BUSINESS_OBJECT, DEPENDENT_BUSINESS_OBJECT;
	}
}
