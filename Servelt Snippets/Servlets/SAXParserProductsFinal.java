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
import prasanna.Product;
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


public class SAXParserProductsFinal extends HttpServlet {
Product product;
List<Product> products= new ArrayList<Product>();
String elementValueRead;

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{

PrintWriter out=response.getWriter();


SAXParserFactory factory = SAXParserFactory.newInstance();
String s = request.getParameter("param");




DefaultHandler handler = new DefaultHandler(){
public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("Product")) {
         
       product= new Product();
       
           
        }


    }

    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("Product")) {
            products.add(product);
             
	    return;
        }
         if (element.equalsIgnoreCase("ProductID")) {
            product.setProductId(elementValueRead);
            
	    return;
        }
        
        if (element.equalsIgnoreCase("ProductName")) {
            product.setProductName(elementValueRead);
            
	    return;
        }
        if(element.equalsIgnoreCase("ProductType")){
           product.setProductType(elementValueRead);
            
	    return;
        }
        if(element.equalsIgnoreCase("ProductPrice")){
            product.setProductPrice(elementValueRead);
           
	    return;
        }
        if(element.equalsIgnoreCase("ProductSpec")){
            product.setProductSpec(elementValueRead);
            
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
           
            prettyPrint(request,response,s);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }

}

@SuppressWarnings("unchecked")
    public void prettyPrint(HttpServletRequest request,HttpServletResponse response,String s) throws ServletException, IOException{

  List<String> products1= new ArrayList<String>();
  ArrayList<List<String>> products2 = new ArrayList<List<String>>();
  Map<String,List<String>> productmap= new HashMap<String,List<String>>();
  String a= "Phone";

  String b= "Tablets";
  String c= "Laptop";
  String d= "TV";

 PrintWriter out= response.getWriter();
	
        for (Product product: products) {
        	String check= product.getProductType();
           
        	//String s= "Phone";
        	if(check.equals(s)){

        	
		 products1.add(product.getProductName());
	
		}
    }
 products.clear();       
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
    products1.add(temp1.get(3));
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
    products1.add(temp1.get(3));
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
    products1.add(temp1.get(3));
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
    products1.add(temp1.get(3));
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
for(String plist: products1)
{
    //out.println("hi");
    out.println("<tr>");
    out.println("<td>");
    
    out.println("<form action=\"/csj/ShowProductFinal\" method=\"get\">");
    out.println("<input type=\"submit\" value="+plist+" name=\"Show\">");
    out.println("<input type=\"hidden\" value="+s+" name=\"Type\">");
    out.println("</form>");
    out.println("</td>");
    out.println("</tr>");

}
out.println("</table>");


    
       request.getRequestDispatcher("SpecificCategoryFinal.html").include(request,response);
    }


}

