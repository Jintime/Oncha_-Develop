document.getElementById("myForm").addEventListener("submit", submitForm);

function submitForm(event) {
    event.preventDefault();
    const id_number = document.getElementById("my_num1").value;
    const genderCode = document.getElementById("my_num2").value;
    const username = document.getElementById("username").value;
    const nickname =document.getElementById("nickname").value;
    // 생년월일 추출

    // 성별 추출
    const gender = (parseInt(genderCode) % 2 === 0) ? "여성" : "남성";

    if(genderCode<3){let birthDateTime = new Date("19"+id_number)}
    else {let birthDateTime = new Date("20"+id_number);}
    // 폼 데이터 생성
    const formData = new FormData();
    formData.append("name", username);
    formData.append("birth", birthDateTime);
    formData.append("nickname", nickname);
    formData.append("gender", gender);

    // 폼 데이터 전송 (fetch를 사용하여 서버로 전송)
    fetch("your-server-url", {
        method: "POST",
        body: formData
    })
        .then(response => {
            // 응답 처리
            // ...
        })
        .catch(error => {
            // 에러 처리
            // ...
        });
}