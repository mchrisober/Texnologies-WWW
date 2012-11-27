
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class GetImageServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        
        response.setContentType("text/html");
        
        String file = request.getParameter("img");
       
        String[] reply = new String[6];
       
        reply[0] = file;
        
        try {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File("/var/lib/tomcat6/webapps/flick/xmls/" + file.replaceFirst("[.][^.]+$", "") + ".xml"));

            doc.getDocumentElement ().normalize ();
                                  
            NodeList name_list = doc.getElementsByTagName("Description");
            Element name_element = (Element)name_list.item(0);

            NodeList textFNList = name_element.getChildNodes();
            
            try {
                reply[1] = ((Node)textFNList.item(0)).getNodeValue().trim();    
            }
            catch(NullPointerException ex) {
                reply[1] = "";   
            }

            
            name_list = doc.getElementsByTagName("Width");
            name_element = (Element)name_list.item(0);

            textFNList = name_element.getChildNodes();
            
            reply[2] = ((Node)textFNList.item(0)).getNodeValue().trim();
            
            name_list = doc.getElementsByTagName("Height");
            name_element = (Element)name_list.item(0);

            textFNList = name_element.getChildNodes();
            
            reply[3] = ((Node)textFNList.item(0)).getNodeValue().trim();
            
            name_list = doc.getElementsByTagName("Rotation");
            name_element = (Element)name_list.item(0);
            
            textFNList = name_element.getChildNodes();
            
            reply[4] = ((Node)textFNList.item(0)).getNodeValue().trim();
            name_list = doc.getElementsByTagName("Views");
            name_element = (Element)name_list.item(0);

            textFNList = name_element.getChildNodes();
            
            reply[5] = "" + (Integer.parseInt(((Node)textFNList.item(0)).getNodeValue().trim()) + 1);
                        
            
                        
        }
        catch(ParserConfigurationException pce) {}
        catch(SAXException se) {}
        catch(IOException ioe) {} 
        
        PrintWriter out_xml = new PrintWriter("/usr/share/tomcat6/webapps/flick/xmls/" + file.replaceFirst("[.][^.]+$", "") + ".xml");
	out_xml.println("<Photo>\n    <Description>" + reply[1] + "</Description>\n    <Width>" + reply[2] + "</Width>\n    <Height>" + reply[3] + "</Height>\n    <Rotation>" + reply[4] + "</Rotation>\n    <Views>" + reply[5] + "</Views>\n</Photo>");
	out_xml.close();
       
        request.setAttribute("styles", reply);
        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        
        try {    
            view.forward(request, response);
        } 
        catch (ServletException ex) {} 
        catch (IOException ex) {} 
   }
    
    
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
       
       response.setContentType("text/html");
       
       String[] reply = new String[6];
       
       String file = request.getParameter("file");
       String caption = request.getParameter("caption");
       String width = request.getParameter("width");
       String height = request.getParameter("height");
       String rotation = request.getParameter("slider");
       
       String views = "";
       String old_caption = "";
       String old_width = "";
       String old_height = "";
       String old_rotation = "";
       
       try {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File("/var/lib/tomcat6/webapps/flick/xmls/" + file.replaceFirst("[.][^.]+$", "") + ".xml"));

            doc.getDocumentElement ().normalize ();
                                  
            NodeList name_list = doc.getElementsByTagName("Description");
            Element name_element = (Element)name_list.item(0);

            NodeList textFNList = name_element.getChildNodes();
            
            try {
                old_caption = ((Node)textFNList.item(0)).getNodeValue().trim();    
            }
            catch(NullPointerException ex) {
                old_caption = "";   
            }  
            
            name_list = doc.getElementsByTagName("Width");
            name_element = (Element)name_list.item(0);

            textFNList = name_element.getChildNodes();
            
            old_width = ((Node)textFNList.item(0)).getNodeValue().trim();
            
            name_list = doc.getElementsByTagName("Height");
            name_element = (Element)name_list.item(0);

            textFNList = name_element.getChildNodes();
            
            old_height = ((Node)textFNList.item(0)).getNodeValue().trim();
            
            name_list = doc.getElementsByTagName("Rotation");
            name_element = (Element)name_list.item(0);

            textFNList = name_element.getChildNodes();
            
            old_rotation = ((Node)textFNList.item(0)).getNodeValue().trim();
            
             name_list = doc.getElementsByTagName("Views");
            name_element = (Element)name_list.item(0);

            textFNList = name_element.getChildNodes();
            
            views = ((Node)textFNList.item(0)).getNodeValue().trim();
                        
        }
        catch(ParserConfigurationException pce) {}
        catch(SAXException se) {}
        catch(IOException ioe) {} 
       
       reply[0] = file;
       
       if(caption.equals("")) {
           caption = old_caption;
       }
       
       int temp = 0;
       try {
           if(width.equals("")) {
               width = old_width;
           }
           else {
               temp = Integer.valueOf(width);
               if(temp <= 0) {
                   width = old_width;
               }               
           }
       }
       catch(NumberFormatException ex)  {
           width = old_width;
       }
       
       try {
           if(height.equals("")) {
               height = old_height;
           }
           else {
               temp = Integer.valueOf(height);
               if(temp <= 0) {
                   height = old_height;
               }
           }
       }
       catch(NumberFormatException ex)  {
           height = old_height;
       }
       
       try {
           if(rotation.equals("")) {
               rotation = old_rotation;
           }
           else {
               temp = Integer.valueOf(rotation);
               if(temp <= 0) {
                   rotation = old_rotation;
               }
           }
       }
       catch(NumberFormatException ex)  {
           rotation = old_rotation;
       }
       
     
             
             PrintWriter out_xml = new PrintWriter("/usr/share/tomcat6/webapps/flick/xmls/" + file.replaceFirst("[.][^.]+$", "") + ".xml");
	     out_xml.println("<Photo>\n    <Description>" + caption + "</Description>\n    <Width>" + width + "</Width>\n    <Height>" + height + "</Height>\n    <Rotation>" + rotation + "</Rotation>\n    <Views>" + views + "</Views>\n</Photo>");
	     out_xml.close();
             reply[1] = caption;
             reply[2] = width;
             reply[3] = height;
             reply[4] = rotation;
             reply[5] = views;
       
       
        request.setAttribute("styles", reply);
        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        
        try {    
            view.forward(request, response);
        } 
        catch (ServletException ex) {} 
        catch (IOException ex) {} 
       
   }
    
}
