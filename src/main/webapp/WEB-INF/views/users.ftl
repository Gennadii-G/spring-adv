<html>
<head>
    <title>Users List</title>
</head>
<body>
<h3>Users List</h3>
<br/><br/>
<div>

    <table border="1">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>email</th>
            <th>birthday</th>
        </tr>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.birthday}</td>
            </tr>
        </#list>
</table>
</div>
</body>
        </html>