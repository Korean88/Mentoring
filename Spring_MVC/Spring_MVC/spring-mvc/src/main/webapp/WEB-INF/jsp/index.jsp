<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="tilesTemplate">
    <tiles:putAttribute name="body">
        <h2>Side Navigation Example</h2>
        <p>Note that you control the size of the sidenav with style="width:value". To change the color,
            change the w3-color class.</p>
        <p>Make sure to add the margin-left property to the page content, with a value that is equal to the width of
            the sidenav. If you omit this, the navigation pane will overlay/sit on top of the page content.</p>
    </tiles:putAttribute>
</tiles:insertDefinition>