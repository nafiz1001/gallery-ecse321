import Backend from './backend'

async function createListing(listingDto){
    const res = await Backend.createListing(listingDto, "A123456");
    if (res){
        return res.data;
    }
    return null;
}

async function getListings() {
    return await Backend.getListings().catch(console.error);
}

async function getListing(id) {
    const response = await getListings().catch(console.error);
    if (response) {
        const matchedListings = result.filter(l => l.id == id);
        if (matchedListings) {
            return matchedListings[0];
        } else {
            return null;
        }
    }

    return null;
}

export default {
    createListing: createListing,
    getListing: getListing,
    getListings: getListings
}