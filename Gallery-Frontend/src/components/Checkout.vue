<template>
    <div id="checkout">
        <div id="checkout-forms">
            <h2>Checkout Information</h2>
            <div id="email">
                <h3>Email</h3>
                <div id="error-email" class="error-hidden">Insert error here</div>
                <div>Email (optional): </div>
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
                        <div>Card Number: </div>
                        <input type="text" placeholder="012345">
                    </div>
                    <div>
                        <div>Pin Code: </div>
                        <input type="text" placeholder="123">
                    </div>
                </div>
                <div v-if="paymentType === 'paypal'">
                    <div>
                        <div>Paypal ID: </div>
                        <input type="text" placeholder="012345">
                    </div>
                    <div>
                        <div>Password: </div>
                        <input type="text" placeholder="0123">
                    </div>
                </div>
            </div>
            <div id="delivery">
                <h3>Delivery</h3>
                <div id="error-delivery" class="error-hidden">Insert error here</div>
                <input type="radio" id="pickup" value="pickup" v-model="deliveryType">
                <label for="pickup">Pick Up</label>
                <br>
                <input type="radio" id="shipping" value="shipping" v-model="deliveryType">
                <label for="shipping">Shipping</label>
            </div>
            <div id="address">
                    <h3>Address</h3>
                    <div id="error-address" class="error-hidden">Insert error here</div>
                    <div>
                        <div>Street Number: </div>
                        <input v-model="address.streetNumber" placeholder="9999">
                    </div>
                    <div>
                        <div>Street: </div>
                        <input v-model="address.street" placeholder="Atwater Avenue">
                    </div>
                    <div>
                        <div>City: </div>
                        <input v-model="address.city" placeholder="Montreal">
                    </div>
                    <div>
                        <div>Province: </div>
                        <input v-model="address.province" placeholder="Quebec">
                    </div>
                    <div>
                        <div>Postal Code: </div>
                        <input v-model="address.postalCode" placeholder="H0H 0H0">
                    </div>
            </div>
            <button v-on:click="pay(paymentType, deliveryType, address, email)">Buy Now!</button>
        </div>
        <div id="cart">
            <h2>Cart</h2>
            <ListingRow v-for="l in getListings()" :key="l.id" :id="l.id" />
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

    #checkout-forms {
        width: 100%;
        max-width: 100%;
    }


    #checkout-forms > div {
        margin-bottom: 1em;
    }

    #checkout h3 {
        font-size: 1.5em;
    }

    #cart {
        display: flex;
        flex-direction: column;
        width: 100%;
        max-width: 100%;
    }

    #checkout-forms > div {
        text-align: left;
        align-content: left;
    }

    .error-hidden {
        visibility: hidden;
        font-size: 0%;
    }

    .error-shown {
        visibility: visible;
        color: red;
        font-style: italic;
        word-wrap: break-word;
        max-width: 100%;
        font-size: 100%;
    }
</style>

<script>
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'
import ListingRow from './ListingRow'

function getListings() {
    return [
                {
                    id: 0,
                    canPickUp: true,
                    canDeliver: false
                },
                {
                    id: 1,
                    canPickUp: false,
                    canDeliver: true
                }
            ];
}

function validateEmail(email) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function validateDeliveryType(deliveryType) {
    const listings = getListings();
    const result = listings.filter(l => deliveryType === 'pickup' ? l.canPickUp : l.canDeliver);
    return result.length > 0;
}

function validateAddress(address, deliveryType) {
    let result = getListings().filter(l => l.canDeliver);
    if (deliveryType === 'shipping' || result) {
        const re = /([A-Z]\d){3}/;
        return re.test(address.postalCode.toUpperCase().split(' ').join(''));
    }

    result = getListings().filter(l => l.canPickUp);
    return deliveryType === 'pickup' && result;
}

function pay(paymentType, deliveryType, address, email) {
    function makeErrorAppear(id, msg) {
        const el = document.getElementById(id);
        el.setAttribute('class', 'error-shown');
        el.textContent = msg;
    }

    function makeErrorDisappear(id) {
        const el = document.getElementById(id);
        el.setAttribute('class', 'error-hidden');
    }

    let isPaymentValid = true;
    if (email && !validateEmail(email)) {
        makeErrorAppear('error-email', 'Email format invalid');
        isPaymentValid = false;
    } else {
        makeErrorDisappear('error-email');
    }

    if (!validateDeliveryType(deliveryType)) {
        makeErrorAppear('error-delivery', "The delivery type is invalid");
        isPaymentValid = false;
    } else {
        makeErrorDisappear('error-delivery');
    }

    if (!validateAddress(address, deliveryType)) {
        makeErrorAppear('error-address', "The address must be specified correctly. At least the postal code must be specified");
        isPaymentValid = false;
    } else {
        makeErrorDisappear('error-address');
    }
}

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
            email: '',
            error: {
                email: '',
                deliveryType: '',
                address: ''
            }
        }
    },
    methods: {
        pay: pay,
        getListings: getListings
    }
}
</script>