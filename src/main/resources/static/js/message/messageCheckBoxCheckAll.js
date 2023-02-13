// 상단 체크박스 클릭 시 전체 선택 
function messageCheckBoxCheckAll(selectAll) {
    const checkboxes = document.getElementsByName('messageCheckBox');

    checkboxes.forEach((cb) => {
        cb.checked = selectAll.checked;
    })

}