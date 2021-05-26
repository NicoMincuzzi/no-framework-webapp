# no-framework-webapp

## Servlet
### What Is a Servlet?
A servlet is a Java programming language class used to extend the capabilities of servers that host applications accessed by means of a request-response programming model. Although servlets can respond to any type of request, they are commonly used to extend the applications hosted by web servers. For such applications, Java Servlet technology defines HTTP-specific servlet classes.

The `javax.servlet` and `javax.servlet.http` packages provide interfaces and classes for writing servlets. All servlets must implement the Servlet interface, which defines lifecycle methods. When implementing a generic service, you can use or extend the GenericServlet class provided with the Java Servlet API. The HttpServlet class provides methods, such as doGet and doPost, for handling HTTP-specific services.

Servlet Lifecycle
The lifecycle of a servlet is controlled by the container in which the servlet has been deployed. When a request is mapped to a servlet, the container performs the following steps.

If an instance of the servlet does not exist, the web container:

Loads the servlet class

Creates an instance of the servlet class

Initializes the servlet instance by calling the init method (initialization is covered in Creating and Initializing a Servlet)

The container invokes the service method, passing request and response objects. Service methods are discussed in Writing Service Methods.

If it needs to remove the servlet, the container finalizes the servlet by calling the servlet's destroy method. For more information, see Finalizing a Servlet.

17.2.1 Handling Servlet Lifecycle Events
You can monitor and react to events in a servlet's lifecycle by defining listener objects whose methods get invoked when lifecycle events occur. To use these listener objects, you must define and specify the listener class.

17.2.1.1 Defining the Listener Class
You define a listener class as an implementation of a listener interface. Table 17-1 lists the events that can be monitored and the corresponding interface that must be implemented. When a listener method is invoked, it is passed an event that contains information appropriate to the event. For example, the methods in the HttpSessionListener interface are passed an HttpSessionEvent, which contains an HttpSession.

Use the @WebListener annotation to define a listener to get events for various operations on the particular web application context. Classes annotated with @WebListener must implement one of the following interfaces:

javax.servlet.ServletContextListener
javax.servlet.ServletContextAttributeListener
javax.servlet.ServletRequestListener
javax.servlet.ServletRequestAttributeListener
javax.servlet..http.HttpSessionListener
javax.servlet..http.HttpSessionAttributeListener
For example, the following code snippet defines a listener that implements two of these interfaces:

import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class SimpleServletListener implements ServletContextListener,
        ServletContextAttributeListener {
    ...
17.2.2 Handling Servlet Errors
Any number of exceptions can occur when a servlet executes. When an exception occurs, the web container generates a default page containing the following message:

A Servlet Exception Has Occurred
But you can also specify that the container should return a specific error page for a given exception.


17.4 Creating and Initializing a Servlet
Use the @WebServlet annotation to define a servlet component in a web application. This annotation is specified on a class and contains metadata about the servlet being declared. The annotated servlet must specify at least one URL pattern. This is done by using the urlPatterns or value attribute on the annotation. All other attributes are optional, with default settings. Use the value attribute when the only attribute on the annotation is the URL pattern; otherwise, use the urlPatterns attribute when other attributes are also used.

Classes annotated with @WebServlet must extend the javax.servlet.http.HttpServlet class. For example, the following code snippet defines a servlet with the URL pattern /report:

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/report")
public class MoodServlet extends HttpServlet {
    ...
The web container initializes a servlet after loading and instantiating the servlet class and before delivering requests from clients. To customize this process to allow the servlet to read persistent configuration data, initialize resources, and perform any other one-time activities, you can either override the init method of the Servlet interface or specify the initParams attribute of the @WebServlet annotation. The initParams attribute contains a @WebInitParam annotation. If it cannot complete its initialization process, a servlet throws an UnavailableException.

Use an initialization parameter to provide data needed by a particular servlet. By contrast, a context parameter provides data that is available to all components of a web application.

17.5 Writing Service Methods
The service provided by a servlet is implemented in the service method of a GenericServlet, in the doMethod methods (where Method can take the value Get, Delete, Options, Post, Put, or Trace) of an HttpServlet object, or in any other protocol-specific methods defined by a class that implements the Servlet interface. The term service method is used for any method in a servlet class that provides a service to a client.

The general pattern for a service method is to extract information from the request, access external resources, and then populate the response, based on that information. For HTTP servlets, the correct procedure for populating the response is to do the following:

Retrieve an output stream from the response.

Fill in the response headers.

Write any body content to the output stream.

Response headers must always be set before the response has been committed. The web container will ignore any attempt to set or add headers after the response has been committed. The next two sections describe how to get information from requests and generate responses.

17.5.1 Getting Information from Requests
A request contains data passed between a client and the servlet. All requests implement the ServletRequest interface. This interface defines methods for accessing the following information:

Parameters, which are typically used to convey information between clients and servlets

Object-valued attributes, which are typically used to pass information between the web container and a servlet or between collaborating servlets

Information about the protocol used to communicate the request and about the client and server involved in the request

Information relevant to localization

You can also retrieve an input stream from the request and manually parse the data. To read character data, use the BufferedReader object returned by the request's getReader method. To read binary data, use the ServletInputStream returned by getInputStream.

HTTP servlets are passed an HTTP request object, HttpServletRequest, which contains the request URL, HTTP headers, query string, and so on. An HTTP request URL contains the following parts:

http://[host]:[port][request-path]?[query-string]
The request path is further composed of the following elements.

Context path: A concatenation of a forward slash (/) with the context root of the servlet's web application.

Servlet path: The path section that corresponds to the component alias that activated this request. This path starts with a forward slash (/).

Path info: The part of the request path that is not part of the context path or the servlet path.

You can use the getContextPath, getServletPath, and getPathInfo methods of the HttpServletRequest interface to access this information. Except for URL encoding differences between the request URI and the path parts, the request URI is always comprised of the context path plus the servlet path plus the path info.

Query strings are composed of a set of parameters and values. Individual parameters are retrieved from a request by using the getParameter method. There are two ways to generate query strings.

A query string can explicitly appear in a web page.

A query string is appended to a URL when a form with a GET HTTP method is submitted.

17.5.2 Constructing Responses
A response contains data passed between a server and the client. All responses implement the ServletResponse interface. This interface defines methods that allow you to do the following.

Retrieve an output stream to use to send data to the client. To send character data, use the PrintWriter returned by the response's getWriter method. To send binary data in a Multipurpose Internet Mail Extensions (MIME) body response, use the ServletOutputStream returned by getOutputStream. To mix binary and text data, as in a multipart response, use a ServletOutputStream and manage the character sections manually.

Indicate the content type (for example, text/html) being returned by the response with the setContentType(String) method. This method must be called before the response is committed. A registry of content type names is kept by the Internet Assigned Numbers Authority (IANA) at http://www.iana.org/assignments/media-types/.

Indicate whether to buffer output with the setBufferSize(int) method. By default, any content written to the output stream is immediately sent to the client. Buffering allows content to be written before anything is sent back to the client, thus providing the servlet with more time to set appropriate status codes and headers or forward to another web resource. The method must be called before any content is written or before the response is committed.

Set localization information, such as locale and character encoding. See Chapter 20, "Internationalizing and Localizing Web Applications" for details.

HTTP response objects, javax.servlet.http.HttpServletResponse, have fields representing HTTP headers, such as the following.

Status codes, which are used to indicate the reason a request is not satisfied or that a request has been redirected.

Cookies, which are used to store application-specific information at the client. Sometimes, cookies are used to maintain an identifier for tracking a user's session (see Session Tracking
