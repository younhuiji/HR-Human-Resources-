// 회의실 장소
function roomNamePlace(e) {
    var roomPlace_a = ["1층 102호"];
    var roomPlace_b = ["1층 103호"];
    var roomPlace_c = ["2층 202호"];
    var roomPlace_d = ["2층 203호"];
    var roomPlace_e = ["3층 302호"];
    var roomPlace_f = ["3층 303호"];
    var roomPlace_g = ["4층 402호"];
    var roomPlace_h = ["4층 403호"];
    var roomPlace_i = ["5층 502호"];
    var roomPlace_j = ["5층 503호"];
    var target = document.getElementById("roomPlace");

    if(e.value == "회의실1") var k = roomPlace_a;
    else if(e.value == "회의실2") var k = roomPlace_b;
    else if(e.value == "회의실3") var k = roomPlace_c;
    else if(e.value == "회의실4") var k = roomPlace_d;
    else if(e.value == "회의실5") var k = roomPlace_e;
    else if(e.value == "회의실6") var k = roomPlace_f;
    else if(e.value == "회의실7") var k = roomPlace_g;
    else if(e.value == "회의실8") var k = roomPlace_h;
    else if(e.value == "회의실9") var k = roomPlace_i;
    else if(e.value == "회의실10") var k = roomPlace_j;
    // target.textContent.length = 0;
    target.options.length = 0;



    // for(x in k) {
    //     var inp = document.createElement("option");
    //     inp.value = k[x];
    //     inp.innerHTML = k[x];
    //     target.appendChild(inp);
    // }
}


// $("#example-table-1 tr").click(function(){
//
//     var str = ""
//     var tdArr = new Array();	// 배열 선언
//
//     // 현재 클릭된 Row(<tr>)
//     var tr = $(this);
//     var td = tr.children();
//
//     // tr.text()는 클릭된 Row 즉 tr에 있는 모든 값을 가져온다.
//     console.log("클릭한 Row의 모든 데이터 : "+tr.text());
//
//     // 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
//     td.each(function(i){
//         tdArr.push(td.eq(i).text());
//     });
//
//     console.log("배열에 담긴 값 : "+tdArr);
//
//     // td.eq(index)를 통해 값을 가져올 수도 있다.
//     var no = td.eq(0).text();
//     var userid = td.eq(1).text();
//     var name = td.eq(2).text();
//     var email = td.eq(3).text();
//
//
//     str +=	" * 클릭된 Row의 td값 = No. : <font color='red'>" + no + "</font>" +
//         ", 아이디 : <font color='red'>" + userid + "</font>" +
//         ", 이름 : <font color='red'>" + name + "</font>" +
//         ", 이메일 : <font color='red'>" + email + "</font>";
//
//     $("#ex1_Result1").html(" * 클릭한 Row의 모든 데이터 = " + tr.text());
//     $("#ex1_Result2").html(str);
// });

var now_utc = Date.now()
var timeOff = new Date().getTimezoneOffset()*60000;
var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
document.getElementById("reserveDate").setAttribute("min", today);