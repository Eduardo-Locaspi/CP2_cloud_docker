# 2. Criar network
docker network create minha-rede

# 💾 3. Criar volume do banco
docker volume create mysql_data

# 🐬 4. Subir MySQL
docker run -d \
--name mysql-rm561713 \
--network minha-rede \
-p3306:3306 \
-eMYSQL_ROOT_PASSWORD=123456 \
-eMYSQL_DATABASE=meubanco \
-eMYSQL_USER=user \
-eMYSQL_PASSWORD=123456 \
-v mysql_data:/var/lib/mysql \
mysql:8.0

#🔎 Testar MySQL
docker ps
docker logs-f mysql-rm561713

# 5. Acessar banco (opcional)
docker exec-it mysql-rm561713 mysql-u user-p
# senha = 123456

# 5.1. Dentro do Container
use meubanco;
show tables;


# 6. Subir API Spring Boot
docker run-d \
--name api-rm561713 \
--network minha-rede \
-p8080:8080 \
-v $(pwd):/app \
-w /app \
-eSPRING_DATASOURCE_URL=jdbc:mysql://mysql-rm561713:3306/meubanco \
-eSPRING_DATASOURCE_USERNAME=user \
-eSPRING_DATASOURCE_PASSWORD=123456 \
maven:3.9-eclipse-temurin-21 \
bash-c"mvn clean package -DskipTests && java -jar target/*.jar"  




# 7. Testar o CRUD com seus endpoints(POSTMAN)
# GET
http://22.220.147.230:8080/usuarios/todos
# POST
http://22.220.147.230:8080/usuarios/novo
{
    "chaveComposta": {
        "id": 3,
        "rm": "rm3"
    },
    "senha": "senha3",
    "status": "INATIVO",
    "pessoa": {
        "id": 2,
        "nome": "Pessoa 2",
        "cpf": "124",
        "dataNascimento": "2006-01-01",
        "email": "pessoa2@fiap.com.br"
    }
}

# PUT
http://22.220.147.230:8080/usuarios/atualizar/{id}/{rm}
{
    "chaveComposta": {
        "id": 3,
        "rm": "rm3"
    },
    "senha": "senhaALTERADA",
    "status": "INATIVO",
    "pessoa": {
        "id": 2,
        "nome": "Pessoa 2",
        "cpf": "124",
        "dataNascimento": "2006-01-01",
        "email": "pessoa2@fiap.com.br"
    }
}
# DELETE
http://22.220.147.230:8080/usuarios/excluir/{id}/{rm}




