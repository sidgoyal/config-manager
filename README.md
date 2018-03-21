# Configuration Manager  [![Build Status](https://travis-ci.org/sidgoyal/config-manager.svg?branch=master)](https://travis-ci.org/sidgoyal/config-manager)  [![codecov](https://codecov.io/gh/sidgoyal/config-manager/branch/master/graph/badge.svg)](https://codecov.io/gh/sidgoyal/config-manager) . 


A very lightweight configuration manager built along the lines of spring boot configuration (Without the overhead of spring)

## How it works

You can get configuraion elements from different source with a well-defined heirarchy precedence. 

The following is the configuration-source herirachy from top to bottom  :
* System Property (example : `java -Dconfig.key=configValue`
* Environment Variable (example : `export config_key=configValue`
* Environment specific application-{env}.properties file
* application.properties file

To set the configuration environment,
  * Set the system property `-Dio.github.sidgoyal.config.env=dev`
  * Set the environment variable `io_github_sidgoyal_config_env=dev`
  
### Environment Variables
A lot of traditional java properties are structured with `.`  (ex : `service.endpoint=localhost)
such traditional java properties do not work very well with environemnt variables

So, Environment variables by default are separated by `_`

You can change the default separator by setting it in the builder :
 
    new ConfigManagerBuilder().setEnvSeparator("__") 
    
You can also add a prefix to the environment variables :

    new ConfigManagerBuilder().setEnvPrefix("prefix")
    
    
    
### Usage 

Use `ConfigManagerBuilder` to build a `ConfigManager` instance

Load a property using the configurationManger instance:
   
    configManager.getProperty("key");
