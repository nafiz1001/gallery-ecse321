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

async function getListings() {
    const result = await Backend.getListings().catch(console.error);
    if (result)
        return result.data;
    else
        return null;
}

async function getListing(id) {
    const response = await getListings().catch(console.error);
    if (response) {
        const matchedListings = response.filter(l => l.id == id);
        if (matchedListings) {
            return matchedListings[0];
        } else {
            return null;
        }
    }

    return null;
}

async function loadListingsFromDatabase() {
    const listings = await getListings().catch(console.error);
    localStorage.setItem('listings', JSON.stringify(listings));
    return listings;
}

export default {
    createListing: createListing,
    getListing: getListing,
    getListings: getListings,
    loadListingsFromDatabase: loadListingsFromDatabase
}