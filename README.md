# STD029006 - Lista de Exercicio 01: Sistema de lista de exercícios cliente e servidor

Desenvolva uma aplicação, seguindo o modelo cliente e servidor, para atuar como um sistema
para resolução de listas de exercícios pelos alunos da disciplina de Sistemas Distribuídos. O aluno
fará uso da aplicação cliente para conectar na aplicação servidora. Ao se conectar, o aluno precisa
informar sua matrícula e sua senha. Após isso, a aplicação servidora deverá apresentar as questões,
uma por vez, e assim que o aluno responder uma questão, a aplicação servidora deverá apresentar a
próxima. Quando não houverem mais questões, a aplicação servidora deverá informar para o aluno
o total de questões que ele respondeu, bem como o total de questões que ele acertou. Por fim, a
conexão pode ser encerrada.

A conexão do cliente com a aplicação servidora pode ser interrompida antes que o aluno termine
de responder toda a lista de exercícios. Dessa forma, a aplicação servidora deverá manter em
memória o progresso de cada aluno. Quando o aluno fizer a nova conexão, então a aplicação
servidora deverá apresentar a próxima questão que ainda não fora respondida pelo aluno. Se não
houverem mais questões para serem respondidas por aquele aluno, então a aplicação servidora
deverá informar para o aluno o total de questões que ele respondeu, bem como o total de questões
que ele acertou. O progresso dos alunos só poderá ser perdido se a aplicação servidora for reiniciada.

As questões deverão ser somente do tipo de múltipla escolha. Ou seja, cada questão deverá
ter um enunciado e 4 alternativas, sendo que somente uma delas deverá ser considerada como
a alternativa correta. É necessário que você elabore 5 questões sobre assuntos que já foram
apresentados até aqui na disciplina de Sistemas Distribuídos. Essas questões também farão parte
de sua avaliação nessa lista de exercício 01. A lista de questões, bem como a lista de alunos e
senhas, podem ser estáticas e mantidas em memória. Não há necessidade de fazer rotinas para
manutenção das mesmas (adicionar, remover, alterar).

As aplicações cliente e servidora poderão ser desenvolvidas na linguagens C ou Java e devem
usar obrigatoriamente a API de sockets. Faça uma composição Docker para permitir que o código do
cliente e do servidor sejam executados em diferentes contêineres em uma mesma rede Docker.

## Compilando e executando o projeto

W.I.P

## Fluxograma Básico do projeto

![Fluxograma](/Imagens/FluxogramaQuizApp.png)

## Rascunho de Classes UML

![UML](/Imagens/RascunhoUML.png)

## Questões de Sistemas Distribuídos

1-O conceito de transparência pode ser aplicado a diversos aspectos de um sistema distribuído. Assinale a alternativa correta quanto ao tipo de transparência e a respectiva descrição.
1) Acesso: mostra o lugar onde um recurso está localizado.
2) Replicação: oculta que um recurso é replicado. (Correta)
3) Falhas: mostra a falha e a recuperação de um recurso.
4) Localização: usuário precisar estar ciente que os recursos físicos vieram e foram mudados de local.

2-Qual destas características, não fazem parte do conceito de Sistemas Distribuídos:
1) Concorrência;
2) Sistema assíncrono;
3) Partilha de recursos;
4) Homogeneidade. (Correta)


3-O que é um Middleware?
1) Software que gerencia as comunicações entre as aplicações que estão entre a aplicação que você está oferecendo e entre os hardware que compõem o sistema distribuído. (Correta)
2) Hardware que gerencia as comunicações entre as aplicações que estão entre o hardware que você está oferecendo e entre os hardware que compõem o sistema distribuído.
3) Sistema Operacional que gerencia as comunicações entre os sistemas operacionais que estão entre a aplicação que você está oferecendo e entre os hardware que compõem o sistema distribuído.
4) Sistema distribuído que gerencia as comunicações entre os Hardware que estão entre a aplicação que você está oferecendo e entre os aplicações que compõem o sistema operacional.

4-Qual destas formas não representam os estilos de arquitetura para se projetar uma aplicação de Sistema Distruibuido:
1) Camadas;
2) Objetos;
3) Realista; (Correta)
4) Espaços de dados compartilhados;

5-O que é um modelo cliente
1) Uma aplicação que é modelo como um conjunto de serviços que são providos pelos cliente e um conjunto de servidores que acessam esses serviços.
2) Uma aplicação que é modelo como um conjunto de serviços que são providos pelos servidores e um conjunto de clientes que acessam esses serviços. (Correta)
3) Um seviço que é modelado como um conjunto de aplicações que são providos pelos clientes e um conjunto de servidores que acessam essas aplicações.
4) Um sistemas que é modelado como um conjunto de serviços que são providos pelo servidores e um conjunto de cliente que acessam esses sistemas.


## Requisitos

|Requisistos|Concluído|
|---|---|
|Aplicação Cliente|ok|
|Aplicação Servidor|ok|
|Permitir Ocorrer Interrupção|ok|
|Questões de Sistemas Distribuídos|ok|
|Lista de Alunos|ok|
|Docker Compose|-|
