<template>
  <div class="container">
    <h1>Login</h1>
    <hr>
    <h1>Welcome Back!</h1><br><br><br>
<label id="label" for="username"><b>Username</b></label><br>
    <input type="text" placeholder="Enter Username" name="username" id="username" v-model="account.username" required>

    <br><label id="label" for="psw"><b>Password</b></label><br>
    <input type="password" placeholder="Enter Password" name="psw" id="psw" v-model="account.password" required>

    <button type="submit" class="registerbtn" v-on:click="authenticateAccount(account)">Login</button>

    <div class="container signinbtn">
    <p>Don't have an account? <router-link to="/CustomerSignUp">Create account</router-link>.</p>
  </div>
  </div>
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

<script>
import Account from '../assets/js/account'

async function authenticateAccount(account) {
  const {username, password} = account;
  const data = await Account.loadAccountFromDatabase(username, password);
  if (data) {
    confirm(`Successfully logged in as ${data.username}`);
  } else {
    alert(`Failed to log in as ${data.username}`);
  }
}

export default {
  data() {
    return {
      account: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    authenticateAccount: authenticateAccount
  }
}
</script>