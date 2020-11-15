<template>
  <div class="container">
    <h1>Create Listing</h1>
    <p>Please fill in this form to create a listing.</p>
    <hr>
    <label id="label" for="picture"><b>Picture Link</b></label><br>
    <input id="fileInput" placeholder="Enter Picture URL" type="text" v-model="art.picture" />
    <br>
    <br><label id="label" for="title"><b>Title</b></label><br>
    <input type="text" placeholder="Enter Title" name="title" id="listingTitle"  v-model="art.title"/><br>

    <br><label id="label" for="price"><b>Price</b></label><br>
    <input type="number" placeholder="Enter Price" name="price" id="listingPrice" v-model="listing.price" />

    <br><label id="label" for="quantity"><b>Quantity</b></label><br>
    <input type="number" min="1"
            max="100"
            step="1" name="quantity" id="listingQuantity" placeholder="Enter Quantity" v-model="listing.quantity"/>

    <br><br><label for="checkbox"><b>Available For Shipping</b></label>
     <input type="checkbox" id="checkbox" v-model="listing.canShipping"/>
    

      <br><br><label for="checkbox"><b>Available For In-Store Pickup</b></label>
    <input type="checkbox" id="checkbox" v-model="listing.canPickup"/>
  

    <br><label id="label" for="tags"><b>Tags</b></label><br>
    <input type="text" placeholder="Enter Tags" name="tags" id="listingTags" v-model="listing.tags" />

    <br><label id="label" for="height"><b>Height</b></label><br>
    <input type="text" placeholder="Enter Height" name="height" id="listingHeight" v-model="art.height" />

    <br><label id="label" for="width"><b>Width</b></label><br>
    <input type="text" placeholder="Enter Width" name="width" id="listingWidth" v-model="art.width"/>

    <br><label id="label" for="depth"><b>Depth</b></label><br>
    <input type="text" placeholder="Enter Depth" name="depth" id="listingDepth" v-model="art.depth" />

     <br><label id="label" for="description"><b>Description</b></label><br>
    <input type="text" placeholder="Enter Art Description" name="description" id="listingDescription" v-model="art.description" />

    <button type="submit" class="createListingBtn" v-on:click="createListing(art,listing)">Create Listing </button>
    
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

import Backend from '../assets/js/backend'
import Art from '../assets/js/art'
import Listing from '../assets/js/listing'
import Dtos from '../assets/js/dtos'
import Profile from '../assets/js/profile'
import Account from '../assets/js/account'

  function artFailure(error) {
    alert("Your art creation failed");
    console.error(error);
  }
  function listingFailure(error) {
    alert("Your listing creation failed");
    console.error(error);
  }

  async function createListing(art, listing) {
    const profile = Profile.getProfile();
    if (profile) {
      async function createListing2(art){

      const listingDto = new Dtos.ListingDto(
          listing.price, Date.now(), listing.canPickup, listing.canShipping, listing.quantity, art, profile, listing.tags
          );
          await Listing.createListing(listingDto).then(response => confirm(`Successfully created listing! ID is ${response.id}`)).catch(listingFailure);
      }

      const artDto = new Dtos.ArtDto(
          art.title, art.description, art.height, art.width, art.picture, null, Date.now(), profile, art.depth,null
      );

      const createdArt = await Art.createArt(artDto).then(
          createListing2
      ).catch(artFailure);
      } else {
        alert("You need to sign in as artist before creating new listings");
        window.location = '/#/ArtistSignIn';
      }
    }

  export default{
    
    
    data(){
          return{
              art:{
                  picture:'',
                  title:'',
                  height:'',
                  width:'',
                  depth:'',
                  description:''
              },

              listing:{
                  price:0,
                  quantity:0,
                  canShipping: false,
                  canPickup:false,
                  tags:''
              }
          }
      },

      methods: {
          createListing: createListing
      }
    }
</script>