---
layout: post
title: Simplificando - Struts 2
category: Struts 2
permlink: /simplificando-struts2.html
last_updated: 2012-10-07
---

A maior parte do conteúdo sobre Struts 2 que encontramos por aí ensina a usar o framework de uma forma
bastante complicada. Não gosto muito do Struts 2, acho um framework ultrapassado, que fica devendo muito
para as outras opções que temos hoje. Ainda sim, muitos precisam trabalhar com ele, então vou simplificar
nesse post a forma de trabalhar com esse framework.

Esse exemplo será bem simples, mostrando apenas como fazer uma requisição e o redirecionamento para outra
action, isso sem utilizar XML, sem herdar de ActionSupport, sem implementar Action ou nada que é comumente
usado. Aqui, só será necessário fazer uso do pacote de convenções do Struts 2 e de algumas de suas anotações.

Primeiro, a configuração. Abaixo, o build.gradle do projeto.

* build.gradle *

E a configuração que deve ser colocada no web.xml:

* web.xml *

Agora vou criar uma classe com 3 actions, uma para listar carros, uma que faz o direcionamento para o 
formulário de inserção de um novo carro, e uma que salva o novo no banco. A classe fica assim:

* CarroAction *

Como diria Jack, o Estripador, vamos por partes:

 
