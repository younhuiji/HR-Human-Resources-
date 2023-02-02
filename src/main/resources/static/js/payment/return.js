window.addEventListener('DOMContentLoaded', event => {

    const buttons = document.querySelectorAll('#insertButton');
    buttons.forEach(btn => {
        btn.addEventListener('click', getReturn);
    });

    function getReturn() {
        const leaveNo = document.querySelector('#leaveNo').value;

        const result = confirm(' 하시겠습니까?');

        if(result) {
            axios
                .get('/api/approver/compete/' + leaveNo)
                .then(response => {
                    alert('승인 처리가 완료되었습니다.');
                    open('http://localhost:8889/')
                })
                .catch(err => {
                    console.log(err)
                }) // 실패 응답 처리
        }

    }


}) // window end