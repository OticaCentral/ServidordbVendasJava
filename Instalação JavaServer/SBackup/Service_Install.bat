@echo off

@echo .
@echo . Service_Install.bat - Instalador do Serviço
@echo .

setlocal
if "%JAVA_HOME%" == "" set JAVA_HOME=C:/Program Files/Java/jdk1.6.0_24
set JVMDIR=%JAVA_HOME%/jre/bin/server
set DIR_JAR=C:\SBackup

@echo . Instalando o Serviço
JavaService.exe -install SampleService "%JVMDIR%\jvm.dll" -Djava.class.path="%DIR_JAR%\SSBackup.jar" -Xms16M -Xmx512M -start org.objectweb.javaservice.test.SampleService -err "%DIR_JAR%\erros.log" -current "%DIR_JAR%"

@echo .

@echo . Iniciando o Serviço ...
net start SampleService
@echo .

pause