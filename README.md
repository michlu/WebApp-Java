# WebApp-Java
JavaEE  examples
<br><br>
<b>JDBC</b>
- łaczenie z baza danych MySQL
- przykładowa baza danych: https://dev.mysql.com/doc/index-other.html (world database)
- naprawa błędu <i>"The server timezone value 'UTC' is unrecognized or represents more than one timezone."</i><br>
  <code>?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC</code>

<b>SQL Injection</b> 
- <code>PrearedStatement</code> jako sposób ochrony przed atakami 

<b>DAO Simple</b>
 - DAO - Data Access Object, CRUD - Create, Read, Update, Delete
 - KlasaJavy < - > KlasaJavyDao < - > Tabela DB
 - klasa dostawcy ConnectionProvider - udostepnia tylko jeden obiekt DataSource
