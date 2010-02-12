package mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SendMailServletTest extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doGet(req, resp);

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String msgBody = "Test mail....";
//		UserService userService = UserServiceFactory.getUserService();
//		User user = userService.getCurrentUser();

		resp.setContentType("text/plain");
//		if (user != null) {
			try {
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("stepheninje@gmail.com",
						"Stephen Inje"));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
						"suneetainje@gmail.com", "Mr. User"));
				msg
						.setSubject("Your suneetainje@gmail.com account can send test mails");
				msg.setText(msgBody);
				Transport.send(msg);

				resp.getWriter().println("Mail sent....Check your mail ");
			} catch (AddressException e) {
				resp.getWriter().println("AddressException " + e.getMessage());
			} catch (MessagingException e) {
				resp.getWriter()
						.println("MessagingException " + e.getMessage());
			}
//		} else {
//			resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
//		}
	}
}
