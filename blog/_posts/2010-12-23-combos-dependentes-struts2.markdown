---
layout: post
title: Combos dependentes com Struts 2
category: Struts2
permalink: /combos-dependentes-struts2.html
last_updated: 2012-07-19
---

Este é um exemplo do clássico problema dos combos dependentes, do tipo estado - cidade.
Por si só, o suporte a ajax do Struts 2 não é suficiente, então é necessário fazer uso de um 
[plugin para JSON][1] que facilita esse trabalho. Para usá-lo, além da [configuração padrão para uso 
do Struts 2][2], é preciso adicionar mais um jar nas dependências do [build.gradle][3]:

<pre>compile <span class="str">'org.apache.struts.xwork:xwork-core:2.3.4'</span></pre>

E está pronto para utilizar. Abaixo está a classe com as duas Actions, a que popula o combo de 
estados da forma usual, e a que popula o combo de cidades com json:

<script src="https://gist.github.com/3146833.js?file=LocalidadeAction.java"></script>

Até aqui, poucas novidades. A anotação **@ParentPackage(“json-default”)** é usada para extender 
o pacote do JSON, seu uso é necessário para converter as informações para esse formato ao 
enviá-las para a página.

A maior diferença está na anotação result da action cidades:

<pre>
@Result<span class="b">(</span>name <span class="b">=</span> <span class="str">"ok"</span>, type <span class="b">=</span> <span class="str">"json"</span>, params <span class="b">= {</span> <span class="str">"excludeProperties"</span>, <span class="str">"estados, estado"</span> <span class="b">})</span>
</pre>

O **type=”json”** indica o formato da resposta da action. Só isso é suficiente para que quando 
chamada, a action serialize os dados disponíveis no formato JSON, assim todo método **get** que 
disponibiliza alguma informação para a página vai trazer seus valores nesse formato. Para listar 
apenas as cidades é necessário usar o **params**, passando **“excludeProperties”** como chave 
e em seguida os valores que não devem ser serializados separados por vírgula. Para fazer um 
teste, remova o params, e será possível ver os demais valores usando uma ferramenta como o 
firebug (plugin do firefox).

Abaixo, o código da localidade.jsp.

<script src="https://gist.github.com/3146833.js?file=localidade.jsp"></script>

E o populaCidades.js, que usa o jQuery para fazer a requisição e montar o combo com o resultado.

<script src="https://gist.github.com/3146833.js?file=populaCidades.js"></script>

Creio que com as tags do Struts 2 seja possível fazer isso sem precisar fazer uso do jQuery, 
mas prefiro não usá-las. Prefiro usar o jQuery eu mesmo a acreditar no Struts 2 funcionando sozinho.

<br>
<a href="https://github.com/juliano/simpledev/tree/master/struts2">
  <img src="/images/github.png"> <strong>Código do exemplo.</strong>
</a>

[1]: https://cwiki.apache.org/confluence/display/WW/JSON%20Plugin
[2]: /simplificando-struts2.html
[3]: /simplificando-gradle.html
