<template>
  <div id="ProfilePage">
    <a class="leftTextTop>">All Profiles</a>
    <div class="profilePic">
      <img src="https://i.ibb.co/D10fJkh/vincent-van-gogh-self-portrait-painting-musee-dorsay-via-wikimedia-commons-promojpg.jpg" />
      <h2 class="artistName" > "Artist Name" </h2>
      </div>
      <div class="passline"></div>
      <div class="bio">
      
      <h3 style=" font-family: Raleway"> "Bio" </h3>
    
    </div>
<div class="passline"></div>
<hr class="solid">
<div class="passline"></div>
      <div >
      
      <h2 class="profileListings"> Listings </h2>

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