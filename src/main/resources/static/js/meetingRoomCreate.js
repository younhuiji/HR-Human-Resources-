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

    target.options.length = 0;

    for(x in k) {
        var inp = document.createElement("option");
        inp.value = k[x];
        inp.innerHTML = k[x];
        target.appendChild(inp);
    }
}



var now_utc = Date.now()
var timeOff = new Date().getTimezoneOffset()*60000;
var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
document.getElementById("reserveDate").setAttribute("min", today);