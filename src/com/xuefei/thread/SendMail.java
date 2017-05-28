package com.xuefei.thread;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.xuefei.entity.User;

public class SendMail implements Runnable {
	User user=null;
	public SendMail(User user){
		this.user=user;
	}
	@Override
	public void run() {
		Properties props=new Properties();
		props.setProperty("mail.host", "smtp.126.com");//ѡ���ʼ���������ַ
		props.setProperty("mail.smtp.auth", "true");//��֤��½
		
		//���������Base64����
		Session s=Session.getDefaultInstance(props, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("xuefei_19951015@126.com","xueFEI1193");
			}
		});
		
		s.setDebug(true);
		
		MimeMessage msg=new MimeMessage(s);
		try {
			msg.setFrom(new InternetAddress("xuefei_19951015@126.com"));//������setFrom����setSend
			msg.setRecipient(RecipientType.TO, new InternetAddress(user.getMail()));
			msg.setSubject("����һ�⼤���ʼ�");
			String html="�װ���"+user.getName()+":<br>";
			html+="&nbsp;&nbsp;��ϲ��ע�᱾��վ��Ա������24Сʱ�ڵ���������Ӽ����û�<br>";
			html+="&nbsp;&nbsp;<a href='http://localhost/WebActive/ActServlet?code="+user.getCode()+"'>�����û�</a>";
			msg.setContent(html, "text/html;charset=utf-8");//�����ʼ���Ϣ
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		//�����ʼ�
		try {
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
