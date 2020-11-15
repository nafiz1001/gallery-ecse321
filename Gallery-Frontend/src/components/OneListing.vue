<template>
  <div id="Listing">
    <div class="split left1">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <router-link to="/BrowseListings">All Listings</router-link>
      <div class="centered1">
        <img
          :src="getListing(id).art.image"
        />
        <div class="passline"></div>
        <h3 style="font-family: Raleway; color: #2b2d42">Select Quantity:</h3>
        <form>
          <input
            style="font-family: Raleway; color: #2b2d42; font-size: 20px"
            type="number"
            min="1"
            max="100"
            step="1"
            v-model="quantity"
            required
          />
          <input
            style="font-family: Raleway; color: #2b2d42; font-size: 20px"
            type="submit"
            v-on:click="error.addToCart = !addToCart(0, quantity); if (error.addToCart) window.alert('You\'ve reached max quantity for this listing');"
            value="Add to Cart!"
          />
        </form>
      </div>
    </div>

    <div class="split right1">
      <div class="passBigLine"></div>
      <h1
        style="
          text-decoration: underline;
          font-family: Raleway;
          font-weight: bold;
        "
      >
        Listing Specifications
      </h1>
      <div class="title">
        <h3 style="display: inline; font-family: Raleway; font-weight: bold">
          Title:
        </h3>
        <p style="display: inline; font-size: 22px; font-family: Raleway">
          {{getListing(id).art.name}}
        </p>
      </div>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Author:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
       {{getListing(id).publisher.fullname}}
      </p>
      <a class="viewP"> View Profile</a>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Price:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        {{getListing(id).price}}
      </p>

<div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Delivery Options:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">Shipping: {{listing.canDeliver ? "&#9746;" : "&#9745;"}} </p>
      
      <p style="display: inline; font-size: 22px; font-family: Raleway">In-store pickup: {{listing.canPickup ? "&#9746;" : "&#9745;"}} </p>
  

      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Quantity Available:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        {{getListing(id).quantity}}
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Description:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        {{getListing(id).art.description}}
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Dimensions (cm):
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        {{getListing(id).art.width}} x {{getListing(id).art.height}} x {{getListing(id).art.depth}}
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Date Published:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        {{getListing(id).datePulished}}
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Tags:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
         {{getListing(id).tags}}
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Listing ID:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        00000
      </p>
    </div>
  </div>
</template>

<style>
.viewP {
  display: inline;
  font-family: Raleway;
  font-size: 20px;

  color: #000000;
  margin: 8px;
  border: 2px solid #000000;
  padding: 3px;
}
.viewP:hover {
  color: #000000;
}
.title {
  display: inline;
}
.passBigLine {
  margin: 7em;
}
.passline {
  margin: 1em;
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
.split {
  height: 100%;
  width: 50%;
  position: fixed;
  z-index: 1;
  top: 10;
  overflow-x: hidden;
  padding-top: 20px;
}

/* Control the left side */
.left1 {
  left: 0;
  background-color: #ffffff;
}

/* Control the right side */
.right1 {
  right: 0;
  background-color: #00ff80;
  text-align: left;
  padding-left: 10px;
}

/* If you want the content centered horizontally and vertically */
.centered1 {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}

/* Style the image inside the centered container, if needed */
.centered1 img {
  padding: 4px;
  border: 3px solid #2b2d42;
  width: 550px;
}
</style>

<script>
import Cart from '../assets/js/cart'
import Backend from '../assets/js/backend'
import Listing from '../assets/js/listing'

async function getListings() {
  const listings = await Listing.getListings();
  localStorage.setItem('listings', JSON.stringify(listings));
};

getListings();

function getListing(id) {
  const listingString = localStorage.getItem('listings');
  while (!listingString);
  if(listingString) {
    const listings = JSON.parse(listingString);
    return listings.find(l => l.id == id);
  }
}

export default {
  props: ['id'],
  data() {
    return {
      quantity: 0,
      error: {
        addToCart: false
      },
      window: window,
      listing: {
        title: '',
        image: '',
        name: '',
        price: '',
        quantity:'',
        tags: ''

      }
    }
  },
  methods: {
    addToCart: Cart.addToCart,
    getListing: getListing
  }
};
</script>