<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <!--<meta http-equiv="X-UA-Compatible" content="IE=edge"/>-->
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Logout</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>

</head>
<body>
    <div class="modal-dialog modal-login">
        <div class="modal-content">
            <div class="modal-header">
                <div class="avatar">
                    <img src="/resources/pictures/avatar.png" alt="Avatar"/>
                </div>
                <h4 class="modal-title">You already logged in</h4>
                <!--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>-->
            </div>
            <div class="modal-body">
                <form id="logoutForm" action="/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-lg btn-block login-btn">Logout</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<!--</div>-->
</body>
</html>