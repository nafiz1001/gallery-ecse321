<template>
	  <div id="Account">
		<h1>My Account</h1>
		<hr>
<div class="profilePic1">
      <img :src="picture" />
      </div><br>
      <a class="edit"> Change Profile Picture</a>
      <div class="passline"></div>
	<div class="username">
        <h3 style="display: inline; font-family: Raleway; font-weight: bold">
          Username: {{username}}
        </h3>
        <p style="display: inline; font-size: 22px; font-family: Raleway">
        </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Password:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway" >
        
      </p>
     <!-- <input type="password" placeholder="****" > -->
      <a class="edit"> Edit Password</a>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Date Joined:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Date of Birth:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
		  
		  </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Profile ID:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Revenu ($):
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        $ -1000
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Address:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        
      </p>
	  <a class="edit"> Edit Address</a>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Payment Type:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway" ></p>
      <a class="edit" > Edit Payment Type</a>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Bio:
      </h3>
      <a class="edit" > Edit Bio</a>
      <div class="passline"></div>
      <button >Edit Profile</button>
      <div class="passline"></div>
       <h2 class="profileListings" style="display: inline; font-family: Raleway; font-weight: bold"> My Listings </h2><br><br>
      <router-link to="/CreateListing" class="edit" >Add Listing</router-link>

      <div class="showListings" id="profileListings">
        <div v-for="l in listings" :key="l.id">
          <a :href="`/Listing/${l.id}`"><img :src="l.art.image"></a>
        </div>
      </div>

		        

      
      
      


      
   </div>
     

	</div>


  
</template>



<style>
.passline {
    margin: 3em;
}

.profilePic1 img{
  width: 150px;
  height: 150px;
  padding: 4px;
  border: 3px solid #FFFFFF;
}
.profilePic1{
  display:inline;
  
  padding-top: 20px;
}

.edit {
  display: inline;
  font-family: Raleway;
  font-size: 20px;

  color: #000000;
  margin: 8px;
  border: 2px solid #000000;
  padding: 3px;
}
.edit:hover {
  color: #000000;
}

.profilePic img{
  width: 150px;
  height: 150px;
  padding: 4px;
  border: 3px solid #FFFFFF;
}

.showListings{
  display:flex;
  flex-wrap: wrap;
}

.showListings img{
  width: 250px;
  height: 250px;
  margin: 20px;
  border: 5px solid #00ff80;
  
}
.showListings img:hover{
  border: 5px solid #FFFFFF;
}

.profileListings{
  display:inline;
  text-decoration:underline; 
  font-family: Raleway; 
  
}
</style>

<script>
import Profile from '../assets/js/profile'
import Accoutn from '../assets/js/account'
import Listing from '../assets/js/listing'

Profile.loadProfilesFromDatabase();
Listing.loadListingsFromDatabase();

const profile = Profile.getProfile();
let listings = [];
if (profile) {
  listings = Listing.getListings(profile.id);
  listings = listings.filter(l => l.publisher.id == profile.id);
} else {
  alert("You need to sign in as artist before viewing MyProfile");
}

export default {
  data() {
    return {
      username: profile.accountDto.username,
      picture: profile.picture,
      listings: listings
    }
  },
}
</script>

