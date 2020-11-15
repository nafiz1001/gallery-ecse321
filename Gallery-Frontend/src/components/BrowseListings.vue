<template>
    <div id="browseListings">
        <div id="filterHeader">
            <h2>Filters</h2>
            <div id="filters">
                <form id="filterRadios">
                    <input type="radio" id="noFilters" name="filterType" value="noFilters" checked = "checked" v-on:click="sort(this.id)"> 
                    <label for="noFilters">No Filters</label>
                    <br>
                    <input type="radio" id="pickUpOnly" name="filterType" value="pickUpOnly" v-on:click="sort(this.id)">
                    <label for="pickUpOnly">Pick Up Only</label>
                    <br>
                    <input type="radio" id="deliveryOnly" name="filterType" value="deliveryOnly" v-on:click="sort(this.id)">
                    <label for="deliveryOnly">Delivery Only</label>      
                    <br>
                    <input type="radio" id="ascendingPrice" name="filterType" value="ascendingPrice" v-on:click="sort(this.id)">
                    <label for="ascendingPrice">Ascending Price</label>   
                    <br>
                    <input type="radio" id="descendingPrice" name="filterType" value="descendingPrice" v-on:click="sort(this.id)">
                    <label for="descendingPrice">Descending Price</label>  
                    <br>
                    <input type="radio" id="priceRange" name="filterType" value="priceRange" v-on:click="sort(this.id)">
                    <label for="priceRange">Price Range</label>  
                    <br>
                    <form id="ranges">
                        <label for="min">Min:</label>
                        <input type="text" placeholder="0.00" id="min" v-on:input="min">
                        <br>
                        <label for="max">Max:</label>
                        <input type="text" placeholder="1000.00" id="max" v-on:input="max">
                    </form>
                     <form id="filterButton">
                        <input type="button" id="setFiler" value="Set Filter">
                    </form>
                </form>
            </div>
        </div>
        <div id="listings">
            <h2>Listings</h2>
            <div id="listings-grid">
                <div v-for="c in listings" :key="c.id">
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
            id: 0,
        },
        {
            id: 1,
        },
        {
            id: 2,
        },
        {
            id: 3,
        }
    ]
}

function sort(id) {
    
    if (id == 'noFilter') {
        filteredListings = listings;
    } else if (id == 'pickUpOnly') {
        return listings.filter(l => l.canDeliver == true)
    } else if (id == 'deliveryOnly') {
        return listings.filter(l => l.canPickUp == true)
    } else if (id == 'ascendingPrice') {
       listings.sort((a, b) => (a.price > b.price ? 1 : -1))
    } else if (id == 'descendingPrice') {
        listings.sort((a, b) => (a.price < b.price ? 1 : -1))
    } else if (id == 'priceRange') {
        return listings.filter(l => l.price >= min && l.price <= max);
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
            listings: getListings(),
            filteredListings: []
        };
    },
    methods: {
        sort: sort
   }
}
</script>