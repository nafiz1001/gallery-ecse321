<template>
    <div id="checkout">
        <div id="checkout-forms">
            <h2>Checkout Information</h2>
            <div id="email">
                <h3>Email</h3>
                <div v-show="error.email" class="error">{{error.email}}</div>
                <div>Email (optional): </div>
                <input v-model="email" placeholder="">
            </div>
            <div id="payment">
                <h3>Payment</h3>
                <div>
                    <input type="radio" id="credit" value="credit" v-model="payment.type">
                    <label for="credit">Credit Card</label>
                    <br>
                    <input type="radio" id="paypal" value="paypal" v-model="payment.type">
                    <label for="paypal">PayPal</label>
                </div>
                <div>
                    <div v-show="error.payment.id" class="error">{{error.payment.id}}</div>
                    <div>{{payment.type === 'credit' ? 'Card Number' : 'Paypal ID'}}: </div>
                    <input type="text" v-model="payment.id" placeholder="012345">
                    <div v-show="error.payment.pass" class="error">{{error.payment.pass}}</div>
                    <div>{{payment.type === 'credit' ? 'Pin' : 'Password'}}: </div>
                    <input type="password" v-model="payment.pass" placeholder="*****">
                </div>
            </div>
            <div id="delivery">
                <h3>Delivery</h3>
                <div v-show="error.deliveryType" class="error">{{error.deliveryType}}</div>
                <input type="radio" id="pickup" value="pickup" v-model="deliveryType">
                <label for="pickup">Pick Up</label>
                <br>
                <input type="radio" id="shipping" value="shipping" v-model="deliveryType">
                <label for="shipping">Shipping</label>
            </div>
            <div id="address">
                    <h3>Address</h3>
                    <div v-show="error.address" class="error">{{error.address}}</div>
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
            <button v-on:click="pay(email, payment, deliveryType, address).then(e => error = e)">Buy Now!</button>
        </div>
        <div id="cart">
            <h2>Cart</h2>
            <div v-for="c in cart" :key="c.id" v-show="c.quantity > 0" >
                <ListingRow  :id="c.id" />
                <input type="number" min="0" :max="maxQuantity(c.id)" step="1" v-model="c.quantity" v-on:input="setQuantity(c.id, c.quantity)" required>
            </div>
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

    #cart > div {
        display: flex;
    }

    #checkout-forms > div {
        text-align: left;
        align-content: left;
    }

    .error {
        color: red;
        font-style: italic;
        word-wrap: break-word;
    }
</style>

<script>
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'
import ListingRow from './ListingRow'
import Confirmation from './Confirmation'
import Cart from '../assets/js/cart'
import Listing from '../assets/js/listing'
import Payment from '../assets/js/payment'
import listing from '../assets/js/listing'

async function pay(email, payment, deliveryType, address) {
    let paymentFormValid = true;
    const error = {
        email: '',
        deliveryType: '',
        address: '',
        payment: {id: '', pass: ''}
    };

    async function startPaying(listingDtos) {
        const cart = Cart.getCart();
        listingDtos = listingDtos.filter(l => l.id in cart)
        if (listingDtos) {
            for (const listing of listingDtos) {
                if (cart[listing.id].quantity > listing.quantity) {
                    alert(`You can't buy more than ${listing.quantity} of ${listing.art.name} by ${listing.publisher.fullname}`);
                    return error;
                }
            }

            function validateEmail(email) {
                const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return re.test(String(email).toLowerCase());
            }

            function validateDeliveryType(deliveryType) {
                const result = listingDtos.filter(l => deliveryType === 'pickup' ? l.canPickUp : l.canDeliver);
                return result.length > 0;
            }

            function validateAddress(address, deliveryType) {
                let result = listingDtos.filter(l => l.canDeliver && !l.canPickUp);
                if (deliveryType === 'shipping' && result) {
                    const re = /[a-zA-Z][0-9][a-zA-Z]\s[0-9][a-zA-Z][0-9]/;
                    return address.postalCode && re.test(address.postalCode.toUpperCase().split(' ').join(''));
                }

                result = listingDtos.filter(l => l.canPickUp);
                return deliveryType === 'pickup' && result;
            }

            if (email && !validateEmail(email)) {
                error.email = "Email format invalid";
                paymentFormValid = false;
            }

            if (!validateDeliveryType(deliveryType)) {
                error.deliveryType = "The delivery type is invalid";
                paymentFormValid = false;
            }


            if (!validateAddress(address, deliveryType)) {
                error.address  = "The address must be specified correctly. At least the postal code must be specified";
                paymentFormValid = false;
            }

            if (!payment.id) {
                error.payment.id = `${payment.type === 'paypal' ? 'Paypal id' : 'Credit card number'} must be specified`;
                paymentFormValid = false;
            }

            if (!payment.pass) {
                error.payment.pass = `${payment.type === 'paypal' ? 'Paypal password' : 'Credit card pin'} must be specified`;
                paymentFormValid = false;
            }

            if (paymentFormValid) {
                const paymentType = payment.type === "paypal" ? 0 : 1;
                const deliveryType2 = deliveryType === "pickup" ? 0 : 1;
                address = deliveryType === "pickup" ? null : address;
                const paymentDto = new DTOs.PaymentDto(payment.id + payment.pass, paymentType, deliveryType2, address, listingDtos, null);
                const response = await Payment.pay(paymentDto).then(dto => {
                    console.log(dto);
                    window.confirm(`Transaction successfully processed. Your confirmation number ${dto.data.confirmationNumber}`)
                }).catch(error => window.alert(`Failed to process your payment ${error}`));
            } else {
                alert("Correct your payment form");
                console.log(error);
            }
        } else {
            alert("Could not find any listing from your cart");
        }

        return error;
    }

    return await Listing.getListings().then(startPaying).catch(console.error);
}

export default {
    name: 'checkout',
    components: {
        ListingRow: ListingRow,
        Confirmation: Confirmation
    },
    data() {
        return {
            payment: {type: 'credit', id: '', pass: ''},
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
                address: '',
                payment: {
                    id: '',
                    pass: ''
                }
            },
            cart: {1: {id: 1, quantity: 1}},
            window: window
        };
    },
    methods: {
        pay: pay,
        maxQuantity (id) {
            return 1;
        },
        setQuantity: Cart.setQuantity
    }
}
</script>