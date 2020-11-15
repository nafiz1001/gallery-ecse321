import { resolve } from '../../../config/dev.env';
import Backend from './backend'

function getCart() {
    let cartString = localStorage.getItem('cart');
    let cart = {};
    if (cartString) {
      cart = JSON.parse(cartString);
    }
  
    return cart;
  }
  
  function addToCart(id, quantity) {
    const currQuantity = getQuantity(id);
    return Backend.getListing(id).then(response => {
      const maxQuantity = response.data.quantity;
      if (currQuantity + quantity > maxQuantity) {
        throw new Error(`You've exceeded the max quantity of the listing with id ${id}`);
      } else {
          setQuantity(id, getQuantity(id) + Number(quantity))
          return id;
      }
    });
  }
  
function getQuantity(id) {
    let cart = getCart();
    let quantity = 0;

    if (id in cart)
        quantity = cart[id].quantity;

    return Number(quantity);
}

function setQuantity(id, quantity) {
    let cart = getCart();
  
    //console.log(quantity);
    cart[id] = {
        id: Number(id),
        quantity: Number(quantity)
    };
    console.log(cart)

    if (quantity === 0)
      delete cart[id];

    localStorage.setItem('cart', JSON.stringify(cart));
  }

export default {
    getCart: getCart,
    addToCart: addToCart,
    getQuantity: getQuantity,
    setQuantity: setQuantity
}