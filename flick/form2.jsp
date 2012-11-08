<%@ page import="java.util.*" %>
<html>

<head>
<link rel="stylesheet" href="./style.css" type="text/css"></link>
<link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />

<title>Image Uploader</title>

</head>

<body>

<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=23686307289";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<div class="mer">
Welcome to my Image Uploader&trade; </div> <br>

<div class="container">

<form action="UploadServlet" method="post" enctype="multipart/form-data" >
Image Upload
<input type="file" name="file" size="50" accept="image/x-png, image/gif, image/jpeg, image/bmp, image/tgif, image/tif, image/jpg" class="input" /> <br><br>

     
<b>Description:</b><br></font><textarea name="caption" id="caption" class="input"></textarea> <br>

 

<br>

<input type="submit" value="Upload File" class="button"/>

</div>

<div class="fb-like" data-href="http://83.212.100.122:8080/flick/start" data-send="true" data-width="450" data-show-faces="true"></div>
<div class="mer4">Image Gallery</div>
</form>

        <%
            String[] reply = (String [])request.getAttribute("styles");
             out.println("<table>");
             for(int i = 0; i < reply.length; i++) {
               if(i % 5 == 0 && i != 0 ) {
                   out.println("<tr>");
               }
               out.println("<td><img src=\"./Photos/" + reply[i] + "\" width=\"200\" height=\"150\"><br>");
               out.println("<center><a href=\"showimage?img=" + reply[i] + "\">Show-Edit Photo</a></center></td>");
               
        
             }
             out.println("</table>");
                     
            %>

</body>

<div class="mer3">
<center>
<iframe src="http://free.timeanddate.com/clock/i3ctw6ym/n26/szw110/szh110/hoc000/hbw4/cf100/hnce1ead6/fav0/fiv0/mqc000/mqs3/mql25/mqw6/mqd96/mhc000/mhs3/mhl20/mhw6/mhd96/mmc000/mms3/mml10/mmw2/mmd96/hhw16/hmw16/hmr4/hsc000/hss3/hsl90" frameborder="0" width="112" height="112"></iframe>
</center>
</div>


<div class="mer2"> Created and Designed by Marios</div>
</form>

</html>