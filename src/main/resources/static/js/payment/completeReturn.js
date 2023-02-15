function getComplete(str) {
    const no = document.querySelector('#number').value;
    const result = confirm('승인 하시겠습니까?');

    if(result) {
        axios
            .get(str + no)
            .then(response => {
                alert('승인 처리가 완료되었습니다.');
                location.reload();
            })
            .catch(err => {
                console.log(err)
            })
    }

}
