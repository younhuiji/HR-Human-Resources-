const form = document.querySelector('#formModify');
const modifyBtn = document.querySelector('#modifyBtn')
modifyBtn.addEventListener('click', function(){
    const title = document.querySelector('#title').value;
    const purpose = document.querySelector('#purpose').text;
    if (title === '') {
        alert('제목을 입력해주세요.')
        return;
    } else if (purpose === '') {
        alert('사용목적을 입력해주세요.')
        return;
    }
    const result = confirm('수정하시겠습니까?')
    if(result) {
        form.action = '/meetingRoom/update';
        form.method = 'post';
        form.submit();
    }

});
