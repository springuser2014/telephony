# About

TBD

# Features

TBD

# Inital configuration

TBD

# Prerequisities

Properly installed basic stuff from Java world:

- Java 1.6 >=
- Maven 3

To prepare an completed development environment we also need:

- PostgreSQL database >= 8.4
- MongoDB 2.6
- Tomcat 6.0.36 server

## Used technologies

- Restlet 2.1
- Maven 3
- JPA 2.0 (Hibernate 3.5)
- Guice 3.0
- Flyway 2.2 (+ flyway-test-extensions)
- Arquillian 1.1.1
- Mockito 1.9.5

## Technologies which could be in use soon:
- Groovy
- Spock framework
- Geb
- ElasticSearch
- Hibernate Envers
- jQuery, jQuery UI
- backbone.js
- handlebars
- underscore.js
- require.js
- karma
- instanbul
- sinon

# TODO

[List of upcoming features](https://github.com/pawelhenek/telephony/issues)

# Testing configuration

To launch tests it is required to prepare corresponding databases and configurations:

1. `telephony (telephony-parent/src/main/resources/META-INF/persistence.xml)` - to production usage
2. `telephony-test (telephony-core/src/test/resources/META-INF/persistence.xml)` - to unit/integration tests in core module
3. `testphony-ws (telephony-ws/src/test/resources/META-INF/persistence.xml)` - to integration tests for web services module

Here are some default configs:
persistence.xml (1.)

    <property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/telephony"/>
    <property name="hibernate.connection.username" value="postgres"/>
    <property name="hibernate.connection.password" value="postgres"/>


persistence.xml (2.)

    <property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/telephony-test"/>
    <property name="hibernate.connection.username" value="postgres"/>
    <property name="hibernate.connection.password" value="postgres"/>

persistence.xml (3.)

    <property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/telephony-ws-test"/>
    <property name="hibernate.connection.username" value="postgres"/>
    <property name="hibernate.connection.password" value="postgres"/>


For integration tests telephony uses arquillian. To lanuch our integration tests except databases we also need a properly configured tomcat 6 server. Here is a snippet from tomcat's bin/startup.sh file:

    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote "
    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.port=8089 "
    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.ssl=false "
    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.authenticate=false "
    export JAVA_OPTS;
   
Or for windows .bat file (bin/startup.bat)

    set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote 
    set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote.port=8089 
    set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote.ssl=false 
    set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote.authenticate=false 
         
Of course tomcat-users.xml should like similar to:

	<tomcat-users>
	
	    <role rolename="manager-gui"/>
	    <role rolename="manager-status"/>
	    <role rolename="manager-script"/>
	    <role rolename="manager-jmx"/>
	    
	    <user username="tomcat" password="tomcat" roles="manager-gui,manager-status,manager-script,manager-jmx"/>
		
	    <user username="arquillian" password="arquillian" roles="manager-gui,manager-status,manager-script,manager-jmx"/>
	
	</tomcat-users>


We need to add this at the beginning of the file to enable deployment by jmx.
