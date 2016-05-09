<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
    <link href="<c:url value="/resources/css/w3.css"/>" rel="stylesheet"/>
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
    <script type="application/javascript" src="<c:url value="/resources/script/w3.js"/>"></script>
</head>
<body>

<nav class="w3-sidenav w3-white w3-card-2" style="display:none">
    <a href="javascript:void(0)"
       onclick="w3_close()"
       class="w3-closenav w3-large">Close &times;</a>
    <tiles:insertAttribute name="menu"/>
</nav>

<div id="main">

    <header class="w3-container w3-orange">
        <span class="w3-opennav w3-xlarge" onclick="w3_open()">&#9776;</span>
        <tiles:insertAttribute name="header"/>
    </header>

    <div class="w3-container">
        <tiles:insertAttribute name="body"/>
    </div>

    <footer class="w3-container w3-orange">
        <tiles:insertAttribute name="footer"/>
    </footer>

</div>

</body>
</html>
