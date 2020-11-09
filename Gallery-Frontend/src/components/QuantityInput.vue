<template>
    <input type="number" min="1" :max=maxquantity step="1" :value="value" required>
</template>

<script>
function getQuantity(id) {
        return id + 1;
}

export default {
    props: ['id', 'value'],
    data() {
        return {
            maxquantity: getQuantity(this.id)
        }
    }
}
</script>