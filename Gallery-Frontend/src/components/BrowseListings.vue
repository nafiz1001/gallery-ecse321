<template>
    <div id="browseListings">
        <div id="filterHeader">
            <h2>Filters</h2>
            <div id="filters">
                <div id="filterRadios">
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

    #filters {
        width: 100%;
        max-width: 100%;
        margin-left: 50px;
        width: 200px;
        height: 210px;
        border: 2px solid black;
    }

    #filterHeader {
        margin-left: 50px;
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
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'
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

function ShowHideDiv(radioFilter) {
    var dvPriceRange = document.getElementById("dvPriceRange");
    dvPriceRange.style.display = radioFilter.value == "priceRange" ? "block" : "none";
}

export default {
    name: 'browseListings',
    components: {
        ListingGrid: ListingGrid
    },
    data: () => {
        return {
            listings: getListings()
        };
    }

}
</script>