<template>
    <div id="browse-artists">
        <b-form-input
          @input="search_text()"
          v-model="search"
          type="text"
          placeholder="Search by Artist"
          id="searchBar"
        ></b-form-input>
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
            image: 'https://media.timeout.com/images/105277448/750/422/image.jpg',
            name: 'Alejandra The Great',
            id: 0,
        },
        {
            image: 'https://media.timeout.com/images/105277448/750/422/image.jpg',
            name: 'Alejandra The Great1',
            id: 1,
        },
       
    ]
}

export default {
    name: 'browseArtists',
    components: {
        ArtistGrid: ArtistGrid
    },
    data: () => {
        return {
            profiles: getProfiles(),
            search: ''
        }
    },
    methods: {
        search_text(search) {
            var profiles = getProfiles();
            if (search == '') {
                return profiles
            } else {
                return profiles.filter(p => p.name == search)
            }
        }
    }
}
</script>