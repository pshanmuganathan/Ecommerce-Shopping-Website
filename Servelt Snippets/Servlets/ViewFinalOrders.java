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
import prasanna.AccountDetails;
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


public class ViewFinalOrders extends HttpServlet {

@SuppressWarnings("unchecked")
protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{


       PrintWriter out= response.getWriter();  
      
   
      
 //Object s= request.getSession(false).getAttribute("Name");
  //String b= s.toString();

  



      ArrayList<String> cartlist= new ArrayList<String>();

    ArrayList<List<String>> cartlist2 = new ArrayList<List<String>>();
      
    

  

      try
{
  
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\FinalOrdersList.txt");
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
    out.println("<td>Items in cart to be Delivered : </td>");
    out.println("</tr>");  
for(List<String> plist: cartlist2)
{
      out.println("<tr>");
    out.println("<td>"+plist.get(0)+"</td>");
 out.println("<td>"+plist.get(1)+"--</td>");
 out.println("<td>"+plist.get(2)+"--</td>");
 out.println("<td>"+plist.get(3)+"--</td>");
  //out.println("<td>");
 //out.println("<form method=\"get\" action=\"/csj/CheckOutFinal\">");
//out.println("<input type=\"submit\" value=\"CheckOut\" name=\"CheckOut\">");
//out.println("<input type=\"hidden\" value="+plist.get(2)+" name=\"Type\">");
//out.println("<input type=\"hidden\" value="+plist.get(1)+" name=\"pname\">");
//out.println("</td>");
    out.println("</tr>");
}
out.println("</table>");    

//request.setAttribute("alertMsg", "Account Added. Login Now");
    //response.sendRedirect(request.getContextPath() + "/trial.jsp");
        request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);


}
}




