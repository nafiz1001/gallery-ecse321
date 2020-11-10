<template>
  <div id="HomePage">
    <div class="split left">
      <div class="centered">
        <h2
          style="
            font-family: Didot;
            font-size: 40px;
            color: #ffffff;
            font-weight: bold;
          "
        >
          I am a Customer!
        </h2>
        <div class="passline"> </div>
        <img src="https://i.ibb.co/JngM8hS/images-removebg-preview.png" />
        <div class="passline"> </div>
        <button
          type="button"
          style="background-color: #00ff80; color: #000000; font-size: 22px; font-family: Raleway; font-weight: bold; border: 2px solid #FFFFFF"
       @click="$router.push('BrowseListings')"
        >
          Continue As Guest
        </button>

        <button
          type="button"
          style="background-color: #00ff80; color: #000000; font-size: 22px; font-family: Raleway; font-weight: bold; border: 2px solid #FFFFFF"
         @click="$router.push('CustomerSignIn')"
        >
          Log In
        </button>

        <button
          type="button"
          style="background-color: #00ff80; color: #000000; font-size: 22px; font-family: Raleway; font-weight: bold; border: 2px solid #FFFFFF"
        @click="$router.push('CustomerSignUp')"
        >
          Create Account
        </button>
      </div>
    </div>

    <div class="split right">
      <div class="centered">
        <h2
          style="
            font-family: Didot;
            font-size: 40px;
            color: #000000;
            font-weight: bold;
          "
        >
          I am an Artist!
        </h2>
        <img src="https://i.ibb.co/YNFx3pJ/art-icon-260nw-349560422-jpg-removebg-preview.png"/>
        <p></p>
        <button
          type="button"
          style="background-color: #00ff80; color: #000000; font-size: 22px; font-family: Raleway; font-weight: bold; border: 2px solid #000000"
         @click="$router.push('ArtistSignIn')"
        >
          Log In
        </button>

        <button
          type="button"
          style="background-color: #00ff80; color: #000000; font-size: 22px; font-family: Raleway; font-weight: bold; border: 2px solid #000000"
        @click="$router.push('ArtistSignUp')"
        >
          Create Profile
        </button>
      </div>
    </div>
  </div>
</template>

<style>

.passline {
    margin: 3em;
}
/* Split the screen in half */
.split {
  height: 100%;
  width: 50%;
  position: fixed;
  z-index: 1;
  top: 10;
  overflow-x: hidden;
  padding-top: 20px;
}

/* Control the left side */
.left {
  left: 0;
  background-color: #000000;
}

/* Control the right side */
.right {
  right: 0;
  background-color: #ffffff;
}

/* If you want the content centered horizontally and vertically */
.centered {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

/* Style the image inside the centered container, if needed */
/* .centered img {
  width: 150px;
  border-radius: 50%;
} */
</style>