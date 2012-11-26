<%@ page import="java.util.*" %>

<head>
<link rel="stylesheet" href="./style2.css" type="text/css"></link>
<link rel="shortcut icon" href="./favicon.ico" type="image/x-icon" />

<title>Your Image</title>

</head>

<html>
<body>
<h1>View-Edit your Image</h1>
<div class="mera">
           
        <p>
            <%
            String[] reply = (String [])request.getAttribute("styles");
           
             
             if(reply[2].equals("Default") && reply[3].equals("Default")) {
		%>
		<div class="mera3">
		<%
                 out.println("<img src=\"./Photos/" + reply[0] + "\" style=\"display:block; rotation:" + reply[4] + "deg; -webkit-transform: rotate(" + reply[4] + "deg); -moz-transform: rotate(" + reply[4] + "deg); filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=" + (Integer.valueOf(reply[4]) / 90) + ");\" /><br>");
             %>
		</div>
		<%
		}
             else if(reply[2].equals("Default")) {
		%>
		<div class="mera3">
		<%
                 out.println("<img src=\"./Photos/" + reply[0] +  "\" height=\"" + reply[3] + "\" style=\"display:block; rotation:" + reply[4] + "deg; -webkit-transform: rotate(" + reply[4] + "deg); -moz-transform: rotate(" + reply[4] + "deg); filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=" + (Integer.valueOf(reply[4]) / 90) + ");\"><br>");
            %>
		</div>
		<%
		}
             else if(reply[3].equals("Default")) {
		%>
		<div class="mera3">
		<%
                 out.println("<img src=\"./Photos/" + reply[0] + "\"  width=\"" + reply[2] + "\" style=\"display:block; rotation:" + reply[4] + "deg; -webkit-transform: rotate(" + reply[4] + "deg); -moz-transform: rotate(" + reply[4] + "deg); filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=" + (Integer.valueOf(reply[4]) / 90) + ");\"><br>");
             	%>
		</div>
		<%
		}
             else {
		%>
		<div class="mera3">
		<%
                 out.println("<img src=\"./Photos/" + reply[0] + "\"  width=\"" + reply[2] + "\" height=\"" + reply[3] + "\" style=\"display:block; rotation:" + reply[4] + "deg; -webkit-transform: rotate(" + reply[4] + "deg); -moz-transform: rotate(" + reply[4] + "deg); filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=" + (Integer.valueOf(reply[4]) / 90) + ");\"><br>");
            	%>
		</div>
		<%
		 }
             
             out.println(reply[1]);
		out.print("Views:");
		out.println(reply[5]);
		%>
		
		<div class="mera2">
		<%
             out.println("<form action=\"edit\" method=\"post\">");
             out.println("<b>Add new Description:</b><br></font><textarea name=\"caption\" id=\"caption\" class=\"input\"></textarea> <br>");
             out.println("<b>Width:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><input type=\"text\" name=\"width\" class=\"input\" /> <br>");
             out.println("<b>Height:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b></font><input type=\"text\" name=\"height\" class=\"input\" /><br><br>");
             out.println("<b>Rotation:</b><input name=\"slider\" type=\"range\" min=\"0\" max=\"270\" value=\"" + reply[4] + "\" step=\"90\" onchange=\"showValue(this.value)\" /> <span id=\"range\">" + reply[4] + "</span><br>");
             out.println("<input type=\"hidden\" name=\"file\" value=\"" + reply[0] + "\"/><br>");
             out.println("<input type=\"submit\" value=\"Edit\" class=\"button\"/>");
             out.println("</form>");
		%>
         	</div>
             
             <%
             
	      out.println("<li><a href=\"./start\">Back to main page</a></li>");
        
            %>

<script type="text/javascript">
function showValue(newValue)
{
	document.getElementById("range").innerHTML=newValue;
}
</script>


</div>

    </body>
</html>

