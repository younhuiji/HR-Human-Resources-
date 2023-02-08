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