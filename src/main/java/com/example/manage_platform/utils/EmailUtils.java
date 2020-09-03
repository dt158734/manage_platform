package com.example.manage_platform.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.log4j.Log4j2;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Log4j2
public class EmailUtils {
    // qq企业邮箱smtp
    private static final String port = "465";
    private static final String host = "your";
    private static final String username = "";
    private static final String password = "";


    /**
     *
     * @param text 文本信息
     * @param subject 主题信息
     * @param to 对方邮箱
     * @return
     */
    public static Map<String, Object> sendEmail(String text, String subject, String to) {
        Map<String, Object> map = new HashMap<>();
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host); // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", host); // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true"); // 用刚刚设置好的props对象构建一个session
        props.put("mail.smtp.starttls.enable", "true");// 使用 STARTTLS安全连接
        props.put("mail.smtp.auth", "true"); // 使用验证
        props.setProperty("mail.smtp.port", port);
        //开启安全协议
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", port);
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            //发件人
            mimeMessage.setFrom(new InternetAddress(username,"管理员"));        //可以设置发件人的别名
            //mimeMessage.setFrom(new InternetAddress(account));    //如果不需要就省略
            //收件人
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //主题
            mimeMessage.setSubject(subject);
            //时间
            mimeMessage.setSentDate(new Date());
            //容器类，可以包含多个MimeBodyPart对象
            Multipart mp = new MimeMultipart();

            //MimeBodyPart可以包装文本，图片，附件
            MimeBodyPart body = new MimeBodyPart();
            //HTML正文
            body.setContent(text, "text/html; charset=UTF-8");
            mp.addBodyPart(body);

            /*//添加图片&附件
            body = new MimeBodyPart();
            body.attachFile(fileStr);
            mp.addBodyPart(body);*/

            //设置邮件内容
            mimeMessage.setContent(mp);
            //仅仅发送文本
            //mimeMessage.setText(content);
            mimeMessage.saveChanges();
            Transport.send(mimeMessage);
            // 重置成功后进行唯一识别码保存
//            administrationDao.updateReset(uuid,userId);

        } catch (MessagingException e) {
            log.error("邮箱发送异常");
            map.put("success",false);
            map.put("msg","邮箱发送异常!");
        } catch (IOException e) {
            log.error("邮箱发送异常");
            map.put("success",false);
            map.put("msg","邮箱发送异常!");
        } catch (Exception e){
            log.error("邮箱发送异常");
            map.put("success",false);
            map.put("msg","数据接收参数异常!");

        }
        return map;
    }

}
