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
A Webpage class that performs multiple operations such as getting tags and links

*/
public class Webpage extends Thread
{

private String address;//the address
private String html;//the html to be fetched
private URLConnection connection; //to get content from a url 
private ArrayList<String> links=new ArrayList<String>();
private ArrayList<String> tags=new ArrayList<String>();
private URL url; //the url
private LinkParser parser =new LinkParser(links);//stores the links in a page
private TagParser parser2 =new TagParser (tags);//stores the tags in a page
private String[] mytags={"html","body","div","a","style","script","li","link","head"}; //an array of tags to be counted


/**
Constructs a webpage
*@param address the url or website name
*/
public Webpage(String address) throws MalformedURLException ,IOException
{

	this.address=address;	
 	this.url = new URL(address);
 	
}
/**
Gets the html of a webpage
*@return html the HTML of the page
*/

	public String getHTML() throws IOException
	{


			connection= url.openConnection();
            String result="";
            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                               new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = br.readLine()) != null) 
            {
                result+=inputLine;
            }
            html=result;
            br.close();
            return html;
	}	

	/**
Get the links through regex and store in the URL list
uses the parser object 
	*/
public void getLinks() throws IOException
{

		parser.parseLinks(this.getHTML());

}
	
	/**
	Gets the tags of the page
	*/
	public void getTags() 
	{
		try
	{
		parser2.parseTags(this.getHTML());
	}
	catch (IOException e) 
	{
		e.printStackTrace();
	}
	}
/**
Print the links in the page
used mainly for debuuging and testing
*/
public void printlinks()
{

	System.out.println("Links extracted from "+this.getAddress());
	System.out.println("    ");
	for(int i = 0; i < links.size(); i++)
	{   
    System.out.println(links.get(i));
	}
	System.out.println("     ");  
}
public void printtags()
{
	for(int i = 0; i < tags.size(); i++)
	{   
    System.out.println(tags.get(i));
	}  
}

/**
counts the number of tags in the tag lines extracted
*@param tag the tag to count
*@return count the count for tag in a page
*/
public int countTag(String tag)
{
	
	int count =0;
	for(String s : tags)
	{

		StringTokenizer st = new StringTokenizer(s,"<>");
		 while (st.hasMoreTokens())
		{
         	if(st.nextToken().startsWith(tag))
         	{
         	count++;
         	}
     	}

}

return count;
}
/**
Prints the number of tags in a page
*/
public void pageCounter()
{
		int count=0;
		for(String s : mytags)
		{
			count=this.countTag(s);
			System.out.println("The number of " +s +" tags in " +this.getAddress() +"  is :" +count );
		}
		System.out.println("    ");
}


/**
Gets a list of links in a page
*@return links list of the links in a page
*/
public ArrayList<String>  getList()
{
return this.links;

}

/**
gets the address of the page
*@return address the address of the page
*/
public String getAddress(){
	return address;
}


/**
used for multithreading so that each page has statistics performed on in parallel
*/
public void run()  {
try{

System.out.println("        ");
System.out.println("Thread created for "+this.getAddress());
System.out.println("  ");
this.getLinks();

this.getTags();
this.pageCounter();

}
catch (IOException e){ e.printStackTrace(); 


		}


}

}