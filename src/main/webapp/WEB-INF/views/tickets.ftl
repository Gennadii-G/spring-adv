<html>
<head>
    <title>Ticket List</title>
</head>
<body>
<h3>Ticket List</h3>
<br/><br/>
<div>

    <table border="1">
        <tr>
            <th>id</th>
            <th>event</th>
            <th>dateTime</th>
            <th>seats</th>
            <th>user</th>
            <th>price</th>
        </tr>
        <#list tickets as ticket>
            <tr>
                <td>${ticket.id}</td>
                <td>${ticket.event}</td>
                <td>${ticket.dateTime}</td>
                <td>${ticket.seats}</td>
                <td>${ticket.user}</td>
                <td>${ticket.price}</td>
            </tr>
        </#list>
</table>
</div>
</body>
        </html>