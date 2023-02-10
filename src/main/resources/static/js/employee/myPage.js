window.addEventListener('DOMContentLoaded',function (){
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

    // 검색 버튼
    const btnSearch = document.querySelector('#btnSearch');
    const search_form = document.querySelector('#search_form');
    btnSearch.addEventListener('click',function (){
        search_form.preventDefault();
        search_form.action = "/myPage/search";
        search_form.method = "get";
        search_form.submit();
    })


    // 이미지 변경
    const employee_img = document.querySelector('#employee_img');
    function getImageFiles(e) {
        const uploadFiles = [];
        const files = e.currentTarget.files;
        const imagePreview = document.querySelector('#new_employee_img');
        const docFrag = new DocumentFragment();

        employee_img.setAttribute("hidden", "hidden");
        // imagePreview.setAttribute("hidden", "hidden");

        if ([...files].length >= 2) {
            alert('이미지는 최대 1개 까지 업로드가 가능합니다.');
            return;
        }

        // 파일 타입 검사
        [...files].forEach(file => {
            if (!file.type.match("image/.*")) {
                alert('이미지 파일만 업로드가 가능합니다.');
                return
            }

            // 파일 갯수 검사
            if ([...files].length < 7) {
                uploadFiles.push(file);
                const reader = new FileReader();
                reader.onload = (e) => {
                    const preview = createElement(e, file);
                    // imagePreview.remove();
                    imagePreview.appendChild(preview);
                };
                reader.readAsDataURL(file);
            }
        });
    }

    function createElement(e, file) {
        // const div = document.createElement('div');
        const img = document.createElement('img');
        img.setAttribute('src', e.target.result);
        img.setAttribute('data-file', file.name);
        // div.appendChild(img);

        return img;
    }

    const realUpload = document.querySelector('.real-upload');
    const upload = document.querySelector('.upload');

    upload.addEventListener('click', () => realUpload.click());

    realUpload.addEventListener('change', getImageFiles);

    // 수정버튼
    const buttonUpdate = document.querySelector('#buttonUpdate');
    const form = document.querySelector('#form');
    buttonUpdate.addEventListener('click', function (){
        form.action = "/myPage/update";
        form.method = "post";
        form.submit();
        alert('수정 완료');
    })
})