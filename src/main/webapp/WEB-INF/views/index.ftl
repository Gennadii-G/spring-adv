<html>
<head>
    <title>hello freemaker</title>
</head>
<body>
<form id="logoutForm" action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit">Logout</button>

</form>
<h3>lorem ipsum dolor =)</h3>
</body>
</html>