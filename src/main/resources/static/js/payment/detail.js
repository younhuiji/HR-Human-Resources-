window.addEventListener('DOMContentLoaded', event => {

    // 반려
    const buttonReturn = document.querySelectorAll('#btnReturn');

    buttonReturn.forEach(btn => {
        btn.addEventListener('click', getReturn);
    });

    function getReturn(event) {

        const leaveNo = document.querySelector('#leaveNo').value;

        axios
            .get('/api/approver/return/' + leaveNo)
            .then(response => {
                showModal()
            })
            .catch(err => {
                console.log(err)
            })
    }

    const divModal = document.querySelector('#modal');
    const modal = new bootstrap.Modal(divModal); // 부트스트랩 Modal 객체 생성.
    const modalReturnReason = document.querySelector('#modalReturnReason');
    const modalLeaveNo = document.querySelector('#modalLeaveNo');
    const modalBtnUpdate = document.querySelector('#modalBtnUpdate');

    function showModal() {
        modal.show(); // modal를 보여주기
    }

    modalBtnUpdate.addEventListener('click', updateReturnReason);

    function updateReturnReason(event) {
        const leaveNo = modalLeaveNo.value;
        const returnReason = modalReturnReason.value;

        if (returnReason == '') {
            alert('반려 사유는 반드시 입력해주세요');
            return;
        }

        const data = {returnReason: returnReason};
        alert(data);
        axios
            .put('/api/approver/' + leaveNo, data)
            .then(
                modal.hide()
            )
            .catch(err => {
                console.log(err);
            })
    }

    // 승인1
    const buttonCompete = document.querySelectorAll('#btnCompete');
    buttonCompete.forEach(btn => {
        btn.addEventListener('click', getCompete);
    });

    function getCompete() {
        const leaveNo = document.querySelector('#leaveNo').value;

        const result = confirm('승인 하시겠습니까?');

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

    // 승인2
    const buttons2 = document.querySelectorAll('#btnCompete2');
    buttons2.forEach(btn => {
        btn.addEventListener('click', getCompete2);
    });

    function getCompete2() {
        const leaveNo2 = document.querySelector('#leaveNo').value;

        const result = confirm('승인 하시겠습니까?');

        if(result) {
            axios
                .get('/api/approver/compete2/' + leaveNo2)
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