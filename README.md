# WebApp-Java
JavaEE  examples
<br><br>
<b>JPA Hibernate</b> <i>[JPA_Hibernate_Example1]</i> (Java Persistence API - ORM Hibernate)<br>
- one to many jednokierunkowe/dwukierunkowe wiazanie
- one to one jednokierunkowe/dwukierunkowe wiazanie
- dziedziczenie a tabele (joined, table per class, single table)

<b>JPQL Hibernate</b> <i>[JPQL_Hibernate_Example2]</i> (Java Persistence Query Language)<br>
- Query, TypeQuery
- funkcje matematyczne i operacje na Stringach
- CriteriaBuilder, CriteriaQuery

<b>JDBC</b>
- łaczenie z baza danych MySQL
- przykładowa baza danych: https://dev.mysql.com/doc/index-other.html (world database)
- naprawa błędu <i>"The server timezone value 'UTC' is unrecognized or represents more than one timezone."</i><br>
  <code>?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC</code>
  <br>alternatywa:<br>
  <code>SET GLOBAL time_zone = '+1:00';</code> - zastosować na bazie danych

<b>SQL Injection</b> 
- <code>PrearedStatement</code> jako sposób ochrony przed atakami 

<b>DAO Simple</b>
 - DAO - Data Access Object, CRUD - Create, Read, Update, Delete
 - KlasaJavy < - > KlasaJavyDao < - > Tabela DB
 - klasa dostawcy ConnectionProvider - udostepnia tylko jeden obiekt DataSource

<b>Spring JDBC-Template</b> <i>[jdbc-template]</i>
- łaczenie z baza danych przy użyciu jdbc-template Springa
- przykładowa baza danych: http://www.mysqltutorial.org/download/2 (classicmodels)

<b>Spring DATA</b> <i>[pure-spring-data-app]</i>
- łaczenie z baza danych przy użyciu Spring DATA (projekt bez spring boot'a)
- konfiguracja przy użyciu Gradle (Hibernate, MySQL, spring-context, spring-data-jpa) 
