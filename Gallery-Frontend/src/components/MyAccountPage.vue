<template>
	<div id="Account">
		<h1>My Account</h1>
		<hr>

	<div class="username">
        <h3 style="display: inline; font-family: Raleway; font-weight: bold">
          Username:
        </h3>
        <p style="display: inline; font-size: 22px; font-family: Raleway">{{account.username}}
        </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Password:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway" v-show="!editable.password">
        *********
      </p>
      <input type="password" v-model="account.password" placeholder="****" v-show="editable.password"> 
      <a class="edit" v-on:click="editable.password = true"> Edit Password</a>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Date Joined:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        {{account.dateJoined}}
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Date of Birth:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
		  {{account.dateOfBirth}}
		  </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Account Number:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        {{account.accountNumber}}
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Revenu ($):
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        $ -1000
      </p>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Address:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        {{account.address}}
      </p>
	  <a class="edit"> Edit Address</a>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Payment Type:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway" v-show="!editable.paymentType">{{account.paymentType}}</p>
      <input type="text" v-model="account.paymentType" placeholder="CREDIT" v-show="editable.paymentType"> 
      <a class="edit" v-on:click="editable.paymentType = true"> Edit Payment Type</a>
      <div class="passline"></div>
      <button v-on:click="editAccount(account).then(res => account = res)">Edit Account</button>
    </div>
	</div>
</template>

<style>

.edit {
  display: inline;
  font-family: Raleway;
  font-size: 20px;

  color: #000000;
  margin: 8px;
  border: 2px solid #000000;
  padding: 3px;
}
.edit:hover {
  color: #000000;
}
</style>

<script>
import Account from '../assets/js/account'

const account = Account.getAccount()
if (!account) {
  alert("You need to sign in before accessing MyAccount");
  window.location = '/#/CustomerSignIn';
}

async function editAccount(account) {
  const editedAccount = await Account.editAccount(account);
  console.log(editedAccount);
  if (editedAccount) {
    confirm("Account successfully edited");
    const oldAccount = Account.getAccount();
    console.log(oldAccount);
    const response =  await Account.loadAccountFromDatabase(oldAccount.username, editedAccount.password).catch(error => console.error(error));
    console.log(response)
    return response
  }

  return account;
}

export default {
  data() {
    return {
      account: account,
      editable: {
        password: false,
        dateOfBirth: false,
        address: false,
        paymentType: false
      }
    }
  },
  methods: {
    editAccount: editAccount
  }
}
</script>