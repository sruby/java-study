htpasswd -bc auth foo 123321
htpasswd -bc auth foo2 123456



kubectl create secret generic basic-auth --from-file=auth --from-file=auth2