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
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.*;


public class CancelOrderDBFinal extends HttpServlet {

@SuppressWarnings("unchecked")
protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{


       PrintWriter out= response.getWriter();  
    String s = request.getParameter("cancel");
    Connection conn = null;
    


  int flag = 0;



  

      try
{
  



Class.forName("com.mysql.jdbc.Driver").newInstance();
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");
if(s.equals("Cancel"))
{
  String oid= request.getParameter("orderid");
  int i= Integer.parseInt(oid);
  String productname= request.getParameter("productname");
  
String removeorder = "DELETE FROM Orders WHERE OrderId=? AND Product=?;";
PreparedStatement pst = conn.prepareStatement(removeorder);
pst.setInt(1,i);
pst.setString(2,productname);
pst.execute();
}
else
{
  String oid= request.getParameter("orderid");
  String removeorder = "DELETE FROM Orders WHERE OrderId="+oid+";";
PreparedStatement pst = conn.prepareStatement(removeorder);
pst.execute();
}





 }

catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 
      

     out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Order Cancelled</p>");

  

//request.setAttribute("alertMsg", "Account Added. Login Now");
    //response.sendRedirect(request.getContextPath() + "/trial.jsp");
       request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);



}
}




