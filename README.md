# Simian - api
 De acordo com DNA enviado, retorna se é de um Humano ou de um Mutante.
 
# Requisitos para execução e construção
 - Java 11
 - Gradle
 
 
# Comandos
 Os comandos descritos abaixo devem ser executados no diretório raiz do projeto
 
# Build
gradlew build

# BootJar
gradlew bootJar 
	
# Configuração do acesso ao banco de dados em memória H2
	http://localhost:8090/h2-console/

- Driver: org.h2.Driver
- URL: jdbc:h2:mem:db
- username=sa
- password=sa 
