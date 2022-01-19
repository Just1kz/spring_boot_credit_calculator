<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <title>Credit Calculator</title>
</head>
<body>
<h2>Welcome to Credit Calculator</h2>
<br>

<input type="button" value="Add Credit for Calculate"
            onclick="window.location.href = 'addCredit'"/>

<input type="button" value="Monitoring IoC"
       onclick="window.location.href = 'beans'"/>

<input type="button" value="H2 in memory DB"
       onclick="window.location.href = 'console'"/>

<br>
<br>
<table>
    <tr>
        <th>Period</th>
        <th>Amount</th>
        <th>Percent</th>
        <th>Status of Calculate Payments</th>
        <th>Operation</th>
    </tr>

    <c:forEach var="credit" items="${credits}">

        <c:url var="editParamsForCreditButton" value="/editCredit">
            <c:param name="creditId" value="${credit.id}"/>
        </c:url>

        <c:url var="calculateButton" value="/calculateSchedulePayments">
            <c:param name="creditId" value="${credit.id}"/>
        </c:url>

        <c:url var="showPaymentButton" value="/showPaymentsOfCredit">
            <c:param name="creditId" value="${credit.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/deleteCredit">
            <c:param name="creditId" value="${credit.id}"/>
        </c:url>

        <tr>
            <td>${credit.periodCredit}</td>
            <td>${credit.amount}</td>
            <td>${credit.costPercentage}</td>
            <td>${credit.status}</td>

            <td>
                <input type="button" value="Edit Credit Params"
                       onclick="window.location.href = '${editParamsForCreditButton}'">
            </td>

            <td>
                <input type="button" value="Calculate Payments"
                onclick="window.location.href = '${calculateButton}'">
            </td>

            <td>
                <input type="button" value="Show Payments"
                       onclick="window.location.href = '${showPaymentButton}'">
            </td>

            <td>
                <input type="button" value="Delete Credit"
                       onclick="window.location.href = '${deleteButton}'">
            </td>

        </tr>
    </c:forEach>

</table>
</body>
</html>