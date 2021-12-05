htpasswd -bc auth foo 123321
htpasswd -bc auth foo2 123456
htpasswd -bc auth foo3 123456



kubectl create secret generic basic-auth --from-file=auth