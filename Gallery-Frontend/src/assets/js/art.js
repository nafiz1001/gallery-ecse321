import Backend from './backend'


async function createArt(artDto){
    const res = await Backend.createArt(artDto, "A123456");
    if (res) {
        return res.data;
    }
    return null;
}

export default {
    createArt: createArt
}

