import Backend from './backend'

function getAccount() {
    const accountString = localStorage.getItem('account');
    if (accountString) {
        return JSON.parse(accountString);
    } else {
        return null;
    }
}

async function loadAccountFromDatabase(username, password) {
    const response = await Backend.getAccount(username, password);
    if (response) {
        console.log(response.data);
        localStorage.setItem('account', JSON.stringify(response.data));
    }

    return response.data;
}

export default {
    getAccount: getAccount,
    loadAccountFromDatabase: loadAccountFromDatabase,
}