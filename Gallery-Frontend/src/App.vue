<template>
  <div id="app">
    <nav class="navbar">
      <!-- <router-link to="/">Home Page!</router-link>  -->
      <h1 style="font-family: Didot; color:	rgb(255,255,255); font-weight:bold"> OnlineGallery </h1>
    <router-link to="/HomePage">Homepage</router-link>
    <router-link to="/checkout">Checkout</router-link>
    </nav>
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
  margin-top: 60px;
}

.navbar{
  background-color: rgb(71, 85, 71)
}
</style>
