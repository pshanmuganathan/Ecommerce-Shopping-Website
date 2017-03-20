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


public class OrderConfirmFinal extends HttpServlet {

@SuppressWarnings("unchecked")
protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{


       PrintWriter out= response.getWriter();  
    String s = request.getParameter("Currentuser");
    Connection conn = null;
    


   Random randomGenerator = new Random();
  int randomInt = randomGenerator.nextInt(100);
  int flag = 0;



        ArrayList<String> orderlist= new ArrayList<String>();

    ArrayList<List<String>> orderlist2 = new ArrayList<List<String>>();

    ArrayList<List<String>> orderlist3 = new ArrayList<List<String>>();
    ArrayList<List<String>> orderlist4 = new ArrayList<List<String>>();
  

      try
{
  
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\CartList.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
orderlist2= (ArrayList<List<String>>)objectInputStream.readObject();


Class.forName("com.mysql.jdbc.Driver").newInstance();
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");
for(List<String> plist: orderlist2)
{
String temp = plist.get(0);
if(s.equals(temp))
{
String insertaccount = "INSERT INTO Orders(OrderId,Username,Product,ProductType,Price)" + "VALUES (?,?,?,?,?);";
PreparedStatement pst = conn.prepareStatement(insertaccount);
pst.setInt(1,randomInt);
pst.setString(2,plist.get(0));
pst.setString(3,plist.get(1));
pst.setString(4,plist.get(2));
pst.setString(5,plist.get(3));
pst.execute();
flag =1;
}
}


for(List<String> plist: orderlist2)
{
  String a= plist.get(0);

  if(s.equals(a))
  {
    orderlist3.add(plist);
  }
}
orderlist2.removeAll(orderlist3);
FileOutputStream fileOutputStream = new FileOutputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\CartList.txt");
ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
objectOutputStream.writeObject(orderlist2);
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

catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 
      

      out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Your Order Id is : "+randomInt+"</p>");

  

//request.setAttribute("alertMsg", "Account Added. Login Now");
    //response.sendRedirect(request.getContextPath() + "/trial.jsp");
        request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);



}
}




