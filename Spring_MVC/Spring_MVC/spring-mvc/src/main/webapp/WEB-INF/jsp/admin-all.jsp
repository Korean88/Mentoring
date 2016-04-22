<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="tilesTemplate">
    <tiles:putAttribute name="body">
        <table class="w3-table w3-striped w3-bordered w3-border w3-large">
            <c:forEach items="${allMeals}" var="meal">
            <tr>
                <td><img src="<c:url value="${meal.imagePath}"/>"></td>
                <td>${meal.name}</td>
                <td>${meal.price}</td>
                <td>Diabetic:${meal.diabetic}
                    <br/>
                    Vegetarian: ${meal.vegetarian}
                </td>
            </tr>
            </c:forEach>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>