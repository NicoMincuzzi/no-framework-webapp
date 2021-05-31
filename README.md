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
