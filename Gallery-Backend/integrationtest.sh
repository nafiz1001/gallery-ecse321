#/bin/sh

curl --location --request GET 'http://localhost:8080/gallery/view'

curl --location --request POST 'http://localhost:8080/identity/create?email=hi@gmail.com' \
--data-raw ''

curl --location --request POST 'http://localhost:8080/account/create' \
--header 'Content-Type: application/json' \
--data-raw '{
  "accountHolderType": "VISA",
  "identity": {
      "email": "hi@gmail.com"
  },
  "profile": [],
  "username": "hi",
  "password": "A123456",
  "dateJoined": "1971-01-01",
  "address": null,
  "dateOfBirth": "1971-01-01",
  "revenus": [],
  "accountNumber": null,
  "paymentType": "0"
}'

curl --location --request POST 'http://localhost:8080/account/edit?password=A123456' \
--header 'Content-Type: application/json' \
--data-raw '{
  "accountHolderType": "MASTER",
  "identity": {
      "email": "hi@gmail.com"
  },
  "profile": [],
  "username": "hi",
  "password": "A123456",
  "dateJoined": "1971-01-01",
  "address": null,
  "dateOfBirth": "1971-01-01",
  "revenus": [],
  "accountNumber": null,
  "paymentType": "0"
}'

curl --location --request GET 'http://localhost:8080/account?username=hi&password=A123456'

curl --location --request POST 'http://localhost:8080/profile/create?password=A123456' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bio": "i'\''m good",
    "picture": "google it",
    "accountDto": {
        "username": "hi"
    },
    "fullname": "diego",
}'

curl --location --request GET 'http://localhost:8080/profile/hi:diego'

curl --location --request POST 'http://localhost:8080/profile/edit?password=A123456' \
--header 'Content-Type: application/json' \
--data-raw '{
    "bio": "i'\''m bad",
    "picture": "google it",
    "accountDto": {
        "username": "hi"
    },
    "fullname": "diego",
    "id": "hi:diego"
}'

curl --location --request POST 'http://localhost:8080/art/create?password=A123456' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Bob",
    "description": "very stupid",
    "height": 100,
    "width": 100,
    "depth": "100",
    "image": "in your brain",
    "listing": null,
    "date": "2020-10-10",
    "owner": {
        "id": "hi:diego"
    },
    "type": "stupid",
    "author": "Bob"
}'

curl --location --request GET 'http://localhost:8080/arts/hi:diego'

curl --location --request POST 'http://localhost:8080/listing/create?password=A123456' \
--header 'Content-Type: application/json' \
--data-raw '{
    "price": 69,
    "datePublished": "2020-10-10",
    "canPickUp": true,
    "canDeliver": false,
    "quantity": 2,
    "art": {
        "id": 1
    },
    "publisher": {
        "id": "hi:diego"
    },
    "tags": "sos"
}'

curl --location --request POST 'http://localhost:8080/listing/edit?password=A123456' \
--header 'Content-Type: application/json' \
--data-raw '{
    "price": 19,
    "datePublished": "2020-10-10",
    "canPickUp": true,
    "canDeliver": false,
    "quantity": 2,
    "art": {
        "id": 1
    },
    "tags": "sos"
    "id": 1
}'

curl --location --request GET 'http://localhost:8080/listings'

curl --location --request POST 'http://localhost:8080/pay' \
--header 'Content-Type: application/json' \
--data-raw '{
    "transactionNumber": 60,
    "paymentDate": "2020-10-10",
    "paymentType": 0,
    "deliveryType": 0,
    "address": null,
    "listing" : [
        {
            "id": 1
        }
    ]
}'

curl --location --request GET 'http://localhost:8080/revenus?username=hi&password=A123456' \
--data-raw ''
