<template>
  <div id="HomePage">
    <div class="split left">
      <div class="centered">
        <h2
          style="
            font-family: Didot;
            font-size: 40px;
            color: rgb(224, 224, 224);
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
          style="background-color: rgb(224, 224, 224); color: rgb(58, 96, 110)"
        >
          Continue As Guest
        </button>

        <button
          type="button"
          style="background-color: rgb(224, 224, 224); color: rgb(58, 96, 110)"
        >
          Log In
        </button>

        <button
          type="button"
          style="background-color: rgb(224, 224, 224); color: rgb(58, 96, 110)"
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
            color: rgb(58, 96, 110);
            font-weight: bold;
          "
        >
          I am an Artist!
        </h2>
        <img src="https://i.ibb.co/gS3kRc8/imageedit-20-9541402536.png"/>
        <p></p>
        <button
          type="button"
          style="background-color: rgb(58, 96, 110); color: rgb(224, 224, 224)"
        >
          Log In
        </button>

        <button
          type="button"
          style="background-color: rgb(58, 96, 110); color: rgb(224, 224, 224)"
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
  background-color: rgb(58, 96, 110);
}

/* Control the right side */
.right {
  right: 0;
  background-color: rgb(224, 224, 224);
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