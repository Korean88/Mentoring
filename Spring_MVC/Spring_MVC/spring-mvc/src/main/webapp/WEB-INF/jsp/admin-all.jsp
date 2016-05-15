<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <td><a class="w3-badge w3-padding-large w3-black" href="<c:url value='/admin/edit/${meal.id}'/>">
                        <i class="fa fa-pencil" style="font-size:24px"></i>
                    </a>&nbsp;&nbsp;
                    <a class="w3-badge w3-padding-large w3-red" href="<c:url value='/admin/delete/${meal.id}'/>">
                        <i class="fa fa-trash" style="font-size:24px"></i>
                    </a>
                    <br>
                    <br>
                    <a class="w3-tag w3-padding-medium w3-blue" href="<c:url value='/cn/${meal.id}.xml'/>">
                        XML
                    </a>&nbsp;
                    <a class="w3-tag w3-padding-medium w3-blue" href="<c:url value='/cn/${meal.id}.json'/>">
                        JSON
                    </a>
                    <p><form:form method="post" action="cart/add/${meal.id}">
                        <input class="w3-tag w3-padding-medium w3-green" type="submit" value="Add to Cart">
                    </form:form></p>
                    </td>
            </tr>
            </c:forEach>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>