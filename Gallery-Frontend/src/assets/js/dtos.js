function AddressDto(streetNumber, street, city, province, postalCode) {
    this.streetNumber = streetNumber;
    this.street = street;
    this.city = city;
    this.province = province;
    this.postalCode = postalCode;
}

function ArtDto( name, description,  height, width,  image, listing,
    date,  owner, depth, author) {
this.name = name;
this.description = description;
this.height = height;
this.width = width;
this.image = image;
this.listing = listing;
this.date = date;
this.owner = owner;
this.depth = depth;
this.author = author;
}

function ListingDto(price,  datePublished,  canPickUp,  canDeliver,  quantity,  art,
     publisher,  tags) {

this.price = price;
this.datePublished = datePublished;
this.canPickUp = canPickUp;
this.canDeliver = canDeliver;
this.quantity = quantity;
this.art = art;
this.publisher = publisher;
this.tags = tags;
}

function PaymentDto(transactionNumber, paymentType, deliveryType, address, listing, identity) {
this.transactionNumber = transactionNumber;
this.paymentType = paymentType;
this.deliveryType = deliveryType;
this.address = address;
this.listing = listing;
this.identity = identity;
}


export default {
    AddressDto: AddressDto,
    ArtDto : ArtDto,
    ListingDto: ListingDto,
    PaymentDto: PaymentDto
}

