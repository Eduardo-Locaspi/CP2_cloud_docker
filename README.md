# 📦 CP2 Cloud Docker — Guia de Execução

Este projeto sobe uma arquitetura com:

- 🐬 MySQL (Docker)
- ☕ Spring Boot API (Docker + Maven)
- 🌐 Comunicação via rede Docker
- 📮 Testes via Postman

---

## ⚙️ 1. Clonar o projeto

```bash
git clone https://github.com/Eduardo-Locaspi/CP2_cloud_docker.git
cd CP2_cloud_docker
```

---

## 🌐 2. Criar rede Docker

```bash
docker network create minha-rede
```

---

## 💾 3. Criar volume do banco

```bash
docker volume create mysql_data
```

---

## 🐬 4. Subir MySQL

```bash
docker run -d \
--name mysql-rm561713 \
--network minha-rede \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=123456 \
-e MYSQL_DATABASE=meubanco \
-e MYSQL_USER=user \
-e MYSQL_PASSWORD=123456 \
-v mysql_data:/var/lib/mysql \
mysql:8.0
```

### 🔎 Testar MySQL

```bash
docker ps
docker logs -f mysql-rm561713
```

---

## 🧪 5. Acessar banco (opcional)

```bash
docker exec -it mysql-rm561713 mysql -u user -p
```

Senha:

```bash
123456
```

Dentro do MySQL:

```bash
use meubanco;
show tables;
```

> ⚠️ As tabelas só aparecem após a API rodar.
> 

---

## ☕ 6. Subir API Spring Boot

👉 Entre na pasta correta:

```bash
cd ~/CP2_cloud_docker/api/projeto-usuarios
```

---

### 🚀 Rodar API

```bash
docker run -d \
--name api-rm561713 \
--network minha-rede \
-p 8080:8080 \
-v $(pwd):/app \
-w /app \
-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-rm561713:3306/meubanco \
-e SPRING_DATASOURCE_USERNAME=user \
-e SPRING_DATASOURCE_PASSWORD=123456 \
maven:3.9-eclipse-temurin-21 \
bash -c "mvn clean package -DskipTests && java -jar target/*.jar"
```

---

### 📜 Ver logs da API

```bash
docker logs -f api-rm561713
```

---

## 📮 7. Testes no Postman

Após subir a API:

```bash
http://20.220.147.230:8080
```

Endpoints disponíveis:

GET
```bash
http://20.220.147.230:8080/usuarios/todos
```

POST
```bash
http://20.220.147.230:8080/usuarios/novo
```
```bash
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
```
PUT 
```bash
http://20.220.147.230:8080/usuarios/atualizar/3/rm3
```
```bash
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
```
DELETE
```bash
http://20.220.147.230:8080/usuarios/excluir/3/rm3
```




