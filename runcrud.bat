del C:\Tomcat\apache-tomcat-9.0.1\webapps\crud
call gradlew build
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:rename
del build\libs\crud.war
ren build\libs\MichalBobrowski-0.0.1-SNAPSHOT.war crud.war
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Cannot rename file
goto fail

:stoptomcat
call %CATALINA_HOME%\bin\shutdown.bat

:copyfile
copy C:\Users\misizaba\Desktop\MichalBobrowski\build\libs\crud.war %CATALINA_HOME%\webapps
if "%ERRORLEVEL%" == "0" goto runtomcat
echo Cannot copy file
goto fail

:runtomcat
call %CATALINA_HOME%\bin\startup.bat
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.