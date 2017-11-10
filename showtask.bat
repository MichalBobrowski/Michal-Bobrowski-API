call runcrud.bat
timeout 45
start chrome -d http://localhost:8080/crud/v1/task/getTasks
