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