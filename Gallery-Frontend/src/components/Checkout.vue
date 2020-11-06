<template>
    <div id="checkout">
        <div>
            <span>Email (optional): </span>
            <input v-model="email" placeholder="">
        </div>
        <div>
            <span>Payment Type: </span>
            <select v-model="paymentType">
                <option disabled value="">Please select one</option>
                <option>Paypal</option>
                <option>Credit Card</option>
            </select>
        </div>
        <div v-if="paymentType === 'Credit Card'">
        <div>
            <span>Card Number: </span>
            <input v-model="transactionNumber" placeholder="012345">
        </div>
        <div>
            <span>Pin Code: </span>
            <input type="text" placeholder="123">
        </div>
        </div>
        <div v-if="paymentType === 'Paypal'">
        <div>
            <span>Paypal Number: </span>
            <input type="text" placeholder="012345">
        </div>
        <div>
            <span>Password: </span>
            <input type="text" placeholder="0123">
        </div>
        </div>
        <div>
            <span>Delivery Type: </span>
            <select v-model="deliveryType">
                <option disabled value="">Please select one</option>
                <option>Pick Up</option>
                <option>Shipping</option>
            </select>
        </div>
        <div v-if="deliveryType === 'Shipping'">
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
</template>

<script>
import Backend from '../assets/js/backend'
import DTOs from '../assets/js/dtos'

export default {
    data: () => {
        return {
            paymentType: '',
            deliveryType: '',
            transactionNumber: '',
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
            const listings = localStorage.getItem('cart')
            const body = 
            Backend.AXIOS.post('/pay', {
                paymentType: paymentType,
                deliveryType: deliveryType,
                transactionNumber: transactionNumber,
                listing: listings,
                identity: {
                    email: email
                },
                address: address
            }).then(response => {
                console.log("Payment succeded for successful reason");
            }).catch(error => {
                console.log("Payment failed for failureful reason");
            })
        }
    }
}
</script>