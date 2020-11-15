<template>
        <div class="artist">
            <a :href="`/#/Profile/${profile.id}`" class="viewA"><img class="artist-img" :src="profile.picture"></a>
            <a :href="`/#/Profile/${profile.id}`" class="viewA"><span style="font-weight: bold">{{profile.fullname}}</span> </a>
        </div>
</template>

<style>
div:active, div:visited {
    border:0 none
}

.artist {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-bottom: 1em;
    padding: 0.5em 0.5em 0.5em 0.5em;
}
.artist-img {
    height: 15em;
    margin-right: 1em;
    border: 5px solid #00ff80;
}

.artist-img:hover {
  border: 5px solid #FFFFFF;
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

export default {
    name: 'artistgrid',
    props: ['id'],
    data() {
        return {
          profile: Profile.getOneProfile(this.id)
        }
  }
}
</script>