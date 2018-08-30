# Projeto Estagiario

O Projeto Estagiario funciona como um serviço de cadastro de informações sobre os estagiários da SeniorSolution.
Hoje o serviço é capaz de realizar atualizações, remoções e consultas de cadastros existentes, bem como chamadas ao serviço de cálculo da aplicação.


## INSTALL

```shell
mvn clean install -U
```


## RUN

```shell
mvn spring-boot:run
```


## TEST

Rodando teste unitário:

```shell
mvn tests
```

Rodando teste integrado:

```shell
mvn failsafe:integration-test
```

Rodando Sonarqube:

```shell
mvn sonar:sonar
```


## TECNOLOGIAS

* [Jackson](https://github.com/FasterXML/jackson-databind) - Pacote geral de ligação de dados para Jackson
* [Spring Boot](https://spring.io/) - Framework de para início da aplicação
* [Java 8](https://www.java.com/en/) - Versão do Java utilizada
* [Maven](https://maven.apache.org/) - Gerenciador de dependências
* [Swagger](https://swagger.io/) - Documentação de requisições
* [Lombok](https://projectlombok.org/) - Deixa o código menos verboso
* [MongoDB](https://www.mongodb.com/) [em memória](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo) - Testes com acesso a banco de dados NoSQL em memória
* [QueryDSL](http://www.querydsl.com/) - Geração de queries dinâmicas complexas, na camada de serviço
* [Fixture Factory](https://github.com/six2six/fixture-factory) - Gerar objetos falsos a partir de templates
* [Mockito](http://site.mockito.org/) - Mock para simulação de serviços para testes
* [Hamcrest](http://hamcrest.org/) - Ferramenta de comparação para testes
* [Feign](https://github.com/OpenFeign/feign) - Torna a escrita de clientes Java http mais fácil
* [Ribbon](https://github.com/Netflix/ribbon) - Biblioteca de comunicação entre processos (chamadas de procedimento remoto) com balanceadores de carga de software incorporados
* [Hystrix](https://github.com/Netflix/Hystrix) - Possibilita criar fallback para serviços e interromper a falha em cascata em sistemas distribuídos complexos onde a falha é inevitável
* [Eureka](https://github.com/Netflix/eureka) - Registro e descoberta de serviços
* [Spring Config](https://github.com/spring-cloud/spring-cloud-config) - Fornece suporte ao servidor e ao cliente para a configuração externalizada em um sistema distribuído
* [CucumberJVM](https://cucumber.io/docs/reference/jvm) - Implementação do Cucumber para as linguagens JVM mais populares
* [Spring Boot Admin](http://codecentric.github.io/spring-boot-admin/current/#register-clients-via-spring-boot-admin) - Registro de cliente do Spring Boot Admin Server
* [FF4J](https://github.com/ff4j/ff4j) - Feature Flipping para Java, facilitado 
* [Rest Assured](https://github.com/rest-assured/rest-assured) - DSL Java para testes fáceis de serviços REST
* [Sleuth](https://github.com/spring-cloud/spring-cloud-sleuth) - Traçado distribuído para Spring Cloud
* [Docker](https://docs.docker.com/) - Fornece uma maneira de executar aplicações isoladas de forma segura em um recipiente, embaladas com todas as suas dependências e bibliotecas
* [JGitFlow](https://bitbucket.org/atlassian/jgit-flow/overview) - Implementação Java do modelo de ramificação de Vincent Driessen
* [JenkinsFile](https://jenkins.io/doc/book/pipeline/jenkinsfile/) - Implementar toda a compilação/teste/implantação de um projeto em um arquivo Jenkins e armazenar isso ao lado de seu código
* [Sonarqube](https://www.sonarqube.org/) - Qualidade contínua do código