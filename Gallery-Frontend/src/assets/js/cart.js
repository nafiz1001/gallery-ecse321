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
    const maxQuantity = Backend.getListing(id).data.quantity;

    if (currQuantity + quantity > maxQuantity) {
        return false;
    } else {
        setQuantity(id, getQuantity(id) + Number(quantity))
        return true;
    }
  }
  
function getQuantity(id) {
    let cart = getCart(id);
    let quantity = 0;

    if (id in cart)
        quantity = cart[id].quantity;

    return Number(quantity);
}

function setQuantity(id, quantity) {
    let cart = getCart(id);
  
    //console.log(quantity);
    cart[id] = {
        id: Number(id),
        quantity: Number(quantity)
    };
    console.log(cart)
  
    localStorage.setItem('cart', JSON.stringify(cart));
  }

export default {
    getCart: getCart,
    addToCart: addToCart,
    getQuantity: getQuantity,
    setQuantity: setQuantity
}