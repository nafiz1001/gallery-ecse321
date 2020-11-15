<template>
        <div class="artist">
            <img class="artist-img" :src="profile.image">
            <a class="viewA"><span style="font-weight: bold">{{profile.name}}</span> </a>
        </div>
</template>

<style>
.artist {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-bottom: 1em;
    padding: 0.5em 0.5em 0.5em 0.5em;
}
.artist > img {
    height: 15em;
    margin-right: 1em;
}

.viewA {
    display: flex;
    font-family: Raleway;
    color: #2b2d42;
}
.viewA:hover {
  color: #2b2d42;
}
</style>

<script>
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'
import Profile from '../assets/js/profile'

function getProfile(id) {
    Profile.loadProfilesFromDatabase();
    const profileString = Profile.getProfiles();
    return JSON.parse(profileString).find(p => p.id == id)
}

export default {
    name: 'artistgrid',
    props: ['id'],
    data() {
        return {
          profile: getProfile(this.id)
        }
  }
}
</script>