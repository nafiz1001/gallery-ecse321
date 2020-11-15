<template>
  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
  <div class="container profilePic">
    
  </div>
    <label id="label" for="picture"><b>Picture Link</b></label><br>
    <input id="fileInput" type="text" v-model="profile.picture"/>
    <br>
    <label id="label" for="firstName"><b>First Name</b></label><br>
    <input type="text" placeholder="Enter First Name" name="firtName" id="firstName" v-model="profile.firstName" required>

    <br><label id="label" for="lastName"><b>Last Name</b></label><br>
    <input type="text" placeholder="Enter Last Name" name="lastName" id="lastName" v-model="profile.lastName" required>

    <br><label id="label" for="email"><b>Email</b></label><br>
    <input type="text" placeholder="Enter Email" name="email" id="email" v-model="profile.email" required>

    <br><label id="label" for="date"><b>Date of Birth</b></label><br>
        <input type="date" id="date" name="date" v-model="profile.dateOfBirth" required>

    <br><label id="label" for="username"><b>Username</b></label><br>
    <input type="text" placeholder="Enter Username" name="username" id="username" v-model="profile.username" required>

    <br><label id="label" for="psw"><b>Password</b></label><br>
    <input type="password" placeholder="Enter Password" name="psw" id="psw" v-model="profile.password" required>

    <br><label id="label" for="psw-repeat"><b>Repeat Password</b></label><br>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" v-model="profile.password2" required>

    <br><label id="label" for="bio"><b>Bio</b></label><br>
    <textarea type="text" placeholder="Write a bio" name="bio" id="bio" v-model="profile.bio"></textarea>
    <hr>

    <button type="submit" class="registerbtn" v-on:click="createProfile(profile)">Register</button>
    <div class="container signinbtn">
    <p>Already have an account? <router-link to="/ArtistSignIn">Sign in</router-link>.</p>
  </div>
  </div>
</template>

<style>
* {box-sizing: border-box}

/* Add padding to containers */
.container {
  padding: 16px;
}

/* Container needed to position the button. Adjust the width as needed */
.profilePic {
  position: relative;
  width: 50%;
}

/* Make the image responsive */
.profilePic img {
  width: 50%;
  height: auto;
}

/* Style the button and place it in the middle of the container/image */
input[type=btn] {
  position: absolute;
  top: 75%;
  left: 50%;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  background-color: #8D99AE;
  color: white;
  font-size: 16px;
  padding: 12px 24px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

input[type=btn]:hover {
  background-color: #2B2D42;
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

/* Half-width textasre field */
textarea {
  width: 50%;
  height: 200px;
  padding: 30px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}


input[type=text]:focus, input[type=password]:focus, input[type=bio] {
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
<!-- -->

  <script>  
import Backend from '../assets/js/backend'
import Account from '../assets/js/account'
import Profile from '../assets/js/profile'

function successful(response) {
  confirm("You successfully created a profile");
  window.location = '/#/ArtistSignIn';
}

function failure(error) {
  alert("Your profile creation failed");
  console.error(error);
}

async function createProfile(profile) {
  if (profile.password != profile.password2) {
    alert("Passwords do no match");
  } else {
      const accountDto = new Backend.AccountDto(
        "Artist", 
        {email : profile.email},
        [],
        profile.username,
        profile.password,
        null,
        null,
        null,
        [],
        "0",
        "CREDIT"
      );
    const createdAccount = await Account.createAccount(accountDto).then(createdAccount => {
      console.log(createdAccount);
      const profileDto = new Backend.ProfileDto(
      profile.bio, 
      profile.picture,
      [],
      createdAccount,
      profile.firstName + ' ' + profile.lastName,
      []
      );
      console.log(profileDto);
      await Profile.createProfile(profileDto, createdAccount.password).then(successful).catch(failure);
    }).catch(failure);
  }
}


export default {
  data () {
    return  {
      profile: {
        firstName: '',
        lastName: '',
        email: '',
        dateOfBirth: Date.now(),
        username: '',
        password: '',
        password2: '',
        bio: '',
        picture: 'https://pbs.twimg.com/profile_images/2996390845/d5f215b28cfce7c235080c37f54b05fb_400x400.jpeg'
      }
    }
  },
  methods: {
      createProfile: createProfile
  }
}
</script>