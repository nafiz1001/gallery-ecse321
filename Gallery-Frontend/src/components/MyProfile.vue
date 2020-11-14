<template>
	  <div id="Account">
		<h1>My Account</h1>
		<hr>
<div class="profilePic1">
      <img src="https://i.ibb.co/D10fJkh/vincent-van-gogh-self-portrait-painting-musee-dorsay-via-wikimedia-commons-promojpg.jpg" />
      </div>
      <a class="edit"> Change Profile Picture</a>
      <div class="passline"></div>
	<div class="username">
        <h3 style="display: inline; font-family: Raleway; font-weight: bold">
          Username:
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
       
    </div>

		        <div class="passline"></div>

      <div >
      
      <h2 class="profileListings" style="padding-left: 525px"> My Listings </h2>
      <router-link to="/CreateListing" class="edit" >Add Listing</router-link>

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
  display:flex;
  flex-direction: row;
  justify-content:flex-start;
  padding-left: 525px;
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
  display:flex;
  justify-content: flex-start;
  
  padding-left: 650px; 
  text-decoration:underline; 
  font-family: Raleway; 
  
}
</style>

