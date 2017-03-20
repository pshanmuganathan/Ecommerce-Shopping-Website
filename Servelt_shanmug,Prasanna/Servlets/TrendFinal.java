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
import com.mongodb.AggregationOutput;



public class TrendFinal extends HttpServlet {
//Product product;

String elementValueRead;

	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException
{

PrintWriter out=response.getWriter();



try{

	MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("CustomerReviews");
DBCollection productReviews= db.getCollection("productReviews");

DBObject groupFields = new BasicDBObject("_id",0);
groupFields.put("_id","$ProductModelName");
groupFields.put("maxrate",new BasicDBObject("$max","$ReviewRating"));
DBObject group= new BasicDBObject("$group",groupFields);
/*
DBObject projectFields = new BasicDBObject("_id",0);
projectFields.put("ProductName","$_id");
projectFields.put("RatingMetric","$maxrate");
DBObject project = new BasicDBObject("$project",projectFields);
*/
DBObject sort = new BasicDBObject();
sort.put("maxrate",-1);
DBObject orderby = new BasicDBObject("$sort",sort);



AggregationOutput aggregate = productReviews.aggregate(group,orderby,new BasicDBObject("$limit",5));
 out.println("<table style=\"position: absolute; top: 500px; left: 500px;\">"); 
   out.println("<tr>");
    out.println("<td>Top 5 Most Liked Products</td>");
    out.println("</tr>"); 
    out.println("<tr>");
	out.println("<td>Product Name</td>");
	out.println("<td>Highest Rating</td>");
	out.println("</tr>");
for(DBObject result: aggregate.results())
{
	BasicDBObject obj = (BasicDBObject)result;
	out.println("<tr>");
	out.println("<td>"+obj.getString("_id")+"</td>");
	
	out.println("<td>"+obj.getString("maxrate")+"</td>");
	out.println("</tr>");

}
out.println("</table>");

DBObject groupFields1 = new BasicDBObject("_id",0);
groupFields1.put("_id","$RetailerZip");
groupFields1.put("count",new BasicDBObject("$sum",1));
DBObject group1= new BasicDBObject("$group",groupFields1);
/*
DBObject projectFields = new BasicDBObject("_id",0);
projectFields.put("ProductName","$_id");
projectFields.put("RatingMetric","$maxrate");
DBObject project = new BasicDBObject("$project",projectFields);
*/
DBObject sort1 = new BasicDBObject();
sort1.put("count",-1);
DBObject orderby1 = new BasicDBObject("$sort",sort1);



AggregationOutput aggregate1 = productReviews.aggregate(group1,orderby1,new BasicDBObject("$limit",5));
out.println("<table style=\"position: absolute; top: 500px; left: 900px;\">"); 
   out.println("<tr>");
    out.println("<td>Top 5 ZIP where maximum sold</td>");
    out.println("</tr>"); 
    out.println("<tr>");
	out.println("<td>RetailerZip</td>");
	out.println("<td>Items Sold</td>");
	out.println("</tr>");

for(DBObject result1: aggregate1.results())
{
	BasicDBObject obj1 = (BasicDBObject)result1;
	out.println("<tr>");
	out.println("<td>"+obj1.getString("_id")+"</td>");
	
	out.println("<td>"+obj1.getString("count")+"</td>");
	out.println("</tr>");


}
out.println("</table>");


DBObject groupFields2 = new BasicDBObject("_id",0);
groupFields2.put("_id","$ProductModelName");
groupFields2.put("Type",new BasicDBObject("$addToSet","$ProductCategory"));
groupFields2.put("count",new BasicDBObject("$sum",1));
DBObject group2= new BasicDBObject("$group",groupFields2);
/*
DBObject projectFields = new BasicDBObject("_id",0);
projectFields.put("ProductName","$_id");
projectFields.put("RatingMetric","$maxrate");
DBObject project = new BasicDBObject("$project",projectFields);
*/
DBObject sort2 = new BasicDBObject();
sort2.put("count",-1);
DBObject orderby2 = new BasicDBObject("$sort",sort2);



AggregationOutput aggregate2 = productReviews.aggregate(group2,orderby2,new BasicDBObject("$limit",5));
out.println("<table style=\"position: absolute; top: 700px; left: 700px;\">"); 
   out.println("<tr>");
    out.println("<td>Top 5 Most Sold Products</td>");
    out.println("</tr>"); 
    out.println("<tr>");
	out.println("<td>Product Name</td>");
	out.println("<td>Type</td>");
	out.println("<td></td>");
	out.println("<td>Items Sold</td>");
	out.println("</tr>");
for(DBObject result2: aggregate2.results())
{
	BasicDBObject obj2 = (BasicDBObject)result2;


	Object type = obj2.get("Type");
	String s= type.toString();
String[] t= s.split("\"");
s=t[1];

out.println("<tr>");
	out.println("<td>"+obj2.getString("_id")+"</td>");
	out.println("<td>"+s+"</td>");
	out.println("<td></td>");
	out.println("<td>"+obj2.getString("count")+"</td>");
	out.println("</tr>");
}
out.println("</table>");
}
catch (Exception e) {
	e.printStackTrace();
    }

 
//out.println("<p style=\"position: absolute; top: 500px; left: 700px;\">Review Stored</p>");
request.getRequestDispatcher("SuccessfulLoginFinal.html").include(request,response);




  
}
} 