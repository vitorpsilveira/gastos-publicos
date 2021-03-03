## Api de despesas públicas e desempenho de avaliação

<p align="center">
 <a href="#status">Status</a> •
 <a href="#pre-requisitos">Pré-requisitos</a> • 
 <a href="#tecnologias">Tecnologias utilizadas</a>
</p>

------------

<h3 id="status"> Status do projeto </h3>

- Desenvolvimento de API de despesas com covid19 - Concluido
- Desenvolvimento de API de despesas com bolsa familia - Concluido
- Desenvolvimento de API para consulta de Avaliações - em construção

------------

<h3 id="pre-requisitos"> Pré-requisitos </h3>
Serão necessarias as seguintes ferramentas e instalações:

[Git](https://git-scm.com), [JDK, Maven e IDE](https://iteris1.sharepoint.com/:p:/s/decola2020sputnik/EcrAs99D4JRMu0nvril_fbYBON3GF_rIMaBywVdYxgriVQ?e=xV0UAt)


```bash
#Clone este repositório
$ git clone https://github.com/vitorpsilveira/gastos-publicos.git

#Dentro da pastas gastos-publicos temos a pasta gastos, onde esta o projeto.
#Abrindo o projeto em sua IDE, executar o comando do Maven Install para instalação das dependencias
$ mvn install

#Após a instalação, basta executar o projeto GastosAplication.
#O projeto iniciará na porta:8080 - acesse http://localhost:8080/swagger-ui.html#/

#Para rodar o Front-end, siga os passos do repositorio: https://github.com/Danielmtt/despesas-covid-19
```

------------

<h3 id="tecnologias"> Tecnologias utilizadas </h3>

- [Maven](https://maven.apache.org/)
- [Spring](https://spring.io/)
