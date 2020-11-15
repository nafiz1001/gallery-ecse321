<template>
  <div id="ProfilePage">
    <a class="leftTextTop>">All Profiles</a>
    <div class="profilePic">
      <img :src="getProfile().picture" />
      <h2 class="artistName" > {{getProfile().fullname}} </h2>
      </div>
      <div class="passline"></div>
      <div class="bio">
      
      <h3 style=" font-family: Raleway"> {{getProfile().bio}} </h3>
    
    </div>
<div class="passline"></div>
<hr class="solid">
<div class="passline"></div>
      <div >
      
      <h2 class="profileListings"> Listings </h2>

      <div class="showListings">
         <div v-for="l in listings" :key="l.id">
          <a :href="`/Listing/${l.id}`"><img :src="l.art.image"></a>
        </div>


      </div>
    
    </div>

  </div>

</template>
      
<style>

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


hr.solid {
  border-top: 3px solid #FFFFFF;
  border-radius: 5px;
  margin-left: 30px;
  margin-right: 30px;
}
.artistName{
  
  display: flex;
  flex-direction: column;
  justify-content: center;
   font-family: Raleway; 
   font-weight: bold
}
.bio{
  display:flex;
  justify-content: flex-start;
  padding-left: 20px;
}
.profileListings{
  display:flex;
  justify-content: flex-start;
  
  padding-left: 20px; 
  text-decoration:underline; 
  font-family: Raleway; 
  font-weight: bold
}
.passline {
  margin: 1em;
}

#ProfilePage {
  width: 100%;
  position: fixed;
  z-index: 1;
  height: 100%;
  top: 10;
  overflow-x: hidden;
  padding-top: 20px;
  background-color: #8d99ae;
  border-left: 40px solid #2b2d42;
  border-right: 40px solid #2b2d42;
  border-top: 40px solid #2b2d42;
  border-bottom: 40px solid #2b2d42;
}
.profilePic img{
  width: 150px;
  height: 150px;
  padding: 4px;
  border: 3px solid #FFFFFF;
}
.profilePic{
  display:flex;
  flex-direction: row;
  justify-content:flex-start;
  padding-left: 20px;
  padding-top: 20px;
}
 a {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  border: 2px solid #2b2d42;
  padding: 3px;
  padding-right: 0px;
  font-family: Raleway;
  font-size: 16px;
  font-weight: bold;
  color: #2b2d42;
  margin: 8px;
}
a:hover {
  color: #2b2d42;
}
</style>

<script>

import Profile from '../assets/js/profile'
import Account from '../assets/js/account'
import Listing from '../assets/js/listing'

Profile.loadProfilesFromDatabase();

const profile = Profile.getProfile();
let listings = [];
if (profile) {
  listings = Listing.getListings(profile.id);
  listings = listings.filter(l => l.publisher.id == profile.id);
} else {
  alert("You need to sign in as artist before viewing MyProfile");
}

export default {
  props: ['id'],
  data(){
    return{
     listings: listings,
     profile:profile
    }
  },
  methods: {
    getProfile: function () {
      console.log(this.id);
      return Profile.getOneProfile(this.id);
    }
  }
}
</script>