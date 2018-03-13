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


public class CheckOutFinal extends HttpServlet {

String elementValueRead;

@SuppressWarnings("unchecked")  
  protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{

PrintWriter out=response.getWriter();


SAXParserFactory factory = SAXParserFactory.newInstance();
String s = request.getParameter("Currentuser");
int total =0;


      ArrayList<String> cartlist= new ArrayList<String>();

    ArrayList<List<String>> cartlist2 = new ArrayList<List<String>>();
      
    

  

      try
{
  
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\CartList.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
cartlist2= (ArrayList<List<String>>)objectInputStream.readObject();







 }
catch (IOException e) {
            System.out.println("IO error");
        }
            
            
catch ( ClassNotFoundException e ) {
            // TODO handle me
   System.out.println("IO error");
        }

   out.println("<table style=\"position: absolute; top: 500px; left: 700px;\">"); 
   out.println("<tr>");
    out.println("<td>Items to CheckOut</td>");
    out.println("</tr>");  
for(List<String> plist: cartlist2)
{
  String temp = plist.get(0);
  int y = 0;

   if(s.equals(temp))
   { 
    out.println("<td>"+plist.get(0)+"</td>");
 out.println("<td>"+plist.get(1)+"--</td>");
 out.println("<td>"+plist.get(2)+"--</td>");
 out.println("<td>"+plist.get(3)+"--</td>");

    out.println("</tr>");
    y = Integer.parseInt(plist.get(3));
      total = total + y;
      }
}
out.println("<tr>");
out.println("<td>Total : "+total+"</td");

out.println("</tr>");
out.println("<tr>");
out.println("<td>Enter Card Details : </td>");
out.println("<td>");
out.println("<input type=\"Text\" value=\"\" name=\"CardNo\">");
out.println("</td>");
out.println("</tr>");
   out.println("<tr>");
  out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/OrderConfirmFinal\">");
out.println("<input type=\"submit\" value=\"CheckOut\" name=\"CheckOut\">");
out.println("<input type=\"hidden\" value="+s+" name=\"Currentuser\">");

out.println("</form>");
out.println("</td>");
 out.println("</tr>");
out.println("</table>");    


//request.setAttribute("alertMsg", "Account Added. Login Now");
    //response.sendRedirect(request.getContextPath() + "/trial.jsp");
        request.getRequestDispatcher("CheckOutFinal.html").include(request,response);



      }
    } 