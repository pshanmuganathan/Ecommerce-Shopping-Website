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
      
       String PId = request.getParameter("PId");
      
      String PType= request.getParameter("state");
      
     int flag= -1;

      try
{
  Class.forName("com.mysql.jdbc.Driver").newInstance();
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");

String selectproduct = "select * from ProductsInfo";
PreparedStatement pst1 = conn.prepareStatement(selectproduct);
ResultSet rs = pst1.executeQuery();
while(rs.next())
{
  String check = rs.getString("productid");
  if(PId.equals(check))
  {
    flag = 0;
    break;

  }

}

if(flag == -1)
{
  out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">ProductId does not exist</p>");
}
else
{

String deleteproduct = "DELETE FROM ProductsInfo WHERE productid=? AND producttype=?;";
PreparedStatement pst = conn.prepareStatement(deleteproduct);
pst.setString(1,PId);
pst.setString(2,PType);

pst.execute();
 out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Product Removed</p>");
}
}

 
catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 




        request.getRequestDispatcher("ManagerPageJSP.jsp").include(request,response);

%>