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


// 지난 날짜 사용 안하기
var now_utc = Date.now()
var timeOff = new Date().getTimezoneOffset()*60000;
var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
document.getElementById("reserveDate").setAttribute("min", today);


// 초대하기
// #invite

window.addEventListener('DOMContentLoaded', event =>{

    //innerHTML ID
    const orgTree = document.querySelector('#orgTree');

    readAllOrgList();
    let orgMember;

    // meetingRoom
    const divModal = document.querySelector('#inviteModal');
    const inviteModal = new bootstrap.Modal(divModal);


    //사용자 상세정보
    const name = document.querySelector('#name');

    function updateDetailInfo(member) {
        console.log(member);
        name.value = member[0].name;


        }

    const modelInviteBtn = document.querySelector('#modelInviteBtn');
    // modelInviteBtn.addEventListener('click', attendeeNew);

    const attendee = document.querySelector('#attendee');

    function attendeeNew(member) {

        // attendee.value = member[0].name;
        document.querySelector("#attendee").value = member[0].name;
        // $('#modelInviteBtn').trigger('click');
        // document.querySelector('#attendee').value = '';
    }


        function findByMemeber(orgMember) {
            for (let i = 0; i < orgMember.length; i++) {
                orgMember[i].addEventListener('click', (e) => {
                    let memberNo = orgMember[i].getAttribute('data-id');
                    axios.get('/api/org/memberInfo/' + memberNo)
                        .then(response => {
                            updateDetailInfo(response.data)
                            attendeeNew(response.data)
                        })
                        .catch(err => {
                            console.log(err)
                        })
                })
            }
        }


        function readAllOrgList() {
            axios.get('/api/org/allList')
                .then(response => {
                    updateOrgList(response.data)
                })
                .catch(err => {
                    console.log(err)
                })
        }

        function updateOrgList(orgList) {
            let str = '';

            //현재 depart, team값
            let depart = '';
            let team = '';

            for (let l of orgList) {
                if (l.department != depart) {
                    endDepartTag()
                    str += '<li>'
                        + '<input type="checkbox" id="' + l.department + '"/>'
                        + '<label class="tree_label" for="' + l.department + '">' + l.department + '</label>'
                        + '<ul>'
                    depart = l.department;
                }
                if (l.team != team) {
                    endTeamTag()
                    str += '<li>'
                        + '<input type="checkbox" id="' + l.team + '"/>'
                        + '<label for="' + l.team + '" class="tree_label">' + l.team + '</label>'
                        + '<ul>'
                    team = l.team;
                }
                str += '<li>'
                    // + '<input class="tree_label" type="text" data-id='+l.id+' value='+l.name +' '+ l.position +' readonly/></li>'
                    + '<span class="tree_label" id="memberInfo" data-id="' + l.id + '">' + l.name + ' ' + l.position + '</span></li>'

            }

            function endDepartTag() {
                if (depart != '') {
                    endTeamTag()
                    str += '</ul>'
                        + '</li>'
                    team = '';
                }
            }

            function endTeamTag() {
                if (team != '') {
                    str += '</ul>'
                        + '</li>'
                }
            }

            orgTree.innerHTML = str;

            orgMember = document.querySelectorAll('#memberInfo')
            findByMemeber(orgMember)


        }

});

