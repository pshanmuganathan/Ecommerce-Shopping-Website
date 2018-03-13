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


public class AddProductFinal extends HttpServlet {

@SuppressWarnings("unchecked")
protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{


       PrintWriter out= response.getWriter();  
      String PId = request.getParameter("PId");
      String PPrice = request.getParameter("PPrice");
    
      String PName = request.getParameter("PName");
      String PType= request.getParameter("state");
      
      String PSpec= request.getParameter("PSpec");
      String error;
       String a= "Phone";
  String b= "Tablets";
  String c= "Laptop";
  String d= "TV";

  

      ArrayList<String> productlist= new ArrayList<String>();

    
      Map<String,List<String>> productmap= new HashMap<String,List<String>>();
    
 Map<String,List<String>> usermap1= new HashMap<String,List<String>>();
  

      try
{
   if(PType.equals(a))
  {
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\SmartPhone1.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
productmap= (HashMap<String,List<String>>)objectInputStream.readObject();

if(productmap.containsKey(PId))
{ error = "Product already exist as " + PId;}
else{
 productlist.add(PId);
      productlist.add(PPrice);
      productlist.add(PType);
      productlist.add(PName);
      productlist.add(PSpec);
productmap.put(PId,productlist);
FileOutputStream fileOutputStream = new FileOutputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\SmartPhone1.txt");
ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
objectOutputStream.writeObject(productmap);
objectOutputStream.flush();
objectOutputStream.close();
fileOutputStream.close();
}


}
else if(PType.equals(b))
{
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\Tablets1.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
productmap= (HashMap<String,List<String>>)objectInputStream.readObject();

if(productmap.containsKey(PId))
{ error = "Product already exist as " + PId;}
else{
 productlist.add(PId);
      productlist.add(PPrice);
      productlist.add(PType);
      productlist.add(PName);
      productlist.add(PSpec);
productmap.put(PId,productlist);
FileOutputStream fileOutputStream = new FileOutputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\Tablets1.txt");
ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
objectOutputStream.writeObject(productmap);
objectOutputStream.flush();
objectOutputStream.close();
fileOutputStream.close();
}
}
else if(PType.equals(c))
{
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\Laptop1.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
productmap= (HashMap<String,List<String>>)objectInputStream.readObject();

if(productmap.containsKey(PId))
{ error = "Product already exist as " + PId;}
else{
 productlist.add(PId);
      productlist.add(PPrice);
      productlist.add(PType);
      productlist.add(PName);
      productlist.add(PSpec);
productmap.put(PId,productlist);
FileOutputStream fileOutputStream = new FileOutputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\Laptop1.txt");
ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
objectOutputStream.writeObject(productmap);
objectOutputStream.flush();
objectOutputStream.close();
fileOutputStream.close();
}
}
else
{
  FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\Tv1.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
productmap= (HashMap<String,List<String>>)objectInputStream.readObject();

if(productmap.containsKey(PId))
{ error = "Product already exist as " + PId;}
else{
 productlist.add(PId);
      productlist.add(PPrice);
      productlist.add(PType);
      productlist.add(PName);
      productlist.add(PSpec);
productmap.put(PId,productlist);
FileOutputStream fileOutputStream = new FileOutputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\Tv1.txt");
ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
objectOutputStream.writeObject(productmap);
objectOutputStream.flush();
objectOutputStream.close();
fileOutputStream.close();
}
}

 }
catch (IOException e) {
            System.out.println("IO error");
        }
            
catch ( ClassNotFoundException e ) {
            // TODO handle me
   System.out.println("IO error");
        } 
     

      

      out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Product Added</p>");

  

//request.setAttribute("alertMsg", "Account Added. Login Now");
    //response.sendRedirect(request.getContextPath() + "/trial.jsp");
        request.getRequestDispatcher("ManagerPageFinal.html").include(request,response);



}
}




