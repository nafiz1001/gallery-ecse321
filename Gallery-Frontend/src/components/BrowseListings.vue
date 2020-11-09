<template>
    <div id="browseListings">
        <div id="filters">
            <h2>Filters</h2>
            <input type="radio" id="noFilters" name="filterType" value="noFilters">
            <label for="noFilters">No Filters</label>
            <br>
            <input type="radio" id="pickUpOnly" name="filterType" value="pickUpOnly">
            <label for="pickUpOnly">Pick Up Only</label>
            <br>
            <input type="radio" id="deliveryOnly" name="filterType" value="deliveryOnly">
            <label for="deliveryOnly">Delivery Only</label>      
            <br>
            <input type="radio" id="ascendingPrice" name="filterType" value="ascendingPrice">
            <label for="ascendingPrice">Ascending Price</label>   
            <br>
            <input type="radio" id="descendingPrice" name="filterType" value="descendingPrice">
            <label for="descendingPrice">Descending Price</label>  
            <br>
            <input type="radio" id="priceRange" name="filterType" value="priceRange" onclick="ShowHideDic(this)">
            <label for="priceRange">Price Range</label>  
            <div id="dvPriceRange" style="display: none">
                <input type="text" id="min" placeholder="0.00">
                <label for="min">Min:</label>
                <input type="text" id="max" placeholder="100000.00">
                <label for="max">Max:</label>
            </div>
        </div>
        <div id="listings">
            <h2>Listings</h2>
             <div v-for="c in listings" :key="c.id">
                <ListingRow  :id="c.id" />
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

    #filters {
        width: 300px;
        height: 250px;
        border: 2px solid black;
    }

    #filters {
        width: 50%;
        max-width: 50%;
    }

    #filters > div {
        margin-bottom: 1em;
    }

    #browseListings h3 {
        font-size: 1.5em;
    }

    #listings {
        display: flex;
        flex-direction: column;
        width: 100%;
        max-width: 100%;
    }

    #listings > div {
        display: flex;
    }

    #filters > div {
        text-align: left;
        align-content: left;
    }

</style>

<script>
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'
import ListingRow from './ListingRow'

function getListings() {
    return [
        {
            id: 0,
        },
        {
            id: 1,
        }
    ]
}

function ShowHideDiv(radioFilter) {
    var dvPriceRange = document.getElementById("dvPriceRange");
    dvPriceRange.style.display = radioFilter.value == "priceRange" ? "block" : "none";
}

export default {
    name: 'browseListings',
    components: {
        ListingRow: ListingRow
    },
    data: () => {
        return {
            listings: getListings()
        };
    }

}
</script>