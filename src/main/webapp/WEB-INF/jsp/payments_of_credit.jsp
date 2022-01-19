<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

<input type="button" value="Back"
       onclick="window.location.href = 'main'"/>

<br>

<h2>Schedule Payment of Credit</h2>
<br>

<br>
<table>
    <tr>
        <th>Month</th>
        <th>Percent of Payment</th>
        <th>Debt of Payment</th>
        <th>Monthly Payment</th>
        <th>Debt after monthly payment</th>
    </tr>

    <c:forEach var="payment" items="${payments}">
    <tr>
        <td>${payment.month}</td>
        <td>${payment.percentPayment}</td>
        <td>${payment.debtPayment}</td>
        <td>${payment.payment}</td>
        <td>${payment.debtAfterPayment}</td>
    </tr>
    </c:forEach>

</table>

</html>