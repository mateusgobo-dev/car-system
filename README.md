# car-system
Sistema de gestão de carros.   

Tecnologias utilizadas:
1. JDK 21
2. SpringBoot
3. Maven
4. React

Banco de dados: **POSTGRES**

O sistema foi divido em 2 partes:   
1. car-profile = Serviço Backend, escrito em **Java com SpringBoot**. Possui testes integrados à container (**100% desacoplado da infraestrutura**)
2. carsystem-client = Front end escrito em **React**;

Colocando o sistema no ar:   
1. Inicie um container postgres em produção, para isso execute o script:
```
docker run --rm  --name carsystem-db \
-p 5434:5432 \
-e POSTGRES_DB=carsystem-db \
-e POSTGRES_USER=sa \
-e POSTGRES_PASSWORD=123 \
-e PGDATA=/var/lib/postgresql/data/pgdata \
-v /home/containers/carsystem-db/mount:/var/lib/postgresql/data \
-d postgres
```
2. Na raíz do projeto **car-profile**, execute o arquivo comando:   
```
mvn clean ou ./mvnw clean
mvn install -DskipTests=true  ou ./mvnw install -DskipTests=true 
java -jar car-profile-0.0.1-SNAPSHOT.jar
```
3. Na raíz do projeto **carsystem-client**, execute o comando:
```
npm start
```

O serviço subirá na **porta 3000**   
O backend utiliza a **porta 8080**


