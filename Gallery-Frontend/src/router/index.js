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
import MyAccountPage from '@/components/MyAccountPage'
import BrowseListings from '@/components/BrowseListings'
import MyProfile from '@/components/MyProfile'
import AboutUs from '@/components/AboutUs'
import BrowseArtists from '@/components/BrowseArtists'
import CreateListing from '@/components/CreateListing'
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
      path: '/Listing/:id',
      name: 'OneListing',
      component: OneListing,
      props: true
    },
    {
      path: '/Profile/:id',
      name: 'OneProfile',
      component: OneProfile,
      props: true
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
    },
    {
      path: '/MyAccountPage',
      name: 'MyAccount',
      component: MyAccountPage
    },
    {
      path: '/browseListings',
      name: 'BrowseListings',
      component: BrowseListings
    },
    {
      path: '/MyProfile',
      name: 'MyProfile',
      component: MyProfile
    },
    {
      path: '/AboutUs',
      name: 'AboutUs',
      component: AboutUs
    },
    {
      path: '/CreateListing',
      name: 'CreateListing',
      component: CreateListing
    },
    {
      path: '/Browse-artists',
      name: '/Browse-artists',
      component: BrowseArtists
    }


  ]
})
