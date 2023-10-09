"use strict"
const MappingButtons = {
    teaFinderBtn: [".teaFinder_bt"],
    liquidBtn: [".liquid_bt"],
    leafBtn: [".leaf_bt"],
    syrupBtn: [".syrup_bt"],
    powderBtn: [".powder_bt"],
    greenTeaBtn: [".green_tea_bt"],
    blackTeaBtn: [".black_tea_bt"],
    whiteTeaBtn: [".white_tea_bt"],
};

document.addEventListener('click', event => {
    const target = event.target;

    for (const [btnName, selectors] of Object.entries(MappingButtons)) {
        for (const selector of selectors) {
            if (target.matches(selector)) {
                getterBtn(btnName);
                return; // 클릭된 버튼을 찾았으면 반복문 종료
            }
        }
    }
});

function getterBtn(btnName) {
    const path = btnName.replace("Btn", "").toLowerCase();
    fetch(`/store/${path}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },
    })
        .then(() => {
            location.href = `/store/${path}`;
        })
        .catch((err) => {
            console.error(new Error(`${path} 에러`));
        });
}
