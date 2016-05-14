<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="tilesLogin">
    <tiles:putAttribute name="menu">&nbsp;</tiles:putAttribute>
    <tiles:putAttribute name="body">

        <br>
        <br>
        <div style="margin: auto; top: 30%; width: 30%;">
            <div class="w3-card-4">
                <div class="w3-container w3-light-green">
                    <h2>Fast Food Authentication</h2>
                </div>
                <form class="w3-container" action="login" method="POST">

                    <c:if test="${param.error != null}">
                        <div class="w3-container w3-section w3-red w3-card-2">
                            <span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span>
                            <p>Username / password incorrect</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="w3-container w3-section w3-blue w3-card-2">
                            <span onclick="this.parentElement.style.display='none'" class="w3-closebtn">&times;</span>
                            <p>You have logged out successfully</p>
                        </div>
                    </c:if>

                    <p>
                        <label class="w3-label w3-text-orange" for="username"><b>Login</b></label>
                        <input class="w3-input w3-border w3-pale-yellow" id="username" type="text" name="login"
                               placeholder="Login" required="true">
                    </p>

                    <p>
                        <label class="w3-label w3-text-orange" for="pass"><b>Password</b></label>
                        <input class="w3-input w3-border w3-pale-yellow" id="pass" type="password" name="password"
                               placeholder="Password" required="true">
                    </p>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <p>
                        <button class="w3-btn w3-light-green">Login</button>
                    </p>

                </form>
            </div>
        </div>
        <br>
        <br>

    </tiles:putAttribute>
</tiles:insertDefinition>