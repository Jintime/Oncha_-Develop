"use strict";
const filterWrap = document.querySelector('.store_filter_wrap');

filterWrap.addEventListener('click', function(event) {
    const target = event.target;
    if (target.classList.contains('filter-checkbox')) {
        updateSelectedFilters();
    }
});
function updateSelectedFilters() {
    const selectedFilters = {
        blending: getCheckboxValues("blended","single_origin","new_type"),
        tea_type: getCheckboxValues("green_tea", "black_tea", "white_tea", "yellow_tea", "blue_tea", "herb_tea"),
        caffeine: getCheckboxValues("isCaffeine", "noCaffeine"),
        category: getCheckboxValues("liquid", "leaf_tea", "tea_bag", "syrup", "powder"),
        flavor: getCheckboxValues("floral", "citrus", "fruity", "smokey", "caramel", "nutty", "mint", "spicy"),
        flavor2: {
            Savory: getRadioValue("Savory"),
            Acidity: getRadioValue("Acidity"),
            Bitter: getRadioValue("Bitter"),
        }
    };

    console.log(selectedFilters);
}

// 해당 체크박스 그룹의 체크된 값을 가져오는 함수
function getCheckboxValues(...groupNames) {
    const values = [];
    groupNames.forEach(groupName => {
        const checkboxes = document.querySelectorAll(`input[name="${groupName}"]:checked`);
        Array.from(checkboxes).forEach(checkbox => {
            values.push(checkbox.value);
        });
    });
    return values;
}

// 해당 라디오 그룹의 선택된 값을 가져오는 함수
function getRadioValue(groupName) {
    const radios = document.querySelectorAll(`input[name="${groupName}"]:checked`);
    return radios.length > 0 ? radios[0].value : null;
}
    // fetch('/filter', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify(selectedData)
    // })
    // .then(response => response.json())
    // .then(data => {
    //     // 서버에서의 응답 처리
    // })
    // .catch(error => {
    //     // 에러 처리
    // });

