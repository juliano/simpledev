---
layout: post
title: Integrando Spring e Hibernate
categories: Spring Hibernate
permalink: /spring-hibernate.html
last_updated: 2013-01-17
---

Integrar Spring e Hibernate é uma tarefa muito comum, mas a forma como isso geralmente é feito me desagrada. É muito comum encontrar classes que herdam de [HibernateDaoSupport][1], o que cresce de forma exagerada e traz comportamento que muitas vezes a classe não deveria possuir.

Quando feito dessa forma, para a maioria das operações é necessário utilizar o objeto [HibernateTemplate][2], obtido através do método getHibernateTemplate(). Então por que não injetar apenas o HibernateTemplate e trabalhar com ele diretamente? E assim foi feito, o que acabou com essa herança desnecessária. Então surgiu um novo problema.

As limitações do HibernateTemplate passaram a incomodar, pois não era possível trabalhar com a [Session][3] diretamente (até era, mas assim o seu controle vinha para as mãos da aplicação), e sem ela, não podemos usar Criteria livremente, restando como alternativas usar DetachedCriteria (horrível), ou criar uma implementação de HibernateCallback para aí sim ter acesso direto a Session, mantendo o controle por parte do Spring.

Dessa forma o Spring está sendo intrusivo demais no design, já que ele devia apenas fornecer a Session e controlar as transações magicamente, nada mais. Comecei a procurar uma forma melhor de fornecer a Session para os Daos, e ainda manter as vantagens que o Spring fornece, como o já citado controle de transações. E a forma que encontrei é a que será mostrada aqui.

As libs primeiro. Abaixo, o [build.gradle][4] do exemplo.

<script src="https://gist.github.com/juliano/4556731.js?file=build.gradle"></script>

Agora as configurações para o Hibernate e para o Spring. Abaixo, o hibernate.cfg.xml:

<script src="https://gist.github.com/juliano/4556731.js?file=hibernate.cfg.xml"></script>

E para o Spring, o applicationContext.xml:

<script src="https://gist.github.com/juliano/4556731.js?file=applicationContext.xml"></script>

Com isso, as configurações estão prontas. O DataSource utilizado é o fornecido pelo próprio Spring, que uso apenas para desenvolvimento, para produção o [c3p0][5] é uma opção mais apropriada. Para configurar a SessionFactory mantive o hibernate.cfg.xml, fazendo uma referência a ele. Apenas lembre que cada nova entidade do sistema deverá ser acrescentada ao mapeamento desse arquivo. Por fim, a configuração do [HibernateTransactionManager][6], para que o Spring faça o controle de transações. Agora, a interface CarroDao:

<script src="https://gist.github.com/juliano/4556731.js?file=CarroDao.java"></script>

Para eliminar todas aquelas dependências das classes do Spring, na implementação será injetada a [SessionFactory][7]:

<script src="https://gist.github.com/juliano/4556731.js?file=HibernateCarroDao.java"></script>

Agora podemos trabalhar livremente com a Session, desde que ela seja obtida da SessionFactory através do método getCurrentSession(), pois dessa forma não tiramos o controle da Session das mãos do Spring, ficando com o melhor das duas ferramentas. Caso seja utilizado o getSession(), fechar a Session passa a ser responsabilidade da sua aplicação.

Trabalhando dessa forma temos uma restrição, precisamos anotar os métodos do Dao com **@Transactional** mesmo quando for só para leitura, onde adicionamos o parâmetro **readOnly = true**.

Usando as ferramentas dessa forma unimos o melhor dos dois mundos, o controle de transações do Spring e a liberdade para trabalhar com a Session do Hibernate, sem grandes interferências no design da aplicação!

<br>
<a href="https://github.com/juliano/simpledev/tree/master/spring-hibernate">
  <img src="/images/github.png"> <strong>Código do exemplo.</strong>
</a>

[1]: http://static.springsource.org/spring/docs/3.2.x/javadoc-api/org/springframework/orm/hibernate3/support/HibernateDaoSupport.html
[2]: http://static.springsource.org/spring/docs/3.2.x/javadoc-api/org/springframework/orm/hibernate3/HibernateTemplate.html
[3]: http://docs.jboss.org/hibernate/core/4.1/javadocs/org/hibernate/Session.html
[4]: /simplificando-gradle.html
[5]: http://sourceforge.net/projects/c3p0/
[6]: http://static.springsource.org/spring/docs/3.2.x/javadoc-api/org/springframework/orm/hibernate4/HibernateTransactionManager.html
[7]: http://docs.jboss.org/hibernate/core/4.1/javadocs/org/hibernate/SessionFactory.html
