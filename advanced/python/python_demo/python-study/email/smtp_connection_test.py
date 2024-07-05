import smtplib
import ssl

def test_smtp_connection(host, port):
    try:
        context = ssl.create_default_context()
        with smtplib.SMTP_SSL(host, port, context=context) as server:
            server.ehlo()
            print(f"Successfully connected to {host} on port {port}")
    except Exception as e:
        print(f"Failed to connect to {host} on port {port}")
        print(f"Error: {str(e)}")

# 测试连接
test_smtp_connection("smtp.163.com", 465)

