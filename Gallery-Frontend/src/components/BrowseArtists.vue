<template>
    <div id="browse-artists">
        <div id="searchBar">
            <input type="text" placeholder="Search..">
        </div>
        <div id="artists">
            <h2>Artists</h2>
            <div id="artists-grid">
                <div v-for="c in profiles" :key="c.id">
                    <ArtistGrid  :id="c.id" />
                </div>
            </div>
        </div>
    </div>
</template>

<style>
    #browse-artists {
        display: flex;
        flex-direction: column;
    }

    #browse-artists {
        margin-left: 1em;
        margin-right: 1em;
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

   

    #artists-grid {
        display: flex;
        flex-wrap: wrap;
        width: 100%;
        max-width: 100%;
    }

    #searchBar {
        width: 100%;
        max-width: 100%;
    }


</style>

<script>
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'
import ArtistGrid from './ArtistGrid'

function getProfiles() {
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
        },
        {
            id: 4,
        },
        {
            id: 5,
        },
        {
            id: 6,
        },
        {
            id: 7,
        },
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
        },
        {
            id: 4,
        },
        {
            id: 5,
        },
        {
            id: 6,
        },
        {
            id: 7,
        }
    ]
}

export default {
    name: 'browseArtists',
    components: {
        ArtistGrid: ArtistGrid
    },
    data: () => {
        return {
            profiles: getProfiles()
        }
    }
}
</script>