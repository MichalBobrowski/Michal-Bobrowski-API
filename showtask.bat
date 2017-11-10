call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openchrome
echo GRADLEW BUILD has errors - breaking work
goto fail

:openchrome
timeout 50
start chrome -d http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Browser cannot start
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work finished successful