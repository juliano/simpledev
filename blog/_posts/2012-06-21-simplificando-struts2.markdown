---
layout: post
title: Simplificando - Struts 2
category: Struts2
permlink: /simplificando-struts2.html
last_updated: 2012-10-07
---

A maior parte do conteúdo sobre Struts 2 que encontramos por aí ensina a usar o framework de uma forma
bastante complicada. Não gosto muito do Struts 2, acho um framework ultrapassado, que fica devendo muito
para as outras opções que temos hoje. Ainda sim, muitos precisam utilizá-lo, então vou simplificar
nesse post a forma de trabalhar com esse framework.

Esse exemplo será bem simples, mostrando apenas como fazer uma requisição e o redirecionamento para outra
action, isso sem utilizar XML, sem herdar de ActionSupport, sem implementar Action ou nada que é comumente
usado. Aqui, só será necessário fazer uso do pacote de convenções do Struts 2 e de algumas de suas anotações.

Primeiro, a configuração. Abaixo, o build.gradle do projeto.

<script src="https://gist.github.com/3106705.js?file=build.gradle"></script>

E o web.xml:

<script src="https://gist.github.com/3106705.js?file=web.xml"></script>

Agora vou criar uma classe com 3 actions, uma para listar carros, uma que faz o direcionamento para o 
formulário de inserção de um novo carro, e uma que salva o novo no banco. A classe fica assim:

<script src="https://gist.github.com/3106705.js?file=CarroAction.java"></script>

Como diria Jack, o Estripador, vamos por partes:

<pre>
@Namespace<span class="b">(</span>value <span class="b">=</span> <span class="str">"/carro"</span><span class="b">)</span>
</pre>

Uso a anotação @Namespace para indicar na url em que área do sistema o usuário está. Por exemplo, 
podemos separar as áreas de uma empresa dessa forma, como rh, contábil, jurídico, etc. 

<pre>
<span class="b">public class</span> <span class="cl">CarroAction</span>
</pre>

Repare que não é necessário herdar de ActionSupport e nem implementar Action, removendo as 
costumeiras dependências em relação ao Struts 2. Usando o pacote de convenções, só é necessário que a 
action esteja em um pacote nomeado como action, actions ou struts2, e o framework vai encontrá-la.

<pre><span class="b">public <span class="mc">CarroAction</span>() {
    this</span>.<span class="at">dao</span> <span class="b">= new</span> JdbcCarroDao<span class="b">();
}</span></pre>

O Dao é uma dependência da Action, portanto o ideal seria injetá-lo pelo construtor, mas achei 
melhor instânciá-lo diretamente para não aumentar a complexidade do exemplo, já que aqui o Struts 2 
é usado sozinho. O ideal seria injetá-lo com auxílio de um Container de IoC, como Spring ou Pico 
Container. Isso será mostrado em outro post.

<pre>@Action<span class="b">(</span>value <span class="b">=</span> <span class="str">"lista"</span>, results <span class="b">=</span> @Result<span class="b">(</span>name <span class="b">=</span> <span class="str">"ok"</span>, location <span class="b">=</span> <span class="str">"/carro/lista.jsp"</span><span class="b">))
public</span> String <span class="b"><span class="mc">lista</span>() {</span>
    carros <span class="b">=</span> dao.<span class="at">lista</span><span class="b">();
    return</span> <span class="str">"ok"</span><span class="b">;</span>
}</pre>

Usando a anotação @Action, indicamos que esse método deve responder as requisições. Para chamar 
essa action, a url é feita no formato http://servidor/nomedaaplicacao/namespace/action. Caso não 
utilize o namespace, ele é omitido também da url. Fica assim:

http://localhost:8080/struts2simples/carro/lista

Porém, enquanto trabalhamos com o Gradle, o nome do projeto não vai na url, ficando assim:

http://localhost:8080/carro/lista

Repare que a referência para a action é definida no parâmetro value, então desde que esse não mude, 
o nome do método pode ser alterado a qualquer momento, sem que haja impacto nas urls da aplicação.

A anotação @Result indica para onde a action redireciona após ser executada. É possível ter mais 
de um redirecionamento baseado no parâmetro name (que pode variar de acordo com o retorno do método 
anotado), mas é interessante manter apenas um redirecionamento (sempre que possível), para não 
aumentar a complexidade. Para manter uma boa organização, mantenha as jsps relacionadas na mesma pasta.

Abaixo, a lista.jsp, redirecionada do método lista:

<script src="https://gist.github.com/3106705.js?file=lista.jsp"></script>

Evitei o uso das tags do Struts, somente jstl é suficiente para iterar pela lista, além 
de achar bem mais simples trabalhar com ela. É aqui que o método getCarros é chamado, por 
isso é necessário que ele esteja disponível na action.

Ao clicar no link “novo”, é chamada a action 'novo':

<pre>@Action<span class="b">(</span>value <span class="b">=</span> <span class="str">"novo"</span>, results <span class="b">=</span> @Result<span class="b">(</span>name <span class="b">=</span> <span class="str">"ok"</span>, location <span class="b">=</span> <span class="str">"/carro/adiciona.jsp"</span><span class="b">))
public</span> String <span class="b"><span class="mc">novo</span>() {
    return</span> <span class="str">"ok"</span><span class="b">;
}</span></pre>

Que apenas redireciona para outra página. Apesar da action não fazer nada além disso, é uma boa 
prática nunca redirecionar direto para a jsp, sempre devemos passar pela action antes. Isso nos leva 
para a adiciona.jsp:

<script src="https://gist.github.com/3106705.js?file=adiciona.jsp"></script>

O método setCarro deve existir para permitir que o Struts faça o trabalho de popular o objeto, 
quando usamos name=“objeto.atributo”. Ao submeter o form, é chamada a action adiciona:

<pre>@Action<span class="b">(</span>value <span class="b">=</span> <span class="str">"adiciona"</span>, results <span class="b">=</span> @Result<span class="b">(</span>name <span class="b">=</span> <span class="str">"ok"</span>, type <span class="b">=</span> <span class="str">"redirectAction"</span>, params <span class="b">= {</span><span class="str">"actionName"</span>, <span class="str">"lista"</span> <span class="b">}))
public</span> String <span class="b"><span class="mc">adiciona</span>() {</span>
    dao.<span class="at">adiciona</span><span class="b">(</span>carro<span class="b">);
    return</span> <span class="str">"ok"</span><span class="b">;
}</span></pre>

Ao inserir o novo carro, é interessante apresentar a lista de carros cadastrados. A diferença 
aqui está no @Result, o atributo type é usado para indicar que o redirecionamento não é para 
uma jsp como nos casos anteriores, mas para a action passando “redirectAction” ao atributo e 
em seguida usando params para informar qual action será chamada. Esse atributo funciona como 
um “mapa informal”, para usá-lo devemos passar os valores no formato chave – valor.

Aqui foi apresentada uma forma simples de trabalhar com o Struts 2, que apesar de não ser a melhor 
ferramenta existente, pode trazer várias facilidades para o desenvolvimento web quando bem explorada.



