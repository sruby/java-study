@echo off
SETLOCAL

:: Set Oracle SID
SET ORACLE_SID=ORCL

:: Set Oracle Home path
SET ORACLE_HOME=c:\users\hisru\downloads\oracle-ws.x64_193000_db_home

:: Start Oracle Database Service
echo Starting Oracle Database Service for %ORACLE_SID%...
net start OracleService%ORACLE_SID%

:: Start Oracle Listener
echo Starting Oracle Listener...
net start OracleOraDb19Home1TNSListener

:: Optionally, start the database instance using SQL*Plus
:: echo Starting Oracle Database Instance...
:: %ORACLE_HOME%\bin\sqlplus / as sysdba @start_db.sql

ENDLOCAL