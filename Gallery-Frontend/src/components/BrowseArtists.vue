<template>
    <div id="browse-artists">
        <form class="form-horizontal" onSubmit="return false;">
            <input type="text" id="searchBar" placeholder="Search By Name" v-model="search">
        </form>
        <div id="artists">
            <h2>Artists</h2>
            <div id="artists-grid">
                <div v-for="c in filteredProfiles" :key="c.id">
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
        width: 95;
        max-width: 100%;
    }


</style>

<script>
import Profile from '../assets/js/profile'
import ArtistGrid from './ArtistGrid'

Profile.loadProfilesFromDatabase();
const profiles = Profile.getProfiles();

function search_text(profiles, search) {
    var profileMatch = [];
    if (profiles == null) {
        return profileMatch;
    }
    for (let i= 0; i < profiles.length; i++) {
        if (profiles[i].fullname.toLowerCase().includes(search.toLowerCase())) {
            profileMatch.push(profiles[i])
        }
    }
    return profileMatch;
}

export default {
    name: 'browseArtists',
    components: {
        ArtistGrid: ArtistGrid
    },
    data () {
        return {
            profiles: profiles,
            search: ''
        };
    },
    computed: {
        filteredProfiles: function() {
            return search_text(this.profiles, this.search);
        } 
    }
}
</script>