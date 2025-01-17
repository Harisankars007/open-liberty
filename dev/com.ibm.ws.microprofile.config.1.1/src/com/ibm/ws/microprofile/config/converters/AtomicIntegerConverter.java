/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.microprofile.config.converters;

import java.util.concurrent.atomic.AtomicInteger;

import com.ibm.websphere.ras.annotation.Trivial;

/**
 *
 */
public class AtomicIntegerConverter extends BuiltInConverter {

    @Trivial
    public AtomicIntegerConverter() {
        super(AtomicInteger.class);
    }

    /** {@inheritDoc} */
    @Override
    public AtomicInteger convert(String value) {
        if (value == null) {
            return null;
        }
        return new AtomicInteger(Integer.parseInt(value));
    }
}
