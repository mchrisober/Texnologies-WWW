import java.io.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

public class UploadServlet extends HttpServlet {
   
    
    
   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 5000 * 1024;
   private int maxMemSize = 4 * 1024;
   private File file ;
   private String filenamep;


   public void init( ){
      // Get the file location where it would be stored.
      filePath = 
             getServletContext().getInitParameter("file-upload");
 
   }
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
      // Check that we have a file upload request
       
       
       isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      String caption = "";
      String width = "";
      String height = "";
      String rotation = "";
      String[] reply = new String[5];
      
      java.io.PrintWriter out = response.getWriter( );
      if( !isMultipart ){
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("/usr/share/tomcat6/webapps/flick/Photos/"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
      List fileItems = upload.parseRequest(request);
	
      // Process the uploaded file items
      Iterator i = fileItems.iterator();

      while ( i.hasNext () ) 
      {
        //out.println("<br>"+filePath+"<br>");
	//out.println("<br>" + "between while & if" + "<br>");
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize();
	    //out.println("before write file " + "<br>");
            // Write the file
            if( fileName.lastIndexOf("/") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("/"))) ;
		//out.println("write file  if" + "<br>");
            }else{
               file = new File( filePath + fileName.substring(fileName.lastIndexOf("/")+1)) ;
            }
            
                reply[0] = filenamep= fileName; 
                fi.write( file ) ;   
            
         }
         else {
             
             if(fi.getFieldName().equals("caption")) {
                 caption = fi.getString();
             }
             
             width = "Default";
             height = "Default";
             rotation = "0";
             
             PrintWriter out_xml = new PrintWriter("/usr/share/tomcat6/webapps/flick/xmls/" + filenamep.replaceFirst("[.][^.]+$", "") + ".xml");
	     out_xml.println("<Photo>\n    <Description>" + caption + "</Description>\n    <Width>" + width + "</Width>\n    <Height>" + height + "</Height>\n    <Rotation>" + rotation + "</Rotation>\n</Photo>");
	     out_xml.close();
             reply[1] = caption;
             reply[2] = width;
             reply[3] = height;
             reply[4] = rotation;
             
         }
      }
	  
        request.setAttribute("styles", reply);
        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        
        try {    
            view.forward(request, response);
        } 
        catch (ServletException ex) {} 
        catch (IOException ex) {} 
	  
 
   }catch(Exception ex) {
       System.out.println(ex);
   }
   }
   
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        
       response.setContentType("text/html");
       
        String[] reply;
       
        File folder = new File("/usr/share/tomcat6/webapps/flick/Photos/");
        File[] listOfFiles = folder.listFiles(); 
        
        reply = new String[listOfFiles.length];
        
        for(int i = 0; i < reply.length; i++) {
            reply[i] = listOfFiles[i].getName();
        }
       
        request.setAttribute("styles", reply);
        RequestDispatcher view = request.getRequestDispatcher("form2.jsp");
        
        try {    
            view.forward(request, response);
        } 
        catch (ServletException ex) {} 
        catch (IOException ex) {} 
   } 
   
   
}

