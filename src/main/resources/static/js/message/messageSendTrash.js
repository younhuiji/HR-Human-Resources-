// 메세지 삭제 버튼
function messageSendTrash(e) {
    const checkboxes = document.getElementsByName('messageCheckBox');

    let flag = null;

    checkboxes.forEach((cb) => {
        if(cb.checked == true) {
            flag = true;
        }
    })

    if(flag == true) {
        let result = confirm("삭제 하시겠습니까?");
        if(result == true) {
            e.setAttribute("type", "submit");
        }
    } else {
        alert("삭제할 메세지를 선택해주세요.");
    }

}