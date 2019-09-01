# Projeto Casa do Código

Projeto gerado no eclipse utilizando Maven

## Funcionamento

Acesse o diretório do código fonte via cmd ou abra no eclipse o terminal via projeto.
Abra o código e localize o arquivo "pom.xml" ou compile o sistema utilizando o comando 
 $: mvn clean install -f pom.xml -Dmaven.test.skip=true
Após compilar o código acesse na pasta do projeto a pasta target e copie o casadocodigo.war para a pasta webapps
do seu tomcat

acesse a pasta bin e execute o comando 
  $: sudo su startup.sh 
ou abra o arquivo startup.bat no seu windows...

URL: http://localhost:8080/casadocodigo

Existe uma URL mágica para cadastrar alguns livros e adicionar o usuário administrador

URL: http://localhost:8080/casadocodigo/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn

login: admin@casadocodigo.com.br
Senha: 123456

