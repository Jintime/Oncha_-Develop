document.getElementById("joinButton").addEventListener("click", submitForm);

async function submitForm(event) {
    event.preventDefault();
    const birthDayNumber = document.getElementById("birthDayNumber").value;
    const genderNumber = document.getElementById("genderNumber").value;
    const username = document.getElementById("username").value;
    const nickname = document.getElementById("nickname").value;

    if (!validateNumber(birthDayNumber,6)) {
        alert("생년월일을 6가지 숫자로 작성해주세요");
        return;
    }

    if (!validateNumber(genderNumber, 1)) {
        alert("주민번호 앞자리를 1가지 숫자로 적어주세요");
        return;
    }

    if (!validateName(nickname, 3)) {
        alert("최소 3자리의 특수문자 없는 닉네임으로 구성되어야합니다.");
        return;
    }


    if (!validateName(username, 2)) {
        alert("최소 2자리의 이름으로 입력해 주세요");
        return;
    }

    let birthDate;
    // 생년월일 추출
    if (genderNumber<3) birthDate = "19"+birthDayNumber;
    else birthDate = "20"+birthDayNumber;

    // 성별 추출
    const gender = (parseInt(genderNumber) % 2 === 0) ? "female" : "male";

    const data = {
        "name" : username,
        "birth" : birthDate,
        "nickname" : nickname,
        "gender" : gender
    }

    const response = await fetch("/register", {
        headers : {
            "Content-Type" : "application/json"
        },
        method: "POST",
        body: JSON.stringify(data)
    });

    if (response.status === 200) {
        location.href = "/";
        return;
    }

    const result = await response.json();
    const messageList = result.data;
    let totalMessage = '';
    for (message of messageList) {
        totalMessage += message
        totalMessage += "\n";
    }
    alert(totalMessage);
}

/**
 *
 * @param string : string
 * @param length : number
 * @returns {boolean}
 */
function validateNumber(string, length) {
    // Remove leading/trailing white spaces
    const trimmedString = string.trim();

    // Check if the string is numeric and has the specified length
    return /^\d+$/.test(trimmedString) && trimmedString.length === length;
}

function validateName(name, length) {
    const trimmedNickname = name.trim();
    return /^[a-zA-Z0-9_\u3131-\uD79D]{2,}$/.test(trimmedNickname) && trimmedNickname.length >= length;
}