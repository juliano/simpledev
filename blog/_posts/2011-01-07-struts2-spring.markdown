---
layout: post
title: Integrando Struts 2 e Spring
categories: Struts2 Spring
permalink: /struts2-spring.html
last_updated: 2012-07-26
---

No [primeiro post sobre Struts 2][1] foi falado que utilizar um container de injeção de dependência é o ideal 
para fazer o controle das dependências das classes do projeto. Nesse post será mostrado como integrar com o 
o Struts 2 com o Spring, o mais famoso container feito em java. Para trabalhar com essas ferramentas de forma 
mais simples, procurei tornar sua utilização o mais próximo possível do [VRaptor][2].

Será usado o mesmo exemplo da aplicação de carros que foi apresentada no post citado, para deixar claro 
quais são os ganhos que o Spring traz para a aplicação.

Primeiro a configuração. Além dos jars padrões para usar o Struts 2 será necessário mais um, 
usado para integração com o Spring, o **struts2-spring-plugin**. Por parte do Spring, é 
necessários adicionar os jars **spring-asm**, **spring-beans**, **spring-context**, 
**spring-core**, **spring-expression** e **spring-web**. Abaixo, o **build.gradle** completo 
do projeto.

<script src="https://gist.github.com/3182217.js?file=build.gradle"></script>

No web.xml, além da configuração do Struts é necessário adicionar uma configuração para o 
listener do Spring:

<script src="https://gist.github.com/3182217.js?file=web.xml"></script>

Ao trabalhar com Spring, é necessário informá-lo quais objetos ele terá que administrar, 
referenciados por ele como *beans*. Isso pode ser feito através de anotações, programaticamente 
ou através de um arquivo XML, que por padrão tem o nome **applicationContext.xml**. Nesse exemplo 
serão utilizadas anotações, mas muitas vezes é interessante usar esse XML, por exemplo, para 
configurar classes do próprio Spring. Aqui ele será usado para informar ao Spring em qual pacote 
ele deve procurar as classes que serão gerenciadas, as quais serão identificadas por meio de 
anotações:

<script src="https://gist.github.com/3182217.js?file=applicationContext.xml"></script>

Ao indicar um pacote, o Spring vai iniciar sua busca por ele e adentrar todos os seus subpacotes, 
buscando por classes anotadas com [@Component][3] e suas especializações, adicionando as classes 
encontradas ao container.

Mesmo minimizando o uso de XMLs, aqui o do Struts se faz necessário. Para realizar a integração 
com o Spring, será preciso indicar ao Struts que o Spring é que fará a instanciação de objetos. 
Para isso será criado o arquivo **struts.xml** dentro da pasta resources:

<script src="https://gist.github.com/3182217.js?file=struts.xml"></script>

Isso finaliza a configuração.

Agora, as anotações. Nesse exemplo só é necessário anotar duas classes, a CarroAction e a 
implementação de CarroDao, a JdbcCarroDao, já que a action depende do dao. Para isso o Spring 
disponibiliza uma anotação generalista **@Component**, mas é mais apropriado usar uma de suas 
especializações. Para a action, será usada a anotação **@Controller**, que indica que a classe 
é, obviamente, um controller:

<pre>
import org.springframework.stereotype.Controller;

@Controller
@Namespace<span class="b">(</span>value <span class="b">=</span> <span class="str">"/carro"</span><span class="b">)</span>
<span class="b">public class</span> <span class="cl">CarroAction</span> <span class="b">{</span>
</pre>

E para o dao, a anotação usada será **@Repository**, a qual indica que a classe é um 
[repositório de objetos][4] ([leia o Domain-Driven Design do Eric Evans][5]):

<pre>
import org.springframework.stereotype.Repository;

@Repository
<span class="b">public class</span> <span class="cl">JdbcCarroDao</span> <span class="b">implements</span> CarroDao <span class="b">{</span>
</pre>

Falta informar ao Spring que ele deve injetar o dao na action. Isso pode ser feito de três 
formas: através de um método set, através do construtor ou diretamente no campo. Essa última 
forma é fortemente desaconselhada, já que o Spring simplesmente injeta o valor do campo 
passando por cima de qualquer outra prática ou princípio, o que impossibilita um teste de 
unidade. Usar o set é uma forma muito comum, mas usar o construtor é a forma mais adequada, 
pois assim fica evidente a depêndencia entre a action e o dao, garantindo programaticamente que 
um não possa ser instanciado sem o outro.

Dessa forma, o construtor da CarroAction fica assim:

<pre>
import org.springframework.beans.factory.annotation.Autowired;

<span class="b">public class</span> <span class="cl">CarroAction</span> <span class="b">{</span>

    <span class="b">private CarroDao</span> dao<span class="b">;</span>

    @Autowired
    <span class="b">public</span> <span class="mc">CarroAction</span>(CarroDao dao<span class="b">) {
        this.</span>dao = dao<span class="b">;
    }</span>
</pre>

O Spring promove o desacoplamento: agora CarroAction faz referência apenas à interface CarroDao, 
sem ter conhecimento algum de sua implementação. Isso torna possível mudar a implementação do dao 
sem que a action seja afetada, apenas informando ao Spring que outro componente deve ser 
injetado. Podemos por exemplo, criar uma nova implementação de CarroDao com Hibernate, e 
essa substituição seria transparente para a CarroAction. Para isso seria necessário uma outra 
integração, dessa vez entre Spring e Hibernate, mas isso é assunto para um próximo post.

<br>
<a href="https://github.com/juliano/simpledev/tree/master/struts2spring">
  <img src="/images/github.png"> <strong>Código do exemplo.</strong>
</a>

[1]: /simplificando-struts2.html
[2]: http://vraptor.caelum.com.br/
[3]: http://static.springsource.org/spring/docs/3.1.x/javadoc-api/org/springframework/stereotype/Component.html
[4]: http://martinfowler.com/eaaCatalog/repository.html
[5]: http://amzn.to/MZyZUz
