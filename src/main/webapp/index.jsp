<html>
<head>
<title>Login</title>
<!-- <spring:url value="/resources/js/validationScript.js" var="validationJs" /> -->

<script>
	function validateInput() {
		var userName = document.getElementById("userName").value;
		var pass = document.getElementById("password").value;
		var passPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})";

		// Validation for User Name
		if (userName == "") {
			document.getElementById("userNameMsg").innerHTML = "User Name can't be left Empty...";
			return false;
		} else if (userName.indexOf("@") < 0) {
			document.getElementById("userNameMsg").innerHTML = "@ needs to be present in the User Name...";
			return false;
		} else if (userName.indexOf("@") == 0) {
			document.getElementById("userNameMsg").innerHTML = "Atleast 1 character should be present before @ in the User Name...";
			return false;
		} else {
			document.getElementById("userNameMsg").innerHTML = "";
		}

		// Validation for Password
		if (pass == "") {
			document.getElementById("passwordMsg").innerHTML = "Password can't be left Empty...";
			return false;
		} else if (!pass.match(passPattern)) {
			document.getElementById("passwordMsg").innerHTML = "Enter valid Password...";
			return false;
		} else {
			document.getElementById("passwordMsg").innerHTML = "";
		}

		return true;
	}
</script>
</head>
<body>
	<form action="validateUser" method="post">
		<div>
			<input type="text" name="userName" id="userName" placeholder="Your User Name" /><br>
			<span id="userNameMsg"></span>
		</div>
		<div>
			<input type="text" name="password" id="password" placeholder="Your password" /><br>
			<span id="passwordMsg"></span>
		</div>
		<input type="submit" value="submit" onclick="return validateInput()">
	</form>
</body>
</html>