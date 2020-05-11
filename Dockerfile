FROM maven:3.6.3-jdk-11-openj9 AS MAVEN_TOOL_CHAIN

# copy source files and maven profile to tmp directory
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/

# build api without running the tests
RUN mvn package -Dmaven.test.skip=true


