# README.txt
#
# Copyright (C) 2012-2025 Rafael Corchuelo.
#
# In keeping with the traditional purpose of furthering education and research, it is
# the policy of the copyright owner to permit non-commercial use and redistribution of
# this software. It has been tested carefully, but it is not guaranteed for any particular
# purposes.  The copyright owner does not offer any warranties or representations, nor do
# they accept any liabilities with respect to them.

Acme AirNav Solutions, Inc.  (Acme ANS, Inc.  for short) is a fictitious company that 
specialises in helping airports manage their resources and coordinate operations through 
the development of software solutions.  The goal of this project is to develop a WIS to 
assist airports in managing logistics for charter flights.  This includes flight scheduling, 
booking management, crew management, incident reporting and resolution, as well as 
aircraft maintenance.  

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
 