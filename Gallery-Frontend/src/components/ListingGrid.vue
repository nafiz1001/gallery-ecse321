<template>
        <div class="listing-grid">
            <img class="listing-grid-img" :src="listing.image">
            <div class="listing-grid-info">
                <div class="passline"></div>
                <a class="viewL"><span style="font-weight: bold">{{listing.title}}</span> </a>
                <div> By <a class="viewA">{{listing.author}}</a> </div>
                <div class="passline"></div>
                <div> {{listing.price}}$</div>
            </div>
        </div>
</template>

<style>
.listing-grid {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-bottom: 1em;
    padding: 0.5em 0.5em 0.5em 0.5em;
}

.listing-grid > img {
    height: 15em;
    margin-right: 1em;
}

.listing-grid > .listing-grid-info {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    text-align: left;
}

.viewL {
    display: flex;
    font-family: Raleway;
    color: #2b2d42;
}
.viewL:hover {
  color: #2b2d42;
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
    id: id,
    }
}

export default {
  name: 'listinggrid',
  props: ['id'],
  data() {
      return {
          listing: getListing(this.id)
      }
  }
}
</script>