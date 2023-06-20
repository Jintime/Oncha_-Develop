"use strict";
const buttons = {
    indexBtn: {
        logoBtn: ".index_bt",
        teaFinderBtn: ".teaFinder_bt",
        storeBtn :".store_bt",
        teaHouseBtn:".teaHouse_bt",
        subBtn:"subscribe_bt",
        loginBtn:".login_bt"
    },
    MyOnchaBtn: {
        myPageBtn: ".myPage_bt",
        rankBtn: ".rank_bt",
        teaFinderBtn: ".teaFinder_bt",
        likeBtn: ".like_bt"
    },
    MyPageBtn:{
        myPageDetailBtn:".mypage/detail_bt",
        etcBtn:".mypage/etc_bt"

    }

};


document.addEventListener('click', event => {
    const target = event.target;
    Object.values(buttons).forEach(buttonObject => {
        Object.values(buttonObject).forEach(btnSelector => {
            if (target.matches(btnSelector)) {
                getterBtn(btnSelector);
            }
        });
    });
});

function  getterBtn (value){
    const path = value.replace(".","").replace("_bt", "");
    fetch(`/${path}`,{
        method : "GET",
        headers : {
            "Content-Type":"application/json"
        },
    })
        .then(()=>{
            location.href =`/${path}`;

        })
        .catch((err)=>{
            console.error(new Error(`${path} 에러`));
        });

}