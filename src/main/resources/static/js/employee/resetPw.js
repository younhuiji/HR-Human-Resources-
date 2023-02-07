window.addEventListener('DOMContentLoaded',function (){
    const form = document.querySelector('#form');
    const btn_setPw = document.querySelector('#btn_setPw');
    btn_setPw.addEventListener('click',function (){
        form.action = "/setPw";
        form.method = "post";
        form.submit();
    })


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
})