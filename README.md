Hi!

That's my personal project on university classes,
so be careful while you use it for your own.

It's very experimental and I am just a student ;-)

It's quite simple app for managing products in electronic products store.

Main features included: 
- Managing products in stores
- Managing stores, users (admins, workers), credentials
- Managing deliveries, sales and reclamations

Used tech stuff:
- Google web Toolkit 2.5
- SmartGWT 3.1
- Restlet 2.1
- Maven 2
- JPA 2.0 (Hibernate 3.5)
- Guice 3.0 (GIN 2.0)
- GWT-platform 0.7
- Flyway 1.7

Project is now under heavy development, in next few weeks there will appear:
- Unit tests
- UI archtecture improvement
- Many new features including authentication (Apache Shiro), mailing (JavaMail) and others.

Testing

After fetching code onto your computer, we need to prepare the development environment.
First of all we need to setup:
- PostgreSQL database 8.4 >=
- Tomcat 6. (0.36) server

Here are some default configs:
persistence.xml (for production usage)

    :::xml
    <property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/telephony"/>
    <property name="hibernate.connection.username" value="postgres"/>
    <property name="hibernate.connection.password" value="postgres"/>


persistence.xml (for local development and tests)
{{{
#!xml

<property name="hibernate.connection.url" value="jdbc:postgresql://localhost:5432/telephony-test"/>
<property name="hibernate.connection.username" value="postgres"/>
<property name="hibernate.connection.password" value="postgres"/>
}}}

For integration tests I use arquillian, here is its configuration:

{{{
#!xml

 <container qualifier="tomcat-remote-6" default="true">
     <configuration>
         <property name="catalinaHome">${user.home}/Servers/apache-tomcat-6.0.36</property>
         <property name="user">arquillian</property>
         <property name="pass">arquillian</property>
         <property name="host">127.0.0.1</property>
         <property name="bindHttpPort">8080</property>
         <property name="serverConfig">server.xml</property>
         <property name="unpackArchive">true</property>
     </configuration>
 </container>
}}}

To lanuch our integration tests we also need a properly configured tomcat 6 server.
Here is a snippet from tomcat's bin/startup.sh file:
{{{
#!bash

JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote "
JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.port=8089 "
JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.ssl=false "
JAVA_OPTS="$JAVA_OPTS -Dcom.sun.management.jmxremote.authenticate=false "
export JAVA_OPTS;
}}}

Stay tuned!



Cheers 
Paul