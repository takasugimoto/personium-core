/**
 * personium.io
 * Copyright 2017-2018 FUJITSU LIMITED
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.personium.core.rule;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import org.slf4j.Logger;

import io.personium.core.event.EventPublisher;
import io.personium.core.event.PersoniumEvent;
import io.personium.core.utils.UriUtils;
import io.personium.test.categories.Unit;

/**
 * Unit Test class for RuleManager.
 */
@Category({ Unit.class })
@RunWith(PowerMockRunner.class)
@PrepareForTest({ EventPublisher.class, UriUtils.class })
public class RuleManagerTest {

    /**
     * Test match().
     * Normal test.
     * argument is null.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_argument_is_null() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = null;
        PersoniumEvent event = null;

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(false));
    }

    /**
     * Test match().
     * Normal test.
     * external of RuleInfo is null.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_external_of_ruleinfo_is_null() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        PersoniumEvent event = new PersoniumEvent();

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(false));
    }

    /**
     * Test match().
     * Normal test.
     * external of event is null.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_external_of_event_is_null() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.FALSE;
        PersoniumEvent event = new PersoniumEvent(null,
            null, null, null, null, null, null, null, null, null, null);

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(false));
    }

    /**
     * Test match().
     * Normal test.
     * external matches with false.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_external_matches_with_false() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.FALSE;
        PersoniumEvent event = new PersoniumEvent(Boolean.FALSE,
                "schema string", "subject", "type", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * external matches with true.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_external_matches_with_true() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.TRUE;
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "schema", "subject", "type", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * schema matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_schema_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        RuleManager.BoxInfo bi = rman.new BoxInfo();
        bi.schema = "http://personium/appCell/";
        ri.external = Boolean.TRUE;
        ri.box = bi;
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/appCell/", "subject", "type", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * schema not matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_schema_not_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        RuleManager.BoxInfo bi = rman.new BoxInfo();
        bi.schema = "http://personium/";
        ri.external = Boolean.TRUE;
        ri.box = bi;
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/appCell/", "subject", "type", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(false));
    }

    /**
     * Test match().
     * Normal test.
     * subject matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_subject_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.subject = "http://personium/dummyCell/#account";
        ri.external = Boolean.TRUE;
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/dummyCell/", "http://personium/dummyCell/#account", "type", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * subject not matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_subject_not_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.subject = "http://personium/dummyCell/";
        ri.external = Boolean.TRUE;
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/appCell/", "http://personium/dummyCell/#account", "type", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(false));
    }
    /**
     * Test match().
     * Normal test.
     * type matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_type_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.TRUE;
        ri.type = "cellctl.Rule.create";
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/dummyCell/", "subject", "cellctl.Rule.create", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * type partial matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_type_partial_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.TRUE;
        ri.type = "cellctl.Rule";
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/dummyCell/", "subject", "cellctl.Rule.create", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * type not matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_type_not_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.TRUE;
        ri.type = "cellctl.Rule.create.some";
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/dummyCell/", "subject", "cellctl.Rule.create", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(false));
    }

    /**
     * Test match().
     * Normal test.
     * object matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_object_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.TRUE;
        ri.object = "personium-localcell:/__ctl/Role";
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/dummyCell/", "subject",
                "cellctl.Role.create", "personium-localcell:/__ctl/Role", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * object matches in boxbounded.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_object_matches_in_boxbounded() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        RuleManager.BoxInfo bi = rman.new BoxInfo();
        bi.schema = "http://personium/appCell/";
        bi.name = "box";
        ri.external = Boolean.FALSE;
        ri.object = "personium-localbox:/col/entity";
        ri.box = bi;
        PersoniumEvent event = new PersoniumEvent(Boolean.FALSE,
                "http://personium/appCell/", "subject", "odata.create",
                "personium-localcell:/box/col/entity", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());
        PowerMockito.mockStatic(UriUtils.class);
        PowerMockito.doReturn("personium-localcell:/box/col/entity")
                .when(UriUtils.class, "convertSchemeFromLocalBoxToLocalCell", "personium-localbox:/col/entity", "box");

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * object partial matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_object_partial_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.FALSE;
        ri.object = "personium-localcell:/__ctl";
        PersoniumEvent event = new PersoniumEvent(Boolean.FALSE,
                "http://personium/appCell/", "subject", "cellctl.Rule.create",
                "personium-localcell:/__ctl/Rule", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * object not matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_object_not_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.FALSE;
        ri.object = "personium-localcell:/__ctl/Role";
        PersoniumEvent event = new PersoniumEvent(Boolean.FALSE,
                "http://personium/appCell/", "subject", "cellctl.Rule.create",
                "personium-localcell:/__ctl/Rule", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(false));
    }

    /**
     * Test match().
     * Normal test.
     * info matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_info_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.TRUE;
        ri.info = "info";
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/dummyCell/", "subject", "cellctl.Rule.create", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * info partial matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_info_partial_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.TRUE;
        ri.info = "info";
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/dummyCell/", "subject", "cellctl.Rule.create", "object", "information",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(true));
    }

    /**
     * Test match().
     * Normal test.
     * info not matches.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void match_Normal_info_not_matches() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        RuleManager.RuleInfo ri = rman.new RuleInfo();
        ri.external = Boolean.TRUE;
        ri.info = "information";
        PersoniumEvent event = new PersoniumEvent(Boolean.TRUE,
                "http://personium/dummyCell/", "subject", "cellctl.Rule.create", "object", "info",
                "requestKey", "eventid", "rulechain", "via", "roles");

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method match = RuleManager.class.getDeclaredMethod("match", RuleManager.RuleInfo.class, PersoniumEvent.class);
        match.setAccessible(true);
        boolean result = (boolean) match.invoke(rman, ri, event);

        // --------------------
        // Confirm result
        // --------------------
        assertThat(result, is(false));
    }

    /**
     * Test publish().
     * Normal test.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void publish_Normal() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        PersoniumEvent event = new PersoniumEvent(
                "cellctl.Rule.create", "personium-localcell:/__ctl/Rule", "200", null);

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());
        PowerMockito.spy(EventPublisher.class);
        PowerMockito.doNothing().when(EventPublisher.class, "send", event);
        PowerMockito.doNothing().when(EventPublisher.class, "sendRuleEvent", event);

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method publish = RuleManager.class.getDeclaredMethod("publish", PersoniumEvent.class);
        publish.setAccessible(true);
        publish.invoke(rman, event);

        // --------------------
        // Confirm result
        // --------------------
        PowerMockito.verifyStatic(Mockito.times(1));
        EventPublisher.sendRuleEvent(event);
    }

    /**
     * Test publish().
     * Normal test.
     * type is not match.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void publish_Normal_type_is_not_match() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        PersoniumEvent event = new PersoniumEvent(
                "cellctl.Role.create", "personium-localcell:/__ctl/Role", "200", null);

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());
        PowerMockito.spy(EventPublisher.class);
        PowerMockito.doNothing().when(EventPublisher.class, "send", event);
        PowerMockito.doNothing().when(EventPublisher.class, "sendRuleEvent", event);

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method publish = RuleManager.class.getDeclaredMethod("publish", PersoniumEvent.class);
        publish.setAccessible(true);
        publish.invoke(rman, event);

        // --------------------
        // Confirm result
        // --------------------
        PowerMockito.verifyStatic(Mockito.times(0));
        EventPublisher.sendRuleEvent(event);
    }

    /**
     * Test publish().
     * Normal test.
     * External event.
     * @throws Exception exception occurred in some errors
     */
    @Test
    public void publish_Normal_External_event() throws Exception {
        // --------------------
        // Test method args
        // --------------------
        RuleManager rman = PowerMockito.mock(RuleManager.class);
        PersoniumEvent event = new PersoniumEvent(true, null, null,
                "cellctl.Rule.create", "personium-localcell:/__ctl/Rule", "200",
                null, null, null, null, null);

        // --------------------
        // Mock settings
        // --------------------
        Logger logger = PowerMockito.mock(Logger.class);
        PowerMockito.doNothing().when(logger).info(anyString());
        PowerMockito.doNothing().when(logger).error(anyString());
        PowerMockito.doNothing().when(logger).debug(anyString());
        PowerMockito.spy(EventPublisher.class);
        PowerMockito.doNothing().when(EventPublisher.class, "send", event);
        PowerMockito.doNothing().when(EventPublisher.class, "sendRuleEvent", event);

        // --------------------
        // Run method
        // --------------------
        Field field = RuleManager.class.getDeclaredField("logger");
        field.setAccessible(true);
        field.set(rman, logger);
        Method publish = RuleManager.class.getDeclaredMethod("publish", PersoniumEvent.class);
        publish.setAccessible(true);
        publish.invoke(rman, event);

        // --------------------
        // Confirm result
        // --------------------
        PowerMockito.verifyStatic(Mockito.times(0));
        EventPublisher.sendRuleEvent(event);
    }

}
