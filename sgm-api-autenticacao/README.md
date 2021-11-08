# API Autenticacao - SGM

Api de autenticação para usuários do SGM - Autenticação baseada em token JWT com percistencia em mongoDB

## Executar localmente

Para executar localmente, use
```
npm install

npm run dev
```

Acesso a aplicação [autenticacao](http://localhost:3000/autenticacao)

## Criar um novo usuário

### POST - http://localhost:3000/sgm-autenticacao/api

Exemplo de body de novo usuário

```
{
    "nome":"admin",
    "email":"admin@gmail.com",
    "roles":"role_admin",
    "senha":"admin"
}
```

## Consultar usuário - geração de token
### GET - http://localhost:3000/sgm-autenticacao/api 

```
Utilizar na Authorization: Basic Auth passando usuario(email) e senha 
```

## Consultar token
### GET - http://localhost:3000/sgm-autenticacao/api/me

```
Utilizar na Authorization: Bearer token
```