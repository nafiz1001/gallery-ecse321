<template>
  <div id="AboutUs">
    
    <div class="galleryPic">
      <img src="https://i.ibb.co/S6YX75T/123770658-356838252085217-4310923901942430812-n.png" />
      <h2 class="galleryName" > La Maison d'Art </h2>
      </div>
      <div class="passline"></div>
      <div class="bio">
      
      <h3 style=" font-family: Raleway"> "Bio" </h3>
    
    </div>
<div class="passline"></div>
<hr class="solid">
<div class="passline"></div>
  <div class="contactUs">
  <h2 class="contactTitle">Contact Us </h2>
  <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Phone Number:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        (123) 123-1234
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Email:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        contactus@lma.ca
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Address: 
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        123 Art Road, QC, Canada, 1A1 A1A
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Opening Hours:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        Open every day from 8AM to 8PM
      </p>
  </div>
     <hr class="solid">
      <div >
      
      <h2 class="galleryListings"> Our Listings </h2>

      <div class="showGListings">
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

.showGListings{
  display:flex;
  flex-wrap: wrap;
}

.showGListings img{
  width: 250px;
  height: 250px;
  margin: 20px;
  border: 5px solid #00ff80;
  
}
.showGListings img:hover{
  border: 5px solid #FFFFFF;
}


hr.solid {
  border-top: 3px solid #FFFFFF;
  border-radius: 5px;
  margin-left: 30px;
  margin-right: 30px;
}
.galleryName{
  
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
.galleryListings{
  display:flex;
  justify-content: flex-start;
  
  padding-left: 20px; 
  text-decoration:underline; 
  font-family: Raleway; 
  font-weight: bold
}

.contactTitle{
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

#AboutUs {
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
.galleryPic img{
  width: 150px;
  height: 150px;
  padding: 4px;
  
}
.galleryPic{
  display:flex;
  flex-direction: row;
  justify-content:center;
  padding-left: 20px;
  padding-top: 20px;
  padding-bottom: 20px;
  padding-right: 20px;
  margin-left: 380px;
  margin-right: 380px;

  border: 3px solid #FFFFFF;

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