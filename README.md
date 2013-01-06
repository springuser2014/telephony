# About

Hi!

That's my personal project on university classes,
so be careful while you use it for your own. It's very experimental
and I don't take responsibility for using it ;-)

# Features

It's quite simple RESTful services for managing products in electronic products store.
Main features includes:

- Managing products in stores
- Managing stores, users (admins, workers, etc) and theirs credentials
- Managing deliveries, sales <s>and reclamations</s>

# Prerequisities

Installed basic stuff from Java world:

- Java 1.6 >=
- Maven 2

To prepare completed development environment we also need:

- PostgreSQL database >= 8.4
- Tomcat 6. (0.36) server

## Used technologies

- <s>Google web Toolkit 2.5</s>
- <s>SmartGWT 3.1</s>
- Restlet 2.1
- Maven 2
- JPA 2.0 (Hibernate 3.5)
- Guice 3.0 (<s>GIN 2.0</s>)
- Flyway 2.0.1
- Arquillian 1.0.2
- <s>GWT-platform 0.7</s>
- Mockito 1.9.5

# Todo

Project is now under intensive development, in next few weeks there will appear:

- Complete unit tests for core module (via JUnit).
- Complete integration tests for web services (via Arquillian).
- -Complete unit tests for UI (via mockito).-
- <s>UI archtecture improvement (by using GWT-platform).</s>
- <s>Many new features including authentication (Apache Shiro), mailing (JavaMail) and others.</s>

# Testing

To launch tests it is required to prepare corresponding databases and configurations:

1. `telephony (telephony-gwt/src/main/resources/META-INF/persistence.xml)` - to production usage
2. `telephony-test (telephony-core/src/test/resources/META-INF/persistence.xml)` - to unit/integration tests in core module
3. `testphony-ws (telephony-ws/src/test/resources/META-INF/persistence.xml)` - to integration tests for web services

Here are some default configs:
persistence.xml (1.)

    :::xml
    <property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/telephony"/>
    <property name="hibernate.connection.username" value="postgres"/>
    <property name="hibernate.connection.password" value="postgres"/>


persistence.xml (2.)

    :::xml
    <property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/telephony-test"/>
    <property name="hibernate.connection.username" value="postgres"/>
    <property name="hibernate.connection.password" value="postgres"/>

persistence.xml (3.)

    :::xml
    <property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/telephony-ws"/>
    <property name="hibernate.connection.username" value="postgres"/>
    <property name="hibernate.connection.password" value="postgres"/>


For integration tests I use arquillian. To lanuch our integration tests except databases we also need a properly configured tomcat 6 server. Here is a snippet from tomcat's bin/startup.sh file:

    :::bash
    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote "
    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.port=8089 "
    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.ssl=false "
    JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.authenticate=false "
    export JAVA_OPTS;

We need to add it at the beginning of the file to enable jmx deployment.

Stay tuned!

Cheers 
Paul