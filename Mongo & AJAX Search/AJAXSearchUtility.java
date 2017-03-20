import java.io.IOException;
import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.*;


public class AJAXSearchUtility extends HttpServlet {


@SuppressWarnings("unchecked")
protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{

String action = request.getParameter("action");
String searchId = request.getParameter("searchId");
StringBuffer sb = new StringBuffer();
  String productname="hi";
  String productprice ="hi";
  String productspec= "hi";
  String producttype= "hi";
  Connection conn = null;
     
       PrintWriter out= response.getWriter();  
       
      try
{

boolean namesAdded = false;
if (action.equals("complete"))
{

if (!searchId.equals(""))
{ 
sb=readdata(request,response,searchId);
if(sb!=null || !sb.equals(""))
{
namesAdded=true;
}
if (namesAdded)
{
response.setContentType("text/xml");
response.getWriter().write("<products>" + sb.toString() + "</products >");
}
}
}
else
{
  try
  {


   Class.forName("com.mysql.jdbc.Driver").newInstance();
  
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");
String selectproducts = "SELECT * FROM ProductsInfo WHERE productid=?;";

PreparedStatement pst = conn.prepareStatement(selectproducts);
pst.setString(1,searchId);

ResultSet rs = pst.executeQuery();
while(rs.next())
{
productname= rs.getString("productname");
productprice=rs.getString("productprice");
productspec=rs.getString("productspec");
producttype= rs.getString("producttype");
}
}
catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 

out.println("<table style=\"position: absolute; top: 500px; left: 700px;\">");
out.println("<tr>");
out.println("<td>Product Name : </td>");
out.println("<td>"+productname+"</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>Product Price : </td>");
out.println("<td>"+productprice+"</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>Product Specification : </td>");
out.println("<td>"+productspec+"</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddCartFinal\">");
out.println("<input type=\"submit\" value=\"AddtoCart\" name=\"AddCart\">");
out.println("<input type=\"hidden\" value="+producttype+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+productname+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+productprice+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddReviewFinal\">");
out.println("<input type=\"submit\" value=\"AddReview\" name=\"AddReview\">");
out.println("<input type=\"hidden\" value="+producttype+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+productname+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+productspec+" name=\"pspec\">");
out.println("<input type=\"hidden\" value="+productprice+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddReviewMongoFinal\">");
out.println("<input type=\"submit\" value=\"ViewReview\" name=\"Review\">");
out.println("<input type=\"hidden\" value="+producttype+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+productname+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+productprice+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("</tr>");

out.println("</table>");
request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);
}
}
catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        }
 




}

public StringBuffer readdata(HttpServletRequest request,HttpServletResponse response,String searchId) throws ServletException, IOException{

HashMap<String,List<String>> data;
StringBuffer sb = new StringBuffer();
data=getData();

Iterator it = data.entrySet().iterator();
while (it.hasNext())
{
Map.Entry pi = (Map.Entry)it.next();
List<String> p= new ArrayList<String>();
p=(List<String>)pi.getValue();
if (p.get(1).toLowerCase().startsWith(searchId))
{
sb.append("<product>");
sb.append("<id>" + p.get(0) + "</id>");
sb.append("<productName>" + p.get(1) + "</productName >");
sb.append("</product >");
}
}
System.out.println(sb);
return sb;

}

public HashMap<String,List<String>> getData() throws ServletException, IOException{
Connection conn = null;

HashMap<String,List<String>> hm=new HashMap<String,List<String>>();
try
{
   Class.forName("com.mysql.jdbc.Driver").newInstance();
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");

String selectCustomerQuery="select * from ProductsInfo";
PreparedStatement pst = conn.prepareStatement(selectCustomerQuery);
ResultSet rs = pst.executeQuery(selectCustomerQuery);
while(rs.next())
{
List<String> product= new ArrayList<String>();
  product.add(rs.getString("productid"));
  product.add(rs.getString("productname"));

hm.put(rs.getString("productid"), product);

}
}
catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 

return hm;

}



}



