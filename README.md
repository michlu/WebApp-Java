# WebApp-Java
JavaEE  examples
<br><br>
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

<b>Spring JDBC-Template</b>
- łaczenie z baza danych przy użyciu jdbc-template Springa
- przykładowa baza danych: http://www.mysqltutorial.org/download/2 (classicmodels)
