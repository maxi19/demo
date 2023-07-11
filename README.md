# demo
Proyecto springboot para el alta de usuario usando jwt


1-setear los datos del datasource en el archivo application properties

db=usuariosdb
host=localhost
port=3306
user=root
password=admin

2-Ejecutar la clase DemoApplication.java

3- ejecutar desde el browser la url del swagger http://localhost:8080/swagger-ui/index.html#

4-para generar un token ejecutar el endpoint http://localhost:8080/users/sign-up
json ejenmplo :
    {
    "name": "mrunix",
    "email": "maxi21@gmail.com",
    "password": "fdwfewfewffe"
    }
respuesta de ejemplo
    {
    "id": "a81572e1-1849-49bf-9c82-9141b34bceea",
    "created": "2023-07-10",
    "lastLogin": null,
    "token": "token_de_ejemplo",
    "active": false
    }

5-localhost:8080/users/login/{token}
respuesta de ejemeplo
    {
    "id": "3dd3e959-4689-41ab-ad43-718d18641152",
    "created": "2023-07-10",
    "lastLogin": null,
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXhpMjFAZ21haWwuY29tIiwiZXhwIjoxNjg5MDU3NjQzLCJpYXQiOjE2ODkwMzk2NDN9.82a8SZ117FlamfJWgCsprMBpGBsx7u4JNeuiXYjNu_vHQ9S-KYn4CyN-udcj0BJaioNY_xtXLEUr0wqauLsk4w",
    "isActive": null,
    "name": "mrunix",
    "email": "maxi21@gmail.com",
    "password": "fdwfewfewffe"
    }
