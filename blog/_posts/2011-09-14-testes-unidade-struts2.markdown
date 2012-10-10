---
layout: post
title: Testes de Unidade - Struts 2
categories: Struts2 Testes
permalink: /testes-unidade-struts2.html
last_updated: 2012-10-08
---

Testes são uma forma de garantir que a aplicação está funcionando corretamente, e quando é preciso alterá-la, através deles garantimos que ela vai continuar funcionando após feitas as alterações. Aqui vou mostrar como devem ser feitos os testes de unidade das actions quando trabalhamos com Struts 2.

Serão criados aqui os testes para a CarroAction, do post [Simplificando - Struts 2][1]. Primeiro, será necessário fazer uma pequena alteração na classe. O construtor da classe agora vai ter que receber o dao ao invés de criá-lo. Fica assim:

<pre>
<span class="b">public</span> <span class="mc">CarroAction</span>(<span class="b">final</span> CarroDao dao) <span class="b">{
    this.</span>dao <span class="b">=</span> dao<span class="b">;
}</span>
</pre>

E para fazer os testes, serão adicionados ao [build.gradle][7] os jars do [JUnit][2] e do [Mockito][3]:

<pre>
testCompile <span class="str">'junit:junit:4.10'</span>
testCompile <span class="str">'org.mockito:mockito-core:1.9.0'</span>
</pre>

Abaixo, a classe de testes. Vou explicar todos os passos, caso o leitor não tenha conhecimento sobre JUnit ou sobre mocks.

<script src="https://gist.github.com/3852794.js?file=CarroActionTest.java"></script>

Primeiro é criada uma instancia de CarroAction, a qual será testada. Repare que somente essa classe deve ser testada, não precisamos que o dao efetivamente salve os objetos passados para ele onde quer que seja, mas precisamos que ele simule esse comportamento, por isso será criado um mock de CarroDao. Um mock é um objeto que simula o comportamento de um objeto real (nessa caso, do CarroDao), que vai se comportar da forma que nós determinarmos – isso será mostrado à seguir.

O método setUp é anotado com **@Before**, uma anotação do JUnit que indica que esse método deve ser chamado antes de cada teste. **MockitoAnnotations.initMocks(this)** inicializa as classes anotadas com **@Mock**, e isso feito, criamos uma nova action, passando o dao mockado.

Cada método anotado com **@Test** será um teste para o JUnit. Assim com o nome diz, nosso primeiro teste vai listar todos os carros (lembrando que [nomes significativos são muito importantes!][4]), e é aqui que devemos dizer para o dao como ele deve se comportar:

<pre>
when<span class="b">(</span>dao.<span class="at">lista</span><span class="b">()).</span><span class="at">thenReturn</span><span class="b">(</span>Collections<span class="b">.</span><span class="at">singletonList</span><span class="b">(new</span> Carro<span class="b">(</span><span class="at">1L</span>, <span class="str">"Camaro"</span>, <span class="at">2012</span><span class="b">)));</span>
</pre>

O Mockito tem uma interface fluente que facilita muita a configuração dos mocks. Aqui instruímos nosso dao mockado da seguinte maneira: “quando seu método lista for chamado, retorne essa lista com um carro só”, assim conseguimos simular uma busca no banco de dados. Em um banco de dados real existem inúmeros registros, mas repare que não precisamos de mais do que um para o teste, ele só precisa retornar uma quantidade de dados suficientes para que nossa action possa trabalhar com eles. Se a action deve trazer uma lista de carros, uma lista contendo um carro é o suficiente.

O método lista retorna uma String, mas não a testei porque ela é para uso do Struts 2, e como sempre retorno “ok”, não achei necessário. Caso o método possa retornar diferentes Strings, deve ser criado um teste considerando isso.

Através do método **getCarros** obtemos a lista de carros, tal qual a jsp faria, e então usamos o método [assertFalse][5] da classe [Assert][6], mostrando que o que é esperado como resultado do teste é que a lista não esteja vazia.

O segundo teste verifica se a action adiciona um novo carro sem que haja erros. Usando o método **setCarro** é informado para a action qual carro deve ser adicionado, e em seguida é chamado o método adiciona para efetuar essa ação. Esse teste não possui um assert, isso porque não há como verificar se o Carro foi adicionado – só é verificado que não ocorre nenhum erro durante esse processo. Ele também é bastante simples porque não há lógica no método adiciona, o Carro apenas é passado para o dao, mas poderia haver alguma lógica que lida com os dados do carro, como uma validação por exemplo. Nesse caso, seria necessário mais um teste, em que é considerada essa situação.

Um ponto importante foi a alteração no construtor da classe CarroAction, pois sem ela, não seria possível prover um mock do dao, tornando a classe intestável. Para tal, foi necessário injetar a dependência, para possibilitar o teste de unidade. Mais uma vantagem de criar testes: melhorar o design das classes!

É responsabilidade do desenvolvedor garantir que o código que ele produz seja de qualidade, caso você não o faça, está tomando uma atitude amadora, além de estar possivelmente produzindo bugs. Pode parecer que escrever testes aumenta o trabalho e o tempo de desenvolvimento (no começo, realmente é necessário mais tempo), mas eu diria que diminui – e muito – o **retrabalho**, além de trazer as outras vantagens já citadas. Tente!

[1]: /simplificando-struts2.html
[2]: http://www.junit.org/
[3]: http://code.google.com/p/mockito/
[4]: /codigo-limpo.html
[5]: http://www.junit.org/apidocs/org/junit/Assert.html#assertFalse%28boolean%29
[6]: http://www.junit.org/apidocs/org/junit/Assert.html
[7]: /simplificando-gradle.html
