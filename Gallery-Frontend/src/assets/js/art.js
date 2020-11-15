import Backend from './backend'
import Account from './account'


async function createArt(artDto) {
    const account = Account.getAccount();
    if(account) {
        const res = await Backend.createArt(artDto, account.password);
        if (res) {
            return res.data;
        }
    }
    return null;
}

export default {
    createArt: createArt
}

