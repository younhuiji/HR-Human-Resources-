// time table 클릭이벤트

// $(function() {
//     $("#listTable #tbodyId").on("mousedown","td",function(){
//         alert( $(this).find("td:eq(0)").text() );
//         alert( $(this).find("td:eq(1)").text() );
//
//
//     });
// });

var dragging = false;
var dragSelectIds = [];
// var tempSelected =[];
var $td = $('td');
var startCell = null;
//const result11 = document.getElementById('result')
const button =document.querySelector('#button');
const myid1 = document.querySelector('#myid1');
const inputDate = document.querySelector('#input_submit');

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
    dragging = true;
    startCell = e.target;
    end(e);
});
$td.on('mouseenter', function(e) {
    if (!dragging) {
        return;
    }
    end(e);
});
$td.on('mouseup', function(e) {
    dragging = false;
    console.log(dragSelectIds);

    button.value = dragSelectIds;

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

$('.saveBtn').on('click', function() {

    //현재 row의 정보 가져오기
    var thisRow = $(this).closest('tr');

    //주소 input 값 가져오기
    var addr = thisRow.find('td:eq(0)').find('input').val();
    //섦졍 input 값 가져오기
    var txt = thisRow.find('td:eq(1)').find('input').val();

    alert(addr + ", " + txt + "을(를) 클릭하였습니다.");

})


// input date 값 받아오기.
function input() {
    const dday = document.querySelector('#reserveDate').value;
    console.log(dday)

    myid1.value = dday;
    myid1.innerHTML = dday;

    //데이터를 불러옴
    // let allMeetingList = [];
    // axios.get('/api/org/meetingList/byDate/'+ dday)
    //     .then(response => {
    //         console.log(response.data)
    //         allMeetingList = response.data;
    //     })
    //     .catch(err => {
    //         console.log(err)
    //     })

}


