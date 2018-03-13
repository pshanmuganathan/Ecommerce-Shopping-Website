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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ShowProductFinal extends HttpServlet {
//Product product;
ArrayList<List<String>> products = new ArrayList<List<String>>();
List<String> product;
String elementValueRead;

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{

PrintWriter out=response.getWriter();


SAXParserFactory factory = SAXParserFactory.newInstance();
String s = request.getParameter("Type");
String tempname= request.getParameter("Show");



DefaultHandler handler = new DefaultHandler(){
public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("Product")) {
         
       product= new ArrayList<String>();
       
           
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
           
            prettyPrint(request,response,s,tempname);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }

}

@SuppressWarnings("unchecked")
    public void prettyPrint(HttpServletRequest request,HttpServletResponse response,String s,String tempname) throws ServletException, IOException{

  List<String> products1= new ArrayList<String>();
  ArrayList<List<String>> products2 = new ArrayList<List<String>>();
  Map<String,List<String>> productmap= new HashMap<String,List<String>>();
  String productname="hi";
  String productprice ="hi";
  String productspec= "hi";
  String a= "Phone";

  String b= "Tablets";
  String c= "Laptop";
  String d= "TV";

 PrintWriter out= response.getWriter();
	
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
if(s.equals(a))
{
     
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\SmartPhone1.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
productmap= (HashMap<String,List<String>>)objectInputStream.readObject();

for (String key: productmap.keySet()) {

     products2.add(productmap.get(key));
}

for(List<String> temp1:products2)
{
  String x = temp1.get(3);
    if(tempname.equals(x))
    {
      productname=x;
      productprice= temp1.get(1);
      productspec=temp1.get(4);
      break;
    }
}
}
else if(s.equals(b))
{
     
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\Tablets1.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
productmap= (HashMap<String,List<String>>)objectInputStream.readObject();

for (String key: productmap.keySet()) {

     products2.add(productmap.get(key));
}

for(List<String> temp1:products2)
{
  String x = temp1.get(3);
    if(tempname.equals(x))
    {
      productname=x;
      productprice= temp1.get(1);
      productspec=temp1.get(4);
      break;
    }
}
}
else if(s.equals(c))
{
     
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\Laptop1.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
productmap= (HashMap<String,List<String>>)objectInputStream.readObject();

for (String key: productmap.keySet()) {

     products2.add(productmap.get(key));
}

for(List<String> temp1:products2)
{
  String x = temp1.get(3);
    if(tempname.equals(x))
    {
      productname=x;
      productprice= temp1.get(1);
      productspec=temp1.get(4);
      break;
    }
}
}
else
{
     
FileInputStream fileInputStream = new FileInputStream("C:\\Tomcat\\apache-tomcat-7.0.34\\webapps\\csj\\WEB-INF\\Tv1.txt");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
productmap= (HashMap<String,List<String>>)objectInputStream.readObject();

for (String key: productmap.keySet()) {

     products2.add(productmap.get(key));
}

for(List<String> temp1:products2)
{
  String x = temp1.get(3);
    if(tempname.equals(x))
    {
      productname=x;
      productprice= temp1.get(1);
      productspec=temp1.get(4);
      break;
    }
}

}
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
request.getRequestDispatcher("AddCartFinal.html").include(request,response);
      }
    } 