function getCompete(str) {
    const no = document.querySelector('#number').value;
    alert(no);
    const result = confirm('승인 하시겠습니까?');

    if(result) {
        axios
            .get(str + no)
            .then(response => {
                alert(str);
                alert('승인 처리가 완료되었습니다.');
                location.reload();
            })
            .catch(err => {
                console.log(err)
            })
    }

}

function getCompete(str) {
    const no = document.querySelector('#number').value;
    alert(no);
    const result = confirm('승인 하시겠습니까?');

    if(result) {
        axios
            .get(str + no)
            .then(response => {
                alert(str);
                alert('승인 처리가 완료되었습니다.');
                location.reload();
            })
            .catch(err => {
                console.log(err)
            })
    }

}