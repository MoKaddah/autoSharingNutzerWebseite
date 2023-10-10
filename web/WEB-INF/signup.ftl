<!doctype html>
<html lang="en">
  <head>
  	<title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<style type="text/css">
            <#include "styles.css">
        </style>
	</head>
	<body>

            <form method="post" action="/DemoServlet/signup" class="login-form">
                <div class="container">
                    <input type="text" placeholder="Name" name="name" required>                           	
                    <input type="email"  placeholder="E-Mail" name="email" required>		      	    
                    <input type="password"  placeholder="Password" name="password" required>
                    <button type="submit">${reg}</button>
                </div>
            </form>

</body>
</html>

