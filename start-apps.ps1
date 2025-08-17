Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   Quiz Application Startup Script" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Set JAVA_HOME environment variable
$env:JAVA_HOME = "C:\Program Files\Java\jdk-23"
Write-Host "JAVA_HOME set to: $env:JAVA_HOME" -ForegroundColor Green

Write-Host ""
Write-Host "Starting React Frontend..." -ForegroundColor Yellow
Start-Process cmd -ArgumentList "/k", "npm start" -WindowStyle Normal

Write-Host ""
Write-Host "Starting Spring Boot Backend..." -ForegroundColor Yellow
Start-Process cmd -ArgumentList "/k", ".\mvnw.cmd spring-boot:run" -WindowStyle Normal

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Applications are starting..." -ForegroundColor Green
Write-Host ""
Write-Host "Frontend: http://localhost:3000" -ForegroundColor White
Write-Host "Backend:  http://localhost:8080" -ForegroundColor White
Write-Host "H2 Console: http://localhost:8080/h2-console" -ForegroundColor White
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Both applications are now running in separate windows." -ForegroundColor Green
Write-Host "You can close this window." -ForegroundColor Gray
