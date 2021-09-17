# no-framework-webapp
[![CI](https://github.com/NicoMincuzzi/no-framework-webapp/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/NicoMincuzzi/no-framework-webapp/actions/workflows/ci.yml)

The aim is to show how to implement a Java Web application, replacing some the most famous frameworks (like Spring and JPA) with plain [servlets](https://github.com/NicoMincuzzi/no-framework-webapp/blob/main/README.md#servlet) and [JDBC](https://github.com/NicoMincuzzi/no-framework-webapp/blob/main/README.md#jdbc).

## Purpose :dart: 

The aim is to develop a web application, which doesn't need to use `frameworks`. In particular, it shows how to develop a project or a single feature with no dependence on a `framework` is a **real possibility**. We acknowledge the availability of alternatives to `frameworks`, by using dedicated libraries and/or standard libraries.

See more information about the [Frameworkless movement](https://github.com/frameworkless-movement/manifesto).

## What dependencies do you need? :link:

```groovy
implementation ('org.eclipse.jetty:jetty-servlet:11.0.2')
```

```groovy
implementation group: 'ch.qos.logback', name: 'logback-classic', version: '1.2.3'
```

## Build and Run

```sh 
docker build -t mytest .

docker container run -it -p 9090:9090 mytest ./scripts/build.sh
docker container run -it -p 9090:9090 mytest ./scripts/tests.sh
docker container run -it -p 9090:9090 mytest ./scripts/run.sh
```

Il file JSON contenente la mappa dovrà essere inserito nella folder `src/main/resources` e dovrà essere etichettato con il nome map.json

## Servlet

### What Is a Servlet?
A servlet is a Java programming language class used to extend the capabilities of servers that host applications accessed by means of a request-response programming model. Although servlets can respond to any type of request, they are commonly used to extend the applications hosted by web servers. For such applications, Java Servlet technology defines HTTP-specific servlet classes.

The `javax.servlet` and `javax.servlet.http` packages provide interfaces and classes for writing servlets. All servlets must implement the Servlet interface, which defines lifecycle methods. When implementing a generic service, you can use or extend the GenericServlet class provided with the Java Servlet API. The HttpServlet class provides methods, such as doGet and doPost, for handling HTTP-specific services.

### Servlet Lifecycle
The lifecycle of a servlet is controlled by the container in which the servlet has been deployed. When a request is mapped to a servlet, the container performs the following steps.

1. If an instance of the servlet does not exist, the web container:

   * Loads the servlet class

   * Creates an instance of the servlet class

   * Initializes the servlet instance by calling the `init` method (initialization is covered in Creating and Initializing a Servlet)
     * Generally, the servlet is created when a user first invokes a URL corresponding to the servlet, but you can also specify that the which servlet should be loaded when the server is first started 

2. The container invokes the `service` method, passing request and response objects.
  * Each time the server receives a request for a servlet, the web container spawns a new thread and calls `service()`

If it needs to remove the servlet, the container finalizes the servlet by calling the servlet's `destroy` method.

<p align="center">
  <img src="https://user-images.githubusercontent.com/48289901/119905545-bdf06480-bf4c-11eb-99d1-44cf34f41740.jpg" alt="servlet"/>
</p>

### ServletContextListener

The ServletContextListener will run your code before the web application is started. For example, you want to initialize a database connection pool before the web application is started.

In this example, we will show you how to create a custom listener class by implementing ServletContextListener, which run your code before the web application is started.

```java
@WebListener
public class ContextListener implements ServletContextListener {
 
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("The application started");
    }
     
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("The application stopped");
    }
}
```

## JDBC

Java Database Connectivity (JDBC) is an application programming interface (API) for the programming language Java, which defines how a client may access a database. It is part of the Java Standard Edition platform and provides methods to query and update data in a database, and is oriented towards relational databases.

[PostgreSQL JDBC Driver](https://jdbc.postgresql.org/documentation/head/intro.html) (PgJDBC for short) allows Java programs to connect to a PostgreSQL database using standard, database independent Java code. Is an open source JDBC driver written in Pure Java (Type 4), and communicates in the PostgreSQL native network protocol. Because of this, the driver is platform independent; once compiled, the driver can be used on any system.
