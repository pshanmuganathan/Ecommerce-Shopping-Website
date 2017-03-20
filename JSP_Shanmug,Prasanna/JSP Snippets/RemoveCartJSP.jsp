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
String s = request.getParameter("Currentuser");
  String pname = request.getParameter("pname");
  int flag = 0;

  



      ArrayList<String> cartlist= new ArrayList<String>();

    ArrayList<List<String>> cartlist2 = new ArrayList<List<String>>();

    ArrayList<List<String>> cartlist3 = new ArrayList<List<String>>();
    ArrayList<List<String>> cartlist4 = new ArrayList<List<String>>();
      
    

  

      try
{
  
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\CartList.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
cartlist2= (ArrayList<List<String>>)objectInputStream.readObject();

for(List<String> plist: cartlist2)
{
  String a= plist.get(0);
  String b= plist.get(1);
  if(s.equals(a) && pname.equals(b))
  {
    cartlist3.add(plist);
  }
}
cartlist2.removeAll(cartlist3);
FileOutputStream fileOutputStream = new FileOutputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\CartList.txt");
ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
objectOutputStream.writeObject(cartlist2);
objectOutputStream.flush();
objectOutputStream.close();
fileOutputStream.close();

FileInputStream fileInputStream1 = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\CartList.txt");
ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
cartlist4= (ArrayList<List<String>>)objectInputStream1.readObject();



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
for(List<String> plist: cartlist4)
{
  String temp = plist.get(0);

   if(s.equals(temp))
   { 
    out.println("<td>"+plist.get(0)+"</td>");
 out.println("<td>"+plist.get(1)+"--</td>");
 out.println("<td>"+plist.get(2)+"--</td>");
 out.println("<td>"+plist.get(3)+"--</td>");
      out.println("<td>");
 out.println("<form method=\"get\" action=\"/csj/RemoveCartJSP.jsp\">");
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
