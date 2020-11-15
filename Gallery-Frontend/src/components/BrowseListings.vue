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
                        <input type="text" placeholder="0.00" id="min" v-model="min">
                        <br>
                        <label for="max">Max:</label>
                        <input type="text" placeholder="1000.00" id="max" v-model="max">
                    </form>
                </form>
            </div>
        </div>
        <div id="listings">
            <h2>Listings</h2>
            <div id="listings-grid">
                <div v-for="c in listingsToDisplay" :key="c.id">
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
        width: 200px;
        height: 425px;
        border: 2px solid black;
    }

    #filterHeader {
        margin-left: 50px;
    }

    #ranges {
        margin-left: 10%;
        width: 200px;
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
        width: 100%;
        max-width: 100%;
    }

    #listings > div {
        display: flex;
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

function getListings() {
    return [
        {
            title: 'Mr. Fishy',
            image: 'https://pbs.twimg.com/profile_images/2996390845/d5f215b28cfce7c235080c37f54b05fb_400x400.jpeg',
            price: 69,
            author: 'Barack Obama',
            canDeliver: true,
            canPickup: false,
            id: 0,
        },
        {
            title: 'Mr. Fishy',
            image: 'https://pbs.twimg.com/profile_images/2996390845/d5f215b28cfce7c235080c37f54b05fb_400x400.jpeg',
            price: 68,
            author: 'Barack Obama',
            canDeliver: true,
            canPickup: false,
            id: 1,
        },
        {
            title: 'Mr. Fishy',
            image: 'https://pbs.twimg.com/profile_images/2996390845/d5f215b28cfce7c235080c37f54b05fb_400x400.jpeg',
            price: 67,
            author: 'Barack Obama',
            canDeliver: true,
            canPickup: false,
            id: 2,
        },
        {
            title: 'Mr. Fishy',
            image: 'https://pbs.twimg.com/profile_images/2996390845/d5f215b28cfce7c235080c37f54b05fb_400x400.jpeg',
            price: 66,
            author: 'Barack Obama',
            canDeliver: true,
            canPickup: false,
            id: 3,
        }
    ]

}

function sort(filterId) {
    const listings = getListings();
    if (filterId == 'noFilters') {
        filteredListings = listings
    } else if (filterId == 'pickUpOnly') {
        return listings.filter(l => l.canDeliver == true)
    } else if (filterId == 'deliveryOnly') {
        return listings.filter(l => l.canPickUp == true)
    } else if (filterId == 'ascendingPrice') {
       listings.sort((a, b) => (a.price > b.price ? 1 : -1))
    } else if (filterId == 'descendingPrice') {
        listings.sort((a, b) => (a.price < b.price ? 1 : -1))
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
            min: '',
            max: '',
            filterId: 'noFilter',
            listingsToDisplay: sort(filterId)
        };
    },
    methods: {
        // sort: sort(filterId),
        // setFilterId(id) {
        //     this.filterId = id;
        // },
        // setMin(min) {
        //     this.min = min
        // },
        // setMax(max) {
        //     this.max = max
        // }
   }
}
</script>