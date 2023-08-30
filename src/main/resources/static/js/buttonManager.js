"use strict";

const MappingButtons = {
    indexBtn: {///index
        logoBtn: ".index_bt",
        categoryBtn:".category_bt",
        teaFinderBtn: ".teaFinder_bt",
        storeBtn :".store_bt",
        teaHouseBtn:".teaHouse_bt",
        subBtn:"subscribe_bt",
        loginBtn:".login_bt",
        myonchaBtn:".myoncha_bt",
        mypageBtn:"mypage_bt",
    },///마이온차
    MyOnchaBtn: {
        myPageBtn: ".mypage_bt",
        rankBtn: ".rank_bt",
        teaFinderBtn: ".teaFinder_bt",
        likeBtn: ".like_bt"
    },//마이페이지
    MyPageBtn:{
        myPageDetailBtn:".detail_bt",
        etcBtn:".etc_bt",
        orderInfoBtn:"orderInfo_bt",
        refundDetailBtn:".refundDetail_bt",
        CSBtn:"CS_bt"
    }
};

document.addEventListener('click', event => {
    const target = event.target;
    Object.values(MappingButtons).forEach(buttonObject => {
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