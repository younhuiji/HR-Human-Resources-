window.addEventListener('DOMContentLoaded', function (){

    // input 아이디 가져오기
    const employeeNo = document.querySelector('#employeeNo');

    // 아이디 입력시 변화 감지
    employeeNo.addEventListener('change',function(){
        const employeeNoValue = employeeNo.value;
        console.log(employeeNoValue);
        axios
            .post('/checkNo',{
                No : employeeNoValue
            })
            .then(function (response) {
                displayEmployeeNoCheckMsg(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    })

    // 아이디중복확인
    function displayEmployeeNoCheckMsg(data) {
        if (data === 'employeeNoOk') {
            const employeeNoOkMsg = document.getElementById('employeeNoOk');
            const inputEmployeeNo = document.querySelector('#employeeNo');
            employeeNoOkMsg.className='text-success';
            inputEmployeeNo.classList.add('border_success');
        }else{
            const employeeNoNotOkMsg = document.querySelector('#employeeNoNotOk');
            employeeNoNotOkMsg.className = 'text-danger';
        }
    }

    // 비밀번호가져오기
    const password = document.querySelector('#password');
    const passwordNotOk = document.querySelector('#passwordNotOk');
    const passwordNotOkMsg = document.querySelector('#passwordNotOkMsg');
    const passwordOk = document.querySelector('#passwordOk');
    // 비밀번호 입력시 체크
    password.addEventListener('change', function (){
        let pw_length = password.value.length
        if (pw_length< 8 || pw_length > 15) {
            passwordNotOk.className = '';
            passwordNotOkMsg.className = '';
            passwordOk.className = 'd-none';
            password.classList.add('border_danger')
            password.classList.remove('border_success');
        }else{
            passwordOk.className = '';
            passwordNotOk.className = 'd-none';
            passwordNotOkMsg.className = 'd-none';
            password.classList.add('border_success');
            password.classList.remove('border_danger');
        }
    })

    // 비밀번호 재확인
    // 비밀번호 재확인 값 가져오기
    const check_password = document.querySelector('#check_password');
    const double_check_password_ok = document.querySelector('#double_check_password_ok');
    const double_check_password_not_ok = document.querySelector('#double_check_password_not_ok');
    const password_check_text = document.querySelector('#password_check_text');
    // 비밀번호가 일치하는지 확인
    check_password.addEventListener('change',function (){
        let check_password_value = check_password.value
        let pass_word_value = password.value
        if (check_password_value === pass_word_value) {
            double_check_password_ok.className = '';
            double_check_password_not_ok.className = 'd-none';
            check_password.classList.add('border_success')
            check_password.classList.remove('border_danger');
        }else{
            password_check_text.className = 'text-danger';
            double_check_password_ok.className = 'd-none';
            double_check_password_not_ok.className = '';
            check_password.classList.add('border_danger')
            check_password.classList.remove('border_success');
        }
    })

    // 이름
    const name = document.querySelector('#name');
    const name_not_ok = document.querySelector('#name_not_ok');
    // 이름 input에 이벤트 작성.
    name.addEventListener('change',function (){
        let name_length = name.value.length;
        if (name_length > 0) {
            name.classList.add('border_success');
            name.classList.remove('border_danger');
            name_not_ok.className = 'd-none';
        } else {
            name.classList.add('border_danger');
            name.classList.remove('border_success');
            name_not_ok.className = '';
        }
    })

    // 사내 전화번호
    const phone = document.querySelector('#phone');
    const phone_not_ok = document.querySelector('#phone_not_ok');

    // 사내 전화번호 이벤트 작성.
    phone.addEventListener('chane', function (){

    })
})