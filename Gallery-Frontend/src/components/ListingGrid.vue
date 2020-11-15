<template>
        <div class="listing-grid1">
            <a :href="`/#/Listing/${listing.id}`"> <img class="listing-grid1-img" :src="listing.art.image"> </a>
            <div class="listing-grid1-info">
                <div class="passline"></div>
                <a :href="`/#/Listing/${listing.id}`" class="viewL"><span style="font-weight: bold">{{listing.art.name}}</span> </a>
                <div> By <a :href="`/#/Profile/${listing.publisher.id}`" class="viewA"><span style="font-weight: bold">{{listing.publisher.fullname}}</span></a> </div>
                <div class="passline"></div>
                <div> {{listing.price}}$</div>
            </div>
        </div>
</template>

<style>
a:active, a:visited {
    border:0 none
}

.listing-grid1 {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-bottom: 1em;
    padding: 0.5em 0.5em 0.5em 0.5em;
}

.listing-grid1-img {
    height: 15em;
    margin-right: 1em;
    border: 5px solid #00ff80;
}

.listing-grid1-img:hover {
    border: 5px solid #FFFFFF;
}

.listing-grid1-info {
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