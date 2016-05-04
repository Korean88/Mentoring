<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="tilesTemplate">
    <tiles:putAttribute name="body">

        <br/>
        <br/>

        <div class="w3-container w3-light-green">
            <h2>Add New Meal</h2>
        </div>

        <form:form method="post" class="w3-container" enctype="multipart/form-data">
            <p>
                <form:label path="name">Name</form:label>
                <form:input path="name" class="w3-input"/>
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
                <input type="file" name="file"/>
                <br/>
            </p>
            <p>
                <input type="submit" value="Save"/>
            </p>
        </form:form>
    </tiles:putAttribute>
</tiles:insertDefinition>