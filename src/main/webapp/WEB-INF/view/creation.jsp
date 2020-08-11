<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <meta http-equiv='cache-control' content='no-cache'>
        <meta http-equiv='expires' content='0'>
        <meta http-equiv='pragma' content='no-cache'>
        <title>Test Form</title>
        <link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
        <script src="<c:url value='/resources/js/canvas.js'/>"></script>
    </head>
    <body>

        <section class="header">

            <div class="alert">
                ${result}
            </div>

            <div class="formDiv">
                <form:form action="validateRoom" method="POST" modelAttribute="coordinatePair">
                    <table>
                        <tr>
                            <td><label>Enter X:</label></td>
                            <td>
                                <form:input type="number" path="x"/>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Enter Y:</label></td>
                            <td>
                                <form:input type="number" path="y"/>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" id="addCircle" name="addCoordinates" value="Add">
                    <input type="submit" name="submitCoordinates" value="Submit">
                </form:form>
            </div>

        </section>

        <section class="main">

            <div class="listDiv">
                <table border="1" cellpadding="5">
                    <caption><h2>Added Coordinates</h2></caption>

                        <tr>
                            <th>X value</th>
                            <th>Y value</th>
                        </tr>

                        <c:forEach var="coordinatePair" items="${service.coordinates}">

                            <tr>
                                <td>
                                    <c:set var="xValue" value="${coordinatePair.x}"/>
                                    <c:out value="${coordinatePair.x}"/>
                                    <script type="text/javascript">var xValue="${coordinatePair.x}"</script>
                                </td>
                                <td>
                                    <c:set var="yValue" value="${coordinatePair.y}"/>
                                    <c:out value="${coordinatePair.y}"/>
                                    <script type="text/javascript">var yValue="${coordinatePair.y}"</script>
                                </td>
                            </tr>

                        </c:forEach>

                </table>
            </div>

            <div class="cartesianGrid">
                <canvas id="my-canvas" width="800" height="800"></canvas>
                <script>drawCartesianGrid();</script>
                <c:forEach var="coordinatePair" items="${service.coordinates}">
                    <script>drawCircle(${coordinatePair.x}, ${coordinatePair.y})</script>
                </c:forEach>
            </div>

        </section>
    </body>
</html>