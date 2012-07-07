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

@Namespace(value = "/carro")

Uso a anotação @Namespace para indicar na url em que área do sistema o usuário está. Por exemplo, 
podemos separar as áreas de uma empresa dessa forma, como rh, contábil, jurídico, etc. 

public class CarroAction

Repare que não é necessário herdar de ActionSupport e nem implementar Action, removendo as 
costumeiras dependências em relação ao Struts 2. Usando o pacote de convenções, só é necessário que a 
action esteja em um pacote nomeado como action, actions ou struts2, e o framework vai encontrá-la.

public CarroAction() {
    this.dao = new JdbcCarroDao();
}

O Dao é uma dependência da Action, portanto o ideal seria injetá-lo pelo construtor, mas achei 
melhor instânciá-lo diretamente para não aumentar a complexidade do exemplo, já que aqui o Struts 2 
é usado sozinho. O ideal seria injetá-lo com auxílio de um Container de IoC, como Spring ou Pico 
Container. Isso será mostrado em outro post.

@Action(value = "lista", results = @Result(name = "ok", location = "/carro/lista.jsp"))
public String lista() {
    carros = dao.lista();
    return "ok";
}

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

lista.jsp

Evitei o uso das tags do Struts, somente jstl é suficiente para iterar pela lista, além 
de achar bem mais simples trabalhar com ela. É aqui que o método getCarros é chamado, por 
isso é necessário que ele esteja disponível na action.

Ao clicar no link “novo”, é chamada a action 'novo':

@Action(value = "novo", results = @Result(name = "ok", location = "/carro/adiciona.jsp"))
public String novo() {
    return "ok";
}

Que apenas redireciona para outra página. Apesar da action não fazer nada além disso, é uma boa 
prática nunca redirecionar direto para a jsp, sempre devemos passar pela action antes. Isso nos leva 
para a adiciona.jsp:

adiciona.jsp

O método setCarro deve existir para permitir que o Struts faça o trabalho de popular o objeto, 
quando usamos name=“objeto.atributo”. Ao submeter o form, é chamada a action adiciona:

@Action(value = "adiciona", results = @Result(name = "ok", type = "redirectAction", params = {"actionName", "lista" }))
public String adiciona() {
    dao.adiciona(carro);
    return "ok";
}

Ao inserir o novo carro, é interessante apresentar a lista de carros cadastrados. A diferença 
aqui está no @Result, o atributo type é usado para indicar que o redirecionamento não é para 
uma jsp como nos casos anteriores, mas para a action passando “redirectAction” ao atributo e 
em seguida usando params para informar qual action será chamada. Esse atributo funciona como 
um “mapa informal”, para usá-lo devemos passar os valores no formato chave – valor.

Aqui foi apresentada uma forma simples de trabalhar com o Struts 2, que apesar de não ser a melhor 
ferramenta existente, pode trazer várias facilidades para o desenvolvimento web quando bem explorada.



