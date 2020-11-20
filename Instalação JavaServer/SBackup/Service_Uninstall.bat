@echo . Parando o serviço "SampleService" ...
net stop SampleService
@echo .

@echo . Desinstalando o Serviço "SampleService" ...
JavaService.exe -uninstall SampleService
@echo .

pause