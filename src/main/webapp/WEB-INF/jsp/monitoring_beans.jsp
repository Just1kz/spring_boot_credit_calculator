<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<body>
<h2>Welcome to Credit Calculator</h2>
<br>


<input type="button" value="Back"
       onclick="window.location.href = 'main'"/>
<br>

<table>
    <tr><h3>Beans in IoC</h3></tr>
    <br>

<c:forEach var="bean" items="${beans}">
    <tr>
        <td>${bean.toString()}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>