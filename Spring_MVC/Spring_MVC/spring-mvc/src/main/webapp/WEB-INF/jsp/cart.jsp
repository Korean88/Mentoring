<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="tilesTemplate">
    <tiles:putAttribute name="body">
        <table class="w3-table w3-striped w3-bordered w3-border w3-large" style="margin: 10px; margin-right: 10px;">
            <c:forEach items="${cart.allMeals}" var="meal">
                <tr>
                    <td><img src="<c:url value="${meal.imagePath}"/>"></td>
                    <td>${meal.name}</td>
                    <td>${meal.price}</td>
                    <td>Diabetic:${meal.diabetic}
                        <br/>
                        Vegetarian: ${meal.vegetarian}
                    </td>
                    <td>
                    <form:form method="post" action="cart/delete/${meal.id}">
                        <input class="w3-btn w3-ripple w3-red" type="submit" value="Remove"/>
                    </form:form>
                        <br>
                        <br>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>