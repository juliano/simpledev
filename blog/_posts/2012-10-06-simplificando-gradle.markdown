---
layout: post
title: Simplificando - Gradle
categories: Gradle ALM
permalink: /simplificando-gradle.html
---

Nos posts do blog sempre é mostrado o **build.grade**, o qual contém as libs dos projetos e mais algumas configurações. Nesse post vou apresentar o [Gradle][1], como ele facilita no gerenciamento do ciclo de vida da aplicação, porque usá-lo e os comandos necessários para rodar facilmente os projetos do blog.

De forma geral, o Gradle é uma ferramenta para automatizar o build de sua aplicação, tal como Maven ou Ant, porém muito mais simples do que elas. Se você ainda não usa uma ferramenta para isso, aconselho fortemente à fazê-lo, pois tudo que não é automatizado aumenta seu trabalho.

A maioria dos arquivos de build dos projetos no blog fazem as seguintes tarefas:
- cuida das dependências
- compila o código
- gera o pacote
- executa os testes
- faz o deploy da app e sobe o servidor

Gradle usa os repositórios do Maven para baixar as dependências da aplicação. Ele faz o download dos jars e cuida do classpath do projeto para você.

As tarefas que podemos executar o Gradle são chamadas através de comandos no terminal. Alguns deles são:

* **gradle build** - compila o código, gera o war, executa os testes e checa a integridade do pacote. Caso esteja faltando algum jar, ele faz o download e coloca no classpath.
* **gradle test** - compila o código e executa todos os testes.
* **gradle war** - compila o código e gera o war, muito melhor do que exportar pela IDE.

Além disso, o Gradle nos oferece várias facilidades além do que gira em torno do build, muitas delas empregadas através de plugins muito fáceis de se utilizar, que adicionamos com apenas uma linha:

<pre>apply plugin: <spam class='str'>'jetty'</spam></pre>
* **gradle jettyRun** - usando esse plugin, o Gradle nos traz um Jetty embarcado, uma excelente opção quando estamos desenvolvendo. Ao executar o comando, o código é compilado, o pacote gerado, o deploy é feito e o servidor é iniciado.

<pre>apply plugin: <spam class='str'>'eclipse'</spam></pre>
* **gradle eclipse** - Não precisamos ficar presos a uma IDE, mas geralmente não é fácil ter o mesmo projeto adequado ao formato de mais do que uma - não sem uma boa ferramenta. Gradle nos ajuda a gerar o formato de projeto para algumas IDEs, como Eclipse, IDEA e em breve Netbeans, de forma que não precisamos manter os arquivos de configurações específicos de cada IDE dentro do controle de versão.

Aqui foram mostradas algumas poucas tarefas que podemos automatizar com a ajuda do Gradle. Há muito mais que podemos fazer com ele, como minificar e compactar o conteúdo estático de um site. Você vai encontrar muito mais do que ele pode fazer na [documentação do site][2].

Usar ferramentas para automatizar nosso trabalho vai deixá-lo mais fácil e reduzir os erros que podemos cometer ao fazer certas tarefas na mão. Automatização é fundamental para manter a saúde de seu projeto!

[1]: http://www.gradle.org/
[2]: http://www.gradle.org/docs/current/userguide/userguide.html
