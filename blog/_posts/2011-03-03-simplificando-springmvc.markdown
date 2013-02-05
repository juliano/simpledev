---
layout: post
title: Simplificando - Spring MVC
category: Spring
permalink: /simplificando-springmvc.html
last_updated: 2012-08-01
---

O Spring MVC é uma ótima ferramenta. É bem mais avançado que o Sruts 2 e supre quase todas as
suas falhas de forma simples. Não se encontra muito material sobre o Spring MVC 3, mas há 
muito sobre a versão 2.5. Aqui será mostrado como trabalhar com a versão 3 adotando a maior 
parte de seus recursos.

A configuração primeiro. Abaixo, o [build.gradle][10] do exemplo.

<script src="https://gist.github.com/juliano/3229414.js?file=build.gradle"></script>

Também temos de configurar o web.xml.

<script src="https://gist.github.com/juliano/3229414.js?file=web.xml"></script>

**DispatcherServlet** é a classe que habilita o uso Spring MVC, a qual vai trabalhar as 
requisições recebidas. **HiddenHttpMethodFilter** é usado para habilitar o suporte RESTful do 
Spring. Mais sobre isso mais tarde.

Por padrão, o Spring vai buscar o arquivo de configurações na pasta WEB-INF, sendo o nome do
arquivo o mesmo nome da DispatcherServlet (no exemplo esse nome é context), acrescentando 
–servlet.xml, então vamos ver a configuração do arquivo context-servlet.xml:

<script src="https://gist.github.com/juliano/3229414.js?file=context-servlet.xml"></script>

Neste arquivo estão alguns itens de configuração importantes, **context:component-scan** vai 
buscar todas as classes anotadas no local indicado por *base-package*, procurando em todos os 
seus pacotes e subpacotes.

A tag **mvc:annotation-driven** ativa os beans do Spring necessários para direcionar as 
requisições recebidas para os *Controllers*, além de configurar esses beans para que forneçam 
alguns serviços padrões, entre eles:

* Serviço de conversão de dados, quando for informado, por exemplo, um número (que é na verdade 
enviado como String), ele já faz a conversão automaticamente;
* Formatação de números e datas (esse último, caso o [Joda Time][1] esteja no classpath);
* Validação (caso uma implementação da [JSR-303][2] esteja no classpath, como 
[Hibernate Validator][3]);
* Suporte a XML (caso [JAXB][4] esteja no classpath);
* Suporte a JSON (caso [Jackson][5] esteja no classpath).

A classe **InternalResourceViewResolver** é usada para estabelecer alguns padrões: a propriedade 
**prefix** indica que as views vão ficar em */WEB-INF/jsp/* (uma boa prática recomendada, já 
que assim as jsps não podem ser acessadas diretamente), e a propriedade **suffix** indica que 
todas terão .jsp no fim de seu nome, de forma que quando essa classe recebe a String “lista”, 
ela faz redirecionamento para */WEB-INF/jsp/lista.jsp*.

Finalizada a configuração, hora de mostrar as classes. Abaixo, o CarroController:

<script src="https://gist.github.com/juliano/3229414.js?file=CarroController.java"></script>

Trabalhando com Spring MVC é muito simples manter as operações referentes ao Carro na mesma 
classe. Vamos por partes:

<pre>
@Controller
@RequestMapping<span class="b">(</span><span class="str">"/carro"</span><span class="b">)
public class</span> <span class="cl">CarroController</span> <span class="b">{

	private final</span> CarroDao dao;

