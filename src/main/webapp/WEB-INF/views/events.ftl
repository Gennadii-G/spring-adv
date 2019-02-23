<html>
<head>
    <title>Event List</title>
</head>
<body>
<h3>Event List</h3>
<br/><br/>
<div>

    <table border="1">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>rate</th>
            <th>basePrice</th>
            <th>dateTime</th>
            <th>auditorium</th>
        </tr>
        <#list events as event>
            <tr>
                <td>${event.id}</td>
                <td>${event.name}</td>
                <td>${event.rate}</td>
                <td>${event.basePrice}</td>
                <td>${event.dateTime}</td>
                <td>${event.auditorium}</td>
            </tr>
        </#list>
</table>
</div>
</body>
        </html>