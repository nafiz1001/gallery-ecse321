import Backend from './backend'
import Profile from './profile'
import Account from './account'

async function createListing(listingDto){
    const account = Account.getAccount();
    if (account) {
        const res = await Backend.createListing(listingDto, account.password);
        if (res){
            return res.data;
        }
    }
    return null;
}

function getListings() {
    const result = localStorage.getItem('listings');
    if (result)
        return JSON.parse(result);
    else
        return [];
}

function getListing(id) {
    const result = getListings();
    if (result) {
        const matchedListings = result.filter(l => l.id == id);
        if (matchedListings) {
            return matchedListings[0];
        } else {
            return null;
        }
    }
}

async function loadListingsFromDatabase() {
    const listings = await Backend.getListings().catch(console.error);
    localStorage.setItem('listings', JSON.stringify(listings.data));
    return listings;
}

export default {
    createListing: createListing,
    getListing: getListing,
    getListings: getListings,
    loadListingsFromDatabase: loadListingsFromDatabase
}