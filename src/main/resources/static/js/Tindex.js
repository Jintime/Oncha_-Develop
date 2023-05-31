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
reserveBtn.addEventListener("click",()=>getterBtn("teaHouse"));
subBtn.addEventListener("click",()=>getterBtn("subscribe"));

const inner = document.querySelector(".inner"),
firstImg = inner.querySelectorAll("img")[0],
arrowIcons =document.querySelectorAll(".product_Container i");

let isDragStart=false,prevPageX,prevScrollLeft;


const showHideIcons =()=>{
    let scrollWidth = inner.scrollWidth- inner.clientWidth;
    arrowIcons[0].style.display = inner.scrollLeft==0 ?"none":"block";
    arrowIcons[1].style.display = inner.scrollLeft==scrollWidth ?"none":"block";
}

arrowIcons.forEach(icon =>{
    icon.addEventListener("click",()=>{
        let firstImgWidth = firstImg.clientWidth+14;
        inner.scrollLeft+=icon.id=="left"?-firstImgWidth:firstImgWidth;
        setTimeout(() => showHideIcons(),60);

    });
});

const dragStart=(e)=>{
    isDragStart =true;
    prevPageX=e.pageX;
    prevScrollLeft =inner.scrollLeft;
}

const dragging=(e)=>{
    if(!isDragStart)return;
    e.preventDefault();
    inner.classList.add("dragging");
    let positionDiff = e.pageX-prevPageX;
    inner.scrollLeft =prevScrollLeft-positionDiff;
    showHideIcons();

}

const dragStop=()=>{
    isDragStart=false;
    inner.classList.remove("dragging");
}

inner.addEventListener("mousedown",dragStart);
inner.addEventListener("mousemove",dragging);
inner.addEventListener("mouseup",dragStop);
inner.addEventListener("mouseleave",dragStop);



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