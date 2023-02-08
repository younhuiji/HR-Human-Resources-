window.addEventListener('DOMContentLoaded',function (){
    // 비밀번호 가져오기
    const password_input = document.querySelector('#password_input');
    const passwordNotOkMsg = document.querySelector('#passwordNotOkMsg');
    password_input.addEventListener('keydown', function (){
        let pw_length = password_input.value.length
        if (pw_length< 7) {
            passwordNotOkMsg.className = '';
            password_input.classList.add('border_danger','error_icon')
            password_input.classList.remove('border_success','ok_icon');
        }else{
            passwordNotOkMsg.className = 'd-none';
            password_input.classList.add('border_success','ok_icon');
            password_input.classList.remove('border_danger','error_icon');
        }
    })

    // 비밀번호 재확인
    const check_password = document.querySelector('#check_password');
    const password_check_text = document.querySelector('#password_check_text');
    // 비밀번호가 일치하는지 확인
    check_password.addEventListener('change',function (){
        if(password_input.classList.contains('border_danger')){
            check_password.classList.add('border_danger','error_icon')
            check_password.classList.remove('border_success','ok_icon');
            return;
        }

        let check_password_value = check_password.value
        let pass_word_value = password_input.value
        if (check_password_value === pass_word_value) {
            password_check_text.className = 'd-none';
            check_password.classList.add('border_success','ok_icon')
            check_password.classList.remove('border_danger','error_icon');

        }else{
            password_check_text.className = 'text-danger';
            check_password.classList.add('border_danger','error_icon')
            check_password.classList.remove('border_success','ok_icon');
        }
    })
    // 비밀번호 설정 버튼
    const pw_form = document.querySelector('#pw_form');
    const btn_setPw = document.querySelector('#btn_setPw');
    btn_setPw.addEventListener('click',event =>{
        event.preventDefault();
        if (password_input.classList.contains('border_danger') || check_password.classList.contains('border_danger')) {
            alert('비밀번호 설정 양식을 확인해주세요.')
        }else if(password_input.value===''||check_password.value===''){
            alert('비밀번호를 입력해주세요.')
        }
        else{
            pw_form.action = "/setPw";
            pw_form.method = "post";
            pw_form.submit();
        }
    })

})