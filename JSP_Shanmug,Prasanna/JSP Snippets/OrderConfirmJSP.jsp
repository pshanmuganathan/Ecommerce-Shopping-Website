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
   <%@ page import="java.util.Random"%>
<%
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
        request.getRequestDispatcher("SuccessfulLoginJSP.jsp").include(request,response);

%>