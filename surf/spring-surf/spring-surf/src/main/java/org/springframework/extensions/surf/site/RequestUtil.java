/**
 * Copyright (C) 2005-2009 Alfresco Software Limited.
 *
 * This file is part of the Spring Surf Extension project.
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
 */

package org.springframework.extensions.surf.site;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utility class for constructing and binding RequestContext objects.
 * 
 * @author muzquiano
 * @author kevinr
 */
public class RequestUtil
{
    /**
     * Performs a servlet include.  This is the principal means
     * for handling any kind of JSP include that occurs within the framework.
     * 
     * With this method, all dispatch path referencing is relative 
     * to the request.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param dispatchPath String
     * @throws ServletException
     * @throws IOException
     */
    public static void include(HttpServletRequest request,
            HttpServletResponse response, String dispatchPath)
            throws ServletException, IOException
    {
        request.getRequestDispatcher(dispatchPath).include(request, response);
    }

    /**
     * Performs a servlet include.  This is the principal means
     * for handling any kind of JSP include that occurs within the framework.
     * 
     * With this method, all dispatch path referencing is relative 
     * to the servlet context.
     * 
     * @param context ServletContext
     * @param request ServletContext
     * @param response ServletRequest
     * @param dispatchPath String
     *
     * @throws ServletException
     * @throws IOException
     */
    public static void include(ServletContext context, ServletRequest request,
            ServletResponse response, String dispatchPath)
            throws ServletException, IOException
    {
        RequestDispatcher disp = context.getRequestDispatcher(dispatchPath);
        disp.include(request, response);
    }

    /**
     * Performs a servlet forward.  This is the principal means
     * for handling any kind of JSP include that occurs within the framework.
     * 
     * With this method, all dispatch path referencing is relative 
     * to the request.
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param dispatchPath String
     * @throws ServletException
     */    
    public static void forward(HttpServletRequest request,
            HttpServletResponse response, String dispatchPath)
            throws ServletException
    {
        try
        {
            request.getRequestDispatcher(dispatchPath).forward(request, response);
        }
        catch (Exception ex)
        {
            throw new ServletException(ex);
        }
    }

    /**
     * Performs a servlet forward.  This is the principal means
     * for handling any kind of JSP include that occurs within the framework.
     * 
     * With this method, all dispatch path referencing is relative 
     * to the servlet context.
     * 
     * @param context ServletContext
     * @param request ServletRequest
     * @param response ServletResponse
     * @param dispatchPath String
     * @throws ServletException
     */    
    public static void forward(ServletContext context, ServletRequest request,
            ServletResponse response, String dispatchPath)
            throws ServletException
    {
        try
        {
            RequestDispatcher disp = context.getRequestDispatcher(dispatchPath);
            disp.forward(request, response);
        }
        catch (Exception ex)
        {
            throw new ServletException(ex);
        }
    }
}
