<template>
	  <div id="Account">
		<h1>My Profile</h1>
		<hr>
<div class="profilePic1">
      <img :src="picture" />
      </div><br>
      <input type="text" v-model="picture" v-show="editable.picture">
      <a class="edit" v-on:click="editable.picture = true"> Change Profile Picture</a>
      <div class="passline"></div>
	<div class="username">
        <h3 style="display: inline; font-family: Raleway; font-weight: bold">
          Username: {{username}}
        </h3>
        <p style="display: inline; font-size: 22px; font-family: Raleway">
        </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Password: {{password}}
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway" v-show="!editable.password">
        *********
      </p>
     <input type="password" v-model="password" placeholder="****" v-show="editable.password"> 
      <a class="edit" v-on:click="editable.password = true"> Edit Password</a>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Date Joined: {{DateJoined}}
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Date of Birth: {{DateOfBirth}}
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
		  
		  </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Profile ID: {{ID}}
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
        Address: {{address}}
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        
      </p>
      <input type="text" v-model="address" v-show="editable.address">
	  <a class="edit" v-on:click="editable.address = true"> Edit Address</a>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Payment Type: {{paymentType}}
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway" v-show="!editable.paymentType"></p>
      <input type="text" v-model="paymentType" placeholder="CREDIT" v-show="editable.paymentType">
      <a class="edit" v-on:click="editable.paymentType = true"> Edit Payment Type</a>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Bio: {{bio}} 
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway" v-show="!editable.bio"></p>
      <input type="text" v-model="bio" v-show="editable.bio">
      <a class="edit" v-on:click="editable.bio = true"> Edit Bio</a>
      <div class="passline"></div>
      <button v-on:click="editProfile(profile).then(res => profile = res)" >Edit Profile</button>
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
import Account from '../assets/js/account'
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

async function editProfile(profile) {
  const editedProfile = await Profile.editProfile(profile);
  console.log(editedProfile);
  if (editedProfile) {
    confirm("Profile successfully edited");
    const oldProfile = Profile.getProfile();
    console.log(oldProfile);
    const response =  await Profile.loadProfileFromDatabase(oldProfile.accountDto.username, editedProfile.accountDto.password).catch(error => console.error(error));
    console.log(response)
    return response
  }

  return profile;
}

export default {
  data() {
    return {
      username: profile.accountDto.username,
      password: profile.accountDto.password,
      picture: profile.picture,
      listings: listings,
      ID: profile.id,
      DateJoined: profile.accountDto.DateJoined,
      DateOfBirth: profile.DateOfBirth,
      bio: profile.bio,
      paymentType: profile.paymentType,
      address: profile.accountDto.address,
      editable: {
        password: false,
        address: false,
        paymentType: false,
        bio: false,
        picture: false
      }
    }
  },
  methods: {
    editProfile: editProfile
  }
}
</script>

