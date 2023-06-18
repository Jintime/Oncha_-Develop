document.getElementById("myForm").addEventListener("submit", submitForm);

function submitForm(event) {
    event.preventDefault();
    const birthDayNumber = document.getElementById("birthDayNumber").value;
    const genderNumber = document.getElementById("genderNumber").value;
    const username = document.getElementById("username").value;
    const nickname = document.getElementById("nickname").value;

    let birthDateTime;
    // 생년월일 추출
    if (genderNumber<3) birthDateTime = "19"+birthDayNumber;
    else birthDateTime = "20"+birthDayNumber;

    // 성별 추출
    const gender = (parseInt(genderNumber) % 2 === 0) ? "female" : "male";

    const formData = new FormData();
    formData.append("name", username);
    formData.append("birth", birthDateTime);
    formData.append("nickname", nickname);
    formData.append("gender", gender);

    // 폼 데이터 전송 (fetch를 사용하여 서버로 전송)
    fetch("/register", {
        headers : {
          "Content-Type" : "application/json"
        },
        method: "POST",
        body: JSON.parse(formData)
    })
        .then(response => {
            if (response.status === 200) {
                location.href = "/";
            }
        })
        .catch(error => {
            alert(error.message);
        });
}