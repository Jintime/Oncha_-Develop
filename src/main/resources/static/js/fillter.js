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

    // 결과 출력 또는 백엔드로 데이터 전송
    console.log(selectedData);
    // 백엔드로 데이터를 전송하려면 AJAX 등의 방법을 이용하여 서버에 데이터를 전송하면 됩니다.
    // 예를 들면, fetch API를 이용하여 POST 요청을 보낼 수 있습니다.
    // fetch('/backend', {
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

// 이벤트 리스너 등록
document.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
    checkbox.addEventListener('change', applyFilters);
});