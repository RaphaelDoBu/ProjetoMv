# ProjetoMv
Projeto para entrevista tecnica utilizando Springboot, Thymeleaf, Bootstrap, OracleSql.

## SpringBoot
http://localhost:8080/product/list

## Docker
Foi necessário criar uma nova instancia de usuário utilizando Docker, com os seguintes comandos:
SQL Developer.

Below useful commands use in the tutorial:

1. Log into Docker hub (in order to access oracle repository)
 docker login
2. Download image
 docker pull store/oracle/database-enterprise:12.2.0.1
3. Run image
 docker run -d -p 1521:1521 --name oracle store/oracle/database-enterprise:12.2.0.1
4. Connect to container
 docker exec -it oracle bash -c "source /home/oracle/.bashrc; sqlplus /nolog"

5. Copy below script to open SQL shell

 connect sys as sysdba;
 -- Here enter the password as 'Oradoc_db1'
 
 alter session set "_ORACLE_SCRIPT"=true;
 
 create user dummy identified by dummy;
 
 GRANT ALL PRIVILEGES TO dummy;

4. Configure SQL Developer

 Username: dummy
 
 Password: dummy
 
 Hostname: localhost
 
 Port: 1521
 
 Service name: ORCLCDB.localdomain
 
## Package e Procedures Oracle Sql
    CREATE OR REPLACE PACKAGE PCK_PRODUTO AS
     PROCEDURE ATUALIZA_TODOS(VALOR NUMBER);

     PROCEDURE ATUALIZA_ESPECIFICO(ID_PRODUTO NUMBER, VALOR NUMBER);
    END;

    CREATE OR REPLACE PACKAGE BODY PCK_PRODUTO AS

      PROCEDURE ATUALIZA_TODOS(VALOR NUMBER) IS
      BEGIN
          UPDATE PRODUTO SET PRECO_PRODUTO = PRECO_PRODUTO + (PRECO_PRODUTO * (VALOR/100));
      END ATUALIZA_TODOS;

      PROCEDURE ATUALIZA_ESPECIFICO(ID_PRODUTO NUMBER, VALOR NUMBER) IS
      BEGIN
          UPDATE PRODUTO SET PRECO_PRODUTO = PRECO_PRODUTO + (PRECO_PRODUTO * (VALOR/100))
           WHERE CODIGO_PRODUTO = ID_PRODUTO;
      END ATUALIZA_ESPECIFICO;
    
    END PCK_PRODUTO;
