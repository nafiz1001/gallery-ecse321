import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Checkout from '@/components/Checkout'
import HomePage from '@/components/HomePage'
import ArtistSignUp from '@/components/ArtistSignUp'
import ArtistSignIn from '@/components/ArtistSignIn'
import CustomerSignUp from '@/components/CustomerSignUp'
import CustomerSignIn from '@/components/CustomerSignIn'
import OneListing from '@/components/OneListing'
import OneProfile from '@/components/OneProfile'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/checkout',
      name: 'Checkout',
      component: Checkout
    },
    {
      path: '/Listing',
      name: 'OneListing',
      component: OneListing
    },
    {
      path: '/Profile',
      name: 'OneProfile',
      component: OneProfile
    },
    {
      path: '/HomePage',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/ArtistSignUp',
      name: 'ArtistSignUp',
      component: ArtistSignUp
    },
    {
      path: '/ArtistSignIn',
      name: 'ArtistSignIn',
      component: ArtistSignIn
    },
    {
      path: '/CustomerSignUp',
      name: 'CustomerSignUp',
      component: CustomerSignUp
    },
    {
      path: '/CustomerSignIn',
      name: 'CustomerSignIn',
      component: CustomerSignIn
    }
  ]
})
