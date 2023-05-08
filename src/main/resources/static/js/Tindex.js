"use strict";

const logoBtn = document.querySelector(".logo"),
    finderBtn=document.querySelector(".finder_bt"),
    storeBtn =document.querySelector(".store_bt"),
    reserveBtn=document.querySelector(".reserve_bt"),
    subBtn=document.querySelector(".sub_bt"),
    loginBtn=document.querySelector("#login");

loginBtn.addEventListener("click",()=>getterBtn("login"));
logoBtn.addEventListener("click",()=>getterBtn("index"));
finderBtn.addEventListener("click",()=>getterBtn("finder"));
storeBtn.addEventListener("click",()=>getterBtn("store"));
reserveBtn.addEventListener("click",()=>getterBtn("reservation"));
subBtn.addEventListener("click",()=>getterBtn("subscribe"));

function  getterBtn (value){

    fetch(`/${value}`,{
        method : "GET",
        headers : {
            "Content-Type":"application/json"
        },
    })
        .then(()=>{
            location.href =`/${value}`;

        })
        .catch((err)=>{
            console.error(new Error(`${value} 에러`));
        });

}