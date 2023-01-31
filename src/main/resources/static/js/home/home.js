window.addEventListener('DOMContentLoaded',function (){
    // 자바스크립트 날짜 시간 가져오기
    let today = new Date();
    console.log(today);
    let year = today.getFullYear();
    let month = ('0' + (today.getMonth() + 1)).slice(-2);
    let date = ('0' + today.getDate()).slice(-2);

    let hours = today.getHours(); // 시
    let minutes = today.getMinutes();  // 분
    let employeeNo = document.querySelector('#get_employeeNo').value;

    console.log(month);
    const data = {
        month: month, // 달
        date: date, // 날짜
        hours: hours, // 시
        minutes: minutes, // 분
        employeeNo:employeeNo // 사원 번호
    };

    // 업무시작 버튼
    const startButton = document.querySelector('#startButton');
    startButton.addEventListener('click',function (){
        axios.post('/attendance',data)
            .then(response=>{
                console.log(response);
            })
            .catch(error=>{
                console.log(error);
            })
    })
})
