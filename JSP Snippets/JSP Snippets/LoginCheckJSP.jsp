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

String userid = request.getParameter("userid");
String pwd = request.getParameter("password");

checklogin(request,out,response,userid,pwd);
%>
<%!
public void checklogin(HttpServletRequest request,JspWriter out,HttpServletResponse response,String userid,String pwd)throws ServletException, IOException {
String checkusername="hi";
int flag=0;
int flaghead=1;

try
{
Connection conn = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");

String selectaccount = "select * from Registeration";
PreparedStatement pst1 = conn.prepareStatement(selectaccount);
ResultSet rs = pst1.executeQuery();
while(rs.next())
{
  checkusername = rs.getString("username");
  String checkpwd = rs.getString("passwd");
  String checkusertype = rs.getString("usertype");
  if(userid.equals(checkusername) && pwd.equals(checkpwd))
  {
      
      if(checkusertype.equals("StoreManager"))
      {
        flag=0;
        break;
      }
      else if(checkusertype.equals("Salesman"))
      {
        flag=1;
        break;
      }
      else
      {
        flag=2;
        break;
      }
      
  }
  else
  {
      flag=3;
  }

}
}
catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 
     

if(flag == 0)
{
  request.getRequestDispatcher("ManagerPageFinal.jsp").include(request, response);
}
else if(flag == 1)
{
  request.getRequestDispatcher("SalesmanPageFinal.jsp").include(request, response);
}
else if(flag == 2)
{
  HttpSession ses = request.getSession(true);
             ses.setAttribute("Name",checkusername);
             out.println("<p style=\"position: absolute; top: 315px; left: 700px; font-size: x-large\">"+checkusername+"</p>");

            request.getRequestDispatcher("SuccessfulLoginJSP.jsp").include(request, response);
}

else
{
    out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Invalid User</p>");

            
            request.getRequestDispatcher("Login.jsp").include(request,response);
}

      }
%>
