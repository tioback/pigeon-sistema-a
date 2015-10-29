# Prática para Pixeon - Sistema-A - Renato Back

O cenário desta prática é criar um sistema que centralize informações de exames de diversas clínicas para que o paciente possa consultar. O sistema armazena apenas informações do fictício Padrão X. Por opção arquitetural, o Sistema A não armazena imagens, ao invés disso, ele permite às clínicas mapearem um serviço que dê acesso às imagens dos exames. 

O objetivo principal era projetar e implementar a funcionalidade que mapeasse os serviços de imagem de cada clínica de forma transparente para o usuário. Também deve ser evitada a necessidade de novas versões do sistema caso se queira adicionar uma nova clínica.

O sistema já trás em memória alguns dados que permitem validar os objetivos da prática. Contudo, visando exemplificar e demonstrar essas funcionalidades, este sistema fornece três telas muito simples de cadastro de pacientes, clínicas e exames.

## Requisitos

Para executar o projeto, os seguintes requisitos precisam ser atendidos:

1. Tomcat 7 ou superior;
2. JDK 8 ou superior;
3. Navegador (FireFox, Opera, Safari, IE, Chrome, etc).

## Funcionalidades do sistema

Para testar as funcionalidades do sistema é preciso:

1. Fazer deploy do arquivo sistema-a.war no Tomcat.
2. Acessar http://localhost:8080/sistema-a/ (Obs.: a porta pode variar, dependendo da configuração do Tomcat).
3. Através do menu inicial é possível:

> * Cadastrar um novo Paciente;
> * Listar todos os Pacientes;
> * Cadastrar uma nova Clínica;
> * Listar todas as Clínicas;
> * Cadastrar um novo Exame

## Explicação

### Estrutura

Utilizou-se uma abordagem multicamadas, com separação das lógicas de modelagem, apresentação, serviço, persistência e controle, representadas respectivamente pelos sub-pacotes de br.tioback.pixeon.sistema_a: entity, web.printer, service, persistence e web.servlet.

#### Apresentação (br.tioback.pixeon.sistema_a.web.printer)

No estado atual, o sistema funciona apenas na apresentação HTML. Iniciou-se o desenvolvimento para JSON, mas nem todas as funcionalidades foram concluídas. As funções GET podem ser testadas por clientes REST adicionando o cabeçalho HTTP "Accept" com o valor "text/json". Por padrão, o sistema trabalha sempre como "text/html", facilitando a operação via browser. As principais classes da apresentação são os ResponsePrinter's.

#### Controle (br.com.tioback.pixeon.sistema_a.web.servlet)

O sistema funciona em cima de Servlets e suas operações são associadas aos métodos HTTP: GET, POST, PUT e DELETE. Até o momento, apenas os métodos GET e POST estão implementados. Os servlets ficaram responsáveis pela navegação das requisições pelas áreas de negócio do sistema. Não foi adicionado no sistema um tratamento de erros adequado, o que vital no caso de ser utilizado em ambiente de produção.

#### Modelo (br.com.tioback.pixeon.sistema_a.entity)

As entidades do sistema são:

* Clínica

> Possui informações referentes à Clínica e ao sistema que ela opera, como a template da URL do serviço de imagens o nome da clínica e um identificador único gerado pelo sistema.

* Exame

> Armazena informações de relacionamento com a Clínica, o Paciente e a quantidade de imagens, além de um identificador único, gerado pelo sistema.

* Paciente

> Representa as informações do Paciente, como o nome e um identificador único, gerado pelo sistema.

#### Serviço/Regras de Negócio (br.tioback.pixeon.sistema_a.service)

As classes de serviço são responsáveis pela execução das regras de negócio e encaminhamento das requisições para a camada de persistência. É nelas que é feita a conversão a URI relativa das imagens para a URI real do sistema de imagens da clínica com base no template da URI a ela associado e nos valores dos atributos fornecidos pelo exame.

#### Persistência (br.tioback.pixeon.sistema_a.persistence)

Compreende as classes do padrão de projeto DAO (Data Access Object), que facilita a migração da aplicação para outra lógica de persistência, caso desejado. O modelo atual utiliza HashMaps em memória, desaconselhado para um ambiente de produção, mas conveniente para a demonstração da aplicação. Por se tratar de dados em memória, sempre que a aplicação é encerrada, as alterações são perdidas. Ao iniciá-la novamente, alguns dados são pré-carregados para permitir a demonstração da aplicação sem necessidade inserir novos registros.

### Abordagem para solucionar o problema

Utilizei os seguintes princípios para elaborar minha abordagem: que as Clínicas só possuem um serviço de imagem; as imagens se encontram sempre nas clínicas; e, que quaisquer parâmetros para os serviços de imagem são disponibilizados pelo Padrão X.

* Como cada clínica só tem um sistema de imagens, a informação do template da URL fica nela.
* Como os parâmetros para os serviços de imagem são disponibilizados no Padrão X e este define os as informações dos exames, considerei que apenas os atributos dos exames são utilizados nas URIs dos serviços, no meu caso, clinicaId, exameId, pacienteId e imagemId.
* O template das URLs possui placeholders para serem substituídos com as informações dos exames, por exemplo:

	> Template: http://clini.ca/imagem/${imagemId}
	
	> Exame: { "id" : 1, "pacienteId" : 1, "clinicaId" : 1, "imagens" : [ 1, 2 ] }
	
	> Resultado: http://clini.ca/imagem/1
	
* Considerando que as imagens são responsabilidade do serviço da clínica e que o Sistema A apenas deve redirecionar, não me preocupei em validar se a imagem existe ou se o serviço está disponível. Desta forma, o cliente apenas recebe um link e, quando nele clica, o Sistema A faz um HTTP Temporary Redirect (Status 302) para o link montado a partir do template, saindo do domínio do Sistema A.

### Melhorias

1. Utilizar servidor web embarcado (ex.: Jetty)
2. Concluir implementações JSON
3. Migrar persistência para JPA
4. Migrar persistência de memória para algum DBS externo da aplicação
5. Utilizar balanceador de carga para permitir escalabilidade.
6. Utilizar JSF nas telas de cadastro