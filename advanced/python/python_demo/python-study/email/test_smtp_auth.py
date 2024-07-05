import smtplib
import ssl

def test_smtp_auth(host, port, username, password):
    try:
        context = ssl.create_default_context()
        with smtplib.SMTP_SSL(host, port, context=context) as server:
            server.ehlo()
            server.login(username, password)
            print(f"Successfully authenticated to {host} on port {port}")
    except Exception as e:
        print(f"Failed to authenticate to {host} on port {port}")
        print(f"Error: {str(e)}")

# 测试连接和身份认证
test_smtp_auth("smtp.163.com", 465, "xxx", "xxx")