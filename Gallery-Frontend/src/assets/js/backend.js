import axios from 'axios'
const config = require('../../../config')

const frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
const backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

const AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function getListing(id) {
  return {id: id};
}

function pay(paymentType, deliveryType, transactionNumber, address, email) {
  const listings = localStorage.getItem('cart');
  
  const body = {
      paymentType: paymentType,
      deliveryType: deliveryType,
      transactionNumber: transactionNumber,
      listing: listings,
      identity: {
          email: email
      },
      address: address
  };

  AXIOS.post('/pay', body).then(response => {
      console.log("Payment succeded for successful reason");
  }).catch(error => {
      console.log("Payment failed for failureful reason");
  });
}

export default {
  getListing: getListing,
  pay: pay
}