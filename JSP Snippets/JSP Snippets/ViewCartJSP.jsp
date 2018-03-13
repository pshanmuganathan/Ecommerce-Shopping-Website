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

Object s= request.getSession(false).getAttribute("Name");
  String b= s.toString();
  int flag = 0;

  



      ArrayList<String> cartlist= new ArrayList<String>();

    ArrayList<List<String>> cartlist2 = new ArrayList<List<String>>();
      
    

  

      try
{
  
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\CartList.txt");
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
    out.println("<td>Items in Cart</td>");
    out.println("</tr>");  
for(List<String> plist: cartlist2)
{
  String temp = plist.get(0);

   if(s.equals(temp))
   { 
    out.println("<td>"+plist.get(0)+"</td>");
 out.println("<td>"+plist.get(1)+"--</td>");
 out.println("<td>"+plist.get(2)+"--</td>");
 out.println("<td>"+plist.get(3)+"--</td>");
     out.println("<td>");
     
 out.println("<form method=\"get\" action=\"/csj/RemoveCartFinal\">");
out.println("<input type=\"submit\" value=\"Remove\" name=\"Remove\">");
out.println("<input type=\"hidden\" value="+s+" name=\"Currentuser\">");
out.println("<input type=\"hidden\" value="+plist.get(1)+" name=\"pname\">");
out.println("</form>");
out.println("</td>"); 

    out.println("</tr>");
    flag = 1;
      }
}
if(flag == 1)
{
   out.println("<tr>");
  out.println("<td>");
 out.println("<form method=\"get\" action=\"/csj/CheckOutFinal\">");
out.println("<input type=\"submit\" value=\"CheckOut\" name=\"CheckOut\">");
out.println("<input type=\"hidden\" value="+s+" name=\"Currentuser\">");
out.println("</form>");
out.println("</td>");
 out.println("</tr>");
out.println("</table>");    
}
else
{
out.println("<tr><td>No Items in cart</td></tr>"); 
out.println("</table>");   
}
//request.setAttribute("alertMsg", "Account Added. Login Now");
    //response.sendRedirect(request.getContextPath() + "/trial.jsp");
        request.getRequestDispatcher("SuccessfulLoginJSP.jsp").include(request,response);

%>