import Backend from './backend'

async function pay(paymentDto) {
    return await Backend.pay(paymentDto).catch(console.error);
}

export default {
    pay: pay
}