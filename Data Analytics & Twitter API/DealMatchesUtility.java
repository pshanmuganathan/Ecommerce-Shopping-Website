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


public class DealMatchesUtility extends HttpServlet {


@SuppressWarnings("unchecked")
protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{

       
       PrintWriter out= response.getWriter();  

Connection conn = null;
String line;
int index=0;
List<String> products1= new ArrayList<String>();
List<String> products2= new ArrayList<String>();
HashMap<String,List<String>> hm=new HashMap<String,List<String>>();
HashMap<String,List<String>> selectedproducts=new HashMap<String,List<String>>();

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
  product.add(rs.getString("productprice"));
  product.add(rs.getString("producttype"));
  product.add(rs.getString("productspec"));

hm.put(rs.getString("productname"), product);

}
}
catch (Exception e ) {
            // TODO handle me
   System.out.println("IO error");
        } 
 //out.println("<h2 style=\"position: absolute; top: 450px; left: 500px;\">ONLINE DEALS  TODAY</h2>");

for(Map.Entry<String, List<String>> entry : hm.entrySet())
{

if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey()))
{
  BufferedReader reader = new BufferedReader(new FileReader (new File("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\DealMatches.txt")));
line=reader.readLine();
if(line==null)
{ 
  out.println("<p style=\"position: absolute; top: 500px; left: 700px;\"> No Products matched</p>");
  break;
}
else
{

  do {
  if(line.contains(entry.getKey()))
  {
    
  out.println("<p style=\"position: absolute; top: 550px; left: 500px;\">"+line+"</p>");
  out.println("<br>");


  selectedproducts.put(entry.getKey(),entry.getValue());
  break;
  }
  }while((line = reader.readLine()) != null);

}
}

}
products1= (List<String>)selectedproducts.values().toArray()[0];
System.out.println(products1);
products2= (List<String>)selectedproducts.values().toArray()[1];
out.println("<table style=\"position: absolute; top: 700px; left: 500px;\">");
out.println("<tr>");
out.println("<td>Product Name : </td>");
out.println("<td>"+products1.get(1)+"</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>Product Price : </td>");
out.println("<td>"+products1.get(2)+"</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>Product Specification : </td>");
out.println("<td>"+products1.get(4)+"</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddCartFinal\">");
out.println("<input type=\"submit\" value=\"AddtoCart\" name=\"AddCart\">");
out.println("<input type=\"hidden\" value="+products1.get(3)+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+products1.get(1)+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+products1.get(2)+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddReviewFinal\">");
out.println("<input type=\"submit\" value=\"AddReview\" name=\"AddReview\">");
out.println("<input type=\"hidden\" value="+products1.get(3)+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+products1.get(1)+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+products1.get(4)+" name=\"pspec\">");
out.println("<input type=\"hidden\" value="+products1.get(2)+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddReviewMongoFinal\">");
out.println("<input type=\"submit\" value=\"ViewReview\" name=\"Review\">");
out.println("<input type=\"hidden\" value="+products1.get(3)+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+products1.get(1)+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+products1.get(2)+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("</tr>");

out.println("</table>");
out.println("<table style=\"position: absolute; top: 700px; left: 900px;\">");
out.println("<tr>");
out.println("<td>Product Name : </td>");
out.println("<td>"+products2.get(1)+"</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>Product Price : </td>");
out.println("<td>"+products2.get(2)+"</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>Product Specification : </td>");
out.println("<td>"+products2.get(4)+"</td>");
out.println("</tr>");
out.println("<tr>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddCartFinal\">");
out.println("<input type=\"submit\" value=\"AddtoCart\" name=\"AddCart\">");
out.println("<input type=\"hidden\" value="+products2.get(3)+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+products2.get(1)+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+products2.get(2)+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddReviewFinal\">");
out.println("<input type=\"submit\" value=\"AddReview\" name=\"AddReview\">");
out.println("<input type=\"hidden\" value="+products2.get(3)+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+products2.get(1)+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+products2.get(4)+" name=\"pspec\">");
out.println("<input type=\"hidden\" value="+products2.get(2)+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddReviewMongoFinal\">");
out.println("<input type=\"submit\" value=\"ViewReview\" name=\"Review\">");
out.println("<input type=\"hidden\" value="+products2.get(3)+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+products2.get(1)+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+products2.get(2)+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("</tr>");

out.println("</table>");

request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request, response);

}


}

