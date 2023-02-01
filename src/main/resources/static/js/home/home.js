window.addEventListener('DOMContentLoaded',function (){
    // 자바스크립트 날짜 시간 가져오기
    let today = new Date();
    let employeeNo = document.querySelector('#get_employeeNo').value;
    // 업무시작 버튼
    const startButton = document.querySelector('#startButton');
    if (startButton != null) {
        startButton.addEventListener('click',function (){
            let month = today.getMonth() + 1;
            let day = today.getDate();
            let hours = today.getHours(); // 시
            let minutes = today.getMinutes();  // 분


            const data = {
                month: month, // 달
                day: day, // 날짜
                hours: hours, // 시
                minutes: minutes, // 분
                employeeNo:employeeNo // 사원 번호
            };

            axios.post('/attendance',data)
                .then(response=>{
                    let min = String(today.getMinutes());
                    if (min.length === 1) {
                        min = '0' + min;
                    }
                    alert('오늘하루도 힘차게!! 출근시간 : ' + today.getHours() + ":" + min);
                    location.reload();
                    console.log(response);
                })
                .catch(error=>{
                    console.log(error);
                })
        });
    }


    // 업무 종료 버튼 클릭시 disabled랑 회색 버튼으로 바꾸기.
    const endButton = document.querySelector('#endButton');
    if (endButton != null) {
        endButton.addEventListener('click',function (){
            const result = confirm('업무 종료 하시겠습니까? 되돌릴 수 없습니다.');
            if (result) {
                axios.get('/endWork?hours=' + today.getHours() + '&minutes=' + today.getMinutes() + '&employeeNo=' + employeeNo)
                    .then(response => {
                        let min = String(today.getMinutes());
                        if (min.length === 1) {
                            min = '0' + min;
                        }
                        alert('내일도 힘차게!! 퇴근시간 : ' + today.getHours() + ":" + min);
                        location.reload();
                        console.log(response)
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }
        });
    }
})


