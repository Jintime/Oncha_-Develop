import {initializePostCode, getPostCode} from './util/daumpostcode.js';

(function onLoadPostCode () {
  initializePostCode();
  const button = document.getElementById("postcodebutton");
  const data = {};
  const zip = document.querySelector("#zip");
  const address = document.querySelector("#address1");
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
})();
