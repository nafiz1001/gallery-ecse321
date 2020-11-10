<template>
    <div id="confirmation">
        <h2>Your transaction has been accepted</h2>
        <div>Your confirmation number: {{confirmationnumber}}</div>
    </div>
</template>

<script>
export default {
    props: ['confirmationnumber']
}
</script>