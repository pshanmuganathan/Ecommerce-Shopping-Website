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
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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


public class AddReviewFinal extends HttpServlet {
//Product product;
ArrayList<List<String>> products = new ArrayList<List<String>>();
List<String> product;
String elementValueRead;

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{

PrintWriter out=response.getWriter();

Object s= request.getSession(false).getAttribute("Name");
  String b= s.toString();

String productname = request.getParameter("pname");
String productprice= request.getParameter("pprice");
String productspec = request.getParameter("pspec");
String producttype = request.getParameter("Type");


 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     //get current date time with Date()
     Date date = new Date();
     String x= dateFormat.format(date);



out.println("<form method=\"get\" action=\"/csj/AddReviewMongoFinal\">");
out.println("<table style=\"position: absolute; top: 500px; left: 700px;\">");
out.println("<tr>");
out.println("<td>Product Name : </td>");
out.println("<td>"+productname+"</td>");
out.println("<td><input type=\"hidden\" value="+productname+" name=\"pname\"></td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Product Name : </td>");
out.println("<td>"+producttype+"</td>");
out.println("<td><input type=\"hidden\" value="+producttype+" name=\"ptype\"></td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Product Price : </td>");
out.println("<td>"+productprice+"</td>");
out.println("<td><input type=\"hidden\" value="+productprice+" name=\"pprice\"></td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Product Specification : </td>");
out.println("<td>"+productspec+"</td>");
out.println("<td><input type=\"hidden\" value="+productspec+" name=\"pspec\"></td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Retailer Name : </td>");
out.println("<td>Best Deal </td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Retailer Zip : </td>");
out.println("<td>");
out.println("<input type=\"TEXT\" size=\"15\" name=\"reatilerzip\">");
out.println("</td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Retailer City : </td>");
out.println("<td>");
out.println("<input type=\"TEXT\" size=\"15\" name=\"reatilercity\">");
out.println("</td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Retailer State : </td>");
out.println("<td>");
out.println("<input type=\"TEXT\" size=\"15\" name=\"reatilerstate\">");
out.println("</td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Product On Sale : </td>");
out.println("<td>Yes</td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Manufacturer Name : </td>");
out.println("<td>"+productname+"</td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Manufacturer Rebate : </td>");
out.println("<td>Yes</td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>User ID : </td>");
out.println("<td>"+b+"</td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>User Age : </td>");
out.println("<td>");
out.println("<input type=\"TEXT\" size=\"15\" name=\"userage\">");
out.println("</td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>User Gender : </td>");
out.println("<td>");
out.println("<select name=\"sex\">");
out.println("<option value=\"Male\">Male</option>");
out.println("<option value=\"Female\">Female</option>");
out.println("</select>");
out.println("</td>");
out.println("</tr>");


out.println("<tr>");
out.println("<td>User Occupation : </td>");
out.println("<td>");
out.println("<select name=\"occupation\">");
out.println("<option value=\"Accountant\">Accountant</option>");
out.println("<option value=\"Student\">Student</option>");
out.println("<option value=\"Businessman\">Businessman</option>");
out.println("<option value=\"Others\">Others</option>");
out.println("</select>");
out.println("</td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Review Rating : </td>");
out.println("<td>");
out.println("<select name=\"rating\">");
out.println("<option value=\"1\">1</option>");
out.println("<option value=\"2\">2</option>");
out.println("<option value=\"3\">3</option>");
out.println("<option value=\"4\">4</option>");
out.println("<option value=\"5\">5</option>");
out.println("</select>");
out.println("</td>");
out.println("</tr>");


out.println("<tr>");
out.println("<td>Review Date : </td>");
out.println("<td>"+x+"</td>");
out.println("<td><input type=\"hidden\" value="+x+" name=\"reviewdate\"></td>");
out.println("</tr>");

out.println("<tr>");
out.println("<td>Review : </td>");
out.println("<td>");
out.println("<textarea name=\"review\" rows=\"2\" cols=\"40\">");
out.println("</textarea>");
out.println("</td>");
out.println("</tr>");



out.println("<tr>");
out.println("<td>");
out.println("<input type=\"submit\" value=\"Add\" name=\"Review\">");
out.println("</td>");
out.println("</tr>");
out.println("</table>");
out.println("</form>");

request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);
      }
    } 