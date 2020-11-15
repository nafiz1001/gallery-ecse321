import axios from 'axios'
import { profile } from '../../../config/dev.env'
import dtos from './dtos'
const config = require('../../../config')

const frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
const backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

const AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


function getListings() {
  return AXIOS.get(`/listings`).catch(console.error);
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

async function createAccount(accountDto) {
  return await AXIOS.post('/account/create', accountDto).catch(error => console.error(error));
}

async function editAccount(accountDto, password) {
  return await AXIOS.post('/account/edit', accountDto, {
    params: {
      'password': password
    }
  }).catch(error => console.error(error))
}

async function getAccount(username, password) {
  return await AXIOS.get('/account', { 
    params: {
      'username': username,
      'password': password
    }
  }).catch(error => console.error(error))
}

async function createArt(artDto, password){
  return await AXIOS.post('/art/create', artDto, {
    params:{
      'password': password
    }
  }).catch(error => console.error(error))
}

async function createListing(listingDto, password){
  return await AXIOS.post('/listing/create', listingDto,{
    params:{
      'password':password
    }
  }).catch(error => console.error(error))
}

function ProfileDto(bio, picture, listingDtos, accountDto, fullname, arts) {
  this.bio = bio;
  this.picture = picture;
  this.listingDtos = listingDtos;
  this.accountDto = accountDto;
  this.fullname = fullname;
  this.arts = arts;
}

async function createProfile(profileDto, password) {
  return await AXIOS.post('/profile/create', profileDto, {
    params: {
      'password': password
    }
  }).catch(error => console.error(error));
}

async function getProfile(id) {
  return await AXIOS.get(`/profile/${id}`);
}

async function pay(paymentDto) {
  return await AXIOS.post('/pay', paymentDto).catch(error => console.error(error));
}

export default {
  getListings: getListings,
  createAccount: createAccount,
  getAccount: getAccount,
  editAccount: editAccount,
  AccountDto: AccountDto,
  ProfileDto: ProfileDto,
  createProfile: createProfile,
  getProfile: getProfile,
  createArt: createArt,
  createListing: createListing,
  pay: pay
}