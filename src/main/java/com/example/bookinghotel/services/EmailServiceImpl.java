
package com.example.bookinghotel.services;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.mail.SimpleMailMessage;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.mail.javamail.JavaMailSenderImpl;
        import org.springframework.mail.javamail.MimeMailMessage;
        import org.springframework.mail.javamail.MimeMessageHelper;
        import org.springframework.stereotype.Service;

        import javax.mail.MessagingException;
        import javax.mail.internet.MimeMessage;
        import java.util.Properties;
@Service
public class EmailServiceImpl implements EmailService {



    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendAMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("hotelbookingserviceyoy@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendAHTMLMessage(String to, String subject,String link,String name) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);


        String content = "<div class=\"es-wrapper-color\" style=\"background-color: #FAFAFA;\">\n" +
                "    <!--[if gte mso 9]><v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\"> <v:fill type=\"tile\" color=\"#fafafa\"></v:fill> </v:background><![endif]-->\n" +
                "    <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "        style=\"border-collapse: collapse;border-spacing: 0px;padding: 0;margin: 0;width: 100%;height: 100%;\">\n" +
                "        <tr>\n" +
                "            <td valign=\"top\" style=\"padding: 0;margin: 0;\">\n" +
                "\n" +
                "                <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\"\n" +
                "                    style=\"border-collapse: collapse;border-spacing: 0px;table-layout: fixed;width: 100%;background-color: transparent;\">\n" +
                "                    <tr>\n" +
                "                        <td class=\"es-adaptive\" align=\"center\" style=\"padding: 0;margin: 0;\">\n" +
                "                            <table class=\"es-header-body\"\n" +
                "                                style=\"background-color: #3d5ca3;border-collapse: collapse;border-spacing: 0px;\"\n" +
                "                                width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#3d5ca3\" align=\"center\">\n" +
                "                                <tr>\n" +
                "                                    <td class=\"es-p20t es-p20b es-p20r es-p20l\"\n" +
                "                                        style=\"background-color: #ffffff;padding: 0;margin: 0;padding-top: 20px;padding-bottom: 20px;padding-left: 20px;padding-right: 20px;\"\n" +
                "                                        bgcolor=\"#ffffff\" align=\"left\">\n" +
                "                                        <!--[if mso]><table width=\"560\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"270\" valign=\"top\"><![endif]-->\n" +
                "                                        <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\"\n" +
                "                                            style=\"border-collapse: collapse;border-spacing: 0px;float: left;\">\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"es-m-p20b\" width=\"270\" align=\"left\"\n" +
                "                                                    style=\"padding: 0;margin: 0;\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                                        role=\"presentation\"\n" +
                "                                                        style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"es-m-p0l es-m-txt-c\" align=\"left\"\n" +
                "                                                                style=\"font-size: 0px;padding: 0;margin: 0;\"><a\n" +
                "                                                                    href=\"https://viewstripo.email\" target=\"_blank\"\n" +
                "                                                                    style=\"text-decoration: none;color: #1376C8;font-size: 14px;\">\n" +
                "                                                                    <img src=\"https://drive.google.com/uc?export=view&id=1yZiajS8hSWNWpBdZSUPhCCaTSnMfCUt9\"\n" +
                "                                                                        alt style=\"display: block\" width=\"183\"\n" +
                "                                                                        height=\"69\"></img>\n" +
                "                                                                </a></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        <!--[if mso]></td><td width=\"20\"></td><td width=\"270\" valign=\"top\"><![endif]-->\n" +
                "                                        <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\"\n" +
                "                                            style=\"border-collapse: collapse;border-spacing: 0px;float: right;\">\n" +
                "                                            <tr>\n" +
                "                                                <td width=\"270\" align=\"left\" style=\"padding: 0;margin: 0;\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                                        role=\"presentation\"\n" +
                "                                                        style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"es-p10t es-m-txt-c\" align=\"right\"\n" +
                "                                                                style=\"padding: 0;margin: 0;padding-top: 10px;\">\n" +
                "                                                                <span class=\"es-button-border es-button-border-1\"\n" +
                "                                                                    style=\"border-color: #e06666;background: #ffffff;border-style: solid solid solid solid;border-width: 2px 2px 2px 2px;display: inline-block;border-radius: 10px;width: auto;\"><a\n" +
                "                                                                        href=\"http://localhost:8080/\"\n" +
                "                                                                        class=\"es-button\" target=\"_blank\"\n" +
                "                                                                        style=\"color: #e06666;text-decoration: none;font-size: 14px;border-style: solid;border-color: #FFFFFF;border-width: 15px 20px 15px 20px;display: inline-block;background: #FFFFFF;border-radius: 10px;font-family: arial, &quot;helvetica neue&quot;, helvetica, sans-serif;font-weight: bold;font-style: normal;line-height: 120%;width: auto;text-align: center;\">Our\n" +
                "                                                                        Website</a></span>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"\n" +
                "                    style=\"border-collapse: collapse;border-spacing: 0px;table-layout: fixed;width: 100%;\">\n" +
                "                    <tr>\n" +
                "                        <td style=\"background-color: #fafafa;padding: 0;margin: 0;\" bgcolor=\"#fafafa\" align=\"center\">\n" +
                "                            <table class=\"es-content-body\"\n" +
                "                                style=\"background-color: #ffffff;border-collapse: collapse;border-spacing: 0px;\"\n" +
                "                                width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                <tr>\n" +
                "                                    <td class=\"es-p40t es-p20r es-p20l\"\n" +
                "                                        style=\"background-color: #fef0c9;padding: 0;margin: 0;padding-left: 20px;padding-right: 20px;padding-top: 40px;\"\n" +
                "                                        bgcolor=\"#fef0c9\" align=\"left\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                            style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                            <tr>\n" +
                "                                                <td width=\"560\" valign=\"top\" align=\"center\"\n" +
                "                                                    style=\"padding: 0;margin: 0;\">\n" +
                "                                                    <table\n" +
                "                                                        style=\"background-position: left top;border-collapse: collapse;border-spacing: 0px;\"\n" +
                "                                                        width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                                        role=\"presentation\">\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"es-p5t es-p5b\" align=\"center\"\n" +
                "                                                                style=\"font-size: 0;padding: 0;margin: 0;padding-top: 5px;padding-bottom: 5px;\">\n" +
                "                                                                <img src=\"https://drive.google.com/uc?export=view&id=1TAS6q3N-tJY25VJIIOJcfb9Y_h_d45Qe\"\n" +
                "                                                                    alt style=\"display: block\" width=\"175\"\n" +
                "                                                                    height=\"208\"></img>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"es-p15t es-p15b\" align=\"center\"\n" +
                "                                                                style=\"padding: 0;margin: 0;padding-top: 15px;padding-bottom: 15px;\">\n" +
                "                                                                <h1\n" +
                "                                                                    style=\"color: #333333;font-size: 20px;margin: 0;line-height: 120%;font-family: arial, &quot;helvetica neue&quot;, helvetica, sans-serif;font-style: normal;font-weight: normal;\">\n" +
                "                                                                    <strong>FORGOT YOUR </strong>\n" +
                "                                                                </h1>\n" +
                "                                                                <h1\n" +
                "                                                                    style=\"color: #333333;font-size: 20px;margin: 0;line-height: 120%;font-family: arial, &quot;helvetica neue&quot;, helvetica, sans-serif;font-style: normal;font-weight: normal;\">\n" +
                "                                                                    <strong>&nbsp;PASSWORD?</strong>\n" +
                "                                                                </h1>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"es-p40r es-p40l\" align=\"left\"\n" +
                "                                                                style=\"padding: 0;margin: 0;padding-left: 40px;padding-right: 40px;\">\n" +
                "                                                                <p\n" +
                "                                                                    style=\"text-align: center;margin: 0;font-family: helvetica, &quot;helvetica neue&quot;, arial, verdana, sans-serif;line-height: 150%;color: #666666;font-size: 16px;\">\n" +
                "                                                                    HI,&nbsp;"+name+"\n" +
                "                                                                    </p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"es-p35r es-p40l\" align=\"left\"\n" +
                "                                                                style=\"padding: 0;margin: 0;padding-right: 35px;padding-left: 40px;\">\n" +
                "                                                                <p\n" +
                "                                                                    style=\"text-align: center;margin: 0;font-family: helvetica, &quot;helvetica neue&quot;, arial, verdana, sans-serif;line-height: 150%;color: #666666;font-size: 16px;\">\n" +
                "                                                                    There was a request to\n" +
                "                                                                    change your password!</p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"es-p25t es-p40r es-p40l\" align=\"center\"\n" +
                "                                                                style=\"padding: 0;margin: 0;padding-top: 25px;padding-left: 40px;padding-right: 40px;\">\n" +
                "                                                                <p\n" +
                "                                                                    style=\"margin: 0;font-family: helvetica, &quot;helvetica neue&quot;, arial, verdana, sans-serif;line-height: 150%;color: #666666;font-size: 16px;\">\n" +
                "                                                                    If did not make this request, just ignore this\n" +
                "                                                                    email. Otherwise, please click the button below\n" +
                "                                                                    to change your password:</p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"es-p40t es-p40b es-p10r es-p10l\" align=\"center\"\n" +
                "                                                                style=\"padding: 0;margin: 0;padding-left: 10px;padding-right: 10px;padding-top: 40px;padding-bottom: 40px;\">\n" +
                "                                                                <span class=\"es-button-border es-button-border-1\"\n" +
                "                                                                    style=\"border-color: #e06666;background: #ffffff;border-style: solid solid solid solid;border-width: 2px 2px 2px 2px;display: inline-block;border-radius: 10px;width: auto;\"><a\n" +
                "                                                                        href=\""+link+"\"\n" +
                "                                                                        class=\"es-button\" target=\"_blank\"\n" +
                "                                                                        style=\"color: #e06666;text-decoration: none;font-size: 14px;border-style: solid;border-color: #FFFFFF;border-width: 15px 20px 15px 20px;display: inline-block;background: #FFFFFF;border-radius: 10px;font-family: arial, &quot;helvetica neue&quot;, helvetica, sans-serif;font-weight: bold;font-style: normal;line-height: 120%;width: auto;text-align: center;\">RESET\n" +
                "                                                                        PASSWORD</a></span>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"es-p20t es-p10r es-p10l\"\n" +
                "                                        style=\"background-position: center center;padding: 0;margin: 0;padding-left: 10px;padding-right: 10px;padding-top: 20px;\"\n" +
                "                                        align=\"left\">\n" +
                "                                        <!--[if mso]><table width=\"580\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"199\" valign=\"top\"><![endif]-->\n" +
                "\n" +
                "                                        <!--[if mso]></td>\n" +
                "<td width=\"20\"></td><td width=\"361\" valign=\"top\"><![endif]-->\n" +
                "\n" +
                "                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"es-p5t es-p20b es-p20r es-p20l\"\n" +
                "                                        style=\"background-position: left top;padding: 0;margin: 0;padding-top: 5px;padding-bottom: 20px;padding-left: 20px;padding-right: 20px;\"\n" +
                "                                        align=\"left\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                            style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                            <tr>\n" +
                "                                                <td width=\"560\" valign=\"top\" align=\"center\"\n" +
                "                                                    style=\"padding: 0;margin: 0;\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                                        role=\"presentation\"\n" +
                "                                                        style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                                        <tr>\n" +
                "                                                            <td align=\"center\" style=\"padding: 0;margin: 0;\">\n" +
                "                                                                <p\n" +
                "                                                                    style=\"font-size: 14px;margin: 0;font-family: helvetica, &quot;helvetica neue&quot;, arial, verdana, sans-serif;line-height: 150%;color: #666666;\">\n" +
                "                                                                    Contact us: <a target=\"_blank\"\n" +
                "                                                                        style=\"font-size: 14px;color: #666666;text-decoration: none;\"\n" +
                "                                                                        href=\"tel:1900 5454 40\">1900 5454 40</a> | <a\n" +
                "                                                                        target=\"_blank\" href=\"mailto:hotelbookingserviceyoy@gmail.com\"\n" +
                "                                                                        style=\"font-size: 14px;color: #666666;text-decoration: none;\">hotelbookingserviceyoy@gmail.com</a>\n" +
                "                                                                </p>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"\n" +
                "                    style=\"border-collapse: collapse;border-spacing: 0px;table-layout: fixed;width: 100%;\">\n" +
                "                    <tr>\n" +
                "                        <td style=\"background-color: #fafafa;padding: 0;margin: 0;\" bgcolor=\"#fafafa\" align=\"center\">\n" +
                "                            <table class=\"es-content-body\"\n" +
                "                                style=\"background-color: transparent;border-collapse: collapse;border-spacing: 0px;\"\n" +
                "                                width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"transparent\" align=\"center\">\n" +
                "                                <tr>\n" +
                "                                    <td class=\"es-p15t\"\n" +
                "                                        style=\"background-position: left top;padding: 0;margin: 0;padding-top: 15px;\"\n" +
                "                                        align=\"left\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                            style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                            <tr>\n" +
                "                                                <td width=\"600\" valign=\"top\" align=\"center\"\n" +
                "                                                    style=\"padding: 0;margin: 0;\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                                        role=\"presentation\"\n" +
                "                                                        style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                                        <tr>\n" +
                "                                                            <td class=\"es-p20b es-p20r es-p20l\" align=\"center\"\n" +
                "                                                                style=\"font-size: 0;padding: 0;margin: 0;padding-bottom: 20px;padding-left: 20px;padding-right: 20px;\">\n" +
                "                                                                <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                                                    border=\"0\" role=\"presentation\"\n" +
                "                                                                    style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                                                    <tr>\n" +
                "                                                                        <td\n" +
                "                                                                            style=\"border-bottom: 1px solid #fafafa;background: none;height: 1px;width: 100%;margin: 0px;padding: 0;\">\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </table>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"\n" +
                "                    style=\"border-collapse: collapse;border-spacing: 0px;table-layout: fixed;width: 100%;background-color: transparent;\">\n" +
                "                    <tr>\n" +
                "                        <td style=\"background-color: #fafafa;padding: 0;margin: 0;\" bgcolor=\"#fafafa\" align=\"center\">\n" +
                "                            <table class=\"es-footer-body\"\n" +
                "                                style=\"background-color: transparent;border-collapse: collapse;border-spacing: 0px;\"\n" +
                "                                width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"transparent\" align=\"center\">\n" +
                "                                <tr>\n" +
                "                                    <td class=\"es-p15t es-p5b es-p20r es-p20l\" align=\"left\"\n" +
                "                                        style=\"padding: 0;margin: 0;padding-bottom: 5px;padding-top: 15px;padding-left: 20px;padding-right: 20px;\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                            style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                            <tr>\n" +
                "                                                <td width=\"560\" valign=\"top\" align=\"center\"\n" +
                "                                                    style=\"padding: 0;margin: 0;\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                                        style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                                        <tr>\n" +
                "                                                            <td align=\"center\"\n" +
                "                                                                style=\"display: none;padding: 0;margin: 0;\"></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"\n" +
                "                    style=\"border-collapse: collapse;border-spacing: 0px;table-layout: fixed;width: 100%;\">\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"padding: 0;margin: 0;\">\n" +
                "                            <table class=\"es-content-body\"\n" +
                "                                style=\"background-color: transparent;border-collapse: collapse;border-spacing: 0px;\"\n" +
                "                                width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\">\n" +
                "                                <tr>\n" +
                "                                    <td class=\"es-p30t es-p30b es-p20r es-p20l\" align=\"left\"\n" +
                "                                        style=\"padding: 0;margin: 0;padding-left: 20px;padding-right: 20px;padding-top: 30px;padding-bottom: 30px;\">\n" +
                "                                        <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                            style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                            <tr>\n" +
                "                                                <td width=\"560\" valign=\"top\" align=\"center\"\n" +
                "                                                    style=\"padding: 0;margin: 0;\">\n" +
                "                                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"\n" +
                "                                                        style=\"border-collapse: collapse;border-spacing: 0px;\">\n" +
                "                                                        <tr>\n" +
                "                                                            <td align=\"center\"\n" +
                "                                                                style=\"display: none;padding: 0;margin: 0;\"></td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</div>";


        helper.setFrom("hotelbookingserviceyoy@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        emailSender.send(message);
    }
}
