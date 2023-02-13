// 검색창 옆에 긴급, 중요, 일반 버트 하나만 체크
function messageTypeCheckBoxCheck(element) {
    const checkBoxes = document.querySelectorAll("input[name='messageType']");

    if(element.checked == true) {
        checkBoxes.forEach((cb) => {
            cb.checked = false;
        })
        element.checked = true;
    }
};