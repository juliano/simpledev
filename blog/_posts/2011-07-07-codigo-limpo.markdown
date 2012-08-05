---
layout: post
title: Código Limpo
category: Agile
permalink: /codigo-limpo.html
last_updated: 2012-08-05
---

Estive presente no [Agile Brazil 2011][1], e dentre as várias palestras que assisti, uma que me fez pensar 
bastante foi o keynote do [Vinicius Teles][2], intitulada [“O dia em que a Terra <strike>acabou</strike> parou, 
porque o software travou”][3]. De forma bastante resumida, Vinicius disse que vários dos problemas que 
encaramos diariamente é culpa nossa, dos desenvolvedores, e acho ele tem razão. Vide os problemas (seja 
lentidão ou dificuldade de utilização) dos sistemas dos correios, aeroportos ou bancos, os quais geram filas 
quilométricas que temos de enfrentar. Ou seja, todos pagam pelos bugs dos sistemas que **nós criamos!**

Ele sugeriu um novo manifesto, o **Manifesto Vergonha Na Cara**, que estabelece que **Código de verdade é 
código testado e limpo!** Como também estou cansado de enfrentar código de péssima qualidade, resolvi fazer 
uma pequena contribuição; durante a palestra, foi sugerido como um dos primeiros passos a leitura do livro 
[Clean Code do Uncle Bob][4], e como muita gente ainda não leu esse livro, criei um pequeno resumo, com o qual, 
espero ajudar a despertar interesse pelo mesmo, levando os leitores à produzir código limpo, já que, como foi 
muito falado durante o evento, **programador profissional é aquele que produz código limpo e testado!**

O resumo que fiz aqui é referente a apenas alguns tópicos do livro, os quais acho que devem ser os primeiros 
passos para um código melhor.
<br>
<h3>Nomes significativos</h3>

Nomes de classes, variáveis, métodos, etc, devem ser significativos, indicando claramente o que faz ou o que 
representa. A intenção deve ser visível através dos nomes. Crie nomes pronunciáveis para facilitar a 
comunicação, evite acrônimos e siglas.

Evite nomes confusos, os quais podem levar quem lê o código a conclusões erradas.

Use nomes que refletem o domínio do sistema, o contexto e os problemas que devem ser resolvidos.
<br>
<h3>Funções</h3>

Funções devem ser pequenas. Aliás, elas devem ser ainda menores. Deve haver apenas um nível de abstração por 
função.

Funções devem fazer uma coisa, e apenas uma. Novamente, utilize um nome que descreva bem o que a função faz. 
Utilize o menor número de argumentos possível, fazendo o máximo para que não passem de três.

Cuidado com “efeitos colaterais”, funções não podem fazer nada “escondido”. Use exceções ao invés de códigos 
de erro, e considere que tratar exceções já é uma coisa a ser feita.
<br>
<h3>Comentários</h3>

Comentários não salvam um código ruim. Procure explicar o que o código faz **COM CÓDIGO.**

Crie nomes de métodos e de variáveis informativos, ao invés de explicar com um comentário o que um método com 
um nome ruim faz. Use comentários para deixar uma expressão complexa mais clara (como uma regex), para avisar 
sobre as possíveis conseqüências de uma alteração, ou para ressaltar a importância de certo ponto do código.

Comentários ruins também poluem o código. Não escreva comentários redundantes, inúteis, ou pior, com falsas 
informações. Também não deve ser usado para indicar quando ou por quem foi alterado, para isso temos 
ferramentas de controle de versão. Não escreva comentários confusos ou grandes demais.

Não comente código que não será mais usado, simplesmente remova-o.
<br>
<h3>Objetos e estruturas de dados</h3>

Siga a [Lei de Demeter][5]. Faça boa abstração e encapsulamento. Não crie objetos burros.
<br>
<h3>Tratamento de erro</h3>

Use exceções ao invés de códigos de erro. Use exceções não checadas, e utilize mensagens de erro informativas.

Não retorne e nem passe null.
<br>
<h3>Testes</h3>

Siga as [3 leis do TDD][6].

Use uma assertiva por teste, e teste um conceito por vez.

Os testes devem ser rápidos, independentes, reprodutíveis em qualquer ambiente, auto-validáveis e escritos no 
momento certo. Mantenha o código de seus testes limpo.
<br>
<h3>Classes</h3>

Classes devem ser pequenas e seguir o [princípio da responsabilidade única][7]. Devem ser coesas, essa coesão 
resulta em classes pequenas. Classes devem ser criadas visando a mudança, então programe orientado à interface.

Outros pontos que não posso deixar de citar são: Todos os testes devem estar passando; refatoração deve ser 
feita constantemente, visando à melhoria contínua; código duplicado deve ser evitado a todo custo; classes e 
métodos devem ser pequenos; o código deve ser o mais expressivo possível.
<br>
Esse resumo é um levantamento de alguns pontos importantes, tanto que aqui é mostrado o que deve ser feito, 
não o porquê ou o como, isso pode ser encontrado no livro. Esse post é o primeiro passo, espero que todos dêem 
continuidade lendo esse livro e muitos outros, buscando criar um código melhor. Eu não quero mais código sujo 
atrapalhando a minha vida. E você?

[1]:http://www.agilebrazil.com/2011/pt/index.php
[2]:http:/twitter.com/viniciusteles
[3]:http://www.agilebrazil.com/2011/pt/detalhes.php#key_vinicius
[4]:http://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882
[5]:http://en.wikipedia.org/wiki/Law_of_Demeter
[6]:http://butunclebob.com/ArticleS.UncleBob.TheThreeRulesOfTdd
[7]:http://en.wikipedia.org/wiki/Single_responsibility_principle
