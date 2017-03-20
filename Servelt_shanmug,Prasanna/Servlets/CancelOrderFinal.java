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
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class CancelOrderFinal extends HttpServlet {

@SuppressWarnings("unchecked")
protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{


       PrintWriter out= response.getWriter();  
       String orderid = request.getParameter("PId");
       int id= Integer.parseInt(orderid);
       String username="hi";
       String product = "hi";
       String type= "hi";
       String price = "hi";
Object s= request.getSession(false).getAttribute("Name");
  String b= s.toString();
int flag=-1, oid= 0;
     
  
Connection conn = null;
    
      ArrayList<String> orderlist= new ArrayList<String>();

    
     
    ArrayList<List<String>> orderlist2 = new ArrayList<List<String>>();
     ArrayList<List<String>> orderlist3 = new ArrayList<List<String>>();
  

      try
{
 
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");
String selectorders = "SELECT * FROM Orders where OrderId="+id+";";
PreparedStatement pst = conn.prepareStatement(selectorders);
ResultSet rs = pst.executeQuery();

while(rs.next())
{
 
   username= rs.getString("Username");

if(username.equals(b))
{
  flag=0;
}
else
{
  flag=1;
}
}
}

catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 
try
{     
if(flag == 0)
{
 String selectorders = "SELECT * FROM Orders where OrderId="+id+";"; 
PreparedStatement pst = conn.prepareStatement(selectorders);
ResultSet rs = pst.executeQuery();
   out.println("<table style=\"position: absolute; top: 500px; left: 500px;\">"); 
   out.println("<tr>");
    out.println("<td>Orders Placed</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>OrderId</td>");
    out.println("<td>Username</td>");
    out.println("<td>Product</td>");
    out.println("<td>Type</td>");
    out.println("<td>Price</td>");
    out.println("</tr>");
    while(rs.next())
{
   oid= rs.getInt("OrderId");
   username= rs.getString("Username");
 product= rs.getString("Product");
 type = rs.getString("ProductType");
 price= rs.getString("Price");
 out.println("<tr>");
 out.println("<td>"+oid+"</td>");
 out.println("<td>"+username+"</td>");
 out.println("<td>"+product+"</td>");
 out.println("<td>"+type+"</td>");
 out.println("<td>"+price+"</td>");
 out.println("</tr>");
}
out.println("<tr>");
out.println("<td>");
 out.println("<form method=\"get\" action=\"/csj/CancelOrderDBFinal\">");
out.println("<td>Enter the specific Product name</td>");

out.println("<td>");
out.println("<input type=\"TEXT\" size=\"15\" name=\"productname\">");
out.println("<input type=\"hidden\" value="+oid+" name=\"orderid\">");
out.println("<input type=\"submit\" value=\"Cancel\" name=\"cancel\">");
out.println("</form>");
out.println("</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/CancelOrderDBFinal\">");

out.println("<input type=\"hidden\" value="+oid+" name=\"orderid\">");
out.println("<input type=\"submit\" value=\"CancelALL\" name=\"cancel\">");
out.println("</form>");
out.println("</td>");
out.println("</tr>");
out.println("</table>");
request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);
}
else
{
  out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Order Doesnt belong to You</p>");
  request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);
}
}
  catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        }     

    // out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Order Deleted</p>");

  

//request.setAttribute("alertMsg", "Account Added. Login Now");
    //response.sendRedirect(request.getContextPath() + "/trial.jsp");
        



}

}