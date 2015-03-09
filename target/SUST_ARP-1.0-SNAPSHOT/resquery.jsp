<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Result Query Table</title>
        <link rel="stylesheet"  href="css/reset.css"/>
        <link rel="stylesheet"  href="css/style.css"/>
        <script src="js/check.js"></script>
    </head>

    <body>
    
        <div class="mainview" align="center">
            <header>
                <h2>SUST ADMISSION RESULT PROCESSING SYSTEM</h2>
            </header>

            <div class="mainbody" >

                <div class="tableview" align="center">

                    <form action="pdfServlet" name="resQuery" method="post">
                        <input type="hidden" value="resQuery" name="methodName"/>
                        
                        <div class="query_table" align="center">

                            <table align="center" id="resTable">

                              
                                     <tr>
                                        <th>Unit</th>
                                        <th>Group</th>
                                        <th>Pos Type</th>
                                        <th>Quota Name</th>
                                        <th>S.Position</th>
                                        <th>E.Position</th>
                                        <th>File No</th>
                                        <th>Order In File</th>
                                    </tr>
                               

                                
                                     <%
                                            List list = (List) request.getAttribute("listResultQuery");
                                            
                                            if(list.size()==0){
                                                RequestDispatcher reqDispatch;
                                                reqDispatch = request.getRequestDispatcher("/message.jsp?message=PLEASE GO TO ADMIN PAGE AND INSERT DATA!");
                                                reqDispatch.forward(request, response);
                                            }

                                            Iterator itr;
                                            for (itr = list.iterator(); itr.hasNext();) {

                                                String key = (String) itr.next();
                                                String unitName = (String) itr.next();
                                                String groupName = (String) itr.next();
                                                String posTypeName = (String) itr.next();
                                                String quotaName = (String) itr.next();
                                                String sPos = (String) itr.next();
                                                String ePos = (String) itr.next();
                                                String pdfNo = (String) itr.next();
                                                String priority = (String) itr.next();
                                                
                                                if (groupName.equals("0")) {
                                                    groupName = "";
                                                }
                                                if (sPos == null) {
                                                    sPos = "";
                                                }

                                                if (ePos == null) {
                                                    ePos = "";
                                                }

                                                if (pdfNo == null) {
                                                    pdfNo = "";
                                                }

                                                if (priority == null) {
                                                    priority = "";
                                                }
                                        %>

                                        <tr>
                                            <td><%=unitName%></td>
                                            <td><%=groupName%></td>
                                            <td><%=posTypeName%></td>
                                            <td><%=quotaName%></td>
                                            <td id="sroll"><input type="text" name="startPosition" placeHolder="S Position" onkeypress="return isNumberKey(event)" value="<%=sPos%>" /> </td>
                                            <td id="eroll"><input type="text" name="endPosition" placeHolder="E Position" onkeypress="return isNumberKey(event)" value="<%=ePos%>" /> </td>
                                            <td id="pdf"><input type="text" name="pdfNo" placeHolder="File No" onkeypress="return isNumberKey(event)" value="<%=pdfNo%>" /> </td>
                                            <td id="priority"><input type="text" name="priority" placeHolder="Order In File" onkeypress="return isNumberKey(event)"  value="<%=priority%>" /> </td>
                                            <td><input type="hidden" name="queryKey" value="<%=key%>" /> </td>
                                        </tr>
                                        <%}%>
                                    
                                
                               
                              </table>
                        </div>

                        <div class="query_input">
                            <ul>
                                <li>
                                     <input type="submit" value="result pdf" name="operation"/>
                                     <input type="submit" value="topsheet pdf" name="operation"/>
                                     <input type="submit" value="mobile number" name="operation"/>
                                     <input type="button" value="Reset" onclick="resetResultQueryForm()"/>
                                </li>
                             
                            </ul>
                        </div>

                    </form>

                </div>
            </div>

            <div class="footer">
                <p><a href="index.html">Home</a></p>
                <p>Developed By: SUST-CSE</p>
            </div> 
            
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="js/resjquery.js"></script>
    </body>
</html>