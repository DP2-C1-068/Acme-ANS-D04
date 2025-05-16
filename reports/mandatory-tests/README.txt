Esta carpeta esta pensada para albergar los test que se ejecutan en la versión del proyecto en la
cuál se había realizado el testing formal para todos los requisitos funcionales menos los requisitos
GRUPALES SUPLEMENTARIOS II.

La creación de esta nueva entidad que alberga objetos en la base de datos ha hecho que los test no sean
reproducibles, pues en los casos de prueba donde se crea una nueva entidad, el identificador que recibe no 
es el que espera la base de datos, pues el primero libre de cuando se realizó el testing formal no es el
primero libre ahora. (Antes el primero libre era id:217 y ahora es id:266)

Por este motivo, esta versión del proyecto solo cuenta con los test relativos a la entidad BannedPassenger,
la cual es la correspondiente a los requisitos funcionales relativos a GRUPAL SUPLEMENTARIO II.