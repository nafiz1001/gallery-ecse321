import axios from 'axios'
const config = require('../../../config')

const frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
const backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

const AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function getListing(id) {
  return {id: id, quantity: id + 1};
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

function AccountDto(accountHolderType, identity, profile, username,
  password, dateJoined, address, dateOfBirth, revenus,
  accountNumber, paymentType) {
    this.accountHolderType = accountHolderType;
    this.identity = identity;
    this.profile = profile;
    this.username = username;
    this.password = password;
    this.dateJoined = dateJoined;
    this.address = address;
    this.dateOfBirth = dateOfBirth;
    this.revenus = revenus;
    this.accountNumber = accountNumber;
    this.paymentType = paymentType;
}

function createAccount(accountDto, successful, failure) {
  AXIOS.post('/account/create', accountDto).then(response => {
    successful(response);
  }).catch(error => {
    failure(error);
  });
}

export default {
  getListing: getListing,
  createAccount: createAccount,
  AccountDto: AccountDto,
  pay: pay
}