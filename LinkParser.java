import java.io.IOException;
import java.io.*;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.net.URL;
import java.net.URLConnection;
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

A class that extracts links and add them to a list
*/
public class LinkParser
{



private ArrayList<String> links=new ArrayList<String>();

/**
Constructs a LinkParser
*@param links the list of links 
*/
public LinkParser(ArrayList<String> links){

	this.links=links;

}

/**
Parses an html and adds it to a list using regex
*@param html the html to parse links from 

*/
public void parseLinks(String html)
{
       
		   Pattern plink = Pattern.compile("(((http)://)(www\\.))+(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(/[a-zA-Z0-9\\&amp;%_\\./-~-]*)?");
           Matcher link = plink.matcher(html);
           while (link.find()&&links.size()<WebStats.max_pages) 
           {
           links.add(link.group());
           }



}





}
