@echo off
cd /d C:\Users\hisru\Documents\GitHub\juejin_demo
:: 设置 Git 代理
git config --local http.proxy 127.0.0.1:7890
git config --local https.proxy 127.0.0.1:7890

git add .
git commit -m "Daily auto-commit"
git push

cd /d C:\Users\hisru\Documents\GitHub\java-study
:: 设置 Git 代理
git config --local http.proxy 127.0.0.1:7890
git config --local https.proxy 127.0.0.1:7890
git add .
git commit -m "Daily auto-commit"
git push

cd /d C:\Users\hisru\Documents\code\python_project
:: 设置 Git 代理
git config --local http.proxy 127.0.0.1:7890
git config --local https.proxy 127.0.0.1:7890
git add .
git commit -m "Daily auto-commit"
git push

cd /d C:\Users\hisru\Documents\GitHub\strategy_project
:: 设置 Git 代理
git config --local http.proxy 127.0.0.1:7890
git config --local https.proxy 127.0.0.1:7890
git add .
git commit -m "Daily auto-commit"
git push

cd /d C:\Users\hisru\Documents\code\zs-duij
:: 设置 Git 代理
git add .
git commit -m "Daily auto-commit"
git push