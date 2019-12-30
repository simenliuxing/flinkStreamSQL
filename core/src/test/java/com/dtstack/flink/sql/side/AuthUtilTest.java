/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dtstack.flink.sql.side;

import com.dtstack.flink.sql.util.AuthUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2019/12/30
 * Company: www.dtstack.com
 * @author maqi
 */
public class AuthUtilTest {
    @Test
    public void testBuildFormat() {
        Map<String, String> loginModuleOptions = new HashMap<>();
        loginModuleOptions.put("useKeyTab", "true");
        loginModuleOptions.put("useTicketCache", "false");
        loginModuleOptions.put("keyTab", "\"/path/to/keytab.keytab\"");
        loginModuleOptions.put("principal", "\"myhbaseuser@MY.HADOOP.DOMAIN\"");
        AuthUtil.JAASConfig build = AuthUtil.JAASConfig.builder().setEntryName("Client").setLoginModule("com.sun.security.auth.module.Krb5LoginModule")
                .setLoginModuleFlag("required").setLoginModuleOptions(loginModuleOptions).build();

        String result = "Client {\n" +
                "\tcom.sun.security.auth.module.Krb5LoginModule  required\n" +
                "\tprincipal=\"myhbaseuser@MY.HADOOP.DOMAIN\"\n" +
                "\tkeyTab=\"/path/to/keytab.keytab\"\n" +
                "\tuseKeyTab=true\n" +
                "\tuseTicketCache=false;\n" +
                "\n" +
                "};";

        Assert.assertEquals(result, build.toString());
    }
}
