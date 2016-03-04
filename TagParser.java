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
*Name:   Ramy El Khayat 
*Name:   Ji Qi
*Course: CPS506, Winter 2016, Assignment #1
*Due:    2016.01.11 23:59
*Credit: This is entirely our own work.
*A class that parses HTMLs as strings and gets starting tags and adds them to a list 
*/
public class TagParser
{



private ArrayList<String> tags=new ArrayList<String>(); //

/**
*Constructs a Tag Parser object 
*@param tags the list of tags
*/
public TagParser(ArrayList<String> tags){

	this.tags=tags;

}



/**
Parses the HTML string to extract start tags using regex
*@param html the html to parse 
*/
public void parseTags(String html)
{


		   Pattern ptags = Pattern.compile("< ?[^//!][^>]*>");
           Matcher tag = ptags.matcher(html);
           while (tag.find()) 
           {
           tags.add(tag.group());
           }

}



}
