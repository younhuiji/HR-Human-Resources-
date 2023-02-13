window.addEventListener('DOMContentLoaded', function (){

    // input 아이디 가져오기
    const employeeNo = document.querySelector('#employeeNo');
    // 아이디 입력시 변화 감지
    employeeNo.addEventListener('keyup',function(){
        let employeeNoValue = employeeNo.value;
        const employeeNoLength = employeeNoValue.length;
        const employeeNo_length_errorMsg = document.querySelector('#employeeNo_length_errorMsg');
        if (employeeNoLength < 8) {
            employeeNo_length_errorMsg.classList.remove('d-none');
            inputEmployeeNo.classList.add('error_icon','border_danger')
            inputEmployeeNo.classList.remove('border_success', 'ok_icon');
            employeeNoOkMsg.className = 'd-none';
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
            employeeNo_length_errorMsg.className = 'd-none';
        }
    }




    // 비밀번호가져오기
    const password = document.querySelector('#password');
    const passwordNotOkMsg = document.querySelector('#passwordNotOkMsg');
    // 비밀번호 입력시 체크
    password.addEventListener('keydown', function (){
        let pw_length = password.value.length
        if (pw_length< 7) {
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
        if(password.classList.contains('border_danger')){
            check_password.classList.add('border_danger','error_icon')
            check_password.classList.remove('border_success','ok_icon');
            return;
        }

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
    const phone_duplicate = document.querySelector('#phone_duplicate');
    // 사내 전화번호 이벤트 작성.
    phone.addEventListener('keyup', function (){
        let phone_length = phone.value.length;
        let phoneValue = phone.value;
        if (phone_length<11) {
            phone.classList.add('border_danger','error_icon');
            phone.classList.remove('border_success','ok_icon');
            phone_not_ok.className = '';
        }else{
            axios
                .get('/checkPhone?phoneValue='+phoneValue)
                .then(function (response) {
                    displayPhoneCheckMsg(response.data);
                })
                .catch(function (error) {
                    console.log(error);
                });

        }
    })

    // 사내전화 중복확인 axios 결과
    function displayPhoneCheckMsg(data) {
        if (data === 'phoneOk') {
            phone.classList.add('border_success','ok_icon');
            phone.classList.remove('border_danger','error_icon');
            phone_not_ok.className = 'd-none';
            phone_duplicate.className = 'd-none';
        }else{
            phone.classList.add('border_danger','error_icon');
            phone.classList.remove('border_success','ok_icon');
            phone_duplicate.className = '';
            phone_not_ok.className = 'd-none';
        }
    }


    // 이메일 값 가져오기
    const email = document.querySelector('#email');
    const email_blank = document.querySelector('#email_blank');
    const duplicateEmailMsg = document.querySelector('#duplicateEmailMsg');
    const notEmailForm = document.querySelector('#notEmailForm');
    // 이메일 input에 이벤트 주기
    email.addEventListener('change', function (){
        let email_length = email.value.length;
        const emailValue = email.value;
        if (email_length < 1) {
            email.classList.add('border_danger','error_icon');
            email.classList.remove('border_success','ok_icon');
            email_blank.className = '';
            duplicateEmailMsg.className = 'd-none';
        } else{
            const substring = '@';
            if (emailValue.indexOf(substring) === -1) {
                email.classList.add('border_danger','error_icon');
                email.classList.remove('border_success','ok_icon');
                email_blank.className = 'd-none';
                duplicateEmailMsg.className = 'd-none';
                notEmailForm.className = '';
            }else{
                axios
                    .get('/checkEmail?emailValue='+emailValue)
                    .then(function (response) {
                        console.log(response.data)
                        displayEmailCheckMsg(response.data);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    })

    //이메일 중복확인 함수
    function displayEmailCheckMsg(data){
        if (data === 'emailNotOk') {
            email.classList.add('border_danger','error_icon');
            email.classList.remove('border_success','ok_icon');
            duplicateEmailMsg.className = '';
        }else{
            email.classList.add('border_success','ok_icon');
            email.classList.remove('border_danger','error_icon');
            email_blank.className = 'd-none';
            duplicateEmailMsg.className = 'd-none';
            notEmailForm.className = 'd-none';
        }
    }

    // select 박스 부서에맞게 변경되게하기
    // 부서
    const department = document.querySelector('#department');
    
    //팀
    const team = document.querySelector('#team');
    const team_develop = document.querySelectorAll('.develop');
    const team_human = document.querySelectorAll('.human');
    const team_marketing = document.querySelectorAll('.marketing');
    // 맡은일
    const work = document.querySelector('#work');

    department.addEventListener('change',function (){
        let department_value = department.value;
        console.log(department_value);
        if (department_value == '인사관리부') {
            // 인사부일경우
            for(let i=0; i<team_develop.length; i++){
                team_develop[i].setAttribute('hidden','hidden');
            }
            for (let i = 0; i < team_marketing.length; i++) {
                team_marketing[i].setAttribute('hidden','hidden');
            }
            for (let i = 0; i < team_human.length; i++) {
                team_human[i].removeAttribute('hidden');
            }

        }else if(department_value=='개발부'){
            // 개발부일경우
            for(let i=0; i<team_human.length; i++){
                team_human[i].setAttribute('hidden','hidden');
            }
            for (let i = 0; i < team_marketing.length; i++) {
                team_marketing[i].setAttribute('hidden','hidden');
            }
            for (let i = 0; i < team_develop.length; i++) {
                team_develop[i].removeAttribute('hidden');
            }
        }
        else{
            // 기획부일경우
            for(let i=0; i<team_human.length; i++){
                team_human[i].setAttribute('hidden','hidden');
            }
            for(let i=0; i<team_develop.length; i++){
                team_develop[i].setAttribute('hidden','hidden');
            }
            for (let i = 0; i < team_marketing.length; i++) {
                team_marketing[i].removeAttribute('hidden');
            }
        }
    })

    // 입사일
    const joinedDate = document.querySelector('#joinedDate');

    // 가입하기 버튼 활성화 / 비활성화
    const btnSubmit = document.querySelector('#btnSubmit');
    const form = document.querySelector('#form');
    btnSubmit.addEventListener('click',function (){
        if(employeeNo.classList.contains('border_danger')||
            password.classList.contains('border_danger')||
            check_password.classList.contains('border_danger')||
            name.classList.contains('border_danger')||
            phone.classList.contains('border_danger')||
            email.classList.contains('border_danger')){
            alert("형식에 맞게 작성해 주세요.")
        }else if(employeeNo.value===''||
        password.value===''||
        check_password.value===''||
        name.value===''||
        phone.value===''||
        email.value===''){
            alert('작성되지 않은 내용이 있습니다.')
        }else if(department.value===''||team.value===''||work.value===''){
            alert("소속을 입력해주세요");
        }else if(joinedDate.value===''){
            alert('입사일을 입력해주세요.');
        }
        else{
            form.action = "/join";
            form.method = "post";
            form.submit();
            alert('가입을 환영합니다.')
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