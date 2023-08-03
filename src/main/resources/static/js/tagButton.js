// 체크박스 이름과 해당 값에 대응하는 태그 매핑 객체
const tagMapping = {
    "blended": "블렌디드 x",
    "single_origin": "싱글오리진 x",
    "new_type": "뉴타입 x",
    "green_tea": "녹차 x",
    "black_tea": "홍차 x",
    "white_tea": "백차 x",
    "yellow_tea": "황차 x",
    "blue_tea": "청차 x",
    "herb_tea":"허브차 x",
    "isCaffeine":"카페인 유 x",
    "noCaffeine" :"카페인 무 x",
    "liquid":"액상 x",
    "leaf_tea":"잎차 x",
    "tea_bag":"티백 x",
    "syrup":"청 x",
    "powder":"가루 x",
    "floral":"플로럴 x",
    "citrus":"시트러스 x",
    "fruity":"프루티 x",
    "smokey":"스모키 x",
    "caramel":"카라멜 x",
    "nutty":"너티 x",
    "mint":"민트 x",
    "spicy":"스파이시 x",

    // 다른 체크박스들도 추가해주세요
};

function createTagButton(tag, tagsWrapper) {
    const tagButton = document.createElement('button');
    tagButton.className = 'tag-button';
    tagButton.innerHTML = tag;

    // 버튼 클릭 이벤트 처리
    tagButton.addEventListener('click', function() {
        const correspondingCheckboxId = Object.keys(tagMapping).find(key => tagMapping[key] === tag);
        if (correspondingCheckboxId) {
            const correspondingCheckbox = document.getElementById(correspondingCheckboxId);
            correspondingCheckbox.checked = false;
            updateSelectedTags();
        }
    });

    tagsWrapper.appendChild(tagButton);
}

function updateSelectedTags() {
    let selectedTags = [];

    // tagMapping 객체를 순회하면서 체크박스의 상태를 확인
    for (const checkboxName in tagMapping) {
        if (document.getElementById(checkboxName).checked) {
            selectedTags.push(tagMapping[checkboxName]);
        }
    }

    // 버튼 태그를 갱신
    const tagsWrapper = document.querySelector('.tags-wrapper');
    tagsWrapper.innerHTML = '';
    for (const tag of selectedTags) {
        createTagButton(tag, tagsWrapper);
    }

}

// 체크박스의 변경 상태가 바뀔 때마다 updateSelectedTags 함수 호출
document.querySelectorAll('input[type="checkbox"]').forEach(checkbox => {
    checkbox.addEventListener('change', updateSelectedTags);
});

// 페이지 로드 시 최초로 updateSelectedTags 함수 호출
updateSelectedTags();
