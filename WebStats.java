import java.io.IOException;
import java.io.*;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.net.URL;
import java.net.*;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.regex.*;
import java.util.*;

/**
Name:   Ramy El Khayat 
Name:   Ji Qi
Course: CPS506, Winter 2016, Assignment #1
Due:    2016.01.11 23:59
Credit: This is entirely our own work.

This is the main class that starts the application
by getting an initial page and its possible links(default 10 ) (given a depth-default is 3 )
*/


public class WebStats extends Thread
{


		static Webpage first ; //the initial page , the root
		static int max_pages; // maximum number of pages 
		static int max_depth;	
		static int count_html;
		static int count_body;
		static int count_style;
		static int count_a;
		static int count_script;
		static int count_head;
		static int count_div;
	/**
	Gets a set of the root page's links
	*@param url the initial page's url
	*@return set a set of the links of the initial page
	*/
	public static HashSet<String> visit(String url)  throws MalformedURLException ,IOException
	{
		first=new Webpage(url);
		HashSet<String> set=new HashSet<String>();
		try
		{
			first.getLinks();
			ArrayList<String> links=first.getList();
			for (String s :links ) 
			{
				set.add(s);
			}
			return  set;
		}
		catch (Exception e) 
		{
			return new HashSet<String>();
		}

	}

	/**
	Recursivly get all links from the initial page's links upto the specifed depth

	*@return set2
	*@param url the link to follow
	*@param depth the depth of the search
	*/
	public static HashSet<String> visit(String url,int depth)throws MalformedURLException ,IOException
	{	
		first=new Webpage(url);
		HashSet<String> set2=new HashSet<String>();
		if(depth==1)
			{
				set2.add(first.getAddress());
				return set2;
			}
		else
		{ 
			HashSet<String> t=visit(first.getAddress());
				for(String u:t)
				{ 
					set2.addAll(visit(u,depth-1));
					System.out.println("Fetching "+u);
				}
		}
		set2.add(url);
		return set2;
	}


	


	/**
	The main method
	*/
	public static void main(String[] args) throws MalformedURLException,IOException,InterruptedException{
		
		//getting the initial page through the first command line argument
		String initial_page=args[2];
		if(args.length>=2)
		{
		max_pages=Integer.parseInt(args[0]);
		}
		else
		{
			max_pages=10;//setting default pages to 10 if no command line arguments given for maximum pages
		}
		
		if(args.length>=3)
		{
		max_depth=Integer.parseInt(args[1]);
	}
	else
		{
			max_depth=3;//setting default maximum depth to 3 
		}
		
		HashSet<String> s=visit(initial_page,max_depth);//recursivly get the unique links from the initial page with the specifed depth
		
		//for every string in the set make a new webpage and perform stats on it
		for (String str :s ) 
		{
		Thread next = new Webpage(str);
		next.start();
		}
		System.out.println("Total number of links found");
		System.out.println("-------------------------------------------");

		System.out.println(s.size());
		System.out.println("-------------------------------------------");

		TreeSet<String> myTreeSet = new TreeSet<String>();
		ArrayList<Webpage> myTreeSet2 = new ArrayList<Webpage>();
    	myTreeSet.addAll(s);
    	for (String temp :myTreeSet  ) 
    	{
    		Webpage page = new Webpage(temp);
    		myTreeSet2.add(page);
    		page.getTags();
    	}
    	//Performing final stats
    	System.out.println("Printing all links found");
    	for (String string :myTreeSet ) {
    		System.out.println(string);
    	}
    	for (Webpage w :myTreeSet2 ) 
    	{
				count_html+=w.countTag("html");    		
    	}
    	for (Webpage w :myTreeSet2 ) 
    	{
				count_div+=w.countTag("div");    		
    	}
    	for (Webpage w :myTreeSet2 ) 
    	{
				count_body+=w.countTag("body");    		
    	}
    	for (Webpage w :myTreeSet2 ) 
    	{
				count_a+=w.countTag("a");    		
    	}
    	for (Webpage w :myTreeSet2 ) 
    	{
				count_style+=w.countTag("style");    		
    	}
    	for (Webpage w :myTreeSet2 ) 
    	{
				count_head+=w.countTag("head");    		
    	}
    	for (Webpage w :myTreeSet2 ) 
    	{
				count_script+=w.countTag("script");    		
    	}
    	System.out.println("total number of <html> tags is "+count_html);
    	System.out.println("total number of <div> tags is "+count_div);
    	System.out.println("total number of <head> tags is "+count_head);
    	System.out.println("total number of <script> tags is "+count_script);
    	System.out.println("total number of <body> tags is "+count_body);
    	System.out.println("total number of <style> tags is "+count_style);
    	System.out.println("total number of <a> tags is "+count_a);

	}

}