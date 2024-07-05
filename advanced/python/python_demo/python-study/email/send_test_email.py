import smtplib
import ssl
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart


def send_test_email(host, port, username, password, to_email):
    try:
        context = ssl.create_default_context()
        with smtplib.SMTP_SSL(host, port, context=context) as server:
            server.ehlo()
            server.login(username, password)

            message = MIMEMultipart()
            message["From"] = username
            message["To"] = to_email
            message["Subject"] = "Test Email"

            body = "This is a test email sent from Python."
            message.attach(MIMEText(body, "plain"))

            server.send_message(message)
            print(f"Test email sent successfully to {to_email}")
    except Exception as e:
        print(f"Failed to send test email")
        print(f"Error: {str(e)}")


# 发送测试邮件
send_test_email("smtp.163.com", 465, "xx", "xx", "xx")