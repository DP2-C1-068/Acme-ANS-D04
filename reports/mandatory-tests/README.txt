Esta carpeta está destinada a albergar los tests correspondientes a la versión del proyecto en la 
que se llevó a cabo el testing formal de todos los requisitos funcionales, con la excepción de los 
requisitos Grupales Suplementarios II.

################### ACLARACIONES A LA HORA DE LA CORRECCIÓN#############################

Este proyecto está destinado a albergar los tests correspondientes a los requisitos funcionales
Grupales Suplementarios II, relativos a la entidad "BannedPassenger". En este proyecto, se deben
corregir los aspectos funcionales y de formal testing de esta entidad, el resto de requisitos funcionales
estan implementados en el otro proyecto entregado. Por otra parte, todos los reportes deben ser corregidos
en este proyecto, ya que es donde están en su última version.


La separación en dos proyectos se debe a los siguientes motivos:

1. La incorporación de una nueva entidad que persiste objetos en la base de datos ha provocado que 
los tests anteriores no sean reproducibles. En los casos de prueba donde se crea una nueva entidad, 
el identificador asignado no coincide con el esperado, ya que el primer ID libre ha cambiado desde 
que se realizó el testing formal. Por ejemplo, anteriormente el primer ID libre era 217, mientras 
que ahora es 266.

2. se detectó una pequeña errata durante las pruebas: el mensaje de validación en inglés 
asociado a la clave "acme.validation.header.message", utilizado en anotaciones @ValidShortText 
(para restringir atributos a entre 1 y 50 caracteres), mostraba incorrectamente: 
"Must be between 1 and 75 characters". Este error ha sido corregido en la nueva versión del proyecto, 
donde ahora se muestra el mensaje adecuado: "Must be between 1 and 50 characters".

Por estos motivos, esta versión del proyecto solo incluye los tests correspondientes a la entidad 
"BannedPassenger", que está relacionada con los requisitos funcionales del bloque Grupal Suplementario II.
 