//
//  ========================================================================
//  Copyright (c) 1995-2016 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.server.session;

import java.io.File;

import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.util.IO;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SessionRenewTest extends AbstractSessionRenewTest
{
   
    
    @Before
    public void before() throws Exception
    {
       FileTestServer.setup();
    }
    
    @After 
    public void after()
    {
        FileTestServer.teardown();
    }
    
    @Override
    public AbstractTestServer createServer(int port, int max, int scavenge,int idlePassivatePeriod)
    {
        return new FileTestServer(port, max, scavenge,idlePassivatePeriod);
    }

    @Test
    public void testSessionRenewal() throws Exception
    {
        super.testSessionRenewal();
    }

    /** 
     * @see org.eclipse.jetty.server.session.AbstractSessionRenewTest#verifyChange(java.lang.String, java.lang.String)
     */
    @Override
    public boolean verifyChange(WebAppContext context, String oldSessionId, String newSessionId)
    {
        ((FileTestServer)_server).assertFileExists(oldSessionId, false);
        ((FileTestServer)_server).assertFileExists(newSessionId, true);
        return true;
    }

    
}
