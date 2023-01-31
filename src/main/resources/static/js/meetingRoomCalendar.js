var calendar = document.querySelector('#calendar');
var select_ym = document.querySelector('#select_ym');
var output = document.querySelector('#output');

var v_date = today.getDate(); // 오늘 일수
var v_day = today.getDay(); // 오늘 요일

var year = today.getFullYear(); // 올해
var month = today.getMonth(); // 이번 달
var v_year = today.getFullYear(); // 올해 (변함)
var v_month = today.getMonth(); // 이번 달 (변함)

function calendar_make(a, b) {
    if (a != undefined && b != undefined) { // 인수를 전달 받을 시 (달력 넘기기)
        v_year = a;
        v_month = b;
    } else { // 오늘로 이동
        v_year = year;
        v_month = month;
    }
    // 인수 받는 것을 처음에 둬야 이후 변수들에 대입되어 오류가 안 난다.
    var last_date = new Date(v_year, v_month + 1, 0).getDate(); // 이번달 마지막 일
    var first_day = new Date(v_year, v_month, 1).getDay(); // 이번 달 시작 요일 (0=>일, 1=>월 ...)
    select_ym.innerHTML = '<div>' + v_year + '년 ' + (v_month + 1) + '월' + '</div>';

    var row = calendar.insertRow();
    for (var i = 0; i < first_day; i++) { // 월의 시작일 전의 빈 칸
        var cell = row.insertCell();
    }
    for (var i = 1; i <= last_date; i++) { // 빈칸 이후의 달의 일수
        if (first_day != 7) {
            cell = row.insertCell();
            first_day += 1;
        } else {
            row = calendar.insertRow();
            cell = row.insertCell();
            first_day -= 6;
        }
        cell.setAttribute('id', i);
        cell.setAttribute('class', 'days');
        cell.addEventListener('click', function (self) {
            document.querySelector('#word_add').setAttribute('onclick', 'javascript:words_add(' + self.target.id + ')');
        })
        cell.innerHTML = i;
    }
}