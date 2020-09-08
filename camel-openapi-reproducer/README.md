Run ScratchTestApp.java 'as an application' to start. ScratchWebApp.java exists to start within Tomcat.

pom.xml is completely unchanged from the source project.

C10yOAuthRB.java defines the Camel REST DSL.

I have removed the actual work the routes did because it's not necessary in this reproducer and increases the number of compilation dependencies.

Similarly, I have only included the classes necessary to compile C10yOAuthRB.java, and to start Camel & Spring Boot.

The problem remains:
Caused by: org.apache.camel.RuntimeCamelException: java.lang.IllegalStateException: Cannot find RestApiProcessorFactory in Registry or classpath (such as the camel-openapi-java component)

Thanks for all your help in resolving this issue.
