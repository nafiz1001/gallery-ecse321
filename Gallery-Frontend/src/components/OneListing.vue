<template>
  <div id="Listing">
      <div class="split left1">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a class="leftText>">Back to Listings</a>
  <div class="centered1">
    <img src="https://i.ibb.co/jG0C1WJ/The-Starry-Night-1889-by-Vincent-Van-Gogh-Original-from-Wikimedia-Commons-Digitally-enhanced-by-rawp.jpg">
    <div class="passline"> </div>
    <h3 style="font-family: Raleway; color: #2B2D42  ">Select Quantity: </h3>
    <form>
<input style="font-family: Raleway; color: #2B2D42; font-size: 20px" type="number" min="1" max="100" step="1" value="1" required>
<input style="font-family: Raleway; color: #2B2D42; font-size: 20px" type="submit" value="Add to Cart!">
</form>
  </div>
</div>

<div class="split right1">
 
<div class="passBigLine"> </div>
    <h1 style="text-decoration: underline; font-family: Raleway; font-weight: bold"> Listing Specifications</h1>
    <div class="title">
    <h3 style="display:inline ; font-family: Raleway; font-weight: bold"> Title: </h3> <p style="display:inline; font-size:22px; font-family: Raleway"> mona lisa</p> </div>
   <div class="passline"> </div>
   <h3 style="display:inline; font-family: Raleway; font-weight: bold"> Author: </h3><p style="display:inline; font-size:22px; font-family: Raleway"> van Gogh</p>
    <div class="passline"> </div>
    <h3 style="display:inline; font-family: Raleway; font-weight: bold"> Price: </h3><p style="display:inline; font-size:22px; font-family: Raleway"> 1000000$</p>
   <div class="passline"> </div>
    <h3 style="display:inline; font-family: Raleway; font-weight: bold"> Quantity Available: </h3><p style="display:inline; font-size:22px; font-family: Raleway">1</p>
<div class="passline"> </div>
    <h3 style="display:inline; font-family: Raleway; font-weight: bold"> Description: </h3><p style="display:inline; font-size:22px; font-family: Raleway"> cool art</p>
     <div class="passline"> </div>
     <h3 style="display:inline; font-family: Raleway; font-weight: bold"> Dimensions (cm): </h3> <p style="display:inline; font-size:22px; font-family: Raleway"> 1 x 1 x 1</p>
     <div class="passline"> </div>
     <h3 style="display:inline; font-family: Raleway; font-weight: bold"> Date Published: </h3> <p style="display:inline; font-size:22px; font-family: Raleway"> January 2020</p>
     <div class="passline"> </div>
     <h3 style="display:inline; font-family: Raleway; font-weight: bold"> Tags: </h3><p style="display:inline; font-size:22px; font-family: Raleway"> cool </p>
    <div class="passline"> </div>
     <h3 style="display:inline; font-family: Raleway; font-weight: bold"> Listing ID: </h3> <p style="display:inline; font-size:22px; font-family: Raleway"> 00000 </p>


  
</div>
</div>
</template>

<style>
.title{
    display:inline;
}
.passBigLine{
    margin: 7em;
}
.passline {
    margin: 1em;
}
a{
    display:flex;
    flex-direction: row;
    justify-content:flex-start;
    border: 2px solid #2B2D42 ;
    padding: 3px;
    padding-right: 0px;
    font-family: Raleway; 
    font-size: 16px;
    font-weight: bold;
    color: #2B2D42 ;
     margin:8px;

}
a:hover{
    color: #2B2D42 ;
}
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
.left1 {
  left: 0;
  background-color: #FFFFFF;
}

/* Control the right side */
.right1 {
  right: 0;
  background-color: #00FF80;
  text-align: left;
  padding-left: 10px;
  

}

/* If you want the content centered horizontally and vertically */
.centered1 {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

/* Style the image inside the centered container, if needed */
.centered1 img {
  padding:4px;
   border:3px solid #2B2D42;
  width: 550px;
  
}
</style>

<script>
export default {
    
}
</script>