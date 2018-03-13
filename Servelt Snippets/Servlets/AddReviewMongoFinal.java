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
import java.io.File;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoClient;




public class AddReviewMongoFinal extends HttpServlet {
//Product product;
ArrayList<List<String>> products = new ArrayList<List<String>>();
List<String> product;
String elementValueRead;

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{

PrintWriter out=response.getWriter();

Object s= request.getSession(false).getAttribute("Name");
  String userid= s.toString();

String statusreview = request.getParameter("Review");



if(statusreview.equals("Add"))
{
try
{
	String productname = request.getParameter("pname");
String producttype = request.getParameter("ptype");
String productprice= request.getParameter("pprice");
String productspec = request.getParameter("pspec");
String reatilerzip = request.getParameter("reatilerzip");
String reatilercity = request.getParameter("reatilercity");
String reatilerstate = request.getParameter("reatilerstate");

String manufacturername = request.getParameter("pname");
String userage = request.getParameter("userage");
String sex = request.getParameter("sex");
String occupation = request.getParameter("occupation");
String rating = request.getParameter("rating");
String reviewdate = request.getParameter("reviewdate");
String review = request.getParameter("review");
	MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("CustomerReviews");
DBCollection productReviews= db.getCollection("productReviews");

BasicDBObject doc = new BasicDBObject("title", "Reviews").
append("ProductModelName",productname).
append("ProductCategory",producttype).
append("ProductPrice",productprice).
append("RetailerName","BestDeal").
append("RetailerZip",reatilerzip).
append("RetailerCity",reatilercity).
append("RetailerState",reatilerstate).
append("ProductOnSale","Yes").
append("Manufacturername",manufacturername).
append("ManufacturerRebate","Yes").
append("UserID",userid).
append("UserAge",userage).
append("UserGender",sex).
append("UserOccupation",occupation).
append("ReviewRating",rating).
append("ReviewDate",reviewdate).
append("ReviewText",review);

productReviews.insert(doc);
}
catch (Exception e) {
	e.printStackTrace();
    }

 
out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Review Stored</p>");
request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);
}
else
{
	String productname = request.getParameter("pname");
	String producttype = request.getParameter("Type");
	String productprice = request.getParameter("pprice");
	List<List<String>> datacontainer = new ArrayList<List<String>>();
	try
	{
MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("CustomerReviews");
DBCollection productReviews= db.getCollection("productReviews");

BasicDBObject pull = new BasicDBObject();
List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
obj.add(new BasicDBObject("ProductModelName",productname));
obj.add(new BasicDBObject("ProductCategory",producttype));
pull.put("$and",obj);

DBCursor cursor = productReviews.find(pull);

while(cursor.hasNext())
{
	BasicDBObject object = (BasicDBObject)cursor.next();
	List<String> data = new ArrayList<String>();
	data.add(object.getString("ProductModelName"));
	data.add(object.getString("ProductCategory"));
	data.add(object.getString("ProductPrice"));
	data.add(object.getString("RetailerName"));
	data.add(object.getString("RetailerCity"));
	data.add(object.getString("ManufacturerRebate"));
	data.add(object.getString("UserID"));
	data.add(object.getString("ReviewRating"));
	data.add(object.getString("ReviewDate"));
	data.add(object.getString("ReviewText"));
	datacontainer.add(data);
}

out.println("<table style=\"position: absolute; top: 500px; left: 500px;\" border=\"1\">"); 
	out.println("<tr>");
	 out.println("<td>ProductModelName: "+productname+"</td>");
	 out.println("</tr>"); 
	 	out.println("<tr>");
	 out.println("<td>ProductCategory: "+producttype+"</td>");
	 out.println("</tr>");
	 	out.println("<tr>");
	 out.println("<td>ProductPrice: "+productprice+"</td>");
	 out.println("</tr>");
   out.println("</table>");
   out.println("<div style=\"position: absolute; top: 400px; left: 800px;overflow-y: scroll; height: 500px;\">");
for(List<String> temp: datacontainer)
{
	out.println("<table border=\"1\">"); 
	/*out.println("<tr>");
	 out.println("<td>ProductModelName: "+temp.get(0)+"</td>");
	 out.println("</tr>"); 
 
 out.println("<tr>");
	 out.println("<td>ProductCategory: "+temp.get(1)+"</td>");
	 out.println("</tr>"); 
 
 out.println("<tr>");
	 out.println("<td>ProductPrice: "+temp.get(2)+"</td>");
	 out.println("</tr>"); */
	

	out.println("<tr>");
	 out.println("<td>RetailerName: "+temp.get(3)+"</td>");
	 out.println("</tr>"); 
	

out.println("<tr>");
	 out.println("<td>RetailerCity: "+temp.get(4)+"</td>");
	 out.println("</tr>"); 


out.println("<tr>");
	 out.println("<td>ManufacturerRebate: "+temp.get(5)+"</td>");
	 out.println("</tr>"); 


out.println("<tr>");
	 out.println("<td>UserID: "+temp.get(6)+"</td>");
	 out.println("</tr>"); 
	

out.println("<tr>");
	 out.println("<td>ReviewRating: "+temp.get(7)+"</td>");
	 out.println("</tr>"); 
	

out.println("<tr>");
	 out.println("<td>ReviewDate: "+temp.get(8)+"</td>");
	 out.println("</tr>"); 
	

out.println("<tr>");
	 out.println("<td>ReviewText: "+temp.get(9)+"</td>");
	 out.println("</tr>"); 

out.println("</table>");	
}

out.println("</div>");
request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);
}
catch (Exception e) {
	e.printStackTrace();
    }

}


  
}
} 