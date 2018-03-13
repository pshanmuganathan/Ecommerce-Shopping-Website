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
<%
 Connection conn = null;
     
      String FirstName = request.getParameter("FirstName");
      String LastName = request.getParameter("LastName");
    
      String Username = request.getParameter("Username");
      String Password= request.getParameter("Password");
      String UserType= request.getParameter("state");
      
      String Email= request.getParameter("Email");
      String error;
      int flag = -1;
      
  

      ArrayList userlist= new ArrayList();

    
  

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
        
   System.err.println("IO error");
        } 
     
     
        RequestDispatcher rs=request.getRequestDispatcher("Login.jsp");
        rs.include(request,response);


%>