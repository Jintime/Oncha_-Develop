"use strict";

function applyFilters() {
    const selectedData = {};

    document.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
        const key = checkbox.parentElement.previousElementSibling.getAttribute('data-key');
        const value = checkbox.name;
        if (checkbox.checked && key) {
            selectedData[key] = value;
        }
    });
    console.log(selectedData);
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
}

document.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
    checkbox.addEventListener('change', applyFilters);
});