	@Autowired
	<span class="b">public</span> <span class="mc">CarroController</span>(<span class="b">final</span> CarroDao dao) <span class="b">{
		this.</span>dao <span class="b">=</span> dao<span class="b">;
	}</span>
</pre>

A anotação **@Controller** é usada para dizer ao Spring que a classe é um Controller, e 
**@RequestMapping** indica que ele vai atender à requisições que contenham /carro na url, o 
qual é usado aqui da mesma forma que o [@Namespace do Struts 2][6]. O construtor recebe o dao
por injeção do próprio Spring ([de forma muito parecida com o que é descrito aqui][9]).

Quem efetivamente atende a requisição são os métodos:

<pre>
@RequestMapping<span class="b">(</span>value <span class="b">=</span> <span class="str">"/lista"</span>, method = GET<span class="b">)
public</span> List&lt;Carro&gt; lista<span class="b">() {
	return</span> dao.<span class="at">lista</span><span class="b">();
}</span></pre>

Combinando o value desse **@RequestMapping** com o existente no nome da classe, esse método 
vai atender a url *.../carro/lista*. Todas as demais são feitas dessa mesma forma.

Aqui é feito uso do suporte ao RESTful do Spring, através do parâmetro **method** é informado 
o método HTTP utilizado, de forma que o método lista atenderá apenas um método GET. Ele vai 
retornar uma lista com todos os carros existentes no banco e enviá-la para a jsp. Para qual 
jsp? Por convenção, caso não seja explicitamente informado para onde ir, o Spring vai 
considerar a url usada para definir isso; */carro/lista* fará com que ele procure pela jsp 
*/WEB-INF/jsp/carro/lista.jsp*

E para exibir a lista na jsp, por padrão o Spring vai nomeá-la como “conteúdo da lista" + List, 
nesse caso a lista será chamada de carroList, que pode ser acessado com jstl:

<script src="https://gist.github.com/juliano/3229414.js?file=lista.jsp"></script>

O Spring permite que os métodos devolvam diferentes tipos de retorno, e uma pequena variação 
deles será mostrada nesse exemplo. Todos os tipos de retorno permitidos podem ser vistos 
[na documentação][7].

Abaixo, os métodos para inserção de um carro:

<pre>
@RequestMapping<span class="b">(</span>value <span class="b">=</span> <span class="str">"/novo"</span>, method <span class="b">=</span> GET<span class="b">)
public</span> ModelAndView <span class="mc">novo</span><span class="b">() {
	return new</span> <span class="mc">ModelAndView</span><span class="b">(</span><span class="str">"carro/novo"</span>, <span class="str">"carro"</span><span class="b">, new</span> Carro<span class="b">());</span>
}</pre>

Esse método leva para a página com o formulário de cadastro de um novo carro. Aqui faço uso da 
classe **ModelAndView** para informar para o Spring para onde ir (carro/novo), o nome que será 
usado para referenciar o objeto na página (“carro”, que se não for informado, por padrão será 
“command”), e o objeto propriamente dito. Com isso, o jsp saberá com qual classe está lidando, 
o que facilita para o próximo método:

<pre>
@RequestMapping<span class="b">(</span>value <span class="b">=</span> <span class="str">"/novo"</span>, method <span class="b">=</span> POST<span class="b">)
public</span> String <span class="mc">novo</span><span class="b">(final</span> Carro carro<span class="b">) {</span>
	dao.<span class="at">adiciona</span><span class="b">(</span>carro<span class="b">);
	return</span> <span class="str">"redirect:lista"</span><span class="b">;
}</span></pre>

Os métodos da classe possuem o mesmo nome, mas devido aos métodos HTTP para acesso a cada um 
deles, o Spring sabe exatamente qual chamar. O método recebe um Carro, já que no método 
anterior foi informado para o Spring que era com esse objeto que ele deveria trabalhar. Assim 
ele já faz as conversões necessárias e seta os valores no objeto. Isso torna a forma de 
trabalhar com a ferramenta muito agradável.

A String de retorno diz ao Spring que ele deve redirecionar para o controller indicado após a 
palavra redirect, assim ele vai chamar o controller que lista todos os carros.

Por fim, os métodos de edição:

<pre>
@RequestMapping<span class="b">(</span>value <span class="b">=</span> <span class="str">"/editar/{id}"</span>, method <span class="b">=</span> GET<span class="b">)
public</span> ModelAndView <span class="mc">editar</span><span class="b">(</span>@PathVariable Long id<span class="b">) {</span>
	Carro carro <span class="b">=</span> dao.<span class="at">busca</span><span class="b">(</span>id<span class="b">);
	return new</span> <span class="mc">ModelAndView</span><span class="b">(</span><span class="str">"carro/editar"</span>, <span class="str">"carro"</span>, carro<span class="b">);
}</span></pre>

Esse método busca o Carro para edição através do id, o qual é informado na url. */editar/{id}* 
indica o formato da url, de forma que uma chamada válida seria /editar/2. O parâmetro id 
recebido no método deve ser anotado com **@PathVariable** para que o Spring saiba que o valor 
informado na url deve ser associado a esse método. O uso do ModelAndView é feito da mesma 
forma do método de inserção.

<pre>
@RequestMapping<span class="b">(</span>value <span class="b">=</span> <span class="str">"/editar"</span>, method <span class="b">=</span> PUT<span class="b">)
public</span> String <span class="mc">editar</span><span class="b">(final</span> Carro carro<span class="b">) {</span>
	dao.<span class="at">atualiza</span><span class="b">(</span>carro<span class="b">);
	return</span> <span class="str">"redirect:lista"</span><span class="b">;
}</span></pre>

Esse é o método que vai salvar as alterações feitas no objeto. Aqui é usado o método HTTP 
PUT. O DispatcherServlet do Spring suporta os seguintes métodos HTTP: GET, POST, HEAD, PUT e 
DELETE, onde por convenção, GET é usado para buscar informações, POST para enviar informações, 
PUT para alterar informações e DELETE para removê-las. É semelhante às operações de um CRUD.

Voltando ao método, PUT é usado para alterar os dados do carro, o que quando é feito, é 
redirecionado com a String informada.

Aqui vale citar que os Browsers compreendem apenas os métodos GET e POST, então para indicar 
que o método é PUT é necessário declará-lo como POST e criar um campo oculto de nome _method 
com o valor PUT (o processo é o mesmo para o caso do método DELETE). Felizmente, não é 
necessário fazer isso na mão, pois o Spring possui a tag **form** que já faz esse trabalho:

<script src="https://gist.github.com/juliano/3229414.js?file=editar.jsp"></script>

Essa tag, ao gerar o HTML final vai criar o campo oculto. As demais tags que o Spring 
proporciona podem ser [vistas aqui][8].

Apenas fazer uso da tag não é o suficiente para que o Spring interprete o método que deve ser 
utilizado, e é aí que entra o HiddenHttpMethodFilter, o qual foi incluído no web.xml no começo 
da configuração, que é quem faz esse trabalho de encaminhar a requisição com o método HTTP 
adequado.

Aqui foi mostrada uma forma simples que de trabalhar com Spring MVC 3, o qual é um framework 
muito poderoso, simples, que possui inúmeras ferramentas para ajudar no desenvolvimento. Recomendo!

<br>
<a href="https://github.com/juliano/simpledev/tree/master/spring">
  <img src="/images/github.png"> <strong>Código do exemplo.</strong>
</a>

[1]: http://joda-time.sourceforge.net/
[2]: http://jcp.org/en/jsr/detail?id=303
[3]: http://www.hibernate.org/subprojects/validator.html
[4]: http://jaxb.java.net/
[5]: http://jackson.codehaus.org/
[6]: /simplificando-struts2.html
[7]: http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/mvc.html
[8]: http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/view.html#view-jsp-formtaglib
[9]: /struts2-spring.html
[10]: /simplificando-gradle.html
