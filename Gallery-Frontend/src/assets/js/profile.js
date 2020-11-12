import Backend from './backend'

async function createProfile(profileDto, password) {
    console.log(profileDto);
    console.log(password);
    return await Backend.createProfile(profileDto, password);
}

export default {
    createProfile: createProfile
}