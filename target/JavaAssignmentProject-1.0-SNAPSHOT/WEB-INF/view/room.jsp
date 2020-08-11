<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <meta http-equiv='cache-control' content='no-cache'>
        <meta http-equiv='expires' content='0'>
        <meta http-equiv='pragma' content='no-cache'>
        <title>Your Room</title>
        <script src="<c:url value='/resources/js/canvas.js'/>"></script>
        <link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>">
    </head>
    <body>
        <section class="headerForRoom">
            <h1>Your Beautiful Room &#128522;</h1>
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