$(function() {
    $("#listTable #tbodyId").on("click","td",function(){
        alert("클릭");
        var str = ""
        var tdArr = new Array();

        var tr = $(this);
        var td = tr.children();

        td.each(function(i){
            tdArr.push(td.eq(i).text());
        });

    });
});

// 오늘 이후로 시간 예약하지 못하게
var now_utc = Date.now()
var timeOff = new Date().getTimezoneOffset()*60000;
var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
document.getElementById("reserveDate").setAttribute("min", today);
