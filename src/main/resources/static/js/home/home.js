window.addEventListener('DOMContentLoaded',function (){
    // 자바스크립트 날짜 시간 가져오기
    let today = new Date();

    let month = today.getMonth() + 1;
    let day = today.getDate();

    let hours = today.getHours(); // 시
    let minutes = today.getMinutes();  // 분
    let employeeNo = document.querySelector('#get_employeeNo').value;

    console.log(month);
    const data = {
        month: month, // 달
        day: day, // 날짜
        hours: hours, // 시
        minutes: minutes, // 분
        employeeNo:employeeNo // 사원 번호
    };

    // 업무중인 상황에 새로고침이 와도 내용 보존하는 함수



    // 업무시작 버튼
    const startButton = document.querySelector('#startButton');
    startButton.addEventListener('click',function (){
        axios.post('/attendance',data)
            .then(response=>{
                alert('오늘하루도 힘차게!! 출근시간 : ' + today.getHours()+":"+today.getMinutes())
                console.log(response);
                updateStartTime();
                updateEndTime(response.data);
                updateStateTime();
            })
            .catch(error=>{
                console.log(error);
            })
    })
    function updateStartTime(hours,minutes) {
        const startTime = document.querySelector('#startTime');
        let str = '';
        str += '<p>출근 시간</p>'
        +'<p>'+today.getHours()+':'+today.getMinutes()+'</p>'
        startTime.innerHTML = str;
    }
    function updateEndTime(data) {
        const endTime = document.querySelector('#endTme');
        let str = '';
        str += '<p>예상 퇴근 시간</p>'
            + '<p>' + data + '</p>';
        endTime.innerHTML = str;
    }
    function updateStateTime() {
        const state_time = document.querySelector('#state_time');
        let str = '';
        str='<p>업무중~시간</p>'
        state_time.innerHTML = str;
    }

})


