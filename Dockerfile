# -----------------------------------------------------------------------------
# 1. IMAGEM BASE (A FUNDAÇÃO)
# -----------------------------------------------------------------------------
# A instrução FROM diz ao Docker qual sistema operacional e ferramentas base usar.
# Aqui, você está baixando uma imagem do Linux (Alpine, que é muito leve)
# que já vem com o Java 21 (JDK) instalado.
#
# 🔥 DICA EXTRA: A imagem oficial 'openjdk' foi descontinuada e não recebe mais
# atualizações para o Alpine. Para projetos futuros ou de produção, é recomendado
# usar a imagem da fundação Eclipse, trocando a linha abaixo por:
# FROM eclipse-temurin:21-jdk-alpine
FROM eclipse-temurin:21-jdk-alpine

# -----------------------------------------------------------------------------
# 2. DIRETÓRIO DE TRABALHO (O LOCAL DE AÇÃO)
# -----------------------------------------------------------------------------
# O WORKDIR cria uma pasta chamada "/app" dentro do container e avisa o Docker:
# "A partir de agora, execute todos os próximos comandos dentro desta pasta".
WORKDIR /app

# -----------------------------------------------------------------------------
# 3. COPIANDO ARQUIVOS (DO SEU PC PARA O CONTAINER)
# -----------------------------------------------------------------------------
# O COPY pega o arquivo .jar que foi gerado na sua máquina local (pelo Maven)
# e o transfere para dentro da pasta /app do container, renomeando-o para um
# nome mais simples ("api-recepcao.jar").
COPY target/api-recepcao-0.0.1-SNAPSHOT.jar /app/api-recepcao.jar

# -----------------------------------------------------------------------------
# 4. EXPOSIÇÃO DE PORTA (A PORTA DE ENTRADA)
# -----------------------------------------------------------------------------
# O EXPOSE serve como uma documentação. Ele avisa a quem for rodar o container
# que a sua aplicação lá dentro está escutando na porta 8080.
# (Nota: Ele não publica a porta sozinho, por isso usamos o -p no 'docker run').
EXPOSE 8080

# -----------------------------------------------------------------------------
# 5. COMANDO DE EXECUÇÃO (O MOTOR)
# -----------------------------------------------------------------------------
# O CMD define o comando padrão que será executado no momento em que o
# container for iniciado. É o equivalente a você digitar no terminal:
# java -jar /app/api-recepcao.jar
#
# 🔥 DICA EXTRA: No ecossistema Docker, para rodar aplicações Java, muitos
# desenvolvedores preferem usar a instrução ENTRYPOINT no lugar de CMD. Ambas
# funcionam perfeitamente para este cenário, mas o ENTRYPOINT é semanticamente
# mais adequado para containers que rodam como um executável único.
# Se quiser testar, basta trocar a linha abaixo por:
# ENTRYPOINT ["java", "-jar", "/app/api-recepcao.jar"]
CMD ["java", "-jar", "/app/api-recepcao.jar"]


# =============================================================================
# COMANDOS ÚTEIS DE TERMINAL (Sua colinha)
# =============================================================================

# Construir a imagem no docker (O ponto '.' no final significa "use o Dockerfile desta pasta")
# docker build -t api-recepcao .

# Executar container docker (Mapeando a porta 8080 do seu PC para a 8080 do container)
# docker run -p 8080:8080 api-recepcao
#docker run -p 8080:8080 -e TZ=America/Sao_Paulo api-recepcao

# Comando para ver quais containers existem na máquina (rodando ou parados)
# docker ps -a

# Iniciar um container que estava parado usando o ID ou Nome dele
# docker start "id do container"