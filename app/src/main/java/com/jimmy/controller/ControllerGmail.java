package com.jimmy.controller;
import android.app.Activity;
import android.os.StrictMode;
import android.widget.Toast;
import com.jimmy.modelo.Reportes;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class ControllerGmail {
    private Session session;
    public void enviarGmail(Reportes reporte, Activity mostrarAlerta){
        String correo = "alexander22piedrahita@gmail.com";
        String password = "arayllptnlnsilfa";
        String mensaje = "DATOS DEL REMITENTE: "+
                "Nombre: "+reporte.getNombre()+
                ", Correo: "+reporte.getCorreo()+
                " DATOS DEL REPORTE: "+
                "Codigo: "+reporte.getCodigo()+
                ", Sede: "+reporte.getSede()+
                ", Sala: "+reporte.getSala()+
                ", Pc: "+reporte.getPc()+
                "Descripcion: "+reporte.getDescripcion();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");
        try {
            session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correo,password);
                }
            });
            if(session != null){
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correo));
                message.setSubject("Nuevo reporte de ReportesMayor");
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("jimmy22piedrahita@gmail.com"));
                message.setContent(mensaje,"text/html; charset=utf-8");
                Transport.send(message);
                Toast.makeText(mostrarAlerta, "Se envio correctamente el gmail", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
