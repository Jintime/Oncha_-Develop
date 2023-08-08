const main = document.querySelector("#main"),
    qna=document.querySelector("#qna"),
    result = document.querySelector("#result"),
    endPoint=10,
    select = [0, 0, 0, 0, 0, 0, 0, 0];

function calResult(){
    return select.indexOf(Math.max(...select));
}
function setResult(){
    let point = calResult();
    const resultName = document.querySelector('.resultName');
    resultName.innerHTML = infoList[point].name;

    var resultImg = document.createElement('img');
    const imgDiv = document.querySelector('#resultImg');
    var imgURL = '/img/teaFinder/' + point + '.png';
    resultImg.src = imgURL;
    resultImg.alt = point;
    resultImg.classList.add('img-fluid');
    imgDiv.appendChild(resultImg);

    const resultDesc = document.querySelector('.resultDesc');
    resultDesc.innerHTML = infoList[point].desc;
}

function goResult(){
    qna.style.WebkitAnimation="fadeOut 1s";
    qna.style.animation="fadeOut 1s";
    setTimeout(()=>{
        result.style.WebkitAnimation="fadeIn 1s";
        result.style.animation="fadeIn 1s";
        setTimeout(()=>{
            qna.style.display ="none";
            result.style.display = "block";
        },450)})
    setResult();
}


function addAnswer(answerText,qIdx,idx){
    const a = document.querySelector(".answerBox");
    const answer =document.createElement(`Button`);
    answer.classList.add(`answerList`);
    answer.classList.add('fadeIn');
    a.appendChild(answer);
    answer.innerHTML =answerText;
    answer.addEventListener("click", function(){
        const children = document.querySelectorAll('.answerList');
        for(let i = 0; i < children.length; i++){
            children[i].disabled = true;
            children[i].style.WebkitAnimation = "fadeOut 0.5s";
            children[i].style.animation = "fadeOut 0.5s";
        }
        setTimeout(() => {
            const target = qnaList[qIdx].a[idx].type;
            for(let i = 0; i < target.length; i++){
                select[target[i]] += 1;
            }

            for(let i = 0; i < children.length; i++){
                children[i].style.display = 'none';
            }
            goNext(++qIdx);
        },450)
    }, false);
}

function goNext(qIdx){
    if(qIdx === endPoint){
        goResult();
        return;
    }
    const q =document.querySelector(".qBox");
    q.innerHTML =qnaList[qIdx].q;
    for(let i in qnaList[qIdx].a){
        addAnswer(qnaList[qIdx].a[i].answer,qIdx,i);
    }
    const status = document.querySelector(".statusBar")
    status.style.width= (100/endPoint)*(qIdx+1)+`%`;
}

function begin(){
    main.style.WebkitAnimation="fadeOut 1s";
    main.style.animation="fadeOut 1s";
    setTimeout(()=>{
        qna.style.WebkitAnimation="fadeIn 1s";
        qna.style.animation="fadeIn 1s";
        setTimeout(()=>{
            main.style.display ="none";
            qna.style.display = "block";
        },450);
        let qIdx=0;
        goNext(qIdx);
    },450);

}