<template>
    <div id="browseListings">
        <div id="filterHeader">
            <h2>Filters</h2>
            <div id="filters">
                <form id="filterRadios">
                    <input type="radio" id="noFilters" name="filterType" value="noFilters" v-model="filterId" checked="checked"> 
                    <label for="noFilters">No Filters</label>
                    <br>
                    <input type="radio" id="pickUpOnly" name="filterType" value="pickUpOnly" v-model="filterId">
                    <label for="pickUpOnly">Pick Up Only</label>
                    <br>
                    <input type="radio" id="deliveryOnly" name="filterType" value="deliveryOnly" v-model="filterId">
                    <label for="deliveryOnly">Delivery Only</label>      
                    <br>
                    <input type="radio" id="ascendingPrice" name="filterType" value="ascendingPrice" v-model="filterId">
                    <label for="ascendingPrice">Ascending Price</label>   
                    <br>
                    <input type="radio" id="descendingPrice" name="filterType" value="descendingPrice" v-model="filterId">
                    <label for="descendingPrice">Descending Price</label>  
                    <br>
                    <input type="radio" id="priceRange" name="filterType" value="priceRange" v-model="filterId">
                    <label for="priceRange">Price Range</label>  
                    <br>
                    <form id="ranges">
                        <label for="min">Min:</label>
                        <input type="number" placeholder="0.00" id="min" v-model="min">
                        <br>
                        <label for="max">Max:</label>
                        <input type="number" placeholder="1000.00" id="max" v-model="max">
                    </form>
                </form>
            </div>
        </div>
        <div id="listings">
            <h2>Listings</h2>
            <div id="listings-grid">
                <div v-for="c in filteredListings" :key="c.id">
                    <ListingGrid  :id="c.id" />
                </div>
            </div>
        </div>
    </div>
</template>

<style>
    #browseListings {
        display: flex;
        justify-content: space-between;
        margin-left: 1em;
        margin-right: 1em;
    }

    #filterButton {
        margin-left: 25%;
    }

    #filters {
        width: 100%;
        max-width: 100%;
        margin-left: 50px;
        width: 225px;
        height: 350px;
        border: 2px solid black;
    }

    #filterHeader {
        margin-left: 50px;
    }

    #ranges {
        margin-left: 10%;
        width: 100px;
    }

    #filterRadios {
        margin-left: 10px;
        margin-top: 10px;
        text-align: left;
        align-content: left;
    }

    #filters > div {
        margin-bottom: 1em;
    }

    #browseListings h3 {
        font-size: 1.5em;
    }

    #listings-grid {
        display: flex;
        flex-wrap: wrap;
        width: 33.33%;
        max-width: 100%;
    }

    #listings > div {
        display: flex;
        width: 100px;
    }


    #browseListings {
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

    #listings {
        width: 70%;
        max-width: 70%;
        margin-left: auto;
        margin-right: auto;
    }

</style>

<script>
import Listing from '../assets/js/listing'
import ListingGrid from './ListingGrid'

Listing.loadListingsFromDatabase()
console.log(localStorage.getItem('listings'));
const listings = JSON.parse(localStorage.getItem('listings'));

function sort(filterId, listings, min, max) {
    if (filterId == 'noFilters') {
        return listings;
    } else if (filterId == 'pickUpOnly') {
        return listings.filter(l => l.canDeliver == true)
    } else if (filterId == 'deliveryOnly') {
        return listings.filter(l => l.canPickUp == true)
    } else if (filterId == 'ascendingPrice') {
       return listings.sort((a, b) => (a.price > b.price ? 1 : -1))
    } else if (filterId == 'descendingPrice') {
        return listings.sort((a, b) => (a.price < b.price ? 1 : -1))
    } else if (filterId == 'priceRange') {
        return listings.filter(l => l.price >= min && l.price <= max)
    } else {
        return listings
    }
}



export default {
    name: 'browseListings',
    components: {
        ListingGrid: ListingGrid
    },
    data () {
        return {
            min: 0,
            max: 100,
            filterIds: {
                noFilters: true,
                pickupOnly: false,
                deliveryOnly: false,
                ascendingPrice: false,
                descendingPrice: false,
                priceRange: false
            },
            filterId: '',
            listings: listings
        };
    },
    computed: {
        filteredListings: function() {
            console.log(this.filterId);
            return sort(this.filterId, this.listings, this.min, this.max);
        }
   }
}
</script>