
    function returnReason(str) {

        const no = document.querySelector('#number').value;
        const returnReason = document.querySelector('#returnReason').value;

        if (returnReason == '') {
            alert('반려 사유는 반드시 입력해주세요');
            return;
        }

        const data = {returnReason: returnReason};

        axios
            .put(str + no, data)
            .then(response => {
                    alert('반려 처리가 완료되었습니다.');
                    location.href = 'http://localhost:8889/payment/list';
            })
            .catch(err => {
                console.log(err);
            })


    }

