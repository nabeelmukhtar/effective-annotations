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
package com.google.code.annotation.util;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 
 */
public class ApplicationResources {

	/**
	 * Base name of the resource bundle.
	 */
	private static final String RESOURCE_BUNDLE_BASE = "com.google.code.annotation.util.ApplicationResources";

	/**
	 * Resource bundle for the default locale.
	 */
	private static final ResourceBundle s_defaultBundle = ResourceBundle
			.getBundle(RESOURCE_BUNDLE_BASE);

	/**
	 * Default constructor - never to be used
	 */
	private ApplicationResources() {
	}

	/**
	 * Returns the recource bundle for the specified locale.
	 * 
	 * @param locale
	 *            desired locale, or null for the default system locale
	 * @return application resources bundle for the specified locale
	 */
	private static ResourceBundle getBundle(Locale locale) {
		if (locale == null) {
			return s_defaultBundle;
		} else {
			return ResourceBundle.getBundle(RESOURCE_BUNDLE_BASE, locale);
		}
	}

	/**
	 * Returns the resource associated with the specified key. This method will
	 * lookup a resource using the default locale
	 * 
	 * @param key
	 *            application resource key
	 * @return localized string for the default locale
	 */
	public static String getLocalizedString(String key) {
		return getLocalizedString(key, null, (Object[]) null);
	}

	/**
	 * Returns the resource associated with the specified key. This method will
	 * lookup a resource using the default locale
	 * 
	 * @param key
	 *            application resource key
	 * @param messageArgs
	 *            optional (may be null), args to insert into the resource
	 * @return localized string with formatted args inserted for the default
	 *         locale
	 */
	public static String getLocalizedString(String key,
			Object ... messageArgs) {
		return getLocalizedString(key, null, messageArgs);
	}

	/**
	 * Returns the resource associated with the specified key. This method will
	 * lookup a resource using the specified locale
	 * 
	 * @param key
	 *            application resource key
	 * @param locale
	 *            optional, desired resource locale or null for default locale
	 * @return localized string for the specified locale
	 */
	public static String getLocalizedString(String key, Locale locale) {
		return getLocalizedString(key, locale, (Object[]) null);
	}

	/**
	 * Returns the resource associated with the specified key. This method will
	 * lookup a resource using the specified locale
	 * 
	 * @param key
	 *            application resource key
	 * @param locale
	 *            optional, desired resource locale or null for default locale
	 * @param messageArgs
	 *            optional (may be null), args to insert into the resource or
	 *            null
	 * @return localized string with formatted args inserted for the specified
	 *         locale
	 */
	public static String getLocalizedString(String key,
			Locale locale, Object ... messageArgs) {
		try {
			String msg = getBundle(locale).getString(key);

			// Now delegate formatting to Java.
			return MessageFormat.format(msg, messageArgs);
		} catch (MissingResourceException e) {
			return "!MISSING MESSAGE TEXT " + key + "! Args: "
					+ Arrays.toString(messageArgs);
		} catch (Exception e) {
			return "!INVALID MESSAGE TEXT PATTERN " + key + "! Args: "
					+ Arrays.toString(messageArgs);
		}
	}
}
