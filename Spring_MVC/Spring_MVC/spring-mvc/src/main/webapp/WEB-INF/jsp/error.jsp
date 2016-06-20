<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="tilesTemplate">
    <tiles:putAttribute name="body">

        <div class="w3-red">
            <h2>Error</h2>
            <p>An error ocurred while processing your request</p>
        </div>
        <br>
        <div class="w3-container w3-pale-red w3-leftbar w3-rightbar w3-border-red">
            <p>${exception.message}</p>
        </div>
        <br>

    </tiles:putAttribute>
</tiles:insertDefinition>