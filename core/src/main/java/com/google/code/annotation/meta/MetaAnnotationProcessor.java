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
package com.google.code.annotation.meta;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic.Kind;

import com.google.code.annotation.util.ApplicationResources;

/**
 * @author Nabeel Mukhtar
 * 
 */
@SupportedAnnotationTypes("com.google.code.annotation.meta.*")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class MetaAnnotationProcessor extends AbstractProcessor {
	protected final String ROLE_NAME = "Role";

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		// verify that the role attribute contains a valid role.
		// verify that the bind attribute is unique.
		// verify that the condition attribute conforms to the syntax.
		for (Element child : roundEnv
				.getElementsAnnotatedWith(TargetDesignator.class)) {
			// System.out.println(child.getSimpleName());
			TargetDesignator targetDesignator = child
					.getAnnotation(TargetDesignator.class);
			Set<String> patternRoles = getPatternRoles(child);
			Set<String> bindNames = new HashSet<String>();
			for (Constraint constraint : targetDesignator.value()) {
				// System.out.println(constraint.role());
				if (!constraint.role().isEmpty()
						&& !patternRoles.contains(constraint.role())) {
					// hard coded message
					processingEnv
							.getMessager()
							.printMessage(
									Kind.ERROR,
									ApplicationResources
											.getLocalizedString(
													"com.google.code.annotation.processor.illegalRoleInConstraint",
													constraint.role(), child
															.getSimpleName()));
				}
				if (!constraint.bind().isEmpty()) {
					if (bindNames.contains(constraint.bind())) {
						// hard coded message
						processingEnv
								.getMessager()
								.printMessage(
										Kind.ERROR,
										ApplicationResources
												.getLocalizedString(
														"com.google.code.annotation.processor.duplicateBindInConstraint",
														constraint.bind(),
														child.getSimpleName()));
					} else {
						bindNames.add(constraint.bind());
					}
				}
			}
		}
		return true;
	}

	protected Set<String> getPatternRoles(Element patternAnnotation) {
		final Set<String> roleNames = new HashSet<String>();
		for (TypeElement child : ElementFilter.typesIn(patternAnnotation
				.getEnclosedElements())) {
			if (child.getKind() == ElementKind.ENUM
					&& child.getSimpleName().toString().equals(ROLE_NAME)) {
				for (Element enumValue : ElementFilter.fieldsIn(child
						.getEnclosedElements())) {
					if (enumValue.getKind() == ElementKind.ENUM_CONSTANT) {
						roleNames.add(enumValue.getSimpleName().toString());
					}
				}
			}
		}

		return roleNames;
	}
}
