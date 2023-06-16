export function initializePostCode() {
  const postCodeScript = document.createElement('script');
  postCodeScript.src = '//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js';
  document.head.appendChild(postCodeScript);
}

/**
 * PostCode Object를 받아 해당 object에 property setting
 * doms = [{propertyName, dom}] 형태로 구성. propertyName에 해당하는 값을 dom의 value에 setting
 */
export function getPostCode(postcodeData, domInfos) {
  new daum.Postcode({
    oncomplete: function(data) {
      postcodeData.zonecode = data.zonecode;
      postcodeData.address = data.address;
      postcodeData.roadAddress = data.roadAddress;
      postcodeData.jibunAddress = data.jibunAddress;
      postcodeData.buildingName = data.buildingName;
      postcodeData.sido = data.sido;
      postcodeData.sigungu = data.sigungu;
      postcodeData.roadname = data.roadname;

      for (domInfoItem of domInfos) {
        const key = domInfoItem.propertyName;
        domInfoItem.dom.value = data[key];
      }
    }
  }).open();
}