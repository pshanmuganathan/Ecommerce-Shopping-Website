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
       request.getRequestDispatcher("SuccessfulLoginJSP.jsp").include(request,response);


%>