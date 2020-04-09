# Web Client for IBM Websphere MQ.

Este es un cliente minimalsta para MQ con el propósito de enviar y recibir mensajes a equipos con MQ de la manera mas sencilla posible.

Este es un servicio REST creado en Spring Boot. Utiliza la dependencia al cliente de MQ

		<dependency>
			<groupId>com.ibm.mq</groupId>
			<artifactId>com.ibm.mq.allclient</artifactId>
			<version>9.1.5.0</version>
		</dependency>

Existen dos formas de utilizar esta aplicacion.

1. Por medio del index.html el cual tiene un formulario que permite llenar todos los datos necesarios. Almacena favoritos en el local storage del buscador.

2. Consumiendo el servicio REST enviando la informacion con un POST a /message el cual recibe la siguiente informacion en formato JSON.

{
    message:"Mensaje a enviar codificado a Base64",
    toQueue:"Cola destino",
    replyTo:"Cola donde se espera la respuesta. Blanco si no se espera ninguna",
    queueManager:"Nombre del gestor de colas donde requiere conectarse",
    host:"Direccion IP o nombre de host en donde esta el gestor de colas remoto",
    port:Puerto del gestor de colas, el default es 1414,
    channel:"Nombre del canal de conexion al servidor requerido por el GESTOR, el default es SYSTEM.DEF.SVRCONN",
    timeout:Tiempo a esperar por la respuesta, ignorado si replyTo viene vacio.
}

De momento no soporta conexión a un servidor de MQ local por medio de enlaces o especificar usuario ni contraseña.

josevargasr@gmail.com

