@echo . Parando o servi�o "SampleService" ...
net stop SampleService
@echo .

@echo . Desinstalando o Servi�o "SampleService" ...
JavaService.exe -uninstall SampleService
@echo .

pause