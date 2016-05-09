<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="tilesTemplate">
    <tiles:putAttribute name="body">

        <br/>
        <br/>

        <div class="w3-container">
            <h2>Edit Meal</h2>
        </div>

        <form:form method="post" class="w3-container" enctype="multipart/form-data">
            <p>
                <form:label path="name">Name</form:label>
                <form:input path="name" class="w3-input" />
            </p>
            <p>
                <form:label path="price">Price</form:label>
                <form:input path="price" class="w3-input"/>
            </p>
            <p>
                <form:label path="diabetic">Diabetic</form:label>
                <form:radiobutton path="diabetic" value="true" class="w3-radio"/>Yes
                <form:radiobutton path="diabetic" value="false" class="w3-radio"/>No
            </p>
            <p>
                <form:label path="vegetarian">Vegetarian</form:label>
                <form:radiobutton path="vegetarian" value="true" class="w3-radio"/>Yes
                <form:radiobutton path="vegetarian" value="false" class="w3-radio"/>No
            </p>
            <p>
                <img src="<c:url value='${meal.imagePath}'/>" class="w3-border w3-padding" alt="${meal.name}">
            </p>
            <p>
                <input class="w3-btn w3-light-grey w3-border w3-border-yellow w3-round-xlarge" type="file" name="file"/>
                <br/>
            </p>
            <p>
                <input class="w3-btn w3-green" type="submit" value="Save"/>
            </p>
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>