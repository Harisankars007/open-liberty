/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
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
package jaxrs21.fat.classSubRes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;

import componenttest.app.FATServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/ClassSubResTestServlet")
public class ClassSubResTestServlet extends FATServlet {

    private Client client;

    @Override
    public void init() throws ServletException {
        client = ClientBuilder.newBuilder().build();
    }

    @Override
    public void destroy() {
        client.close();
    }

    @Test
    public void testNoArgPublicConstructor(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Response response = target(req, "NoArgCtor").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("NoArgCtor", response.readEntity(String.class));

        response = target(req, "NoArgCtor").request().post(Entity.text("POST"));
        assertEquals(200, response.getStatus());
        assertEquals("NoArgCtor POST", response.readEntity(String.class));

        response = target(req, "NoArgCtor").request().method("PATCH", Entity.text("PATCH"));
        assertEquals(200, response.getStatus());
        assertEquals("NoArgCtor PATCH", response.readEntity(String.class));

        response = target(req, "NoArgCtor").request().options();
        assertEquals(200, response.getStatus());
        assertTrue(response.getHeaderString("Allow").contains("GET"));
        assertTrue(response.getHeaderString("Allow").contains("POST"));
        assertTrue(response.getHeaderString("Allow").contains("PATCH"));
    }

    @Test
    public void testMultipleMultiArgConstructors(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Response response = target(req, "MultiMultiArgCtor").queryParam("a", "b").request().get();
        assertEquals(200, response.getStatus());
        assertEquals("MultiMultiArgCtor 3", response.readEntity(String.class));

        response = target(req, "MultiMultiArgCtor").queryParam("a", "c").request().post(Entity.text("POST"));
        assertEquals(200, response.getStatus());
        assertEquals("MultiMultiArgCtor POST 3", response.readEntity(String.class));

        response = target(req, "MultiMultiArgCtor").queryParam("a", "d").request().method("PATCH", Entity.text("PATCH"));
        assertEquals(200, response.getStatus());
        assertEquals("MultiMultiArgCtor PATCH 3", response.readEntity(String.class));

        response = target(req, "MultiMultiArgCtor").request().options();
        assertEquals(200, response.getStatus());
        assertTrue(response.getHeaderString("Allow").contains("GET"));
        assertTrue(response.getHeaderString("Allow").contains("POST"));
        assertTrue(response.getHeaderString("Allow").contains("PATCH"));
    }

    @Test
    public void testUnsuitableConstructors(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Response response = target(req, "DefaultVisCtor").request().get();
        assertEquals(500, response.getStatus());

        response = target(req, "PrivateCtor").request().get();
        assertEquals(500, response.getStatus());

        response = target(req, "ProtectedCtor").request().get();
        assertEquals(500, response.getStatus());

        response = target(req, "UnknownParmCtor").request().get();
        assertEquals(500, response.getStatus());
    }

    private WebTarget target(HttpServletRequest request, String path) {
        String base = "http://" + request.getServerName() + ':' + request.getServerPort() + "/classSubResApp/rest/test/sub/";
        return client.target(base + path);
    }

}