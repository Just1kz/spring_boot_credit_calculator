<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>

<body>

<input type="button" value="Back"
       onclick="window.location.href = 'main'"/>
<br>

<h2>Credit info</h2>
<br>

<form:form action="saveCredit" modelAttribute="credit">

    <form:hidden path="id"/>

    Period <form:input path="periodCredit"/>
    <form:errors path="periodCredit"/>
    <br>
    <br>
    Amount <form:input path="amount"/>
    <form:errors path="amount"/>
    <br>
    <br>
    Percent <form:input path="costPercentage"/>
    <form:errors path="costPercentage"/>
    <br>
    <br>
    <input type="submit" value="OK">

</form:form>

</body>



</html>