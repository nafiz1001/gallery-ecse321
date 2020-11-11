<template>
  <div id="app">
    <div class="header">
    &nbsp;&nbsp;
    <h1 style="font-family: Didot; color:	rgb(255,255,255); font-weight:bold"> OnlineGallery </h1>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <nav class="navbar">
      <!-- <router-link to="/">Home Page!</router-link>  -->
      
    
    <li><router-link class="navtext" to="/HomePage"> Homepage </router-link></li>
    <li><router-link class="navtext" to="/checkout">Checkout</router-link></li>
    <li><router-link class="navtext" to="/Listing">View Listing</router-link></li>
    <li><router-link class="navtext" to="/Profile">View Profile</router-link></li>
    <li><router-link class="navtext" to="/browseListings">Browse Listings</router-link></li>
    <li><router-link class="navtext" to="/browse-artists">Browse Artists</router-link></li>
    <li><router-link class="navtext" to="/AboutUs">About Us</router-link></li>
    <li><router-link class="navtext" to="/MyAccountPage">MyAccount</router-link></li>
     </nav>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}

.navtext{
  border: 2px solid #2B2D42 ;
  padding: 3px;
  font-family: Raleway Medium Alt1; 
  font-size: 20px;
  color: #2B2D42 ;
  margin:8px;
  
}
.navtext:hover{
  color: #2B2D42 ;
}

.navbar{
  background-color: #8D99AE;
  margin: 0px;
  padding: 0px;
  position:sticky;
  
  
  
}
.header{
  background-color:#8D99AE ;
  display:flex;
  flex-direction: row;
  justify-content:flex-start;
}

/* ul{
  position:sticky;
  display:flex;
  flex-direction: row;
  justify-content:flex-end;
} */
li{
  display:inline;
}

</style>
