// time table 클릭이벤트

// $(function() {
//     $("#listTable #tbodyId").on("mousedown","td",function(){
//         alert( $(this).find("td:eq(0)").text() );
//         alert( $(this).find("td:eq(1)").text() );
//
//
//     });
// });

// time table!

let tbl = "<table>";

for (let i = 1; i <= 10; i++) {
    tbl += "<tr>";
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
    tbl += "</tr>";
}
tbl += "</table>";
document.getElementById("listTable").innerHTML = tbl;

////////


    var dragging = false;
var dragSelectIds = [];
var $td = $('td');
var startCell = null;
const result11 = document.getElementById('result')
const button =document.querySelector('#button');
const myid1 = document.getElementById("myid1");



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
    } while (true);//
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

    result11.innerHTML = dragSelectIds;
    result11.value = dragSelectIds;
    button.value = dragSelectIds;
    // document.getElementById('result').innerHTML = dragSelectIds;

});


// td로 th값 가져오기

// 버튼 클릭시 Row 값 가져오기
$(".checkBtn").click(function(){

    var str = ""
    var tdArr = new Array();	// 배열 선언
    var checkBtn = $(this);

    // checkBtn.parent() : checkBtn의 부모는 <td>이다.
    // checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
    var tr = checkBtn.parent().parent();
    var td = tr.children();

    // console.log("클릭한 Row의 모든 데이터 : "+tr.text());
    console.log('클릭한 데이터 가져오기'+ button.value);
    console.log('날짜 가져오기'+myid1.value);
    // console.log('회의실 가져오기'+ );

    // var no = td.eq(0).text();
    // var userid = td.eq(1).text();
    // var name = td.eq(2).text();
    // var email = td.eq(3).text();


    // 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
    td.each(function(i){
        tdArr.push(td.eq(i).text());
    });


    $("#ex2_Result1").html(" * 클릭한 Row의 모든 데이터 = " + tr.text());

});




// 오늘 이후로 시간 예약하지 못하게
var now_utc = Date.now()
var timeOff = new Date().getTimezoneOffset()*60000;
var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
document.getElementById("reserveDate").setAttribute("min", today);


// list 첫 화면 오늘 날짜로 맞추기.
window.onload = function() {
    today = new Date();
    console.log("today.toISOString() >>>" + today.toISOString());
    today = today.toISOString().slice(0, 10);
    console.log("today >>>> " + today);
    bir = document.getElementById("reserveDate");
    bir.value = today;
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

    // document.getElementById("myid1").innerHTML = dday;

    myid1.value = dday;
    myid1.innerHTML = dday;
}
