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
      String PPrice = request.getParameter("PPrice");
    
      String PName = request.getParameter("PName");
      String PType= request.getParameter("state");
      
      String PSpec= request.getParameter("PSpec");
      
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

if(flag == 0)
{
  out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">ProductId already exist. Please use a different ProductId</p>");
}
else
{

String insertaccount = "INSERT INTO ProductsInfo(productid,productname,productprice,producttype,productspec)" + "VALUES (?,?,?,?,?);";
PreparedStatement pst = conn.prepareStatement(insertaccount);
pst.setString(1,PId);
pst.setString(2,PName);
pst.setString(3,PPrice);
pst.setString(4,PType);
pst.setString(5,PSpec);
pst.execute();
 out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Product Added</p>");
}
}

 
catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 




        request.getRequestDispatcher("ManagerPageJSP.jsp").include(request,response);

%>