<!DOCTYPE html>
<html>
<head>
<style type="text/css">
    <#include "styles.css">
</style>
	<title></title>
</head>
<body>

<form action="/DemoServlet/login" method="post">
<div class="container">
<label class="text"><b>Username</b></label><br>
<input type="email" placeholder="Enter E-Mail" name="email" required><br>
<label for="psw" class="text"><b>Password</b></label><br>
<input type="password" placeholder="Enter Password" name="password" required><br>
<button type="submit">Login</button>
</div>
<div class="container" style="background-color:#f1f1f1">
<span class="psw"><a href="/DemoServlet/signup">Create account</a></span>
</div>
</form>

</body>
</html>