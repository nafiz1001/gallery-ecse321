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
  border: 2px solid rgb(255,255,255) ;
  padding: 3px;
  font-family: Didot; 
  font-size: 20px;
  color: rgb(255,255,255);
  margin:8px;
  
}
.navtext:hover{
  color: rgb(255,255,255);
}

.navbar{
  background-color: rgb(71, 85, 71);
  margin: 0px;
  padding: 0px;
  position:sticky;
  
  
  
}
.header{
  background-color:rgb(71, 85, 71) ;
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
