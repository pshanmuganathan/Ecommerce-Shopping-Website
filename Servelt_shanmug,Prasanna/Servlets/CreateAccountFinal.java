import java.io.IOException;
import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.*;


public class CreateAccountFinal extends HttpServlet {

@SuppressWarnings("unchecked")
protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{

      Connection conn = null;
       PrintWriter out= response.getWriter();  
      String FirstName = request.getParameter("FirstName");
      String LastName = request.getParameter("LastName");
    
      String Username = request.getParameter("Username");
      String Password= request.getParameter("Password");
      String UserType= request.getParameter("state");
      
      String Email= request.getParameter("Email");
      String error;
      int flag = -1;
      
  

      ArrayList<String> userlist= new ArrayList<String>();

    
      Map<String,List<String>> usermap= new HashMap<String,List<String>>();
    
 Map<String,List<String>> usermap1= new HashMap<String,List<String>>();
  

      try
{


Class.forName("com.mysql.jdbc.Driver").newInstance();
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");

String selectaccount = "select * from Registeration";
PreparedStatement pst1 = conn.prepareStatement(selectaccount);
ResultSet rs = pst1.executeQuery();
while(rs.next())
{
  String check = rs.getString("username");
  if(Username.equals(check))
  {
    flag = 0;
    break;

  }

}

if(flag == 0)
{
  out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Username already exist. Please use a different User name</p>");
}
else
{
String insertaccount = "INSERT INTO Registeration(firstname,lastname,username,passwd,email,usertype)" + "VALUES (?,?,?,?,?,?);";
PreparedStatement pst = conn.prepareStatement(insertaccount);
pst.setString(1,FirstName);
pst.setString(2,LastName);
pst.setString(3,Username);
pst.setString(4,Password);
pst.setString(5,Email);
pst.setString(6,UserType);
pst.execute();
 out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Account Created</p>");
}


}

 
catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 
     

      

     

  

//request.setAttribute("alertMsg", "Account Added. Login Now");
    //response.sendRedirect(request.getContextPath() + "/trial.jsp");
        request.getRequestDispatcher("Login.html").include(request,response);



}

}


