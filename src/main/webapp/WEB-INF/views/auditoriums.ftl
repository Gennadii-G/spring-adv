<html>
<head>
    <title>Auditorium List</title>
</head>
<body>
<h3>Auditorium List</h3>
<br/><br/>
    <div>

        <table border="1">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>seatsNumber</th>
                <th>vipSeats</th>
            </tr>
            <#list auditoriums as auditorium>
            <tr>
                <td>${auditorium.id}</td>
                <td>${auditorium.name}</td>
                <td>${auditorium.seatsNumber}</td>
                <td>${auditorium.vipSeats}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>