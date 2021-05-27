# no-framework-webapp

## What dependencies do you need? 

```groovy
implementation ('org.eclipse.jetty:jetty-servlet:11.0.2')
```

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

2. The container invokes the `service` method, passing request and response objects. Service methods are discussed in Writing Service Methods.

If it needs to remove the servlet, the container finalizes the servlet by calling the servlet's destroy method. For more information, see Finalizing a Servlet.

![servlet](https://user-images.githubusercontent.com/48289901/119904880-5dacf300-bf4b-11eb-8fcb-bacee2f38d88.png)
