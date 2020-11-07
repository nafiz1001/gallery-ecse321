<template>
    <div id="checkout">
        <div id="checkout-forms">
            <h2>Checkout Information</h2>
            <div id="email">
                <h3>Email</h3>
                <span>Email (optional): </span>
                <input v-model="email" placeholder="">
            </div>
            <div id="payment">
                <h3>Payment</h3>
                <div>
                    <input type="radio" id="credit" value="credit" v-model="paymentType">
                    <label for="credit">Credit Card</label>
                    <br>
                    <input type="radio" id="paypal" value="paypal" v-model="paymentType">
                    <label for="paypal">PayPal</label>
                </div>
                <div v-if="paymentType === 'credit'">
                    <div>
                        <span>Card Number: </span>
                        <input type="text" placeholder="012345">
                    </div>
                    <div>
                        <span>Pin Code: </span>
                        <input type="text" placeholder="123">
                    </div>
                </div>
                <div v-if="paymentType === 'paypal'">
                    <div>
                        <span>Paypal ID: </span>
                        <input type="text" placeholder="012345">
                    </div>
                    <div>
                        <span>Password: </span>
                        <input type="text" placeholder="0123">
                    </div>
                </div>
            </div>
            <div id="delivery">
                <h3>Delivery</h3>
                <input type="radio" id="pickup" value="pickup" v-model="deliveryType">
                <label for="pickup">Pick Up</label>
                <br>
                <input type="radio" id="shipping" value="shipping" v-model="deliveryType">
                <label for="shipping">Shipping</label>
            </div>
            <div id="address">
                    <h3>Address</h3>
                    <div>
                        <span>Street Number: </span>
                        <input v-model="address.streetNumber" placeholder="9999">
                    </div>
                    <div>
                        <span>Street: </span>
                        <input v-model="address.street" placeholder="Atwater Avenue">
                    </div>
                    <div>
                        <span>City: </span>
                        <input v-model="address.city" placeholder="Montreal">
                    </div>
                    <div>
                        <span>Province: </span>
                        <input v-model="address.province" placeholder="Quebec">
                    </div>
                    <div>
                        <span>Postal Code: </span>
                        <input v-model="address.postalCode" placeholder="H0H 0H0">
                    </div>
                </div>
            <button v-on:click="pay(paymentType, deliveryType, transactionNumber, address, email)">Buy Now!</button>
        </div>
        <div id="cart">
            <h2>Cart</h2>
            <ListingRow v-for="l in getListings()" :key="l.id" v-bind:id="l.id" />
        </div>
    </div>
</template>

<style>
    #checkout {
        display: flex;
        justify-content: space-between;
        margin-left: 1em;
        margin-right: 1em;
    }

    #checkout h3 {
        font-size: 1.5em;
    }

    #cart {
        display: flex;
        flex-direction: column;
    }

    #checkout-forms > div {
        margin-bottom: 2em;
        text-align: left;
    }
</style>

<script>
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'
import ListingRow from './ListingRow'

export default {
    name: 'checkout',
    components: {
        ListingRow: ListingRow
    },
    data: () => {
        return {
            paymentType: 'credit',
            deliveryType: 'pickup',
            address: {
                streetNumber: '',
                street: '',
                city: '',
                province: '',
                postalCode: ''
            },
            email: ''
        }
    },
    methods: {
        pay: (paymentType, deliveryType, transactionNumber, address, email) => {
            Backend.pay(paymentType, deliveryType, transactionNumber, address, email);
        },
        getListings: () => {
            return [
                {
                    id: 0
                },
                {
                    id: 1
                }
            ];
        }
    }
}
</script>