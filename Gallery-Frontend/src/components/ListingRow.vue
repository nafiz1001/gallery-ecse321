<template>
    <div class="listing-row">
        <img class="listing-row-img" :src="image">
        <div class="listing-row-info">
            <div>
                <span style="font-weight: bold">{{title}}</span> By {{author}}</div>
            <div>{{price}}$</div>
        </div>
    </div>
</template>

<style>
.listing-row {
    display: flex;
    justify-content: space-around;
    border: 1px solid black;
    margin-bottom: 1em;
    padding: 0.5em 0.5em 0.5em 0.5em;
}
.listing-row > img {
    height: 4em;
    margin-right: 1em;
}

.listing-row > .listing-row-info {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    text-align: left;
}
</style>

<script>
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'

export default {
  name: 'listingrow',
  props: ['id'],
  data: () => {
      return {
          title: 'Mr. Fishy',
          image: 'https://pbs.twimg.com/profile_images/2996390845/d5f215b28cfce7c235080c37f54b05fb_400x400.jpeg',
          price: 69,
          author: 'Barack Obama'
      }
  },
  methods: {
      getListing: (id) => {
          return {
              id: 0,
              title: 'Mr. Fishy',
              image: 'https://pbs.twimg.com/profile_images/2996390845/d5f215b28cfce7c235080c37f54b05fb_400x400.jpeg',
              price: 69
          }
      }
  }
}
</script>