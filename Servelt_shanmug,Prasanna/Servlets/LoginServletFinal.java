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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class LoginServletFinal extends HttpServlet {


String elementValueRead;
Connection conn = null;
@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{

PrintWriter out=response.getWriter();

String userid = request.getParameter("userid");
      String pwd = request.getParameter("password");

checklogin(request,response,userid,pwd);



}
@SuppressWarnings("unchecked")
    public void checklogin(HttpServletRequest request,HttpServletResponse response,String userid,String pwd)throws ServletException, IOException {

String checkusername="hi";
int flag=0,flaghead=1;
PrintWriter out= response.getWriter();
try
{
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");

String selectaccount = "select * from Registeration";
PreparedStatement pst1 = conn.prepareStatement(selectaccount);
ResultSet rs = pst1.executeQuery();
while(rs.next())
{
  checkusername = rs.getString("username");
  String checkpwd = rs.getString("passwd");
  String checkusertype = rs.getString("usertype");
  if(userid.equals(checkusername) && pwd.equals(checkpwd))
  {
      
      if(checkusertype.equals("StoreManager"))
      {
        flag=0;
        break;
      }
      else if(checkusertype.equals("Salesman"))
      {
        flag=1;
        break;
      }
      else
      {
        flag=2;
        break;
      }
      
  }
  else
  {
      flag=3;
  }

}
}
catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 
     

if(flag == 0)
{
  request.getRequestDispatcher("ManagerPageFinal.html").include(request, response);
}
else if(flag == 1)
{
  request.getRequestDispatcher("SalesmanPageFinal.html").include(request, response);
}
else if(flag == 2)
{
  HttpSession ses = request.getSession(true);
             ses.setAttribute("Name",checkusername);
             out.println("<p style=\"position: absolute; top: 340px; left: 700px; font-size: x-large\">"+checkusername+"</p>");

            request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request, response);
}

else
{
    out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Invalid User</p>");

            
            request.getRequestDispatcher("Login.html").include(request,response);
}

      }
      
}


  


