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


public class AddCartFinal extends HttpServlet {

@SuppressWarnings("unchecked")
protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{


       PrintWriter out= response.getWriter();  
      String ptype = request.getParameter("Type");
      String pname = request.getParameter("pname");
       String pprice = request.getParameter("pprice");
    
      String error;
 Object s= request.getSession(false).getAttribute("Name");
  String b= s.toString();

  



      ArrayList<String> cartlist= new ArrayList<String>();

    
      Map<String,List<String>> cartmap= new HashMap<String,List<String>>();
     ArrayList<List<String>> cartlist2 = new ArrayList<List<String>>();

  

      try
{
  
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\CartList.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
cartlist2= (ArrayList<List<String>>)objectInputStream.readObject();


      cartlist.add(b);
      cartlist.add(pname);
      cartlist.add(ptype);
      cartlist.add(pprice);
      
//cartmap.put(b,cartlist);
cartlist2.add(cartlist);
FileOutputStream fileOutputStream = new FileOutputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\CartList.txt");
ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
objectOutputStream.writeObject(cartlist2);
objectOutputStream.flush();
objectOutputStream.close();
fileOutputStream.close();




 }
catch (IOException e) {
            System.out.println("IO error");
        }

          
catch ( ClassNotFoundException e ) {
            // TODO handle me
   System.out.println("IO error");
        }

     

      out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Item added to Cart</p>");

  

//request.setAttribute("alertMsg", "Account Added. Login Now");
    //response.sendRedirect(request.getContextPath() + "/trial.jsp");
        request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);



}
}




