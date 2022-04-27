/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package com.Elliott.Engineering.app.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;

/**
 * RUNTIME annotation used to mark fields for injection at runtime
 *
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GucciInject {
}