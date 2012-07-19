---
layout: post
title: Combos dependentes com Struts 2
category: Struts 2
permlink: /combos-dependentes-struts2.html
last_updated: 2012-07-16
---

Este é um exemplo do clássico problema dos combos dependentes, do tip estado - cidade.
Por si só, o suporte a ajax do Struts 2 não é suficiente, então é necessário fazer uso de um 
plugin para JSON que facilita esse trabalho. Para usá-lo, além da configuração padrão para uso 
do Struts 2, é preciso adicionar mais um jar às dependências do **build.gradle**:

<pre>compile <span class="str">'org.apache.struts.xwork:xwork-core:2.3.4'</span></pre>
