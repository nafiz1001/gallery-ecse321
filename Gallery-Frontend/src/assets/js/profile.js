import Backend from './backend'

async function createProfile(profileDto, password) {
    console.log(profileDto);
    console.log(password);
    return await Backend.createProfile(profileDto, password);
}

function getProfile() {
    const profileString = localStorage.getItem('profile');
    if (profileString) {
        return JSON.parse(profileString);
    } else {
        return null;
    }
}

async function loadProfileFromDatabase(id) {
    const response = await Backend.getProfile(id).catch(error => console.error(error));
    if (response) {
        console.log(response.data);
        localStorage.setItem('profile', JSON.stringify(response.data));
    }

    return response.data;
}

export default {
    createProfile: createProfile,
    loadProfileFromDatabase: loadProfileFromDatabase,
    getProfile: getProfile
}