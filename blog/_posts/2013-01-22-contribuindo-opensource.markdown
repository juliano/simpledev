---
layout: post
title: Contribuindo com projetos open source
categories: Opensource
permalink: /contribuindo-com-opensource.html
---

Quando se está começando no mundo do desenvolvimento de software, todo mundo procura saber o que pode fazer para adquirir mais conhecimento e prática, o que é um diferencial para o primeiro emprego na área. Buscando em fóruns é muito comum a resposta *"procure um projeto open source para contribuir"* e como iniciante você logo pensa *"eu vou conseguir experiência contribuindo com o Hibernate? Ou com o Ruby on Rails? Como vou fazer isso?"*. Foi o que pensei quando eu estava começando e li essa sugestão, mas acalme-se, você não precisa ser um expert em programação para entrar no mundo open source (mas precisa para contribuir com esses grandes frameworks, vamos chegar lá ;D). Vamos começar do começo.

Contribuir com um projeto open source vai realmente te trazer a experiência de trabalhar em um projeto real, e mais do que isso, vai te trazer maturidade. Ao contribuir com um projeto assim, o seu código estará disponível para que os outro contribuidores do projeto, os usuários, e quem mais quiser ver como é o seu trabalho. Sendo iniciante ou não, esteja preparado para que apontem falhas, erros e bugs no seu trabalho - o que não quer dizer que isso seja ruim. Diferente de muitos projetos "corporativos" code review é uma prática muito comum no mundo open source. Aceitando as críticas como construtivas, não só suas habilidades técnicas vão melhorar, mas a forma como lida com críticas.

E como começar? Sendo um iniciante, você pode procurar por projetos como o [Para Onde Foi meu Voto][1] ou o [Em quem eu Voto?][2], ambos projetos relacionados à dados abertos desenvolvidos pelo [Raphael Molesim][3] da Lambda3 e do nosso amigo [Jonas Abreu][4], que são sites que expõem dados que o governo disponibiliza, mas que não são facilmente acessíveis ao público em geral (basicamente, é tudo xml). As idéias para esses projetos foram justamente tornar esses dados visíveis para todos, em um formato fácil de entender. Eu não participei dos projetos citados, mas contribuí com outro projeto de dados abertos junto ao Raphael e ao Jonas, o [Contas Refeitas][5] que foi o primeiro projeto com dados aberto que fizemos. Como estávamos aprendendo não deu muito certo e tiramos do ar, mas o aprendizado levou à acertos nos projetos posteriores.

Se você tem um pouco mais de conhecimento, pode procurar um projeto mais elaborado. Hoje dedico parte do meu tempo ao [VidaGeek Games][6], uma plataforma que mistura prática deliberada e Gamefication para ensino que você pode acessar para aprender a utilizar [Regex][7], [Git][8] e futuramente Scala, CSS entre outros. Desenvolvido em Scala, contribuir com esse projeto é uma rara oportunidade para aprender muitas features de Scala que dificilmente usamos no dia a dia, como Parser Combinators, Actors e concorrência, até mesmo manipular compiladores.

E par contribuir com frameworks? Isso é mais complicado, pois exige que você tenha conhecimento avançado em desenvolvimento, pois nesse caso não estamos falando de uma aplicação que você coloca no ar e os usuários simplesmente consomem, é necessário aprender a desenvolver tendo em mente que a ferramenta criada será usada por outros desenvolvedores, o que é algo completamente diferente. O design do framework, a disposição das apis, manter compatibilidade, e o que considero mais importante, a extensibilidade são fundamentais ao lidar com isso. Você já abriu para ler o código fonte de algum dos frameworks que utiliza? É por aí.

O framework open source para o qual fiz minha contribuição foi o [Mirror][9] também do Jonas Abreu. O Mirror é uma DSL criada para facilitar o uso de reflection em Java, além de fazer outras coisas. Aqui minha contribuição foi fazer a funcionalidade de criação de proxies, o que é uma tarefa bastante complicada de se fazer na mão. Após a minha implementação, com o Mirror você pode criar proxies facilmente, apenas indicando a classe do proxy, suas interfaces e definindo o corpo dos métodos interceptados.

Apesar da ordem como enumerei as possibilidades, minha vivência foi a aposta: comecei minha jornada no mundo open source pelo Mirror, o qual foi o mais difícil. Mas posso afirmar que após os 3 dias que levei para fazer a implementação de proxies, senti que meu conhecimento havia crescido de uma forma tal qual não acontecia em anos!

E se você quer contribuir com a sua ferramenta favorita mas ainda precisa aprender mais para isso, ajude traduzindo a documentação para as linguas de seu conhecimento, há várias formas de contribuir!

Contribuir com projetos open source são uma excelente forma de aumentar suas capacidades, especialmente quando na sua empresa você trabalha em um projeto que não te agrega muito conhecimento. Se você é um desenvolvedor que gosta do que faz, procure um projeto open source na linguagem que quer aprender / melhorar, e meta a mão na massa, você só tem a ganhar!

[1]: http://paraondefoimeuvoto.com.br/
[2]: http://www.emquemeuvoto.com.br/
[3]: https://twitter.com/raphaelmolesim
[4]: https://twitter.com/jonasabreu
[5]: https://github.com/jonasabreu/contas-refeitas
[6]: http://games.vidageek.net/
[7]: http://games.vidageek.net/play/regex
[8]: http://games.vidageek.net/play/git
[9]: http://projetos.vidageek.net/mirror/mirror/
