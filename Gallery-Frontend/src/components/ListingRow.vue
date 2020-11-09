<template>
    <div class="listing-row">
        <img class="listing-row-img" :src="listing.image">
        <div class="listing-row-info">
            <div><span style="font-weight: bold">{{listing.title}}</span> By {{listing.author}}</div>
            <div>Pick Up {{listing.canPickUp ? "&#x2705;" : "&#x274C;"}}</div>
            <div>Shipping {{listing.canDeliver ? "&#x2705;" : "&#x274C;"}}</div>
            <div>{{listing.price}}$</div>
            <div>Max Quantity: {{listing.quantity}}</div>
        </div>
    </div>
</template>

<style>
.listing-row {
    display: flex;
    justify-content: space-between;
    margin-bottom: 1em;
    padding: 0.5em 0.5em 0.5em 0.5em;
}
.listing-row > img {
    height: 8em;
    margin-right: 1em;
}

.listing-row > .listing-row-info {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    text-align: left;
}
</style>

<script>
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'

function getListing(id) {
    return {
    title: 'Mr. Fishy',
    image: 'https://pbs.twimg.com/profile_images/2996390845/d5f215b28cfce7c235080c37f54b05fb_400x400.jpeg',
    price: 69,
    author: 'Barack Obama',
    canPickUp: id === 0,
    canDeliver: id === 1,
    id: id,
    quantity: id + 1
    }
}

export default {
  name: 'listingrow',
  props: ['id'],
  data() {
      return {
          listing: getListing(this.id)
      }
  }
}
</script>