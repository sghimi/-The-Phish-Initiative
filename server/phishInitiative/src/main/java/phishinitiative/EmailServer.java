package phishinitiative;

import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.java_websocket.WebSocket;

/**
 *
 * @author dog
 */
//This class contains email sending related methods
public class EmailServer {
	
    //Sends a message given recipient, sender (if sending a scam email), type (if sending a scam email), and websocket session (if sending a results email)
    public static void sendMessage(String recipient, int type, WebSocket ws) throws MessagingException {
	
	//these properties and authentication stuff are for sending smtp mail through the comcast mail server
	//this is because without sending the smtp mail through a trusted mail server, mail will be blocked
	//comcast is the ISP at my house where the server is, so I am allowed to send mail through their server
	//but this will not work unless you are on a comcast/xfinity router using the corresponding account credentials
	
        Properties properties = new Properties();
	properties.put("mail.smtp.auth",true);
	properties.put("mail.smtp.starttls.enable","true");
	properties.put("mail.smtp.host", "smtp.comcast.net");
	properties.put("mail.smtp.port",587);
	
	final String myAccountEmail = "Sonia.morris@comcast.net";
	final String password = "flex1010";
	
	Session session = Session.getInstance(properties, new Authenticator(){
	    @Override
	    protected PasswordAuthentication getPasswordAuthentication(){
		    return new PasswordAuthentication(myAccountEmail, password);
	    }
	});
	Message message = prepareMessage(session, recipient, type, ws);
	//Transport.send(message);   //should be commented out in testing versions (send only works on server)
    }

    private static Message prepareMessage(Session session, String recepient,  int type, WebSocket ws){
	try{
	    Message message = new MimeMessage(session);
	
	    if(ws == null){ //if it's a scam email
		message.setFrom(new InternetAddress(EmailTemplate.templates[type].sender));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
		message.setSubject(EmailTemplate.templates[type].title);
		char c = '"';
		String body = EmailTemplate.templates[type].body1;
		body = body + c + EmailTemplate.templates[type].linkBase + Database.generateKey(recepient) + c;
		body = body + EmailTemplate.templates[type].body2;
		message.setContent(body,"text/html; charset=utf-8"); //this makes the body html based rather than plain text
		System.out.println("Sending email\n");
		System.out.println("From: \n"+EmailTemplate.templates[type].sender+"\n");
		System.out.println("Subj: \n"+message.getSubject()+"\n");
		System.out.println("Text: \n"+body+"\n");
	    }
	    else{ //if it's a results email
		message.setFrom(new InternetAddress("results@phishinitiative.duckdns.org"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
		message.setSubject(Database.generateResultTitle(ws));
		message.setText(Database.generateResultBody(ws));
		System.out.println("Sending email\n");
		System.out.println("From: \n"+"results@phishinitiative.duckdns.org"+"\n");
		System.out.println("Subj: \n"+message.getSubject()+"\n");
		System.out.println("Text: \n"+message.getContent()+"\n");
	    }
	    return message;
	}
	catch (Exception e){
		System.out.println("prepareMessage had an oops");
		e.printStackTrace();
	}
		return null;
	}
    
}
