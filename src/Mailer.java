
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

	public static final String SUBJECT="Ganadores y encuesta! - ProdeKaiser";
	public static final String BODY=" *****  ";
	
	
    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	File archivo = new File("usuarios.txt");
    	FileReader fr = new FileReader(archivo);
		BufferedReader br = new BufferedReader(fr);
    	
        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("prodekaiser@gmail.com", "");
                    }
                });
    	
		String texto;
		texto=br.readLine();
		String mails[] = texto.split(";");

		for(int i=0; i<mails.length;i++) {
			try {
				//TimeUnit.SECONDS.sleep(15);
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("prodekaiser@gmail.com"));
	            message.addRecipient(
	                    Message.RecipientType.TO,
	                    new InternetAddress(mails[i])
	            );
	            message.setSubject(Mailer.SUBJECT);
	            message.setContent(Mailer.BODY, "text/html");
	            
	            Transport.send(message);

	            System.out.println(i + " - Mail enviado a: "+mails[i]);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
			
		}

    
        br.close();
    }
    
    
}
