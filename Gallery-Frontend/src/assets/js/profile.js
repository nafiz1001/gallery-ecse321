import Backend from './backend'
import Account from './account'

async function createProfile(profileDto, password) {
    console.log(profileDto);
    console.log(password);

    const result = await Backend.createProfile(profileDto, password);
    if (result) {
        return result.data;
    }

    return null;
}

function getProfile() {
    const profileString = localStorage.getItem('profile');
    if (profileString) {
        return JSON.parse(profileString);
    } else {
        return null;
    }
}

async function loadProfileFromDatabase(username, password) {
    async function loadProfileFromDatabase(id) {
        return await Backend.getProfile(id).then(response => {
            console.log(response);
            localStorage.setItem('profile', JSON.stringify(response.data));
        }).catch(console.error);
    }

    async function loadAccountSuccessful(account) {
        if (account.profile) {
            console.log(account);
            await loadProfileFromDatabase(account.profile[0].id).catch(console.error);
        } else {
            throw new Error(`Artist username ${username} not found`);
        }
    }
    await Account.loadAccountFromDatabase(username, password).then(loadAccountSuccessful).catch(console.error);
}

export default {
    createProfile: createProfile,
    loadProfileFromDatabase: loadProfileFromDatabase,
    getProfile: getProfile
}