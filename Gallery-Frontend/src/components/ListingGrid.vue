<template>
        <div class="listing-grid">
            <a :href="`/#/Listing/${listing.id}`"> <img class="listing-grid-img" :src="listing.art.image"> </a>
            <div class="listing-grid-info">
                <div class="passline"></div>
                <a :href="`/#/Listing/${listing.id}`" class="viewL"><span style="font-weight: bold">{{listing.art.name}}</span> </a>
                <div> By <a :href="`/#/Profile/${listing.publisher.id}`" class="viewA">{{listing.publisher.fullname}}</a> </div>
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
import listing from '../assets/js/listing'

function getListing(id) {
    listing.loadListingsFromDatabase();
    const listingString = localStorage.getItem('listings');
    console.log(listingString);

    console.log(JSON.parse(listingString));
    return JSON.parse(listingString).find(l => l.id == id);
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