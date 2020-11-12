import Backend from './backend'

async function createAccount(accountDto) {
    const res = await Backend.createAccount(accountDto);
    if (res) {
        return res.data;
    }

    return null;
}

function getAccount() {
    const accountString = localStorage.getItem('account');
    if (accountString) {
        return JSON.parse(accountString);
    } else {
        return null;
    }
}

async function loadAccountFromDatabase(username, password) {
    const response = await Backend.getAccount(username, password).catch(error => console.error(error));
    if (response) {
        console.log(response.data);
        localStorage.setItem('account', JSON.stringify(response.data));
    }

    return response.data;
}

async function editAccount(account) {
    const localAccount = getAccount();
    if (localAccount) {
        const accountDto = new Backend.AccountDto(
            localAccount.accountHolderType, 
            localAccount.identity, 
            localAccount.profile, 
            localAccount.username,
            account.password,
            localAccount.dateJoined,
            account.address,
            account.dateOfBirth,
            localAccount.revenus,
            localAccount.accountNumber,
            account.paymentType
            );
        
        const response = await Backend.editAccount(accountDto, localAccount.password).catch(error => console.error(error));
        return response.data;
    } else {
        return null;
    }
}

export default {
    getAccount: getAccount,
    loadAccountFromDatabase: loadAccountFromDatabase,
    editAccount: editAccount,
    createAccount: createAccount
}