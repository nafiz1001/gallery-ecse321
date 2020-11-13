<template>
  <div class="container">
    <h1>Create Listing</h1>
    <p>Please fill in this form to create a listing.</p>
    <hr>
    <label id="label" for="picture"><b>Picture Link</b></label><br>
    <input id="fileInput" placeholder="Enter Picture URL" type="text"/>
    <br>
    <br><label id="label" for="title"><b>Title</b></label><br>
    <input type="text" placeholder="Enter Title" name="title" id="listingTitle" />

    <br><label id="label" for="price"><b>Price</b></label><br>
    <input type="text" placeholder="Enter Price" name="price" id="listingPrice" />

    <br><label id="label" for="quantity"><b>Quantity</b></label><br>
    <input type="number" min="1"
            max="100"
            step="1" name="quantity" id="listingQuantity" v/>

     <br><br><label id="label" for="shipping"><b>Available For Shipping</b></label><br>
     <input type="radio" id="yesShipping" name="shipping" value="Yes">
        <label for="yesShipping">Yes</label><br>
    <input type="radio" id="noShipping" name="shipping" value="No">
        <label for="noShipping">No</label><br>

    <br><label id="label" for="pickup"><b>Available For In-Store Pickup</b></label><br>
     <input type="radio" id="yesPickup" name="pickup" value="Yes">
        <label for="yesPickup">Yes</label><br>
    <input type="radio" id="noPickup" name="pickup" value="No">
        <label for="noPickup">No</label><br>

    <br><label id="label" for="tags"><b>Tags</b></label><br>
    <input type="text" placeholder="Enter Tags" name="tags" id="listingTags" />

    <br><label id="label" for="height"><b>Height</b></label><br>
    <input type="text" placeholder="Enter Height" name="height" id="listingHeight" />

    <br><label id="label" for="width"><b>Width</b></label><br>
    <input type="text" placeholder="Enter Width" name="width" id="listingWidth" />

    <br><label id="label" for="depth"><b>Depth</b></label><br>
    <input type="text" placeholder="Enter Depth" name="depth" id="listingDepth" />

     <br><label id="label" for="description"><b>Description</b></label><br>
    <input type="text" placeholder="Enter Art Description" name="description" id="listingDescription" />

    <button type="submit" class="createListingBtn" >Create Listing</button>
    
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

/* Set a style for the create listing button */
.createListingBtn {
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

</script>