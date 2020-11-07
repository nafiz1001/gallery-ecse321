<template>
    <div class="listing-item">
        Listing #{{id}}
    </div>
</template>

<script>
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'

export default {
  name: 'listing-item',
  props: ['id'],
  methods: {
      getListing: (id) => {
          const listing = Backend.getListing(id);
          id = listing.id;
      }
  }
}
</script>