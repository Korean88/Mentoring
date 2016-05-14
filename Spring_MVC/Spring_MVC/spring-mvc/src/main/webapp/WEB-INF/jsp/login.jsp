<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="tilesLogin">
    <tiles:putAttribute name="menu">&nbsp;</tiles:putAttribute>
    <tiles:putAttribute name="body" >

        <div style="position: fixed;top: 30%;left: 30%;">
        <div class="w3-card-4">
            <div class="w3-container w3-light-green">
                <h2>Fast Food Authentication</h2>
            </div>
            <form class="w3-container" action="#">

                <p>
                    <label class="w3-label w3-text-orange"><b>Login</b></label>
                    <input class="w3-input w3-border w3-pale-yellow" type="text"></p>

                <p>
                    <label class="w3-label w3-text-orange"><b>Password</b></label>
                    <input class="w3-input w3-border w3-pale-yellow" type="password"></p>

                <p>
                    <button class="w3-btn w3-light-green">Login</button></p>

            </form>
        </div>
        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>