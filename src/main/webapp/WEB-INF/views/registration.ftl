<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <!--<meta http-equiv="X-UA-Compatible" content="IE=edge"/>-->
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Cinema control user registration</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>

</head>
<body>
<!--<div class="text-center">-->
<!-- Button HTML (to Trigger Modal) -->
<!--<a href="#myModal" class="trigger-btn" data-toggle="modal">Click to Open Login Modal</a>-->
<!--</div>-->

<!-- Modal HTML -->
<!--<div id="myModal" class="modal fade">-->
<div class="modal-dialog modal-login">
    <div class="modal-content">
        <div class="modal-header">
            <div class="avatar">
                <img src="/resources/pictures/avatar.png" alt="Avatar"/>
            </div>
            <h4 class="modal-title">Registration</h4>
            <!--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>-->
        </div>
        <div class="modal-body">
            <form action="/user/registration" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="email" id="email" placeholder="email"
                           required="required" autofocus/>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" id="password"
                           placeholder="password" required="required"/>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                           placeholder="confirm password" required="required"/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-lg btn-block login-btn">Login</button>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <a href="#">Forgot Password?</a>
        </div>
    </div>
</div>
<!--</div>-->
</body>
</html>