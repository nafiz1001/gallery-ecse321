import Backend from './backend'


async function createArt(artDto){
    const res = await Backend.createArt(artDto);
    if (res) {
        return res.data;
    }
    return null;
}

