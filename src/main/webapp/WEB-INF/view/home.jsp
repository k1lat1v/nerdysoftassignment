<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.nerdysoft.vitaliim.util.Mappings" %>
<html>
<head>
    <title>Java Assignment</title>
</head>
<body>
    <div align="center">
       <h1>WELCOME TO VITALII`S JAVA ASSIGNMENT PROJECT</h1>
        <c:url var="createRoom" value="${Mappings.CREATION}"/>
        <h2><a href="${createRoom}">Click To Create Room</a></h2>
    </div>


</body>
</html>