window.addEventListener('DOMContentLoaded',function (){
    const department = document.querySelector('#department');

    //팀
    const team = document.querySelector('#team');
    const team_develop = document.querySelectorAll('.develop');
    const team_human = document.querySelectorAll('.human');
    // 맡은일
    const work = document.querySelector('#work');
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


    // 수정버튼
    const buttonUpdate = document.querySelector('#buttonUpdate');
    const form = document.querySelector('#form');
    buttonUpdate.addEventListener('click', function (){
        form.action = "/update";
        form.method = "post";
        form.submit();
        alert('수정 완료');
    })

})