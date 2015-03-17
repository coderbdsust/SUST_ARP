<%@page import="java.util.List"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.util.ArrayList"%>
<!doctype html>
<html>

    <head>
        <title>Welcome</title>
        <link rel="stylesheet"  href="css/style.css"/>
    </head>

    <body>

        <div class="mainview" align="center">
            <header>
                <h1>CONTENT ALERT</h1>
                <p>SUST Admission Result Processing System</p>
            </header>

            <div class="mainbody">
                <div class="alert" >
                    <h1>
                        <%=request.getParameter("message")%>
                    </h1>
                </div>
            </div>

            <div class="footer">
                <p><a href="index.html">Home</a></p>
               <p>Developed By: <a href="http://bd.linkedin.com/in/coderbd/en" target="_blank">coder.bd</a> & <a>Evan</a></p>
            </div> 

        </div>

    </body>
</html>