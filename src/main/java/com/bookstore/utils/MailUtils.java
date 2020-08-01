package com.bookstore.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * 发送邮件的工具类
 */
public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {

		//创建properties类型对象
		Properties props = new Properties();
		//设置邮件传输协议
		props.setProperty("mail.transport.protocol", "SMTP");
		//设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.163.com");
		//设置邮件服务器验证
		props.setProperty("mail.smtp.auth", "true");

		//定义验证信息，
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("w1445182155@163.com", "IWPOSGMBYOEMHZGG");
			}
		};

		//创建和邮件服务器的会话
		Session session = Session.getInstance(props, auth);

		//设置邮件发送的相关信息
		Message message = new MimeMessage(session);

		//设置发送方
		message.setFrom(new InternetAddress("w1445182155@163.com"));

		//设置接收方
		message.setRecipient(RecipientType.TO, new InternetAddress(email));

		//设置邮件的主题，也就是邮件的标题
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

		//设置邮件内容的编码方式
		message.setContent(emailMsg, "text/html;charset=utf-8");

		//发送信息
		Transport.send(message);
	}
}
