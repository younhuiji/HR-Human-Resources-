window.addEventListener('DOMContentLoaded', () => {

    const file  = document.querySelector("#file");
    // file 개수 체크
    file.addEventListener("change", function() {
        let files = document.getElementById("file");
        if(files.files.length > 3) {
            alert("파일은 최대 3개까지 업로드 할 수 있습니다.")
            files.value = "";
        }
    });

    const formBody  = document.querySelector("#formBody");
    const btnSend   = document.querySelector("#btnSend");
    // 쪽지쓰기 버튼
    btnSend.addEventListener("click", function(e) {
        e.preventDefault();
        const result = confirm("보내시겠습니까?")
        if(result) {
            formBody.action = '/message/sendMessage';
            formBody.method = 'post';
            formBody.submit();
        }
    });

});