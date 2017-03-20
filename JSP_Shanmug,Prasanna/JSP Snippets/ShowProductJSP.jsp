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
   <%@ page import="javax.xml.parsers.ParserConfigurationException"%>
   <%@ page import="javax.xml.parsers.SAXParser"%>
   <%@ page import="javax.xml.parsers.SAXParserFactory"%>
   <%@ page import="org.xml.sax.Attributes"%>
   <%@ page import="org.xml.sax.SAXException"%>
   <%@ page import="org.xml.sax.helpers.DefaultHandler"%>
   <%@ page import="java.io.*"%>
   <%!
 ArrayList<List<String>> products = new ArrayList<List<String>>();
List<String> product;
String elementValueRead;
%>
  
<%

SAXParserFactory factory = SAXParserFactory.newInstance();
String s = request.getParameter("Type");
String tempname= request.getParameter("Show");

DefaultHandler handler = new DefaultHandler(){
public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("Product")) {
         
       product= new ArrayList();
       
           
        }


    }

    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("Product")) {
            products.add(product);
             
       return;
        }
         if (element.equalsIgnoreCase("ProductID")) {
            product.add(elementValueRead);
            
       return;
        }
        
        if (element.equalsIgnoreCase("ProductName")) {
            product.add(elementValueRead);
            
       return;
        }
        if(element.equalsIgnoreCase("ProductType")){
           product.add(elementValueRead);
            
       return;
        }
        if(element.equalsIgnoreCase("ProductPrice")){
            product.add(elementValueRead);
           
       return;
        }
        if(element.equalsIgnoreCase("ProductSpec")){
            product.add(elementValueRead);
            
       return;
        }

    }


    public void characters(char[] content, int begin, int end) throws SAXException {
      
        elementValueRead = new String(content, begin, end);
    }

};
try {
   
            SAXParser parser = factory.newSAXParser();
            parser.parse("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\ProductList.xml", handler);
           
            prettyPrint(request,out,response,s,tempname);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
%>
<%!
public void prettyPrint(HttpServletRequest request,JspWriter out,HttpServletResponse response,String s,String tempname) throws ServletException, IOException{

Connection conn = null;
  List<String> products1= new ArrayList<String>();
  ArrayList<List<String>> products2 = new ArrayList<List<String>>();
  String productname="hi";
  String productprice ="hi";
  String productspec= "hi";
 


   
        for (List<String> product: products) {
         String check= product.get(1);
           
         //String s= "Phone";
         if(check.equals(tempname)){

         productname= check;
productprice= product.get(3);
productspec= product.get(4);
break;
   
      }
    }
 try
{
  Class.forName("com.mysql.jdbc.Driver").newInstance();
conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/BestDealdatabase","root","root");
String selectproducts = "SELECT * FROM ProductsInfo WHERE producttype=? AND productname=?;";

PreparedStatement pst = conn.prepareStatement(selectproducts);
pst.setString(1,s);
pst.setString(2,tempname);
ResultSet rs = pst.executeQuery();
while(rs.next())
{
productname= rs.getString("productname");
productprice=rs.getString("productprice");
productspec=rs.getString("productspec");
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
out.println("<input type=\"hidden\" value="+s+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+tempname+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+productprice+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddReviewFinal\">");
out.println("<input type=\"submit\" value=\"AddReview\" name=\"AddReview\">");
out.println("<input type=\"hidden\" value="+s+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+productname+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+productspec+" name=\"pspec\">");
out.println("<input type=\"hidden\" value="+productprice+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("<td>");
out.println("<form method=\"get\" action=\"/csj/AddReviewMongoFinal\">");
out.println("<input type=\"submit\" value=\"ViewReview\" name=\"Review\">");
out.println("<input type=\"hidden\" value="+s+" name=\"Type\">");
out.println("<input type=\"hidden\" value="+productname+" name=\"pname\">");
out.println("<input type=\"hidden\" value="+productprice+" name=\"pprice\">");
out.println("</form>");
out.println("</td>");
out.println("</tr>");

out.println("</table>");
request.getRequestDispatcher("SuccessfulLoginJSP.jsp").include(request,response);
}
%>