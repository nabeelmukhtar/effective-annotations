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

import static com.google.code.annotation.pattern.enterprise.businesslogic.TransferObjectAssembler.Role.TRANSFER_OBJECT_ASSEMBLER;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see <a href="http://www.corej2eepatterns.com/Patterns2ndEd/TransferObjectAssembler.htm">Transfer Object Assembler</a>
 *
 * @author Nabeel Mukhtar
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface TransferObjectAssembler {
	String id() default "";
	Role[] roles() default TRANSFER_OBJECT_ASSEMBLER;
	Class<?>[] collaborators() default {};
	public enum Role {
		TRANSFER_OBJECT_ASSEMBLER, APPLICATION_MODEL, BUSINESS_OBJECT, SESSION_FACADE, DATA_ACCESS_OBJECT, SERVICE; 
	}
}
