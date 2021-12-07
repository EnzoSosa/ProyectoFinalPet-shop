# 🧙 Magic and Strange Pet Shop

Negocio dedicado a la investigación, desarrollo y comercialización de
productos mágicos para **personas no humanas**.

## 🐈 Descripción

Nuestros clientes de Magic and Strange Pet Shop acudieron a nosotros
para mejorar su visibilidad en internet. A medida que coordinábamos con
ellos nos dimos cuenta que necesitaban algo más que una vitrina de
productos.

Por ese motivo les propusimos desarrollar una aplicación web donde
pueden no solo exponer sus productos, sino también llevar un control de
stock y recibir feedback de sus clientes.

Además se desarrolló en principio un registro y login de usuarios para
que en un futuro sus clientes puedan: comentar sobre un producto en
particular, elegir sus favoritos y hacer una tienda online con carrito
de compras.

## 🐕 Contexto

Este proyecto fue realizado como proyecto final del curso de
programación de *Egg Educación*. Curso 3, comisión A y B.

**Fecha de entrega:** 14 de octubre del 2021

### 🦝 Equipo

- Javiera Cabello
- Juan Manuel Espinosa
- Gino Fiorellini
- Pablo Bevilacqua
- Enzo Sosa
- Pedro Ragni
- Brian Gabriel Gonzalez
- Darío Parra

## 💫 Instalación

### Requisitos

- Java 8
- Maven
- MySQL 8

En MySQL debe haber una base de datos llamada `catalogo` o modificar el
archivo de configuración `application.yml`

### Pasos

Copiar cada línea en la terminal los siguientes comandos:

```
git clone https://github.com/PabloBevilacqua/pet-shop.git
cd pet-shop
mvn clean package spring-boot:run
```

Esto descarga el proyecto y todas las dependencias necesarias.

Por defecto el proyecto correrá en el puerto `8080` del localhost.

- Ingresar a `http://localhost:8080`
- Ir al formulario de registro
- Registrar un usuario
- En MySQLWorkbench o el cliente MySQL de preferencia ingresar: `UPDATE
  usuario SET rol='ADMIN' WHERE username='{nombre-de-usuario}';`
  - *Cambiar `{nombre-de-usuario}` por su nombre de usuario, sin
    corchetes*
- Volver al formulario de login, ingrese sus credenciales

## Elecciones

### 🦋 Front-end

Se optó realizar el front con una estética minimalista y a su vez
llamativa, con colores pastel y bordes redondeados.

Por este motivo determinamos que la mejor opción iba a ser escribir
nuestro propios estilos CSS y así practicar lo aprendido en el curso.

### 🥞 Back-end

En el back se realizaron algunos controladores específicos para el panel
de administración, así utilizar la etiqueta `@PreAuthorize` para manejar
todas las peticiones dentro del controlador.

## 🦮 Gracias

A todos nuestros compañeros que nos ayudaron en todo el proceso de
crecimiento desde el principio del cursado hasta la entrega final.

A todos nuestros profesores y couchs: Agustín Fiordelisi, Belén Lima
Flavia Cipolla, Gastón Vidal y Nahuel Bullón.

