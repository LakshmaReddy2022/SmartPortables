import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Trending")

public class Trending extends HttpServlet {

	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Consoles type whether it is microsft or sony or nintendo then add products to hashmap*/

		String name = "Trending";
		String CategoryName = request.getParameter("maker");
		HashMap<String, Wearable> hm = new HashMap<String, Wearable>();
		if(CategoryName==null)
		{
			hm.putAll(SaxParserDataStore.wearables);
		}
		else
		{
			if(CategoryName.equals("fitness_watches"))
			{
				for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Fitness Watches"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
			}
			else if(CategoryName.equals("smart_watches"))
			{
				for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Smart Watches"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
			}
			else if(CategoryName.equals("headphones"))
			{
				for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{ 
			      if(entry.getValue().getRetailer().equals("Headphones"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
			}
			else if(CategoryName.equals("vr"))
			{
				for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Virtual Reality"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
			}
			else if(CategoryName.equals("pt"))
			{
				for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Pet Tracker"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
			}
		}
		

		/* Header, Left Navigation Bar are Printed.

		All the consoles and Console information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Products</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Wearable> entry : hm.entrySet()){
			Wearable wearable = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+wearable.getName()+"</h3>");
			pw.print("<strong>$"+wearable.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/wearables/"+wearable.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearables'>"+
					"<input type='hidden' name='maker' value='"+wearable.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearables'>"+
					"<input type='hidden' name='maker' value='"+wearable.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearables'>"+
					"<input type='hidden' name='maker' value='"+wearable.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");	
		utility.printHtml("Footer.html");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}