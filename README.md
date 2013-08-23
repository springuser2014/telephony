# About

Hi!

This's my personal project on university classes,
so be careful while you use it for your own. It's very experimental version, so I don't take responsibility for using it ;-)

# Features

It's quite simple set of RESTful services for managing products in electronic products store.
Main features includes:

- Managing products in stores
- Managing stores, users (admins, workers, etc) and theirs credentials
- Managing deliveries, sales

# Prerequisities

Properly installed basic stuff from Java world:

- Java 1.6 >=
- Maven 3

To prepare an completed development environment we also need:

- PostgreSQL database >= 8.4
- Tomcat 6. (0.36) server

## Used technologies

- Restlet 2.1
- Maven 3
- JPA 2.0 (Hibernate 3.5)
- Guice 3.0
- Flyway 2.2 (+ flyway-test-extensions)
- Arquillian 1.1.1
- Mockito 1.9.5

# TODO

Project is now under intensive development, in next few weeks there will appear:

- Complete unit tests for core module (via JUnit).
- Complete integration tests for web services (via Arquillian).
- Build a GUI module (via Bootstrap, jQuery and others js libs)

# Testing

To launch tests it is required to prepare corresponding databases and configurations:

1. `telephony (telephony-gwt/src/main/resources/META-INF/persistence.xml)` - to production usage
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

Stay tuned!

Cheers 
Paul
