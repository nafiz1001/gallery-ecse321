<template>
	  <div id="Profile">
		  <h1>My Profile</h1>
      <a class="edit"> Edit Profile</a>
		<hr>

	<div class="fullname">
		<h3 style="display: inline; font-family: Raleway; font-weight: bold">
          Full name:
        </h3>
        <p style="display: inline; font-size: 22px; font-family: Raleway">
          {{profile.fullname}}
        </p>
		<div class="passline"></div>
		<h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Bio:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
         {{profile.bio}}
      </p>
      <div class="passline"></div>
	  <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Picture:
      </h3>
    <div class="profilePic">
		<img
        src="https://i.ibb.co/w47g34z/vincent-van-gogh-self-portrait-painting-musee-dorsay-via-wikimedia-commons-promojpg.jpg"
        />
    </div>
		 <div class="passline"></div>

     <div >
      
      <h2 class="profileListings"> My Listings </h2>
      <a class="edit"> Edit Listings</a>

      <div class="showListings">
          <img
          src="https://i.ibb.co/jG0C1WJ/The-Starry-Night-1889-by-Vincent-Van-Gogh-Original-from-Wikimedia-Commons-Digitally-enhanced-by-rawp.jpg" @click="$router.push('Listing')"
        />
        <img src="https://i.ibb.co/C7k2rpY/10-lesser-known-van-gogh-masterpieces-900x450-c.jpg"/>
        <img src="https://i.ibb.co/Htf6NBf/190430171751-mona-lisa.jpg" />
        <img src="https://i.ibb.co/FgjqzMW/images.jpg" />



      </div>
   </div>
     

	</div>
	</div>

  
</template>



<style>

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
  display:flex;
  justify-content: flex-start;
  
  padding-left: 650px; 
  text-decoration:underline; 
  font-family: Raleway; 
  
}
</style>

<!--
<script>
import Profile from '../assets/js/profile'

const profile = Profile.getProfile()
if (!profile) {
  alert("You need to sign in before accessing MyProfile");
  window.location = '/#/ArtistSignIn';
}

export default {
  data() {
    return {
      profile: profile
    }
  }
}
</script> 

-->