let listTable = document.querySelector('#listTable');
createTable();

function createTable(){
    let tbl= "<table id='meetingTable'>";

    for (let i = 1; i <= 10; i++) {
        tbl+= "<tr>";
        for (let j = 8; j <= 20; j++) {
            if (i == 1) {
                if (j == 8) {
                    tbl += `<th></th>`;
                } else {
                    tbl += `<th>${j}:00</th>`;
                }
            }else {
                if (j == 8) {
                    tbl += `<th>회의실${i}`;
                } else {
                    tbl += `<td id="${j}:00">`;
                }

            }
        }
        tbl+= "</tr>";
    }
    tbl+= "</table>";
    listTable.innerHTML =tbl;
}

var dragging = false;
var dragSelectIds = [];
// var tempSelected =[];
var $td = $('td');
var startCell = null;
//const result11 = document.getElementById('result')
const button =document.querySelector('#button');
const myid1 = document.querySelector('#myid1');
const inputDate = document.querySelector('#input_submit');
let mapTemp = new Map([
    ["9:00", 1],
    ["10:00", 2],
    ["11:00", 3],
    ["12:00", 4],
    ["13:00", 5],
    ["14:00", 6],
    ["15:00", 7],
    ["16:00", 8],
    ["17:00", 9],
    ["18:00", 10],
    ["19:00", 11],
    ["20:00", 12]
]);




function end(e) {
    dragSelectIds = [];

    $td.removeClass('selected-item');

    $(cellsBetween(startCell, e.target)).each(function() {
        $(this).addClass('selected-item');
        dragSelectIds.push($(this).attr('id'));
        console.log('this'+ $(this).children());
    });
}

function cellsBetween(start, end) {
    var bounds, elementsInside;
    elementsInside = [start, end];
    do {
        bounds = getBoundsForElements(elementsInside);
        var elementsInsideAfterExpansion = rectangleSelect('td', bounds);
        if (elementsInside.length === elementsInsideAfterExpansion.length) {
            return elementsInside;
        } else {
            elementsInside = elementsInsideAfterExpansion;
        }
    } while (true);
}

function isPointBetween(point, x1, x2) {
    return (point >= x1 && point <= x2) || (point <= x1 && point >= x2);
}

function rectangleSelect(selector, bounds) {
    var elements = [];
    jQuery(selector).each(function() {
        var $this = jQuery(this);
        var offset = $this.offset();
        var x = offset.left;
        var y = offset.top;
        var w = $this.outerWidth();
        var h = $this.outerHeight();
        if ((isPointBetween(x + 1, bounds.minX, bounds.maxX) && isPointBetween(y + 1, bounds.minY, bounds.maxY)) ||
            (isPointBetween(x + w - 1, bounds.minX, bounds.maxX) && isPointBetween(y + h - 1, bounds.minY, bounds.maxY))
        ) {
            elements.push($this.get(0));
        }
    });
    return elements;
}

function getBoundsForElements(elements) {
    var x1 = elements.reduce(function(currMinX, element) {
        var elementLeft = $(element).offset().left;
        return currMinX && currMinX < elementLeft ? currMinX : elementLeft;
    }, undefined);
    var x2 = elements.reduce(function(currMaxX, element) {
        var elementRight = $(element).offset().left + $(element).outerWidth();
        return currMaxX && currMaxX > elementRight ? currMaxX : elementRight;
    }, undefined);
    var y1 = elements.reduce(function(currMinY, element) {
        var elementTop = $(element).offset().top;
        return currMinY && currMinY < elementTop ? currMinY : elementTop;

    }, undefined);
    var y2 = elements.reduce(function(currMaxY, element) {
        var elementBottom = $(element).offset().top + $(element).outerHeight();
        return currMaxY && currMaxY > elementBottom ? currMaxY : elementBottom;
    }, undefined);
    return {
        minX: x1,
        maxX: x2,
        minY: y1,
        maxY: y2
    };
}

$td.on('mousedown', function(e) {
    if(e.target.style.background == 'green'){
        alert('예약된 회의실입니다.');
        return;
    } else{
        dragging= true;
        startCell= e.target;
    }
    end(e);
});
$td.on('mouseenter', function(e) {
    if (!dragging) {
        return;
    }
    end(e);
});
$td.on('mouseup', function(e) {
    if(e.target.style.background == 'green'){
        alert('예약된 회의실입니다.');
        return;
    } else{
        dragging= false;
        console.log(dragSelectIds);

        button.value =dragSelectIds;
    }


});


// td로 th값 가져오기

// 버튼 클릭시 Row 값 가져오기
$(".checkBtn").click(function(e){

    var str = ""
    var tdArr = new Array();	// 배열 선언
    var checkBtn = $(this);

    // checkBtn.parent() : checkBtn의 부모는 <td>이다.
    // checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
    var tr = checkBtn.parent().parent();
    // var tr = $(this);
    var td = tr.children();

    console.log('클릭한 데이터 가져오기'+ button.value);
    console.log('날짜 가져오기'+myid1.value)

});

// 오늘 이후로 시간 예약하지 못하게
var now_utc = Date.now()
var timeOff = new Date().getTimezoneOffset()*60000;
var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
document.getElementById("reserveDate").setAttribute("min", today);


// list 첫 화면 오늘 날짜로 맞추기.
window.onload = function() {
    today = new Date();
    today = today.toISOString().slice(0, 10);
    bir = document.getElementById("reserveDate");
    bir.value = today;
    myid1.innerHTML = today;
}


// input date 값 받아오기.
function input() {
    const dday = document.querySelector('#reserveDate').value;
    console.log(dday)

    myid1.value = dday;
    myid1.innerHTML = dday;

    // 데이터를 불러옴
    let allMeetingList = [];
    axios.get('/api/org/meetingList/byDate/'+ dday)
        .then(response => {
            console.log(response.data)
            allMeetingList = response.data;
            updateTable(allMeetingList);
        })
        .catch(err => {
            console.log(err)
        })

}

function updateTable(list){

    //초기화
    for(let i = 0; i < listTable.rows.length; i++){
        for(let n = 0; n<13; n++){
            listTable.rows[i].cells[n].style.background='';
        }
    }

    let j = 0;
    for(let i = 0; i < listTable.rows.length; i++){

        if(j >= list.length){
            break;
        }

        while(listTable.rows[i].cells[0].innerText == list[j].roomName){
            let start = mapTemp.get(list[j].start);
            let end = mapTemp.get(list[j].end);
            checkBackground(start, end);
            j++;

            if(j >= list.length){
                break;
            }
        }

        function checkBackground (start, end){
            for(let n = start; n< end; n++){
                listTable.rows[i].cells[n].style.background="green";
            }
        }

    }
}
// 데이터 전송
const btnRegisterRoom = document.getElementById('btnRegisterRoom');
btnRegisterRoom.addEventListener('click', function () {
    console.log('selected:', dragSelectIds);
    console.log()
    let start = 0;
    let end = 0;
    let day = document.querySelector('#reserveDate').value;

    if (dragSelectIds.length > 0) {
        start = mapTemp.get(dragSelectIds[0]);
        end = mapTemp.get(dragSelectIds[dragSelectIds.length - 1]);
    }

    console.log(`start=${start}, end=${end}, day=${day}`);

    location.href = `/meetingRoom/create?start=${start}&end=${end}&day=${day}`;


});




