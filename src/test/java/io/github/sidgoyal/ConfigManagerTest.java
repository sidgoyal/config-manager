package io.github.sidgoyal;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

public class ConfigManagerTest {

	@Rule
	public final EnvironmentVariables env = new EnvironmentVariables();
	
	@After
	public void after() {
//		env.clear("io_github_sidgoyal_config_env");
	}

	@Test
	public void testPropertiesOverride() {
		env.set("io_github_sidgoyal_config_env", "test");
		ConfigManager cm = new ConfigManagerBuilder().build();
		env.set("test_3", "envVariableOverride");
		System.setProperty("test.4","systemPropertyOverride");
		
		assertEquals("default", cm.getProperty("test.1"));
		assertEquals("envSpecificPropertiesFile", cm.getProperty("test.2"));
		assertEquals("envVariableOverride", cm.getProperty("test.3"));
		assertEquals("systemPropertyOverride", cm.getProperty("test.4"));

	}
	
	@Test
	public void testPropertiesOverrideWithoutConfigEnv() {
		ConfigManager cm = new ConfigManagerBuilder().build();
		env.set("test_3", "envVariableOverride");
		System.setProperty("test.4","systemPropertyOverride");
		
		assertEquals("default", cm.getProperty("test.1"));
		assertEquals("default", cm.getProperty("test.2"));
		assertEquals("envVariableOverride", cm.getProperty("test.3"));
		assertEquals("systemPropertyOverride", cm.getProperty("test.4"));

	}
	
	@Test
	public void testEnvPropertiesWithPrefixAndSeparator() {
		ConfigManager cm = new ConfigManagerBuilder().setEnvPrefix("testEnv").setEnvSeparator("__").build();
		env.set("testEnv__test__3", "envVariableOverride");
		System.setProperty("test.4","systemPropertyOverride");
		
		assertEquals("default", cm.getProperty("test.1"));
		assertEquals("default", cm.getProperty("test.2"));
		assertEquals("envVariableOverride", cm.getProperty("test.3"));
		assertEquals("systemPropertyOverride", cm.getProperty("test.4"));

	}

}
