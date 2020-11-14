import Backend from './backend'

async function createListing(listingDto){
    const res = await Backend.createListing(listingDto);
    if (res){
        return res.data;
    }
    return null;
}