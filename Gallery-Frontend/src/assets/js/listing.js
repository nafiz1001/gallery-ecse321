import Backend from './backend'

async function createListing(listingDto){
    const res = await Backend.createListing(listingDto, "A123456");
    if (res){
        return res.data;
    }
    return null;
}

export default {
    createListing: createListing
}