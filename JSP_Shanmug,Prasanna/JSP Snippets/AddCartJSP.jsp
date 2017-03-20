<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="java.util.ArrayList"%>
   <%@ page import="java.util.List"%>
   <%@ page import="java.io.File"%>
   <%@ page import="java.sql.Connection"%>
   <%@ page import="java.sql.DriverManager"%>
   <%@ page import="java.sql.SQLException"%>
   <%@ page import="java.sql.PreparedStatement"%>
   <%@ page import="java.sql.*"%>
   <%@ page import="java.io.*"%>
  
  <%

  String ptype = request.getParameter("Type");
      String pname = request.getParameter("pname");
       String pprice = request.getParameter("pprice");
    
      String error;
 Object s= request.getSession(false).getAttribute("Name");
  String b= s.toString();

  



      ArrayList<String> cartlist= new ArrayList<String>();

    
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
        request.getRequestDispatcher("SuccessfulLoginJSP.jsp").include(request,response);


%>