window.addEventListener('DOMContentLoaded', function (){

    // input 아이디 가져오기
    const employeeNo = document.querySelector('#employeeNo');

    // 아이디 입력시 변화 감지
    employeeNo.addEventListener('keydown',function(){
        const employeeNoValue = employeeNo.value;
        const employeeNoLength = employeeNoValue.length;
        const employeeNo_length_errorMsg = document.querySelector('#employeeNo_length_errorMsg');
        console.log(employeeNoLength);
        if (employeeNoLength < 8 || employeeNoLength > 15) {
            employeeNo_length_errorMsg.classList.remove('d-none');
            inputEmployeeNo.classList.add('error_icon','border_danger')
            inputEmployeeNo.classList.remove('border_success', 'ok_icon');
            employeeNoOkMsg.className = 'd-none';
            return;
        }else{
            axios
                .get('/checkNo?employeeNoValue='+employeeNoValue)
                .then(function (response) {
                    displayEmployeeNoCheckMsg(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }

    })


    // 아이디중복확인
    const employeeNoOkMsg = document.getElementById('employeeNoOk');
    const inputEmployeeNo = document.querySelector('#employeeNo');
    const employeeNoNotOkMsg = document.querySelector('#employeeNoNotOk');
    const employeeNo_length_errorMsg = document.querySelector('#employeeNo_length_errorMsg');
    function displayEmployeeNoCheckMsg(data) {
        if (data === 'employeeNoOk') {
            employeeNoOkMsg.className='text-success';
            inputEmployeeNo.classList.add('border_success','ok_icon');
            inputEmployeeNo.classList.remove('border_danger','error_icon')
            employeeNo_length_errorMsg.className='d-none';
            employeeNoNotOkMsg.className = 'd-none';
        }else{
            employeeNoNotOkMsg.className = 'text-danger';
            inputEmployeeNo.classList.add('error_icon','border_danger')
            inputEmployeeNo.classList.remove('border_success', 'ok_icon');
            employeeNoOkMsg.className = 'd-none';
        }
    }




    // 비밀번호가져오기
    const password = document.querySelector('#password');
    const passwordNotOkMsg = document.querySelector('#passwordNotOkMsg');
    // 비밀번호 입력시 체크
    password.addEventListener('change', function (){
        let pw_length = password.value.length
        if (pw_length< 8 || pw_length > 15) {
            passwordNotOkMsg.className = '';
            password.classList.add('border_danger','error_icon')
            password.classList.remove('border_success','ok_icon');
        }else{
            passwordNotOkMsg.className = 'd-none';
            password.classList.add('border_success','ok_icon');
            password.classList.remove('border_danger','error_icon');
        }
    })

    // 비밀번호 재확인
    // 비밀번호 재확인 값 가져오기
    const check_password = document.querySelector('#check_password');
    const password_check_text = document.querySelector('#password_check_text');
    // 비밀번호가 일치하는지 확인
    check_password.addEventListener('change',function (){
        let check_password_value = check_password.value
        let pass_word_value = password.value
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

    // 이름
    const name = document.querySelector('#name');
    const name_not_ok = document.querySelector('#name_not_ok');
    // 이름 input에 이벤트 작성.
    name.addEventListener('change',function (){
        let name_length = name.value.length;
        if (name_length > 0) {
            name.classList.add('border_success','ok_icon');
            name.classList.remove('border_danger','error_icon');
            name_not_ok.className = 'd-none';
        } else {
            name.classList.add('border_danger','error_icon');
            name.classList.remove('border_success','ok_icon');
            name_not_ok.className = '';
        }
    })

    // 사내 전화번호
    const phone = document.querySelector('#phone');
    const phone_not_ok = document.querySelector('#phone_not_ok');

    // 사내 전화번호 이벤트 작성.
    phone.addEventListener('change', function (){
        let phone_length = phone.value.length;
        if (11<=phone_length<=13) {
            phone.classList.add('border_success','ok_icon');
            phone.classList.remove('border_danger','error_icon');
            phone_not_ok.className = 'd-none';
        }else{
            phone.classList.add('border_danger','error_icon');
            phone.classList.remove('border_success','ok_icon');
            phone_not_ok.className = '';
        }
    })

    // 이메일 값 가져오기
    const email = document.querySelector('#email');
    const email_blank = document.querySelector('#email_blank');
    // 이메일 input에 이벤트 주기
    email.addEventListener('change', function (){
        let email_length = email.value.length;
        const emailValue = email.value;
        if (email_length < 1) {
            email.classList.add('border_danger','error_icon');
            email.classList.remove('border_success','ok_icon');
            email_blank.className = '';
        }else{
            axios
                .get('/checkEmail?emailValue='+emailValue)
                .then(function (response) {
                    console.log(response)
                    displayEmailCheckMsg(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    })

    //이메일 중복확인 함수
    const duplicateEmailMsg = document.querySelector('#duplicateEmailMsg');
    function displayEmailCheckMsg(data){
        if (data === 'emailNotOk') {
            console.log(data);
            email.classList.add('border_danger','error_icon');
            email.classList.remove('border_success','ok_icon');
            duplicateEmailMsg.className = '';
        }else{
            console.log(data);
            email.classList.add('border_success','ok_icon');
            email.classList.remove('border_danger','error_icon');
            email_blank.className = 'd-none';
            duplicateEmailMsg.className = 'd-none';
        }
    }

    // select 박스 부서에맞게 변경되게하기
    // 부서
    const department = document.querySelector('#department');
    
    //팀
    const team = document.querySelector('#team');
    const team_develop = document.querySelectorAll('.develop');
    const team_human = document.querySelectorAll('.human');
    // 맡은일
    department.addEventListener('change',function (){
        let department_value = department.value;
        console.log(department_value);
        if (department_value == 1) {
            // 인사부일경우
            for(let i=0; i<team_develop.length; i++){
                team_develop[i].setAttribute('hidden','hidden');
            }
            for (let i = 0; i < team_human.length; i++) {
                team_human[i].removeAttribute('hidden');
            }


        }else if(department_value==2){
            // 개발부일경우
            for(let i=0; i<team_human.length; i++){
                team_human[i].setAttribute('hidden','hidden');
            }
            for (let i = 0; i < team_develop.length; i++) {
                team_develop[i].removeAttribute('hidden');
            }
        }
    })
})

const photo = document.querySelector('#photo');
let upload_name = document.querySelector('.upload-name');
console.log(photo.files)
photo.addEventListener('change',function (){
    let fileName = photo.files;
    upload_name.value = fileName[0].name;
})