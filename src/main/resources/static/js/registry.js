import {initializePostCode, getPostCode} from './util/daumpostcode.js';

function submit () {

}

function onLoadPostCode () {
  initializePostCode();
  const button = document.getElementById("postcodebutton");

  const data = {};
  const zip = document.querySelector("#zip").value = data.zonecode;
  const address = document.querySelector("#address1").value =  data.address;
  const array = [
    {
      propertyName : 'zonecode',
      dom:zip
    },
    {
      propertyName : 'address',
      dom:address
    },
  ]

  button.addEventListener("click", () => getPostCode(data, array));

}