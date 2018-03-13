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
 String orderid = request.getParameter("PId");
       int id= Integer.parseInt(orderid);
       String username="hi";
       String product = "hi";
       String type= "hi";
       String price = "hi";
Object s= request.getSession(false).getAttribute("Name");
  String b= s.toString();
int flag=-1, oid= 0;
     
  
Connection conn = null;
    
      ArrayList<String> orderlist= new ArrayList<String>();

    
     
    ArrayList<List<String>> orderlist2 = new ArrayList<List<String>>();
     ArrayList<List<String>> orderlist3 = new ArrayList<List<String>>();
  

      try
{
 
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");
String selectorders = "SELECT * FROM Orders where OrderId="+id+";";
PreparedStatement pst = conn.prepareStatement(selectorders);
ResultSet rs = pst.executeQuery();

while(rs.next())
{
 
   username= rs.getString("Username");

if(username.equals(b))
{
  flag=0;
}
else
{
  flag=1;
}
}
}

catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 
try
{     
if(flag == 0)
{
 String selectorders = "SELECT * FROM Orders where OrderId="+id+";"; 
PreparedStatement pst = conn.prepareStatement(selectorders);
ResultSet rs = pst.executeQuery();
   out.println("<table style=\"position: absolute; top: 500px; left: 500px;\">"); 
   out.println("<tr>");
    out.println("<td>Orders Placed</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>OrderId</td>");
    out.println("<td>Username</td>");
    out.println("<td>Product</td>");
    out.println("<td>Type</td>");
    out.println("<td>Price</td>");
    out.println("</tr>");
    while(rs.next())
{
   oid= rs.getInt("OrderId");
   username= rs.getString("Username");
 product= rs.getString("Product");
 type = rs.getString("ProductType");
 price= rs.getString("Price");
 out.println("<tr>");
 out.println("<td>"+oid+"</td>");
 out.println("<td>"+username+"</td>");
 out.println("<td>"+product+"</td>");
 out.println("<td>"+type+"</td>");
 out.println("<td>"+price+"</td>");
 out.println("</tr>");
}
out.println("<tr>");
out.println("<td>");
 out.println("<form method=\"get\" action=\"/csj/CancelOrderDBFinal\">");
out.println("<td>Enter the specific Product name</td>");

out.println("<td>");
out.println("<input type=\"TEXT\" size=\"15\" name=\"productname\">");
out.println("<input type=\"hidden\" value="+oid+" name=\"orderid\">");
out.println("<input type=\"submit\" value=\"Cancel\" name=\"cancel\">");
out.println("</form>");
out.println("</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/CancelOrderDBFinal\">");

out.println("<input type=\"hidden\" value="+oid+" name=\"orderid\">");
out.println("<input type=\"submit\" value=\"CancelALL\" name=\"cancel\">");
out.println("</form>");
out.println("</td>");
out.println("</tr>");
out.println("</table>");
request.getRequestDispatcher("SuccessfulLoginJSP.jsp").include(request,response);
}
else
{
  out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Order Doesnt belong to You</p>");
  request.getRequestDispatcher("SuccessfulLoginJSP.jsp").include(request,response);
}
}
  catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        }     
%>