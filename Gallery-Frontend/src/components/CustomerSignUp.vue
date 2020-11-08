<template>
<form action="action_page.php">
  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
 
    <label id="label" for="firstName"><b>First Name</b></label><br>
    <input type="text" placeholder="Enter First Name" name="firtName" id="firstName" required>

    <br><label id="label" for="lastName"><b>Last Name</b></label><br>
    <input type="text" placeholder="Enter Last Name" name="lastName" id="lastName" required>

    <br><label id="label" for="email"><b>Email</b></label><br>
    <input type="text" placeholder="Enter Email" name="email" id="email" required>

    <br><label id="label" for="username"><b>Username</b></label><br>
    <input type="text" placeholder="Enter Username" name="username" id="username" required>

    <br><label id="label" for="psw"><b>Password</b></label><br>
    <input type="password" placeholder="Enter Password" name="psw" id="psw" required>

    <br><label id="label" for="psw-repeat"><b>Repeat Password</b></label><br>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>

    <button type="submit" class="registerbtn">Register</button>
  </div>

  <div class="container signinbtn">
    <p>Already have an account? <router-link to="/CustomerSignIn">Sign in</router-link>.</p>
  </div>
</form>
</template>

<style>
* {box-sizing: border-box}

/* Add padding to containers */
.container {
  padding: 16px;
}

/* Half-width input fields */
input[type=text], input[type=password], input[type=date], select[name=gender] {
  width: 50%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for the submit/register button */
.registerbtn {
  background-color: #00ff80;
  color: black;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 50%;
  opacity: 0.9;
}

.registerbtn:hover {
  opacity:1;
}

/* Add a blue text color to links */
a {
  color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signinbtn {
  background-color: #f1f1f1;
  text-align: center;
  border: none;
}

label[id=label]{
    left: 330px;
    position: absolute; 
    
}
</style>