# no-framework-webapp
The aim is to develop a web application, which doesn't need to use frameworks. 

## Purpose

## What dependencies do you need? 

```groovy
implementation ('org.eclipse.jetty:jetty-servlet:11.0.2')
```

```groovy
implementation group: 'ch.qos.logback', name: 'logback-classic', version: '1.2.3'
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

If it needs to remove the servlet, the container finalizes the servlet by calling the servlet's `destroy` method.

<p align="center">
  <img src="https://user-images.githubusercontent.com/48289901/119905545-bdf06480-bf4c-11eb-99d1-44cf34f41740.jpg" alt="servlet"/>
</p>

Di seguito alcune informazioni per poterla testare:
I comandi docker da utilizzare sono i seguenti per la build dell'image e per testare i tre distinti step:
docker build -t mytest .

docker container run -it -p 9090:9090 mytest ./scripts/build.sh
docker container run -it -p 9090:9090 mytest ./scripts/tests.sh
docker container run -it -p 9090:9090 mytest ./scripts/run.sh

     2. Il file JSON contenente la mappa dovrà essere inserito nella folder:

src/main/resources

         e dovrà essere etichettato con il nome map.json  

    3. Mediante il commando docker container run -it -p 9090:9090 mytest ./scripts/run.sh viene lanciata l'applicazione, con la quale si può interagire in modalità:
applicazione desktop: in questo caso l'ID della room e la lista degli oggetti da collezionare viene inserita mediante console (Figura 1). In output verrà restituita la tabella contente ID, ROOM_NAME, OBJECTS.

Figura 1: desktop app​

​Web application: l'applicazione mette a disposizione un servizio RESTful che è raggiungibile all'indirizzo: localhost:9090/api/v1/mazeroutepuzzle .  Quest'ultimo è stato testato mediante il tool Postman, mediante il quale è possibile inviare richieste POST HTTP, il cui body presentava l'ID della room e la lista degli oggetti da collezionare in formato JSON (Figura 2).

Figura 2: HTTP request's body

                 All'interno del body della risposta il servizio RESTful restituisce la tabella contente ID, ROOM_NAME, OBJECTS in formato JSON. 
                 
                 Nota: In allegato trovate un esempio di richiesta da poter importare direttamente in Postman in modo da testare la web application.
