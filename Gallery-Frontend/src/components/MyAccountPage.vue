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
		<a class="edit"> Edit Usersame</a>
      <div class="passline"></div>
      <h3 style="display: inline; font-family: Raleway; font-weight: bold">
        Password:
      </h3>
      <p style="display: inline; font-size: 22px; font-family: Raleway">
        *********
      </p>
      <a class="edit"> Edit Password</a>
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
      <p style="display: inline; font-size: 22px; font-family: Raleway">{{account.paymentType}}</p>
      <div class="passline"></div>
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

export default {
  data() {
    return {
      account: account
    }
  }
}
</script>