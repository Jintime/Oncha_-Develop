const loginBtn = document.getElementById("button");

async function login () {
    console.log("로ㅓ그이이이잉ㄴ")
    const userName = document.getElementById("username");
    const password = document.getElementById("password");

    const response = await fetch("/api/auth/login", {
        method:"POST",
        headers:{
            'Content-Type' : 'application/json'
        },
        body:JSON.stringify(
        {
            userName:userName.value,
            password:password.value
    })})
    if (response.status !== 200) {
        alert("다시 로그인 해주십시오.")
    }else {
        location.href = "/";
    }
}

loginBtn.addEventListener("click", () => login());