docker run --name postgres-juridico ^
 -e POSTGRES_DB=juridico ^
 -e POSTGRES_USER=admin ^
 -e POSTGRES_PASSWORD=admin ^
 -p 5432:5432 ^
 -v %cd%\pgdata:/var/lib/postgresql/data ^
 --restart unless-stopped ^
 -d postgres:16

pause
